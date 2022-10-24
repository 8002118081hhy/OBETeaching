package com.module.mapper;

import com.module.pojo.Middle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class MiddleMapperTest {

    @Resource
    private MiddleMapper middleMapper;

    @Test
    public void selectMiddleById() {
        Middle middle = middleMapper.selectMiddleById(20);
        //System.out.println(middle);
    }

    @Test
    public void selectMiddleBySmallId() {
    }

    @Test
    public void selectMiddleByCS() {
    }

    @Test
    public void selectMiddleList() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void insertMiddle() {
    }

    @Test
    public void updateMiddle() {
    }

    @Test
    public void batchUpdate() {
    }

    @Test
    public void deleteMiddleById() {
    }

    @Test
    public void batchDeleteMiddle() {
    }

    @Test
    public void batchAdd() {
    }
}