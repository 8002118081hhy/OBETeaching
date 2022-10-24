package com.module.controller;

import com.github.pagehelper.Page;
import com.module.mapper.StudentMapper;
import com.module.pojo.Student;
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
 * 页面请求控制  学生管理
 */
@Controller
public class StudentController {
    @Autowired
    StudentMapper studentMapper;


    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("manage/studentList")
    public String studentList() {
        return "manage/student/studentList";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("manage/addStudent")
    public String addStudent(Model model) {
        return "manage/student/saveStudent";
    }

    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/editStudent")
    public String editStudent(Integer id, Model model) {
        Student student = studentMapper.selectStudentById(id);
        model.addAttribute("student", student);
        return "manage/student/saveStudent";
    }

    /**
     * 查看详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/studentInfo")
    public String studentInfo(Integer id, Model model) {
        Student student = studentMapper.selectStudentById(id);
        model.addAttribute("student", student);
        return "manage/student/studentInfo";
    }


    /**
     * 分页查询
     *
     * @param page  默认第一页
     * @param limit 默认每页显示10条
     * @return
     */
    @RequestMapping("manage/queryStudentList")
    @ResponseBody
    public ResultUtil getCarouseList(Integer page, Integer limit, String keyword) {
        if (null == page) { //默认第一页
            page = 1;
        }
        if (null == limit) { //默认每页10条
            limit = 10;
        }
        Map map = new HashMap();
        if (StringUtils.isNotEmpty(keyword)) {
            map.put("keyword", keyword);
        }
        Page pageHelper = PageHelper.startPage(page, limit, true);
        pageHelper.setOrderBy(" id desc ");
        List<Student> list = studentMapper.selectAll(map);
        PageInfo<Student> pageInfo = new PageInfo<Student>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

    /**
     * 插入记录
     */
    @RequestMapping("manage/saveStudent")
    @ResponseBody
    public ResultUtil saveStudent(Student student, HttpSession session) {
        try {
            studentMapper.insertStudent(student);
            return ResultUtil.ok("添加学生成功");
        } catch (Exception e) {
            return ResultUtil.error("添加学生出错,稍后再试！");
        }
    }

    /**
     * 更新记录
     */
    @RequestMapping("manage/updateStudent")
    @ResponseBody
    public ResultUtil updateStudent(Student student, HttpSession session) {
        try {
            studentMapper.updateStudent(student);
            return ResultUtil.ok("修改学生成功");
        } catch (Exception e) {
            return ResultUtil.error("修改学生出错,稍后再试！");
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("manage/deleteStudent")
    @ResponseBody
    public ResultUtil deleteStudentById(Integer id) {
        try {
            studentMapper.deleteStudentById(id);
            return ResultUtil.ok("删除学生成功");
        } catch (Exception e) {
            return ResultUtil.error("删除学生出错,稍后再试！");
        }
    }

    /**
     * 根据ID批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping("manage/deletesStudent")
    @ResponseBody
    public ResultUtil deletesStudent(String idsStr) {
        try {
            if (!StringUtils.isBlank(idsStr)) {
                String[] ids = idsStr.split(",");
                for (String id : ids) {
                    studentMapper.deleteStudentById(Integer.parseInt(id));
                }
            }
            return ResultUtil.ok("批量删除学生成功");
        } catch (Exception e) {
            return ResultUtil.error("删除学生出错,稍后再试！");
        }
    }


}
