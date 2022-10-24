package com.module.pojo;
import java.util.Date;



/**
 * 考核方案表 assess
 * 
 * @author administrator
 * @date 2022-04-05 17:58:48
 */
public class Assess
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 考核方案 */
	private String name;
	/** 课程目标 */
	private Integer middleid;
	/** 考核权重 */
	private Double weight;

	private Middle middle;

	private Double fullmark;

	public Double getFullmark() {
		return fullmark;
	}

	public void setFullmark(Double fullmark) {
		this.fullmark = fullmark;
	}

	public Middle getMiddle() {
		return middle;
	}

	public void setMiddle(Middle middle) {
		this.middle = middle;
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
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：考核方案
	 */
	public String getName() 
	{
		return name;
	}
	/**
	 * 设置：课程目标
	 */
	public void setMiddleid(Integer middleid) 
	{
		this.middleid = middleid;
	}
	
	/**
	 * 获取：课程目标
	 */
	public Integer getMiddleid() 
	{
		return middleid;
	}
	/**
	 * 设置：考核权重
	 */
	public void setWeight(Double weight) 
	{
		this.weight = weight;
	}
	
	/**
	 * 获取：考核权重
	 */
	public Double getWeight() 
	{
		return weight;
	}
}
