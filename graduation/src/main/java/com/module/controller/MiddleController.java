package com.module.controller;

import com.github.pagehelper.Page;
import com.module.mapper.BigMapper;
import com.module.mapper.CourseMapper;
import com.module.mapper.MiddleMapper;
import com.module.mapper.SmallMapper;
import com.module.pojo.Big;
import com.module.pojo.Course;
import com.module.pojo.Middle;
import com.module.pojo.Visual;
import com.module.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面请求控制  课程目标管理
 */
@Controller
public class MiddleController {
    @Autowired
    MiddleMapper middleMapper;
    @Autowired
    CourseMapper courseMapper;


    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("manage/middleList")
    public String middleList(Model model,Integer id) {
        model.addAttribute("courseId", id);
        return "manage/middle/middleList";
    }

    @Autowired
    private BigMapper bigMapper;

    @Autowired
    private SmallMapper smallMapper;

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("manage/addMiddle")

    public String addMiddle(Model model, Integer courseId) {
        model.addAttribute("courseId", courseId);
        List<Big> bigs = bigMapper.selectAll(null);
        for (Big big : bigs) {
            big.setSmalls(smallMapper.selectByBigId(big.getId()));
        }
        model.addAttribute("bigs", bigs);
        return "manage/middle/saveMiddle";
    }

    /**
     * 跳转到添加页面1，一门课程的课程目标达成度
     *
     * @return
     */
    @RequestMapping("manage/addMiddle1")
    public String addMiddle1(Model model, Integer courseId) {
        Visual visual=new Visual(); //新建图形可视化对象
        Course course=courseMapper.selectCourseById(courseId); //根据课程id获取课程信息
        visual.setName(course.getName());
        List<Middle>middles=middleMapper.selectMiddleByC(courseId); //根据课程id获取课程的全部评估列表
        visual.setOther2(middles); //存放课程目标达成情况
        model.addAttribute("visual",visual);
        return "manage/course/cachive";

       /* model.addAttribute("courseId", courseId);
        List<Big> bigs = bigMapper.selectAll(null);
        for (Big big : bigs) {
            big.setSmalls(smallMapper.selectByBigId(big.getId()));
        }
        model.addAttribute("bigs", bigs);
        return "manage/middle/saveMiddle";*/
    }


    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/editMiddle")
    public String editMiddle(Integer id, Model model,Integer courseId) {
        Middle middle = middleMapper.selectMiddleById(id);
        model.addAttribute("middle", middle);
        model.addAttribute("courseId", courseId);
        List<Big> bigs = bigMapper.selectAll(null);
        for (Big big : bigs) {
            big.setSmalls(smallMapper.selectByBigId(big.getId()));
        }
        model.addAttribute("bigs", bigs);
        return "manage/middle/saveMiddle";
    }

    /**
     * 查看详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/middleInfo")
    public String middleInfo(Integer id, Model model) {
        Middle middle = middleMapper.selectMiddleById(id);
        model.addAttribute("middle", middle);
        return "manage/middle/middleInfo";
    }


    /**
     * 分页查询
     *
     * @param page  默认第一页
     * @param limit 默认每页显示10条
     * @return
     */
    @RequestMapping("manage/queryMiddleList")
    @ResponseBody
    public ResultUtil getCarouseList(Integer page, Integer limit, String keyword,Integer courseId) {
        if (null == page) { //默认第一页
            page = 1;
        }
        if (null == limit) { //默认每页10条
            limit = 10;
        }
        Map map = new HashMap();
        if(courseId!=null && courseId != 0){
            map.put("courseId", courseId);
        }
        if (StringUtils.isNotEmpty(keyword)) {
            map.put("keyword", keyword);
        }
        Page pageHelper = PageHelper.startPage(page, limit, true);
        pageHelper.setOrderBy(" id desc ");
        List<Middle> list = middleMapper.selectAll(map);
        PageInfo<Middle> pageInfo = new PageInfo<Middle>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

    /**
     * 插入记录
     */
    @RequestMapping("manage/saveMiddle")
    @ResponseBody
    public ResultUtil saveMiddle(Middle middle, HttpSession session) {
        try {
            Middle middle1 = middleMapper.selectMiddleByCS(middle.getCourseid(), middle.getSmallid());
            if(middle1!=null){
                return ResultUtil.error("该课程目标已存在");
            }
            middleMapper.insertMiddle(middle);
            return ResultUtil.ok("添加课程目标成功");
        } catch (Exception e) {
            return ResultUtil.error("添加课程目标出错,稍后再试！");
        }
    }

    /**
     * 更新记录
     */
    @RequestMapping("manage/updateMiddle")
    @ResponseBody
    public ResultUtil updateMiddle(Middle middle, HttpSession session) {
        try {
            Middle middle1 = middleMapper.selectMiddleByCS(middle.getCourseid(), middle.getSmallid());
            if(middle1!=null  && middle1.getId() != middle.getId() ){
                return ResultUtil.error("该课程目标已存在");
            }
            middleMapper.updateMiddle(middle);
            return ResultUtil.ok("修改课程目标成功");
        } catch (Exception e) {
            return ResultUtil.error("修改课程目标出错,稍后再试！");
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("manage/deleteMiddle")
    @ResponseBody
    public ResultUtil deleteMiddleById(Integer id) {
        try {
            middleMapper.deleteMiddleById(id);
            return ResultUtil.ok("删除课程目标成功");
        } catch (Exception e) {
            return ResultUtil.error("删除课程目标出错,稍后再试！");
        }
    }

    /**
     * 根据ID批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping("manage/deletesMiddle")
    @ResponseBody
    public ResultUtil deletesMiddle(String idsStr) {
        try {
            if (!StringUtils.isBlank(idsStr)) {
                String[] ids = idsStr.split(",");
                for (String id : ids) {
                    middleMapper.deleteMiddleById(Integer.parseInt(id));
                }
            }
            return ResultUtil.ok("批量删除课程目标成功");
        } catch (Exception e) {
            return ResultUtil.error("删除课程目标出错,稍后再试！");
        }
    }

    //我写的
    //clazz/list
    @RequestMapping("manage/clazz/list")
    @ResponseBody
    public ResultUtil getClazzList(Integer page, Integer limit, String keyword,Integer courseId) {
        if (null == page) { //默认第一页
            page = 1;
        }
        if (null == limit) { //默认每页10条
            limit = 10;
        }
        Map map = new HashMap();
        if(courseId!=null && courseId != 0){
            map.put("courseId", courseId);
        }
        if (StringUtils.isNotEmpty(keyword)) {
            map.put("keyword", keyword);
        }
        Page pageHelper = PageHelper.startPage(page, limit, true);
        pageHelper.setOrderBy(" id desc ");
        List<Middle> list = middleMapper.selectAll(map);
        PageInfo<Middle> pageInfo = new PageInfo<Middle>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

}
