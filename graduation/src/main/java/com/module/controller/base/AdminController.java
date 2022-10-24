package com.module.controller.base;

import com.module.mapper.AdminMapper;
import com.module.pojo.Admin;
import com.module.util.MD5Util;
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
 * 页面请求控制  管理员管理
 */
@Controller
public class AdminController {
    @Autowired
    AdminMapper adminMapper;

    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("manage/adminList")
    public String adminList() {
        return "manage/admin/adminList";
    }


    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("manage/addAdmin")
    public String addAdmin(Model model) {
        return "manage/admin/saveAdmin";
    }

    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/editAdmin")
    public String editAdmin(Integer id, Model model) {
        Admin admin = adminMapper.selectAdminById(id);
        model.addAttribute("admin", admin);
        return "manage/admin/saveAdmin";
    }

    /**
     * 分页查询
     *
     * @param page  默认第一页
     * @param limit 默认每页显示10条
     * @return
     */
    @RequestMapping("manage/queryAdminList")
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
        PageHelper.startPage(page, limit, true);
        List<Admin> list = adminMapper.selectAll(map);
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

    /**
     * 插入记录
     */
    @RequestMapping("manage/saveAdmin")
    @ResponseBody
    public ResultUtil saveAdmin(Admin admin, HttpSession session) {
        Date nowTime = new Date();
        admin.setCreatetime(nowTime);
        admin.setAdminpassword(MD5Util.getMd5(admin.getAdminpassword())); //加密密码
        try {
            adminMapper.insertAdmin(admin);
            return ResultUtil.ok("添加管理员成功");
        } catch (Exception e) {
            return ResultUtil.error("添加管理员出错,稍后再试！");
        }
    }

    /**
     * 更新记录
     */
    @RequestMapping("manage/updateAdmin")
    @ResponseBody
    public ResultUtil updateAdmin(Admin admin, HttpSession session) {
        Date nowTime = new Date();
        admin.setCreatetime(nowTime);
        admin.setAdminpassword(MD5Util.getMd5(admin.getAdminpassword())); //加密密码
        try {
            adminMapper.updateAdmin(admin);
            return ResultUtil.ok("修改管理员成功");
        } catch (Exception e) {
            return ResultUtil.error("修改管理员出错,稍后再试！");
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("manage/deleteAdmin")
    @ResponseBody
    public ResultUtil deleteAdminById(Integer id) {
        try {
            adminMapper.deleteAdminById(id);
            return ResultUtil.ok("删除管理员成功");
        } catch (Exception e) {
            return ResultUtil.error("删除管理员出错,稍后再试！");
        }
    }

    /**
     * 根据ID批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping("manage/deletesAdmin")
    @ResponseBody
    public ResultUtil deletesAdmin(String idsStr) {
        try {
            if (!StringUtils.isBlank(idsStr)) {
                String[] ids = idsStr.split(",");
                for (String id : ids) {
                    adminMapper.deleteAdminById(Integer.parseInt(id));
                }
            }
            return ResultUtil.ok("批量删除管理员成功");
        } catch (Exception e) {
            return ResultUtil.error("删除管理员出错,稍后再试！");
        }
    }


}
