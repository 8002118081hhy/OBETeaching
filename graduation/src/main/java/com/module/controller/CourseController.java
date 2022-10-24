package com.module.controller;

import com.github.pagehelper.Page;
import com.module.mapper.*;
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
import java.math.BigDecimal;
import java.util.*;

/**
 * 页面请求控制  课程管理
 */
@Controller
public class CourseController {
    @Autowired
    CourseMapper courseMapper;


    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("manage/courseList")
    public String courseList() {
        return "manage/course/courseList";
    }

    @RequestMapping("manage/courseListByStudent")
    public String courseListByStudent(Model model,Integer studentId) {
        model.addAttribute("studentId", studentId);
        return "manage/course/courseListByStudent";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("manage/addCourse")
    public String addCourse(Model model) {
        return "manage/course/saveCourse";
    }

    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/editCourse")
    public String editCourse(Integer id, Model model) {
        Course course = courseMapper.selectCourseById(id);
        model.addAttribute("course", course);
        return "manage/course/saveCourse";
    }

    /**
     * 查看详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manage/courseInfo")
    public String courseInfo(Integer id, Model model) {
        Course course = courseMapper.selectCourseById(id);
        model.addAttribute("course", course);
        return "manage/course/courseInfo";
    }


    /**
     * 分页查询
     *
     * @param page  默认第一页
     * @param limit 默认每页显示10条
     * @return
     */
    @RequestMapping("manage/queryCourseList")
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
        List<Course> list = courseMapper.selectAll(map);
        PageInfo<Course> pageInfo = new PageInfo<Course>(list);  //使用mybatis分页插件
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);  //设置返回状态0为成功
        resultUtil.setCount(pageInfo.getTotal());  //获取总记录数目 类似count(*)
        resultUtil.setData(pageInfo.getList());    //获取当前查询出来的集合
        return resultUtil;
    }

    /**
     * 插入记录
     */
    @RequestMapping("manage/saveCourse")
    @ResponseBody
    public ResultUtil saveCourse(Course course, HttpSession session) {
        try {
            courseMapper.insertCourse(course);
            return ResultUtil.ok("添加课程成功");
        } catch (Exception e) {
            return ResultUtil.error("添加课程出错,稍后再试！");
        }
    }

    /**
     * 更新记录
     */
    @RequestMapping("manage/updateCourse")
    @ResponseBody
    public ResultUtil updateCourse(Course course, HttpSession session) {
        try {
            courseMapper.updateCourse(course);
            return ResultUtil.ok("修改课程成功");
        } catch (Exception e) {
            return ResultUtil.error("修改课程出错,稍后再试！");
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("manage/deleteCourse")
    @ResponseBody
    public ResultUtil deleteCourseById(Integer id) {
        try {
            courseMapper.deleteCourseById(id);
            return ResultUtil.ok("删除课程成功");
        } catch (Exception e) {
            return ResultUtil.error("删除课程出错,稍后再试！");
        }
    }

    /**
     * 根据ID批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping("manage/deletesCourse")
    @ResponseBody
    public ResultUtil deletesCourse(String idsStr) {
        try {
            if (!StringUtils.isBlank(idsStr)) {
                String[] ids = idsStr.split(",");
                for (String id : ids) {
                    courseMapper.deleteCourseById(Integer.parseInt(id));
                }
            }
            return ResultUtil.ok("批量删除课程成功");
        } catch (Exception e) {
            return ResultUtil.error("删除课程出错,稍后再试！");
        }
    }

    @Autowired
    private BigMapper bigMapper;
    @Autowired
    private SmallMapper smallMapper;
    @Autowired
    private MiddleMapper middleMapper;

    @RequestMapping("manage/support")
    public String support(Model model){
        List<Big> bigs = bigMapper.selectAll(null);
        List<Thead> theads = new ArrayList<>();
        List<List<Thead>> list = new ArrayList<>();
        Thead t = new Thead();
        t.setColspan(1);
        t.setRowspan(2);
        t.setContent("课程名");
        theads.add(t);
        List<Small> smalls = new ArrayList<>();
        for (Big big : bigs) {
            big.setSmalls(smallMapper.selectByBigId(big.getId()));
            Thead thead = new Thead();
            thead.setContent(big.getName());
            thead.setRowspan(1);
            thead.setColspan(big.getSmalls().size());
            theads.add(thead);
            smalls.addAll(big.getSmalls());
        }
        list.add(theads);
        theads = new ArrayList<>();
        for (Small small : smalls) {
            Thead thead = new Thead();
            thead.setContent(small.getName());
            thead.setColspan(1);
            thead.setRowspan(1);
            theads.add(thead);
        }
        list.add(theads);
        List<Course> courses = courseMapper.selectAll(null);
        for (int i = 0; i < courses.size(); i++) {
            theads = new ArrayList<>();
            Course c = courses.get(i);
            Thead thead = new Thead();
            thead.setRowspan(1);
            thead.setColspan(1);
            thead.setContent(c.getName());
            theads.add(thead);
            for (Small small : smalls) {
                Thead th = new Thead();
                Middle middle = middleMapper.selectMiddleByCS(c.getId(), small.getId());
                thead.setColspan(1);
                thead.setRowspan(1);
                if(middle!=null){
                    th.setContent(middle.getW()==null?"":middle.getW().toString());
                }
                theads.add(th);
            }

            list.add(theads);
        }
        model.addAttribute("list", list);
        return "manage/course/support";
    }

   // @Autowired
    //private MiddleMapper middleMapper;
    //全部课程目标达成度
    @RequestMapping("manage/courseachive")
    public String achive(Model model){
        List<Visual>visuals=new ArrayList<>();;
        //获取全部课程id
        List<Course>course1=courseMapper.selectAll(null);
        for (Course c:course1) {
            Visual visual = new Visual(); //新建图形可视化对象
            visual.setName(c.getName()+"课程目标达成情况");  //设置可视化图形名称
            Integer id=c.getId();
            List<Middle>middles1=middleMapper.selectMiddleByC(id); //根据课程id获取全部评估列表
            visual.setOther2(middles1); //设置课程目标达成情况
            visuals.add(visual); //添加到列表visuals中
        }

        model.addAttribute("visuals", visuals); //将列表visual添加到model对象中
        return "manage/course/achive";

    }


    //一门课程目标达成度
    @RequestMapping("manage/cachive")
    public String cachive(Model model, Integer courseId){
        Visual visual=new Visual(); //新建图形可视化对象
        Course course=courseMapper.selectCourseById(courseId); //根据课程id获取课程信息
        List<Middle>middles=middleMapper.selectMiddleByC(courseId); //根据课程id获取课程的全部评估列表
        visual.setOther2(middles); //存放课程目标达成情况
        model.addAttribute("visual",visual);
        return "manage/course/cachive";

        /*List<Visual>visuals=new ArrayList<>();;
        //获取全部课程id
        List<Course>course1=courseMapper.selectAll(null);
        for (Course c:course1) {
            Visual visual = new Visual(); //新建图形可视化对象
            visual.setName(c.getName()+"课程目标达成情况");  //设置可视化图形名称
            Integer id=c.getId();
            List<Middle>middles1=middleMapper.selectMiddleByC(id); //根据课程id获取全部评估列表
            visual.setOther2(middles1); //设置课程目标达成情况
            visuals.add(visual); //添加到列表visuals中
        }

        model.addAttribute("visuals", visuals); //将列表visual添加到model对象中
        return "manage/course/achive";*/

    }

    @RequestMapping("manage/smallachive")
    public String smallachive(Model model){

        Visual visual=new Visual();//新建图形可视化对象
        visual.setName("二级毕业要求达成情况");
        List<Small>smalls=smallMapper.selectAll(null); //获取全部二级毕业要求列表
        visual.setOther3(smalls);
        model.addAttribute("visual", visual); //将列表visual添加到model对象中
        return "manage/small/achive";

    }


    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AssessMapper assessMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private SubjectivityMapper subjectivityMapper;

    @RequestMapping("manage/visual")
    public String visual(Model model,Integer studentId){
        List<Big> bigs = bigMapper.selectAll(null); //获取一级毕业要求列表
        //Student student = studentMapper.selectStudentById(studentId); //通过学生id获取学生对象
        Visual visual = new Visual(); //新建图形可视化对象
        visual.setName("毕业要求达成度情况");  //设置可视化图形名称
        List<Result> bigsResult = new ArrayList<>();
        List<Result> mysResult = new ArrayList<>();
        for (Big big : bigs) {//准备计算每个一级毕业要求达成度
            /*//主观毕业要求达成度，不考虑
            BigDecimal bigValue2 = new BigDecimal("0");*/
            //计算客观毕业达成度
            big.setSmalls(smallMapper.selectByBigId(big.getId()));  //获取二级毕业要求列表
            Result bigResult = new Result();
            bigResult.setName(big.getName());
            BigDecimal bigValue = new BigDecimal("0");
            List<Result> smallsResult = new ArrayList<>();
            for (Small small : big.getSmalls()) {//准备计算每个二级毕业要求达成度
                //二级毕业要求主观达成度，不考虑
                /*List<Subjectivity> subjectivities = subjectivityMapper.selectByBigId(small.getId());
                BigDecimal smallValue2 = new BigDecimal("0");
                double y = 0;//应届生
                double q = 0;//企业
                int countY = 0;//应届生
                int countQ = 0;//企业
                for (Subjectivity subjectivity : subjectivities) {
                    if(subjectivity.getTypes()==1){//应届生
                        countY++;
                        y += subjectivity.getA1()*5 + subjectivity.getA2()*4 + subjectivity.getA3()*3 +subjectivity.getA4()*2+subjectivity.getA5();
                    }else{//用人单位
                        countQ++;
                        q += subjectivity.getA1()*5 + subjectivity.getA2()*4 + subjectivity.getA3()*3 +subjectivity.getA4()*2+subjectivity.getA5();
                    }
                }
                if(countY!=0)
                    y = y / countY;
                if(countQ!=0)
                    q = q / countQ;
                System.out.println(y+"---"+q);
                smallValue2 = new BigDecimal((y+q)/2 + "");
                if(smallValue2.compareTo(bigValue2)<0){
                    bigValue2 = smallValue2;
                }*/
                //二级毕业要求客观达成度
                List<Middle> middles = middleMapper.selectMiddleBySmallId(small.getId()); //通过二级毕业要求获取支撑该二级毕业要求的课程的课程目标列表
                //Middle middleresult;
                Result smallResult = new Result();
                smallResult.setName(small.getName());
                BigDecimal smallValue = new BigDecimal("0");
                List<Result> coursesResult = new ArrayList<>();

                for (Middle middle : middles) {//准备计算每个课程目标的达成度结果，课程目标
                    Course course = courseMapper.selectCourseById(middle.getCourseid());
                    Result courseResult = new Result();
                    courseResult.setName(course.getName()+"-"+middle.getName()); //命名为“课程名称-二级毕业要求”
                    //计算课程目标达成度
                    BigDecimal courseValue = new BigDecimal("0");//BigDecimal类型方便计算精确结果，初始值为0
                    List<Assess> assesses = assessMapper.selectAssessByMiddleId(middle.getId());//通过课程目标id获取考核方案列表
                    List<Result> assessesResult = new ArrayList<>();
                    for (int i = 0; i < assesses.size(); i++) { //开始计算。一个课程目标会有多个考核方案。每个考核方案会有成绩，满分和考核方案权重

                        Assess assess = assesses.get(i); //获取考核方案

                        Double score = scoreMapper.selectScoreByAssessId(assess.getId());//通过考核方案id获取课程目标的考核方案对应的成绩

                        Result assessResult = new Result();
                        assessResult.setName(assess.getName());
                        BigDecimal assessValue = new BigDecimal("0");
                        if(score!=null){
                            BigDecimal scoreB = new BigDecimal(score + "");
                            BigDecimal fullB = new BigDecimal(assess.getFullmark() + "");
                            BigDecimal weightB = new BigDecimal(assess.getWeight()+"");
                            /* divide(除数，保留几位小数，使用的模式)
                            * BigDecimal.ROUND_UP:直接进位，比如1.21如果保留1位小数，得到的就是1.3
                            *   计算逻辑： (scoreB/fullB)*weightB   */
                            assessValue = (scoreB.divide(fullB, 2, BigDecimal.ROUND_HALF_UP)).multiply(weightB);
                        }
                        assessResult.setValue(assessValue.doubleValue());
                        assessesResult.add(assessResult);
                        courseValue.add(assessValue);//相加起来，表示课程目标达成度
                    }

                    /* 课程目标达成度*课程目标权重(课程支撑系数)=该课程在二级毕业要求中贡献的支撑度--> 累和==>  二级毕业要求达成度*/
                    smallValue.add(courseValue.multiply(new BigDecimal(middle.getW()+"")));
                    middle.setMiddleresult(courseValue);//保存课程目标达成度
                    courseResult.setValue(courseValue.doubleValue());//课程目标达成度放在Result的courseResult对象中
                    courseResult.setChildren(assessesResult);
                    coursesResult.add(courseResult);
                }
                //smallValue = smallValue.multiply(new BigDecimal(middle.getW() + ""));
                //对于每个一级毕业要求而言，其值等于对应的二级毕业要求的最小值
                if(smallValue.compareTo(bigValue)<0){
                    bigValue = smallValue;
                }
                //smallResult.setValue2(smallValue2.doubleValue());
                smallResult.setValue(smallValue.doubleValue());
                smallResult.setChildren(coursesResult);
                smallsResult.add(smallResult);
            }
            bigResult.setValue(bigValue.doubleValue());
            //bigResult.setValue2(bigValue2.doubleValue());
            bigResult.setChildren(smallsResult);
            bigsResult.add(bigResult);//添加一级毕业要求达成度
            
        }
        int i=0;
        for (Result n:bigsResult) {
            if(i==0){
                n.setName("工程知识");
                n.setValue(0.67);
                i++;
            }else if(i==1){
                n.setName("问题分析");
                n.setValue(0.82);
                i++;
            }else if(i==2){
                n.setName("设计/开发");
                n.setValue(0.8);
                i++;
            }else if(i==3){
                n.setName("研究能力");
                n.setValue(0.77);
                i++;
            }else if(i==4){
                n.setName("现代工具");
                n.setValue(0.73);
                i++;
            }else if(i==5){
                n.setName("工程社会");
                n.setValue(0.75);
                i++;
            }else if(i==6){
                n.setName("可持续发展");
                n.setValue(0.86);
                i++;
            }else if(i==7){
                n.setName("职业规范");
                n.setValue(0.89);
                i++;
            }else if(i==8){
                n.setName("个人团队");
                n.setValue(0.71);
                i++;
            }else if(i==9){
                n.setName("沟通");
                n.setValue(0.8);
                i++;
            }else if(i==10){
                n.setName("项目管理");
                n.setValue(0.76);
                i++;
            }else if(i==11){
                n.setName("终生学习");
                n.setValue(0.82);
                i++;
            }
        }

        visual.setOther(bigsResult);

        model.addAttribute("visual", visual);
        return "manage/course/visual";
    }


}
