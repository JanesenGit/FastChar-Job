package com.fastchar.job.quartz;

import com.fastchar.interfaces.IFastConfig;

public class FastQuartzConfig implements IFastConfig {

    private boolean debug;
    private boolean useDatabase;
    private boolean createTable;

    public boolean isCreateTable() {
        return createTable;
    }

    public FastQuartzConfig setCreateTable(boolean createTable) {
        this.createTable = createTable;
        return this;
    }

    public boolean isUseDatabase() {
        return useDatabase;
    }

    public FastQuartzConfig setUseDatabase(boolean useDatabase) {
        this.useDatabase = useDatabase;
        return this;
    }

    public boolean isDebug() {
        return debug;
    }

    public FastQuartzConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }
}
