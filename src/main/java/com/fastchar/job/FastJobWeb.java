package com.fastchar.job;

import com.fastchar.core.FastChar;
import com.fastchar.core.FastEngine;
import com.fastchar.interfaces.IFastWeb;
import com.fastchar.job.quartz.QuartzJobScheduler;

public class FastJobWeb implements IFastWeb {
    public void onInit(FastEngine engine) throws Exception {
        engine.getOverrides()
                .add(QuartzJobScheduler.class);

        engine.getObservable()
                .addObserver(QuartzJobScheduler.class);

    }

    public void onDestroy(FastEngine engine) throws Exception {

    }
}
