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
import com.fastchar.utils.FastStringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.Set;

@AFastObserver
@AFastClassFind("org.quartz.impl.StdSchedulerFactory")
public class QuartzJobScheduler implements IFastJobScheduler {
    private static final SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    private static final String QuartzTable = "QRTZ_TRIGGERS";

    public void onDatabaseFinish() throws Exception {
        if (FastChar.getConstant().isTestEnvironment()) {
            return;
        }
        FastQuartzConfig config = FastChar.getConfig(FastQuartzConfig.class);
        if (config.isUseDatabase()) {
            File quartzConfig = new File(FastChar.getPath().getClassRootPath(), "quartz.properties");
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
                    if (table.getName().equalsIgnoreCase(QuartzTable)) {
                        hasQuartzTable = true;
                    }
                }
                if (!hasQuartzTable ) {
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
                        scriptRunner.setSendFullScript(true);
                        scriptRunner.setRemoveCRs(true);
                        scriptRunner.runScript(new InputStreamReader(url.openStream()));
                        scriptRunner.closeConnection();
                    }
                }else{
                    recovery();
                }
            }
        }
    }

    public void onWebStop() {
        try {
            for (Scheduler allScheduler : schedulerFactory.getAllSchedulers()) {
                if (!allScheduler.isShutdown()) {
                    allScheduler.shutdown();
                    if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                        FastChar.getLog().info("已停止Quartz任务！");
                    }
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }



    public void startJob(FastJobBase job) {
        try {
            if (job == null) {
                return;
            }
            if (FastStringUtils.isEmpty(job.getDateTime())) {
                return;
            }
            String jobName = job.getCode();
            String jobGroupName = "FastCharJob";
            String triggerName = job.getCode() + "Trigger";
            String triggerGroupName = "FastCharTrigger";

            Scheduler scheduler = schedulerFactory.getScheduler();

            removeJob(job.getCode());

            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(jobName, jobGroupName).build();
            jobDetail.getJobDataMap().put("job", job.toJson());
            jobDetail.getJobDataMap().put("class", job.getClass().getName());

            Date startDate = FastDateUtils.parse(job.getDateTime(), "yyyy-MM-dd HH:mm:ss");

            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startAt(startDate);
            triggerBuilder.endAt(startDate);
            scheduler.scheduleJob(jobDetail, triggerBuilder.build());
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
            if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                FastChar.getLog().info("Quartz添加任务：" + job.getCode());
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public boolean existJob(String code) {
        try {
            String triggerName = code + "Trigger";
            String triggerGroupName = "FastCharTrigger";
            Scheduler scheduler = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            return scheduler.checkExists(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeJob(String code) {
        try {
            String jobGroupName = "FastCharJob";
            String triggerName = code + "Trigger";
            String triggerGroupName = "FastCharTrigger";

            Scheduler scheduler = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            if (scheduler.deleteJob(JobKey.jobKey(code, jobGroupName))) {
                if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                    FastChar.getLog().info("Quartz移除任务：" + code);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    private void recovery() {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
            for (TriggerKey triggerKey : triggerKeys) {
                Trigger trigger = scheduler.getTrigger(triggerKey);
                scheduler.rescheduleJob(triggerKey, trigger);
            }
            scheduler.start();
            if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                FastChar.getLog().info("Quartz已还原" + triggerKeys.size() + "条历史任务！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
