package com.fastchar.job.quartz;

import com.fastchar.core.FastChar;
import com.fastchar.job.core.FastJobBase;
import com.fastchar.utils.FastClassUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

public class QuartzJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        FastJobBase<?> fastJob = (FastJobBase<?>) jobDataMap.get("job");
        if (fastJob == null) {
            if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                FastChar.getLog().error("Quartz失败任务：" + jobDataMap.get("code"));
            }
            return;
        }
        if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
            FastChar.getLog().info("Quartz执行【" + fastJob.getClass().getName() + "】任务：" + fastJob.toJson());
        }
        try {
            fastJob.setRunCount(fastJob.getRunCount() + 1);
            fastJob.run();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (fastJob.getWhileCount() != -1) {
            if (fastJob.getRunCount() >= fastJob.getWhileCount()) {
                if (FastChar.getConfig(FastQuartzConfig.class).isDebug()) {
                    FastChar.getLog().warn("Quartz停止任务：" + fastJob.getCode());
                }
                fastJob.stop();
                return;
            }
        }
        fastJob.setDateTime(fastJob.computeNextDateTime());
        fastJob.start();

    }
}
