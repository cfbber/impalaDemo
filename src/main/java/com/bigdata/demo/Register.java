package com.bigdata.demo;

public class Register {
    private String dataType;

    private String startMonth;
    private String endMonth;


    @Override
    public String toString() {
        return "Register{" +
                "dataType='" + dataType + '\'' +
                ", startMonth='" + startMonth + '\'' +
                ", endMonth='" + endMonth + '\'' +
                '}';
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }
}
