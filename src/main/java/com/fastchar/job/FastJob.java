package com.fastchar.job;

import com.fastchar.core.FastChar;
import com.fastchar.job.core.FastJobBase;
import com.fastchar.job.interfaces.IFastJobScheduler;

public final  class FastJob {

    public static void startJob(FastJobBase job) {
        FastChar.getOverrides().singleInstance(IFastJobScheduler.class).startJob(job);
    }

    public static void removeJob(String... codes) {
        for (String code : codes) {
            FastChar.getOverrides().singleInstance(IFastJobScheduler.class).removeJob(code);
        }
    }


    public static boolean existJob(String code) {
        return FastChar.getOverrides().singleInstance(IFastJobScheduler.class).existJob(code);
    }

}
