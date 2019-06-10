package com.fastchar.job.core;

import com.fastchar.core.FastChar;
import com.fastchar.job.exceptions.FastJobException;
import com.fastchar.job.interfaces.IFastJobScheduler;
import com.fastchar.utils.FastDateUtils;
import com.fastchar.utils.FastNumberUtils;
import com.fastchar.utils.FastStringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class FastJobBase<T> {

    public abstract void run();



    public boolean start() {
        if (FastStringUtils.isEmpty(getCode())) {
            throw new FastJobException("任务的唯一编号[code]不可为空！");
        }

        if (FastStringUtils.isEmpty(getDateTime())) {
            setDateTime(new Date());
        }
        FastChar.getOverrides().singleInstance(IFastJobScheduler.class).startJob(this);
        return true;
    }


    private String code;
    private String dateTime;
    private int whileCount;
    private int whileInterval;//循环的实际间隔 单位秒
    private int runCount;

    public String getDateTime() {
        return dateTime;
    }

    public T setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return (T) this;
    }

    public T setDateTime(Date dateTime) {
        this.dateTime = FastDateUtils.format(dateTime, "yyyy-MM-dd HH:mm:ss");
        return (T) this;
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
        Date startDate = FastDateUtils.parse(getDateTime(), "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.SECOND, getWhileInterval());
        return calendar.getTime();
    }


}
