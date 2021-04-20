package com.fastchar.job.quartz;

import com.fastchar.annotation.AFastClassFind;
import com.fastchar.annotation.AFastObserver;
import com.fastchar.core.FastChar;
import com.fastchar.database.FastScriptRunner;
import com.fastchar.database.info.FastDatabaseInfo;
import com.fastchar.database.info.FastTableInfo;
import com.fastchar.job.core.FastJobBase;
import com.fastchar.job.interfaces.IFastJobScheduler;
import com.fastchar.utils.FastDateUtils;
import com.fastchar.utils.FastFileUtils;
import com.fastchar.utils.FastLockUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

@AFastObserver
@AFastClassFind("org.quartz.impl.StdSchedulerFactory")
public class QuartzJobScheduler implements IFastJobScheduler {
    private static final StdSchedulerFactory SCHEDULER_FACTORY = new StdSchedulerFactory();
    private static final String QUARTZ_TABLE = "QRTZ_TRIGGERS";
    private static final String JOB_GROUP_NAME = "FastCharJobGroup";
    private static final String TRIGGER_GROUP_NAME = "FastCharJobTriggerGroup";
    private static final String TRIGGER_SUFFIX = "FastCharTrigger";

    public  Scheduler getScheduler() {
        try {
            return SCHEDULER_FACTORY.getScheduler();
        } catch (Exception ignored) {
        }
        return null;
    }

    public void onWebReady() throws Exception {
        if (FastChar.getConstant().isTestEnvironment()) {
            return;
        }
        ReentrantLock lock = FastLockUtils.getLock("WEB_READY_QUARTZ_JOB");
        if (lock.isLocked()) {
            return;
        }
        lock.lock();
        try {
            FastQuartzConfig config = FastChar.getConfig(FastQuartzConfig.class);
            File quartzConfig = new File(FastChar.getPath().getClassRootPath(), "quartz.properties");
            if (config.isUseDatabase()) {
                if (!quartzConfig.exists()) {
                    if (config.isDebug()) {
                        FastChar.getLog().info("Quartz自动生成配置文件！");
                    }
                    URL url = QuartzJobScheduler.class.getResource("template_quartz");
                    FastFileUtils.copyURLToFile(url, quartzConfig);
                }

                for (FastDatabaseInfo fastDatabaseInfo : FastChar.getDatabases().getAll()) {
                    boolean hasQuartzTable = false;
                    for (FastTableInfo<?> table : fastDatabaseInfo.getTables()) {
                        if (table.getName().equalsIgnoreCase(QUARTZ_TABLE)) {
                            hasQuartzTable = true;
                            break;
                        }
                    }
                    if (!hasQuartzTable) {
                        if (config.isCreateTable()) {
                            if (config.isDebug()) {
                                FastChar.getLog().info("Quartz自动创建表格！");
                            }
                            String sqlFile = "quartz_mysql.sql";
                            if (fastDatabaseInfo.getType().equalsIgnoreCase("sql_server")) {
                                sqlFile = "quartz_sqlserver.sql";
                            }
                            URL url = QuartzJobScheduler.class.getResource(sqlFile);
                            FastScriptRunner scriptRunner = new FastScriptRunner(FastChar.getDb().getConnection());
                            scriptRunner.setLogWriter(null);
                            scriptRunner.runScript(new InputStreamReader(url.openStream()));
                            scriptRunner.closeConnection();
                        }
                    } else {
                        recovery();
                    }
                }
            } else {
                FastFileUtils.deleteQuietly(quartzConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void onWebStop() {
        ReentrantLock lock = FastLockUtils.getLock("WEB_STOP_QUARTZ_JOB");
        if (lock.isLocked()) {
            return;
        }
        lock.lock();

        try {
            Scheduler scheduler = getScheduler();
            scheduler.pauseAll();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
                if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                    FastChar.getLog().info("已停止Quartz任务！");
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void startJob(FastJobBase<?> job) {
        try {
            if (job == null) {
                return;
            }
            if (job.getDateTime() == null) {
                if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                    FastChar.getLog().error("任务：" + job.getCode() + "启动失败！任务日期为空！");
                }
                return;
            }
            String jobName = job.getCode();
            String triggerName = job.getCode() + TRIGGER_SUFFIX;

            Scheduler scheduler =  getScheduler();
            removeJob(job.getCode());

            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(jobName, JOB_GROUP_NAME).build();
            jobDetail.getJobDataMap().put("job", job);
            jobDetail.getJobDataMap().put("code", job.getCode());

            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            triggerBuilder.withIdentity(triggerName, TRIGGER_GROUP_NAME);
            triggerBuilder.startAt(job.getDateTime());
            Date date = scheduler.scheduleJob(jobDetail, triggerBuilder.build());
            if (!scheduler.isStarted()) {
                scheduler.start();
                scheduler.resumeAll();
                if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                    FastChar.getLog().info("Quartz任务调度器启动成功！");
                }
            }
            if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                FastChar.getLog().info("Quartz添加【" + FastDateUtils.format(date, "yyyy-MM-dd HH:mm:ss") + "】【" + job.getClass().getName() + "】任务：" + job.toJson());
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public boolean existJob(String code) {
        try {
            String triggerName = code + TRIGGER_SUFFIX;
            Scheduler scheduler =  getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
            return scheduler.checkExists(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeJob(String code) {
        try {
            String triggerName = code + TRIGGER_SUFFIX;
            Scheduler scheduler =  getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
            boolean checkExists = scheduler.checkExists(triggerKey);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            JobKey jobKey = JobKey.jobKey(code, JOB_GROUP_NAME);
            scheduler.deleteJob(jobKey);
            if (FastChar.getConfig(FastQuartzConfig.class).isDebug() && checkExists) {
                FastChar.getLog().info("Quartz移除任务：" + code);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    private void recovery() {
        ReentrantLock lock = FastLockUtils.getLock("RECOVERY_QUARTZ_JOB");
        if (lock.isLocked()) {
            return;
        }
        lock.lock();
        try {
            final Scheduler scheduler =  getScheduler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
                        for (TriggerKey triggerKey : triggerKeys) {
                            Trigger trigger = scheduler.getTrigger(triggerKey);
                            scheduler.rescheduleJob(triggerKey, trigger);
                        }
                        scheduler.start();
                        scheduler.resumeAll();
                        if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                            FastChar.getLog().info("Quartz已还原" + triggerKeys.size() + "条历史任务！");
                        }
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
