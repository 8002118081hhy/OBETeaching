package com.module.mapper;

import com.module.pojo.Big;
import java.util.List;
import java.util.Map;

/**
 * 大毕业要求 数据层
 * 
 * @author administrator
 * @date 2022-04-05 17:58:44
 */
public interface BigMapper 
{

	/**
     * 查询大毕业要求信息
     * 
     * @param id 大毕业要求ID
     * @return 大毕业要求信息
     */
	public Big selectBigById(Integer id);
	
	/**
     * 查询大毕业要求列表
     * 
     * @param big 大毕业要求信息
     * @return 大毕业要求集合
     */
	public List<Big> selectBigList(Big big);
	
	/**
     * 查询所有大毕业要求
     * 
     * @return 大毕业要求列表
     */
    public List<Big> selectAll(Map map);
	/**
     * 新增大毕业要求
     * 
     * @param big 大毕业要求信息
     * @return 结果
     */
	public int insertBig(Big big);
	
	/**
     * 修改大毕业要求
     * 
     * @param big 大毕业要求信息
     * @return 结果
     */
	public int updateBig(Big big);
	 /**
     * 批量修改
     * @param list
     * @return
     */
   public int batchUpdate(List<Big> list);
	/**
     * 删除大毕业要求
     * 
     * @param id 大毕业要求ID
     * @return 结果
     */
	public int deleteBigById(Integer id);
	
	/**
     * 批量删除大毕业要求
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteBig(Integer[] ids);
	
    /**
     * 批量添加
     * @param list
     * @return
     */
   public int batchAdd(List<Big> list);
   
}