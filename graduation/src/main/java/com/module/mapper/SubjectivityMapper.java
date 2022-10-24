package com.module.mapper;

import com.module.pojo.Subjectivity;
import java.util.List;
import java.util.Map;

/**
 * 主观毕业要求 数据层
 * 
 * @author administrator
 * @date 2022-04-05 18:08:37
 */
public interface SubjectivityMapper 
{

	/**
     * 查询主观毕业要求信息
     * 
     * @param id 主观毕业要求ID
     * @return 主观毕业要求信息
     */
	public Subjectivity selectSubjectivityById(Integer id);
	
	/**
     * 查询主观毕业要求列表
     * 
     * @param subjectivity 主观毕业要求信息
     * @return 主观毕业要求集合
     */
	public List<Subjectivity> selectSubjectivityList(Subjectivity subjectivity);
	
	/**
     * 查询所有主观毕业要求
     * 
     * @return 主观毕业要求列表
     */
    public List<Subjectivity> selectAll(Map map);

	public List<Subjectivity> selectByBigId(Integer bigid);
	/**
     * 新增主观毕业要求
     * 
     * @param subjectivity 主观毕业要求信息
     * @return 结果
     */
	public int insertSubjectivity(Subjectivity subjectivity);
	
	/**
     * 修改主观毕业要求
     * 
     * @param subjectivity 主观毕业要求信息
     * @return 结果
     */
	public int updateSubjectivity(Subjectivity subjectivity);
	 /**
     * 批量修改
     * @param list
     * @return
     */
   public int batchUpdate(List<Subjectivity> list);
	/**
     * 删除主观毕业要求
     * 
     * @param id 主观毕业要求ID
     * @return 结果
     */
	public int deleteSubjectivityById(Integer id);
	
	/**
     * 批量删除主观毕业要求
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteSubjectivity(Integer[] ids);
	
    /**
     * 批量添加
     * @param list
     * @return
     */
   public int batchAdd(List<Subjectivity> list);
   
}