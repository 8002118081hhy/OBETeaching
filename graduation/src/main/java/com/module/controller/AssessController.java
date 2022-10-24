package com.module.controller;

import com.github.pagehelper.Page;
import com.module.mapper.AssessMapper;
import com.module.pojo.Assess;
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
 * 页面请求控制  考核方案管理
 */
@Controller
public class AssessController {
    @Autowired
    AssessMapper assessMapper;


    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("manage/assessList")
    public String assessList(Model model,Integer id) {
        model.addAttribute("middleId", id);
        return "manage/assess/assessList";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("manage/addAssess")
    public String addAssess(Model model,Integer middleId) {
        model.addAttribute("middleId", middleId);
        return "manage/assess/saveAssess";
    }

    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/editAssess")
    public String editAssess(Integer id, Model model,Integer middleId) {
        Assess assess = assessMapper.selectAssessById(id);
        model.addAttribute("middleId", middleId);
        model.addAttribute("assess", assess);
        return "manage/assess/saveAssess";
    }

    /**
     * 查看详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/assessInfo")
    public String assessInfo(Integer id, Model model) {
        Assess assess = assessMapper.selectAssessById(id);
        model.addAttribute("assess", assess);
        return "manage/assess/assessInfo";
    }


    /**
     * 分页查询
     *
     * @param page  默认第一页
     * @param limit 默认每页显示10条
     * @return
     */
    @RequestMapping("manage/queryAssessList")
    @ResponseBody
    public ResultUtil getCarouseList(Integer page, Integer limit, String keyword,Integer middleId) {
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
        if(middleId!=null && middleId != 0){
            map.put("middleId", middleId);
        }
        Page pageHelper = PageHelper.startPage(page, limit, true);
        pageHelper.setOrderBy(" id desc ");
        List<Assess> list = assessMapper.selectAll(map);
        PageInfo<Assess> pageInfo = new PageInfo<Assess>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

    /**
     * 插入记录
     */
    @RequestMapping("manage/saveAssess")
    @ResponseBody
    public ResultUtil saveAssess(Assess assess, HttpSession session) {

        try {
            assessMapper.insertAssess(assess);
            return ResultUtil.ok("添加考核方案成功");
        } catch (Exception e) {
            return ResultUtil.error("添加考核方案出错,稍后再试！");
        }
    }

    /**
     * 更新记录
     */
    @RequestMapping("manage/updateAssess")
    @ResponseBody
    public ResultUtil updateAssess(Assess assess, HttpSession session) {
        try {
            assessMapper.updateAssess(assess);
            return ResultUtil.ok("修改考核方案成功");
        } catch (Exception e) {
            return ResultUtil.error("修改考核方案出错,稍后再试！");
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("manage/deleteAssess")
    @ResponseBody
    public ResultUtil deleteAssessById(Integer id) {
        try {
            assessMapper.deleteAssessById(id);
            return ResultUtil.ok("删除考核方案成功");
        } catch (Exception e) {
            return ResultUtil.error("删除考核方案出错,稍后再试！");
        }
    }

    /**
     * 根据ID批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping("manage/deletesAssess")
    @ResponseBody
    public ResultUtil deletesAssess(String idsStr) {
        try {
            if (!StringUtils.isBlank(idsStr)) {
                String[] ids = idsStr.split(",");
                for (String id : ids) {
                    assessMapper.deleteAssessById(Integer.parseInt(id));
                }
            }
            return ResultUtil.ok("批量删除考核方案成功");
        } catch (Exception e) {
            return ResultUtil.error("删除考核方案出错,稍后再试！");
        }
    }


}
