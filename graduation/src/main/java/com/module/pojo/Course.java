package com.module.pojo;
import java.util.Date;



/**
 * 课程表 course
 * 
 * @author administrator
 * @date 2022-04-05 18:34:49
 */
public class Course
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 课程名称 */
	private String name;
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
	 * 设置：课程名称
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：课程名称
	 */
	public String getName() 
	{
		return name;
	}
}
