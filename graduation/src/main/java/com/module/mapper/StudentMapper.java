package com.module.mapper;

import com.module.pojo.Student;
import java.util.List;
import java.util.Map;

/**
 * 学生 数据层
 * 
 * @author administrator
 * @date 2022-04-05 17:58:27
 */
public interface StudentMapper 
{

	/**
     * 查询学生信息
     * 
     * @param id 学生ID
     * @return 学生信息
     */
	public Student selectStudentById(Integer id);
	
	/**
     * 查询学生列表
     * 
     * @param student 学生信息
     * @return 学生集合
     */
	public List<Student> selectStudentList(Student student);
	
	/**
     * 查询所有学生
     * 
     * @return 学生列表
     */
    public List<Student> selectAll(Map map);
	/**
     * 新增学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	public int insertStudent(Student student);
	
	/**
     * 修改学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	public int updateStudent(Student student);
	 /**
     * 批量修改
     * @param list
     * @return
     */
   public int batchUpdate(List<Student> list);
	/**
     * 删除学生
     * 
     * @param id 学生ID
     * @return 结果
     */
	public int deleteStudentById(Integer id);
	
	/**
     * 批量删除学生
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteStudent(Integer[] ids);
	
    /**
     * 批量添加
     * @param list
     * @return
     */
   public int batchAdd(List<Student> list);
   
}