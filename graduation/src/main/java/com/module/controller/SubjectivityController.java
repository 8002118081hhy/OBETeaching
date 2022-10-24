package com.module.controller;

import com.github.pagehelper.Page;
import com.module.mapper.BigMapper;
import com.module.mapper.SmallMapper;
import com.module.mapper.SubjectivityMapper;
import com.module.pojo.Big;
import com.module.pojo.Small;
import com.module.pojo.Subjectivity;
import com.module.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
 * 页面请求控制  主观毕业要求管理
 */
@Controller
public class SubjectivityController {
    @Autowired
    SubjectivityMapper subjectivityMapper;


    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("manage/subjectivityList")
    public String subjectivityList(Model model, Integer id) {
        model.addAttribute("studentId", id);
        return "manage/subjectivity/subjectivityList";
    }

    /*@Autowired
    private BigMapper bigMapper;*/
    @Autowired
    private SmallMapper smallMapper;
    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("manage/addSubjectivity")
    public String addSubjectivity(Model model,Integer studentId) {
        model.addAttribute("studentId", studentId);
        List<Small> smalls = smallMapper.selectAll(null);
        model.addAttribute("smalls", smalls);
        return "manage/subjectivity/saveSubjectivity";
    }

    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/editSubjectivity")
    public String editSubjectivity(Integer id, Model model, Integer studentId) {
        model.addAttribute("studentId", studentId);
        List<Small> smalls = smallMapper.selectAll(null);
        model.addAttribute("smalls", smalls);
        Subjectivity subjectivity = subjectivityMapper.selectSubjectivityById(id);
        model.addAttribute("subjectivity", subjectivity);
        return "manage/subjectivity/saveSubjectivity";
    }

    /**
     * 查看详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/subjectivityInfo")
    public String subjectivityInfo(Integer id, Model model) {
        Subjectivity subjectivity = subjectivityMapper.selectSubjectivityById(id);
        model.addAttribute("subjectivity", subjectivity);
        return "manage/subjectivity/subjectivityInfo";
    }


    /**
     * 分页查询
     *
     * @param page  默认第一页
     * @param limit 默认每页显示10条
     * @return
     */
    @RequestMapping("manage/querySubjectivityList")
    @ResponseBody
    public ResultUtil getCarouseList(Integer page, Integer limit, String keyword,Integer studentId) {
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
        if(studentId!=null && studentId != 0){
            map.put("studentId", studentId);
        }
        Page pageHelper = PageHelper.startPage(page, limit, true);
        pageHelper.setOrderBy(" id desc ");
        List<Subjectivity> list = subjectivityMapper.selectAll(map);
        PageInfo<Subjectivity> pageInfo = new PageInfo<Subjectivity>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

    /**
     * 插入记录
     */
    @RequestMapping("manage/saveSubjectivity")
    @ResponseBody
    public ResultUtil saveSubjectivity(Subjectivity subjectivity, HttpSession session) {
        try {
            subjectivityMapper.insertSubjectivity(subjectivity);
            return ResultUtil.ok("添加主观毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("添加主观毕业要求出错,稍后再试！");
        }
    }

    /**
     * 更新记录
     */
    @RequestMapping("manage/updateSubjectivity")
    @ResponseBody
    public ResultUtil updateSubjectivity(Subjectivity subjectivity, HttpSession session) {
        try {
            subjectivityMapper.updateSubjectivity(subjectivity);
            return ResultUtil.ok("修改主观毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("修改主观毕业要求出错,稍后再试！");
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("manage/deleteSubjectivity")
    @ResponseBody
    public ResultUtil deleteSubjectivityById(Integer id) {
        try {
            subjectivityMapper.deleteSubjectivityById(id);
            return ResultUtil.ok("删除主观毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("删除主观毕业要求出错,稍后再试！");
        }
    }

    /**
     * 根据ID批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping("manage/deletesSubjectivity")
    @ResponseBody
    public ResultUtil deletesSubjectivity(String idsStr) {
        try {
            if (!StringUtils.isBlank(idsStr)) {
                String[] ids = idsStr.split(",");
                for (String id : ids) {
                    subjectivityMapper.deleteSubjectivityById(Integer.parseInt(id));
                }
            }
            return ResultUtil.ok("批量删除主观毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("删除主观毕业要求出错,稍后再试！");
        }
    }


}
