package com.fastchar.job.quartz;

import com.fastchar.core.FastChar;
import com.fastchar.job.core.FastJobBase;
import com.fastchar.utils.FastClassUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class QuartzJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) {
        String job = jobExecutionContext.getJobDetail().getJobDataMap().getString("job");
        String className = jobExecutionContext.getJobDetail().getJobDataMap().getString("class");

        Class<?> aClass = FastClassUtils.getClass(className);
        if (FastJobBase.class.isAssignableFrom(aClass)) {
            FastJobBase fastJob = (FastJobBase) FastChar.getJson().fromJson(job, aClass);
            fastJob.run();
            if (fastJob.getWhileCount() != -1) {
                int runCount = fastJob.getRunCount();
                if (runCount + 1 >= fastJob.getWhileCount()) {
                    return;
                }
                fastJob.setRunCount(runCount + 1);
            }
            fastJob.setDateTime(fastJob.computeNextDateTime());
            fastJob.start();
        }
    }
}
