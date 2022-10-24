package com.module.controller.base;

import com.module.mapper.AdminMapper;
import com.module.pojo.Admin;
import com.module.util.MD5Util;
import com.module.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("manage/login")
    public String login(){
        return "manage/login";
    }
    @RequestMapping("manageIndex")
    public String manageIndex(){
        return "manage/index";
    }
    @Autowired
    private AdminMapper adminMapper;

    /*判断是否登录成功*/
    @RequestMapping("manage/loginSubmit")
    /*将返回对象转换成json响应给ajax请求*/
    @ResponseBody
    public ResultUtil loginSubmit(String username, String password, String role, String vcode, HttpSession session){
        String vcodes = (String) session.getAttribute("vcode");
        if(!vcodes.equals(vcode)){
            return ResultUtil.error("验证码错误");
        }
        /*获取加密密码*/
        password = MD5Util.getMd5(password);
        Object o = null;
        o = adminMapper.login(username, password);

        if(o==null){
            return ResultUtil.error("用户名或密码错误");
        }
        session.setAttribute("role", "管理员");
        session.setAttribute("userInfo", o);
        return ResultUtil.ok("登陆成功");
    }

    @RequestMapping("/manage/personalData")
    public String personalData(){
        return "manage/personalData";
    }

    @RequestMapping("/manage/loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute("userInfo");
        return "manage/login";
    }

    @RequestMapping("/manage/changePwd")
    public String changePwd(HttpSession session) {
        //session.removeAttribute("userInfo");
        return "manage/changePwd";
    }


    @RequestMapping("/manage/changePwdSubmit")
    @ResponseBody
    public ResultUtil changePwdSubmit(String oldPwd,String newPwd,HttpSession session) {
        oldPwd = MD5Util.getMd5(oldPwd);
        newPwd = MD5Util.getMd5(newPwd);
        String role = (String) session.getAttribute("role");
            Admin admin = (Admin) session.getAttribute("userInfo");
            if(!admin.getAdminpassword().equals(oldPwd)){
                return ResultUtil.error("旧密码错误！");
            }
            admin.setAdminpassword(newPwd);
            adminMapper.updateAdmin(admin);
            return ResultUtil.ok("修改成功");
    }
}
