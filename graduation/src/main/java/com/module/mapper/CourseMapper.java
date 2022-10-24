package com.module.mapper;

import com.module.pojo.Course;
import java.util.List;
import java.util.Map;

/**
 * 课程 数据层
 * 
 * @author administrator
 * @date 2022-04-05 18:34:49
 */
public interface CourseMapper 
{

	/**
     * 查询课程信息
     * 
     * @param id 课程ID
     * @return 课程信息
     */
	public Course selectCourseById(Integer id);
	
	/**
     * 查询课程列表
     * 
     * @param course 课程信息
     * @return 课程集合
     */
	public List<Course> selectCourseList(Course course);
	
	/**
     * 查询所有课程
     * 
     * @return 课程列表
     */
    public List<Course> selectAll(Map map);
	/**
     * 新增课程
     * 
     * @param course 课程信息
     * @return 结果
     */
	public int insertCourse(Course course);
	
	/**
     * 修改课程
     * 
     * @param course 课程信息
     * @return 结果
     */
	public int updateCourse(Course course);
	 /**
     * 批量修改
     * @param list
     * @return
     */
   public int batchUpdate(List<Course> list);
	/**
     * 删除课程
     * 
     * @param id 课程ID
     * @return 结果
     */
	public int deleteCourseById(Integer id);
	
	/**
     * 批量删除课程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteCourse(Integer[] ids);
	
    /**
     * 批量添加
     * @param list
     * @return
     */
   public int batchAdd(List<Course> list);
   
}