package com.module.pojo;
import java.util.Date;



/**
 * 成绩表 score
 * 
 * @author administrator
 * @date 2022-04-05 17:58:35
 */
public class Score
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 考核方案 */
	private Integer assessid;
	/** 得分 */
	private Double score;
	/** 学生 */
	private Integer studentid;

	private Assess assess;

	public Assess getAssess() {
		return assess;
	}

	public void setAssess(Assess assess) {
		this.assess = assess;
	}

	/**
	 * 设置：ID
	 */
	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：ID
	 */
	public Integer getId() 
	{
		return id;
	}
	/**
	 * 设置：考核方案
	 */
	public void setAssessid(Integer assessid) 
	{
		this.assessid = assessid;
	}
	
	/**
	 * 获取：考核方案
	 */
	public Integer getAssessid() 
	{
		return assessid;
	}
	/**
	 * 设置：得分
	 */
	public void setScore(Double score) 
	{
		this.score = score;
	}
	
	/**
	 * 获取：得分
	 */
	public Double getScore() 
	{
		return score;
	}
	/**
	 * 设置：学生
	 */
	public void setStudentid(Integer studentid) 
	{
		this.studentid = studentid;
	}
	
	/**
	 * 获取：学生
	 */
	public Integer getStudentid() 
	{
		return studentid;
	}
}
