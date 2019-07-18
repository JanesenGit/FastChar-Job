package com.fastchar.job.core;

import com.fastchar.core.FastChar;
import com.fastchar.job.FastJob;
import com.fastchar.job.exceptions.FastJobException;
import com.fastchar.utils.FastStringUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public abstract class FastJobBase<T> implements Serializable {


    private static final long serialVersionUID = -847467329736585898L;

    public abstract void run();

    public boolean start() {
        if (FastStringUtils.isEmpty(getCode())) {
            throw new FastJobException("任务的唯一编号[code]不可为空！");
        }

        if (getDateTime() == null) {
            setDateTime(new Date());
        }
        FastJob.startJob(this);
        return true;
    }


    private String code;
    private Date dateTime;
    private int whileCount;
    private int whileInterval;//循环的实际间隔 单位秒
    private int runCount;

    public Date getDateTime() {
        return dateTime;
    }

    public FastJobBase<T> setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public int getWhileCount() {
        return whileCount;
    }

    public T setWhileCount(int whileCount) {
        this.whileCount = whileCount;
        return (T) this;
    }

    public int getWhileInterval() {
        return whileInterval;
    }

    public T setWhileInterval(int whileInterval) {
        this.whileInterval = whileInterval;
        return (T) this;
    }

    public String getCode() {
        return code;
    }

    public T setCode(String code) {
        this.code = code;
        return (T) this;
    }


    public int getRunCount() {
        return runCount;
    }

    public T setRunCount(int runCount) {
        this.runCount = runCount;
        return (T) this;
    }

    public final String toJson() {
        return FastChar.getJson().toJson(this);
    }

    public final Date computeNextDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, getWhileInterval());
        return calendar.getTime();
    }

}
