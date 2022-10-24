package com.module.pojo;

import java.util.List;

public class Visual {
    //可视化图形名称
    private String name;
    private List<Result> other;
    private List<Middle> other2; //存放评估列表，也就是课程目标达成情况



    private List<Small> other3; //存放所有二级毕业要求列表
    public List<Small> getOther3() {
        return other3;
    }

    public void setOther3(List<Small> other3) {
        this.other3 = other3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Result> getOther() {
        return other;
    }

    public void setOther(List<Result> other) {

        this.other = other;
    }

    public void setOther2(List<Middle> other2) {

        this.other2 = other2;
    }
    public List<Middle> getOther2() {

        return other2;
    }

}
