package com.ale.bean;

public class QueryParam {
    private Integer type;
    private String date;
    private String name;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "QueryParam{" +
                "type=" + type +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
