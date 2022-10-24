package com.module.controller;

import com.github.pagehelper.Page;
import com.module.mapper.AssessMapper;
import com.module.mapper.ScoreMapper;
import com.module.pojo.Assess;
import com.module.pojo.Score;
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
 * 页面请求控制  成绩管理
 */
@Controller
public class ScoreController {
    @Autowired
    ScoreMapper scoreMapper;


    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("manage/scoreList")
    public String scoreList(Integer id,Model model) {
        model.addAttribute("studentId", id);
        return "manage/score/scoreList";
    }


    @Autowired
    private AssessMapper assessMapper;
    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("manage/addScore")
    public String addScore(Model model,Integer studentId) {
        model.addAttribute("studentId", studentId);
        Map map = new HashMap();
        map.put("studentId", studentId);
        List<Assess> assesses = assessMapper.selectAll(map);
        model.addAttribute("assesses", assesses);
        return "manage/score/saveScore";
    }

    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/editScore")
    public String editScore(Integer id, Model model,Integer studentId) {
        model.addAttribute("studentId", studentId);
        Map map = new HashMap();
        map.put("studentId", studentId);
        List<Assess> assesses = assessMapper.selectAll(map);
        model.addAttribute("assesses", assesses);
        Score score = scoreMapper.selectScoreById(id);
        model.addAttribute("score", score);
        return "manage/score/saveScore";
    }

    /**
     * 查看详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/scoreInfo")
    public String scoreInfo(Integer id, Model model) {
        Score score = scoreMapper.selectScoreById(id);
        model.addAttribute("score", score);
        return "manage/score/scoreInfo";
    }


    /**
     * 分页查询
     *
     * @param page  默认第一页
     * @param limit 默认每页显示10条
     * @return
     */
    @RequestMapping("manage/queryScoreList")
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
        List<Score> list = scoreMapper.selectAll(map);
        PageInfo<Score> pageInfo = new PageInfo<Score>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

    /**
     * 插入记录
     */
    @RequestMapping("manage/saveScore")
    @ResponseBody
    public ResultUtil saveScore(Score score, HttpSession session) {
        try {
            scoreMapper.insertScore(score);
            return ResultUtil.ok("添加成绩成功");
        } catch (Exception e) {
            return ResultUtil.error("添加成绩出错,稍后再试！");
        }
    }

    /**
     * 更新记录
     */
    @RequestMapping("manage/updateScore")
    @ResponseBody
    public ResultUtil updateScore(Score score, HttpSession session) {
        try {
            scoreMapper.updateScore(score);
            return ResultUtil.ok("修改成绩成功");
        } catch (Exception e) {
            return ResultUtil.error("修改成绩出错,稍后再试！");
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("manage/deleteScore")
    @ResponseBody
    public ResultUtil deleteScoreById(Integer id) {
        try {
            scoreMapper.deleteScoreById(id);
            return ResultUtil.ok("删除成绩成功");
        } catch (Exception e) {
            return ResultUtil.error("删除成绩出错,稍后再试！");
        }
    }

    /**
     * 根据ID批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping("manage/deletesScore")
    @ResponseBody
    public ResultUtil deletesScore(String idsStr) {
        try {
            if (!StringUtils.isBlank(idsStr)) {
                String[] ids = idsStr.split(",");
                for (String id : ids) {
                    scoreMapper.deleteScoreById(Integer.parseInt(id));
                }
            }
            return ResultUtil.ok("批量删除成绩成功");
        } catch (Exception e) {
            return ResultUtil.error("删除成绩出错,稍后再试！");
        }
    }


}
