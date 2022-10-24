package com.module.controller;

import com.github.pagehelper.Page;
import com.module.mapper.BigMapper;
import com.module.pojo.Big;
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
 * 页面请求控制  大毕业要求管理
 */
@Controller
public class BigController {
    @Autowired
    BigMapper bigMapper;


    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("manage/bigList")
    public String bigList() {
        return "manage/big/bigList";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("manage/addBig")
    public String addBig(Model model) {
        return "manage/big/saveBig";
    }

    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/editBig")
    public String editBig(Integer id, Model model) {
        Big big = bigMapper.selectBigById(id);
        model.addAttribute("big", big);
        return "manage/big/saveBig";
    }

    /**
     * 查看详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/bigInfo")
    public String bigInfo(Integer id, Model model) {
        Big big = bigMapper.selectBigById(id);
        model.addAttribute("big", big);
        return "manage/big/bigInfo";
    }


    /**
     * 分页查询
     *
     * @param page  默认第一页
     * @param limit 默认每页显示10条
     * @return
     */
    @RequestMapping("manage/queryBigList")
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
        List<Big> list = bigMapper.selectAll(map);
        PageInfo<Big> pageInfo = new PageInfo<Big>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

    /**
     * 插入记录
     */
    @RequestMapping("manage/saveBig")
    @ResponseBody
    public ResultUtil saveBig(Big big, HttpSession session) {
        try {
            bigMapper.insertBig(big);
            return ResultUtil.ok("添加大毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("添加大毕业要求出错,稍后再试！");
        }
    }

    /**
     * 更新记录
     */
    @RequestMapping("manage/updateBig")
    @ResponseBody
    public ResultUtil updateBig(Big big, HttpSession session) {
        try {
            bigMapper.updateBig(big);
            return ResultUtil.ok("修改大毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("修改大毕业要求出错,稍后再试！");
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("manage/deleteBig")
    @ResponseBody
    public ResultUtil deleteBigById(Integer id) {
        try {
            bigMapper.deleteBigById(id);
            return ResultUtil.ok("删除一级毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("删除一级毕业要求出错,稍后再试！");
        }
    }

    /**
     * 根据ID批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping("manage/deletesBig")
    @ResponseBody
    public ResultUtil deletesBig(String idsStr) {
        try {
            if (!StringUtils.isBlank(idsStr)) {
                String[] ids = idsStr.split(",");
                for (String id : ids) {
                    bigMapper.deleteBigById(Integer.parseInt(id));
                }
            }
            return ResultUtil.ok("批量删除大毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("删除大毕业要求出错,稍后再试！");
        }
    }


}
