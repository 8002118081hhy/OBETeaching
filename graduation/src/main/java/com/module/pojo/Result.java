package com.module.pojo;

import java.util.List;

public class Result {
    private String name;
    private Double value;//客观毕业要求达成度
    private Double value2;//主观毕业要求达成度
    private List<Result> children;

    public Result(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public Result() {
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<Result> getChildren() {
        return children;
    }

    public void setChildren(List<Result> children) {
        this.children = children;
    }
}
