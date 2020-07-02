package com.fastchar.job.interfaces;

import com.fastchar.annotation.AFastOverrideError;
import com.fastchar.job.core.FastJobBase;

@AFastOverrideError("无法获取IFastJobScheduler对象，请确保是否初始化了FastQuartzConfig配置！")
public interface IFastJobScheduler {

    void startJob(FastJobBase<?> job);

    boolean existJob(String code);

    void removeJob(String code);


}
