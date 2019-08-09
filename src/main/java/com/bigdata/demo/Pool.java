package com.bigdata.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;

@Component
public class Pool {
    private ComboPooledDataSource mysqlPool = new ComboPooledDataSource();
    private ComboPooledDataSource hivePool = new ComboPooledDataSource();
    private ComboPooledDataSource impalaPool = new ComboPooledDataSource();

    @Autowired
    private Environment environment;



    public void init() {
        initMysqlPool();
        initHivePool();
        initImpalaPool();
    }

    public Connection getCon(int database) {
        try {
            switch (database) {
                case 1:
                    return mysqlPool.getConnection();
                case 2:
                    return hivePool.getConnection();
                case 3:
                    return impalaPool.getConnection();
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostConstruct
    private ComboPooledDataSource initMysqlPool() {
        try {
            System.out.println("初始化 mysql 连接池......" + environment.getProperty("mysqlUrl"));

            mysqlPool.setDriverClass("com.mysql.jdbc.Driver");
            mysqlPool.setJdbcUrl(environment.getProperty("mysqlUrl"));
            mysqlPool.setUser(environment.getProperty("mysqlUser"));
            mysqlPool.setPassword(environment.getProperty("mysqlPasswd"));

            mysqlPool.setMaxStatements(0);
            mysqlPool.setTestConnectionOnCheckin(true );

            mysqlPool.setCheckoutTimeout(1000);
            mysqlPool.setMinPoolSize(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlPool;
    }

    @PostConstruct
    private ComboPooledDataSource initHivePool() {
//        ComboPooledDataSource hivePool = new ComboPooledDataSource();

        try {
            System.out.println("初始化 hive 连接池......");
            hivePool.setDriverClass("org.apache.hive.jdbc.HiveDriver");
            hivePool.setJdbcUrl(environment.getProperty("hiveUrl"));
            hivePool.setUser(environment.getProperty("hiveUser"));
            hivePool.setPassword(environment.getProperty("hivePasswd"));

            hivePool.setMinPoolSize(5);
            hivePool.setCheckoutTimeout(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hivePool;
    }
    @PostConstruct
    private void initImpalaPool() {
//        ComboPooledDataSource impalaPool = new ComboPooledDataSource();

        try {

            String impalaUrl = environment.getProperty("impalaUrl");
            System.out.println("初始化 impala 连接池......" + impalaUrl);
            impalaPool.setDriverClass("com.cloudera.impala.jdbc41.Driver");
            impalaPool.setJdbcUrl(impalaUrl);
            impalaPool.setCheckoutTimeout(1000);

            impalaPool.setMinPoolSize(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
