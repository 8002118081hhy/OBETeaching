package com.module.pojo;
import java.util.Date;



/**
 * 主观毕业要求表 subjectivity
 * 
 * @author administrator
 * @date 2022-04-05 18:08:37
 */
public class Subjectivity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 非常满意 */
	private Double a1;
	/** 满意 */
	private Double a2;
	/** 一般 */
	private Double a3;
	/** 不满意 */
	private Double a4;
	/** 非常不满意 */
	private Double a5;
	/** 客观毕业要求 */
	private Integer bigid;

	private Small small;
	/** 类型  1-应届生  2-企业 */
	private Integer types;

	private Integer studentid;

	public Small getSmall() {
		return small;
	}

	public void setSmall(Small small) {
		this.small = small;
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
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
	 * 设置：非常满意
	 */
	public void setA1(Double a1) 
	{
		this.a1 = a1;
	}
	
	/**
	 * 获取：非常满意
	 */
	public Double getA1() 
	{
		return a1;
	}
	/**
	 * 设置：满意
	 */
	public void setA2(Double a2) 
	{
		this.a2 = a2;
	}
	
	/**
	 * 获取：满意
	 */
	public Double getA2() 
	{
		return a2;
	}
	/**
	 * 设置：一般
	 */
	public void setA3(Double a3) 
	{
		this.a3 = a3;
	}
	
	/**
	 * 获取：一般
	 */
	public Double getA3() 
	{
		return a3;
	}
	/**
	 * 设置：不满意
	 */
	public void setA4(Double a4) 
	{
		this.a4 = a4;
	}
	
	/**
	 * 获取：不满意
	 */
	public Double getA4() 
	{
		return a4;
	}
	/**
	 * 设置：非常不满意
	 */
	public void setA5(Double a5) 
	{
		this.a5 = a5;
	}
	
	/**
	 * 获取：非常不满意
	 */
	public Double getA5() 
	{
		return a5;
	}
	/**
	 * 设置：客观毕业要求
	 */
	public void setBigid(Integer bigid) 
	{
		this.bigid = bigid;
	}
	
	/**
	 * 获取：客观毕业要求
	 */
	public Integer getBigid() 
	{
		return bigid;
	}
	/**
	 * 设置：类型  1-应届生  2-企业
	 */
	public void setTypes(Integer types) 
	{
		this.types = types;
	}
	
	/**
	 * 获取：类型  1-应届生  2-企业
	 */
	public Integer getTypes() 
	{
		return types;
	}
}
