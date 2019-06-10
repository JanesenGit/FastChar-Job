package com.fastchar.job.interfaces;

import com.fastchar.job.core.FastJobBase;

public interface IFastJobScheduler {

    void startJob(FastJobBase job);

    boolean existJob(String code);

    void removeJob(String code);


}
