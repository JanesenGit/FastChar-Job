package com.fastchar.job.quartz;

import com.fastchar.core.FastChar;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

public class QuartzConnectionProvider implements ConnectionProvider {
    public Connection getConnection() throws SQLException {
        return FastChar.getDb().getConnection();
    }

    public void shutdown() throws SQLException {

    }

    public void initialize() throws SQLException {

    }
}
