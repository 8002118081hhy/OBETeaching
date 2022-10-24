package com.module.mapper;

import com.module.pojo.Small;
import java.util.List;
import java.util.Map;

/**
 * 小毕业要求 数据层
 * 
 * @author administrator
 * @date 2022-04-05 18:13:52
 */
public interface SmallMapper 
{

	/**
     * 查询小毕业要求信息
     * 
     * @param id 小毕业要求ID
     * @return 小毕业要求信息
     */
	public Small selectSmallById(Integer id);
	
	/**
     * 查询小毕业要求列表
     * 
     * @param small 小毕业要求信息
     * @return 小毕业要求集合
     */
	public List<Small> selectSmallList(Small small);

	/*通过一级id获取二级毕业要求列表*/
	public List<Small> selectByBigId(Integer bigId);

	/**
     * 查询所有小毕业要求
     * 
     * @return 小毕业要求列表
     */
    public List<Small> selectAll(Map map);
	/**
     * 新增小毕业要求
     * 
     * @param small 小毕业要求信息
     * @return 结果
     */
	public int insertSmall(Small small);
	
	/**
     * 修改小毕业要求
     * 
     * @param small 小毕业要求信息
     * @return 结果
     */
	public int updateSmall(Small small);
	 /**
     * 批量修改
     * @param list
     * @return
     */
   public int batchUpdate(List<Small> list);
	/**
     * 删除小毕业要求
     * 
     * @param id 小毕业要求ID
     * @return 结果
     */
	public int deleteSmallById(Integer id);
	
	/**
     * 批量删除小毕业要求
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteSmall(Integer[] ids);
	
    /**
     * 批量添加
     * @param list
     * @return
     */
   public int batchAdd(List<Small> list);
   
}