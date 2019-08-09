package com.bigdata.demo;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String age;
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public User(String name, String age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
