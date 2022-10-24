package com.module.controller.base;

import com.module.util.ResultUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件上传请求处理类
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/uploadFile", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public ResultUtil uploadFile(MultipartFile file, HttpServletRequest request) {
        if (file == null) {
            return ResultUtil.error("文件不能为空！");
        }
        String fileSub = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        if (".jpg".equals(fileSub) || ".jpeg".equals(fileSub) || ".png".equals(fileSub) || ".gif".equals(fileSub)) {
            Random d = new Random();
            String img = System.currentTimeMillis() + "_" + d.nextInt(10) + "" + fileSub;
            //获取当前项目上传文件路径  中的upload文件中
            //将当前日期格式化为文件夹路径 例如"20190203"
            //获取项目路径 项目名（上下文）
            String basePath = request.getSession().getServletContext().getRealPath("/uploads");
            System.out.println("basePath = " + basePath);
            /*
                使用配置文件配置文件上传路径
                String dateStr = (new SimpleDateFormat("yyyyMMdd/")).format(new Date());
                String path = ConfigUtil.getUploadPath() + dateStr;  //读取配置文件中的路径+时间
            */
            String path = basePath;
            try {
                File f = new File(path);
                if (!f.exists()) {
                    f.mkdirs();
                }
                file.transferTo(new File(f, img));
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error("文件上传错误，文件夹不能创建");
            }
            //获取项目路径 例如项目名为test  则值为  /test
            String contextPath = request.getServletContext().getContextPath();
            Map<String, String> map = new HashMap<>();
            map.put("src", contextPath + "/uploads/" + img);
            map.put("savePath", path + img);

            return ResultUtil.ok(map);
        } else {
            return ResultUtil.error("文件格式不支持,请重新选择！");
        }
    }

    /**
     * layui上传文件
     * 返回数据格式
     * {
     * "code": 0 //0表示成功，其它失败
     * ,"msg": "" //提示信息 //一般上传失败后返回
     * ,"data": {
     * "src": "图片路径"
     * ,"title": "图片名称" //可选
     * }
     * }
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/layeditUploadFile", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public Map layeditUploadFile(MultipartFile file, HttpServletRequest request) {
        Map map = new HashMap();
        if (file == null) {
            map.put("code", -1);
            map.put("msg", "文件不能为空！");
            return map;
        }
        String fileSub = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        if (".jpg".equals(fileSub) || ".jpeg".equals(fileSub) || ".png".equals(fileSub) || ".gif".equals(fileSub)) {
            Random d = new Random();
            String img = System.currentTimeMillis() + "_" + d.nextInt(10) + "" + fileSub;
            //获取当前项目上传文件路径  中的upload文件中
            //将当前日期格式化为文件夹路径 例如"20190203"
            //获取项目路径 项目名（上下文）
            String basePath = request.getSession().getServletContext().getRealPath("/uploads");
            System.out.println("basePath = " + basePath);
            /*
                使用配置文件配置文件上传路径
                String dateStr = (new SimpleDateFormat("yyyyMMdd/")).format(new Date());
                String path = ConfigUtil.getUploadPath() + dateStr;  //读取配置文件中的路径+时间
            */
            String path = basePath;
            try {
                File f = new File(path);
                if (!f.exists()) {
                    f.mkdirs();
                }
                file.transferTo(new File(f, img));
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", -1);
                map.put("msg", "文件上传错误，文件夹不能创建！");
                return map;
            }
            //获取项目路径
            String contextPath = request.getServletContext().getContextPath();
            map.put("code", "0"); //0代表成功
            map.put("msg", "上传成功success"); //0代表成功
            Map data = new HashMap();
            data.put("src", contextPath + "/uploads/" + img);
            data.put("savePath", path + img);
            map.put("data", data); //0代表成功
            return map;
        } else {
            map.put("code", -1);
            map.put("msg", "文件格式不支持,请重新选择！");
            return map;
        }
    }

}
