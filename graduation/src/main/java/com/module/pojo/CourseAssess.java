package com.module.pojo;

import java.util.List;

public class CourseAssess {
    private Integer id;

    private List<Middle> middles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Middle> getMiddles() {
        return middles;
    }

    public void setMiddles(List<Middle> middles) {
        this.middles = middles;
    }
}
