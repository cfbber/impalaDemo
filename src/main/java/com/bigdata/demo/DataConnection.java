package com.bigdata.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataConnection {
    private Register register;

    @Autowired
    private Pool pool;

    @Autowired
    private Environment env;

    public List<User> mysqlConnect() {
        Connection con = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            con = pool.getCon(1);

            statement = con.createStatement();
            String mysqlSql = env.getProperty("mysqlSql").replace("start", register.getStartMonth()).replace("end", register.getEndMonth());


            set = statement.executeQuery(mysqlSql);

            List<User> list = new ArrayList<User>();
            while (set.next()) {
                User user = new User(set.getString(1), set.getString(2), set.getString(3));
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                set.close();
                statement.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<User> hiveConnect() {

        Connection con = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            con = pool.getCon(2);

            statement = con.createStatement();

            String hiveSql = env.getProperty("hiveSql").replace("start", register.getStartMonth()).replace("end", register.getEndMonth());


            set = statement.executeQuery(hiveSql);
            List<User> list = new ArrayList<User>();
            while (set.next()) {
                User user = new User(set.getString(1), set.getString(2), set.getString(3));
                list.add(user);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                set.close();
                statement.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<User> impalaConnect() {
        Connection con = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            con = pool.getCon(3);
            statement = con.createStatement();

            String hiveSql = env.getProperty("hiveSql").replace("start", register.getStartMonth()).replace("end", register.getEndMonth());
            System.out.println(hiveSql);
            set = statement.executeQuery(hiveSql);
            List<User> list = new ArrayList<User>();
            while (set.next()) {
                User user = new User(set.getString(1), set.getString(2), set.getString(3));
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                set.close();
                statement.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public List<User> getData(Register register) {
        System.out.println(register);
        this.register = register;

        if ("mysql".equalsIgnoreCase(register.getDataType())) {
            return mysqlConnect();
        } else if ("hive".equalsIgnoreCase(register.getDataType())) {
            return hiveConnect();
        } else {
            return impalaConnect();
        }
    }
}
