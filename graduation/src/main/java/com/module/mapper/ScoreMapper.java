package com.module.mapper;

import com.module.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 成绩 数据层
 * 
 * @author administrator
 * @date 2022-04-05 17:58:35
 */
public interface ScoreMapper 
{

	/**
     * 查询成绩信息
     * 
     * @param id 成绩ID
     * @return 成绩信息
     */
	public Score selectScoreById(Integer id);

	public Score selectScoreAssessId(@Param("assessId") Integer assessId, @Param("studentId") Integer studentId);
	public double selectScoreByAssessId(Integer assessId);
	/**
     * 查询成绩列表
     * 
     * @param score 成绩信息
     * @return 成绩集合
     */
	public List<Score> selectScoreList(Score score);
	
	/**
     * 查询所有成绩
     * 
     * @return 成绩列表
     */
    public List<Score> selectAll(Map map);
	/**
     * 新增成绩
     * 
     * @param score 成绩信息
     * @return 结果
     */
	public int insertScore(Score score);
	
	/**
     * 修改成绩
     * 
     * @param score 成绩信息
     * @return 结果
     */
	public int updateScore(Score score);
	 /**
     * 批量修改
     * @param list
     * @return
     */
   public int batchUpdate(List<Score> list);
	/**
     * 删除成绩
     * 
     * @param id 成绩ID
     * @return 结果
     */
	public int deleteScoreById(Integer id);
	
	/**
     * 批量删除成绩
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteScore(Integer[] ids);
	
    /**
     * 批量添加
     * @param list
     * @return
     */
   public int batchAdd(List<Score> list);
   
}