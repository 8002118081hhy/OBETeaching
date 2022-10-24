package com.module.pojo;
import java.util.Date;



/**
 * 学生表 student
 * 
 * @author administrator
 * @date 2022-04-05 17:58:27
 */
public class Student
{
	private static final long serialVersionUID = 1L;
	
	/** id编号 */
	private Integer id;
	/** 姓名 */
	private String realname;
	/** 性别 */
	private String gender;
	/** 生日 */
	private String birthday;
	/** 班级 */
	private String grade;
	/**
	 * 设置：id编号
	 */
	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：id编号
	 */
	public Integer getId() 
	{
		return id;
	}
	/**
	 * 设置：姓名
	 */
	public void setRealname(String realname) 
	{
		this.realname = realname;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getRealname() 
	{
		return realname;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(String gender) 
	{
		this.gender = gender;
	}
	
	/**
	 * 获取：性别
	 */
	public String getGender() 
	{
		return gender;
	}
	/**
	 * 设置：生日
	 */
	public void setBirthday(String birthday) 
	{
		this.birthday = birthday;
	}
	
	/**
	 * 获取：生日
	 */
	public String getBirthday() 
	{
		return birthday;
	}
	/**
	 * 设置：班级
	 */
	public void setGrade(String grade) 
	{
		this.grade = grade;
	}
	
	/**
	 * 获取：班级
	 */
	public String getGrade() 
	{
		return grade;
	}
}
