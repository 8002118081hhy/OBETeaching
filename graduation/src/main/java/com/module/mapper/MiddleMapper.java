package com.module.mapper;

import com.module.pojo.Middle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程目标 数据层
 * 
 * @author administrator
 * @date 2022-04-05 17:58:40
 */
public interface MiddleMapper 
{

	/**
     * 查询课程目标信息
     * 
     * @param id 课程目标ID
     * @return 课程目标信息
     */
	public Middle selectMiddleById(Integer id);

	public List<Middle> selectMiddleBySmallId(Integer smallId);

	public Middle selectMiddleByCS(@Param("courseId")Integer courseId,@Param("smallId")Integer smallId);

	public List<Middle> selectMiddleByC(@Param("courseId")Integer courseId);

	/**
     * 查询课程目标列表
     * 
     * @param middle 课程目标信息
     * @return 课程目标集合
     */
	public List<Middle> selectMiddleList(Middle middle);
	
	/**
     * 查询所有课程目标
     * 
     * @return 课程目标列表
     */
    public List<Middle> selectAll(Map map);




	/**
     * 新增课程目标
     * 
     * @param middle 课程目标信息
     * @return 结果
     */
	public int insertMiddle(Middle middle);
	
	/**
     * 修改课程目标
     * 
     * @param middle 课程目标信息
     * @return 结果
     */
	public int updateMiddle(Middle middle);
	 /**
     * 批量修改
     * @param list
     * @return
     */
   public int batchUpdate(List<Middle> list);
	/**
     * 删除课程目标
     * 
     * @param id 课程目标ID
     * @return 结果
     */
	public int deleteMiddleById(Integer id);
	
	/**
     * 批量删除课程目标
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteMiddle(Integer[] ids);
	
    /**
     * 批量添加
     * @param list
     * @return
     */
   public int batchAdd(List<Middle> list);
   
}