package com.module.controller;

import com.github.pagehelper.Page;
import com.module.mapper.BigMapper;
import com.module.mapper.CourseMapper;
import com.module.mapper.MiddleMapper;
import com.module.mapper.SmallMapper;
import com.module.pojo.*;
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
 * 页面请求控制  小毕业要求管理
 */
@Controller
public class SmallController {
    @Autowired
    SmallMapper smallMapper;
    MiddleMapper middleMapper;


    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("manage/smallList")
    public String smallList() {
        return "manage/small/smallList";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("manage/addSmall")
    public String addSmall(Model model) {
        List<Big> bigs = bigMapper.selectAll(null);
        model.addAttribute("bigs", bigs);
        return "manage/small/saveSmall";
    }

    /**
     * 跳转到添加页面1
     *
     * @return
     */
    @RequestMapping("manage/smallAchive")
    public String smallAchive(Model model) {
        Visual visual=new Visual(); //新建图形可视化对象
        List<Small>smalls=smallMapper.selectAll(null); //选择所有二级课程目标达成度
        visual.setOther3(smalls); //将二级课程目标达成度存放到visual对象中
        model.addAttribute("visual",visual);
        return "manage/small/achive";
    }

    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/editSmall")
    public String editSmall(Integer id, Model model) {
        Small small = smallMapper.selectSmallById(id);
        model.addAttribute("small", small);
        List<Big> bigs = bigMapper.selectAll(null);
        model.addAttribute("bigs", bigs);
        return "manage/small/saveSmall";
    }

    /**
     * 查看详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/smallInfo")
    public String smallInfo(Integer id, Model model) {
        Small small = smallMapper.selectSmallById(id);
        model.addAttribute("small", small);
        return "manage/small/smallInfo";
    }

    @Autowired
    private BigMapper bigMapper;

    /**
     * 分页查询
     *
     * @param page  默认第一页
     * @param limit 默认每页显示10条
     * @return
     */
    @RequestMapping("manage/querySmallList")
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
        List<Small> list = smallMapper.selectAll(map);
        PageInfo<Small> pageInfo = new PageInfo<Small>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

    /**
     * 插入记录
     */
    @RequestMapping("manage/saveSmall")
    @ResponseBody
    public ResultUtil saveSmall(Small small, HttpSession session) {
        try {
            smallMapper.insertSmall(small);
            return ResultUtil.ok("添加二级毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("添加二级毕业要求出错,稍后再试！");
        }
    }

    /**
     * 更新记录
     */
    @RequestMapping("manage/updateSmall")
    @ResponseBody
    public ResultUtil updateSmall(Small small, HttpSession session) {
        try {
            smallMapper.updateSmall(small);
            return ResultUtil.ok("修改二级毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("修改二级毕业要求出错,稍后再试！");
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("manage/deleteSmall")
    @ResponseBody
    public ResultUtil deleteSmallById(Integer id) {
        try {
            smallMapper.deleteSmallById(id);
            return ResultUtil.ok("删除二级毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("删除二级毕业要求出错,稍后再试！");
        }
    }

    /**
     * 根据ID批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping("manage/deletesSmall")
    @ResponseBody
    public ResultUtil deletesSmall(String idsStr) {
        try {
            if (!StringUtils.isBlank(idsStr)) {
                String[] ids = idsStr.split(",");
                for (String id : ids) {
                    smallMapper.deleteSmallById(Integer.parseInt(id));
                }
            }
            return ResultUtil.ok("批量删除二级毕业要求成功");
        } catch (Exception e) {
            return ResultUtil.error("删除二级毕业要求出错,稍后再试！");
        }
    }


}
