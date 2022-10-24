package com.module.mapper;

import com.module.pojo.Assess;
import java.util.List;
import java.util.Map;

/**
 * 考核方案 数据层
 * 
 * @author administrator
 * @date 2022-04-05 17:58:48
 */
public interface AssessMapper 
{

	/**
     * 查询考核方案信息
     * 
     * @param id 考核方案ID
     * @return 考核方案信息
     */
	public Assess selectAssessById(Integer id);
	
	/**
     * 查询考核方案列表
     * 
     * @param assess 考核方案信息
     * @return 考核方案集合
     */
	public List<Assess> selectAssessList(Assess assess);

	public List<Assess> selectAssessByMiddleId(Integer middleId);

	/**
     * 查询所有考核方案
     * 
     * @return 考核方案列表
     */
    public List<Assess> selectAll(Map map);
	/**
     * 新增考核方案
     * 
     * @param assess 考核方案信息
     * @return 结果
     */
	public int insertAssess(Assess assess);
	
	/**
     * 修改考核方案
     * 
     * @param assess 考核方案信息
     * @return 结果
     */
	public int updateAssess(Assess assess);
	 /**
     * 批量修改
     * @param list
     * @return
     */
   public int batchUpdate(List<Assess> list);
	/**
     * 删除考核方案
     * 
     * @param id 考核方案ID
     * @return 结果
     */
	public int deleteAssessById(Integer id);
	
	/**
     * 批量删除考核方案
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteAssess(Integer[] ids);
	
    /**
     * 批量添加
     * @param list
     * @return
     */
   public int batchAdd(List<Assess> list);
   
}