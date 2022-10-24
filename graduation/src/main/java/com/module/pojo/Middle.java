package com.module.pojo;


import java.math.BigDecimal;

/**
 * 课程目标表 middle
 * 
 * @author administrator
 * @date 2022-04-05 17:58:40
 */
public class Middle
{
	private static final long serialVersionUID = 1L;


	/** ID */
	private Integer id;
	/** 课程目标名称 */
	private String name;
	/** 二级毕业要求id */
	private Integer smallid;
	/** 课程id */
	private Integer courseid;

	private Double w;

	private BigDecimal middleresult;


	public BigDecimal getMiddleresult() {
		return middleresult;
	}

	public void setMiddleresult(BigDecimal middleresult) {
		this.middleresult = middleresult;
	}



	private Small small;

	private Course course;

	@Override
	public String toString() {
		return "Middle{" +
				"id=" + id +
				", name='" + name + '\'' +
				", smallid=" + smallid +
				", courseid=" + courseid +
				", w=" + w +
				", middleresult=" + middleresult +
				", small=" + small +
				", course=" + course +
				'}';
	}

	public Double getW() {
		return w;
	}

	public void setW(Double w) {
		this.w = w;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Small getSmall() {
		return small;
	}

	public void setSmall(Small small) {
		this.small = small;
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
	 * 设置：课程目标名称
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：课程目标名称
	 */
	public String getName() 
	{
		return name;
	}
	/**
	 * 设置：小毕业要求
	 */
	public void setSmallid(Integer smallid) 
	{
		this.smallid = smallid;
	}
	
	/**
	 * 获取：小毕业要求
	 */
	public Integer getSmallid() 
	{
		return smallid;
	}
	/**
	 * 设置：课程
	 */
	public void setCourseid(Integer courseid) 
	{
		this.courseid = courseid;
	}
	
	/**
	 * 获取：课程
	 */
	public Integer getCourseid() 
	{
		return courseid;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
