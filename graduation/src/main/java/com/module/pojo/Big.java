package com.module.pojo;
import java.util.Date;
import java.util.List;


/**
 * 大毕业要求表 big
 * 
 * @author administrator
 * @date 2022-04-05 17:58:44
 */
public class Big
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 名称 */
	private String name;
	/** 一级毕业要求达成度 */
	private double bigresult;

	public double getBigresult() {
		return bigresult;
	}

	public void setBigresult(double bigresult) {
		this.bigresult = bigresult;
	}


	/*一级毕业要求下的二级毕业要求*/
	private List<Small> smalls;

	public List<Small> getSmalls() {
		return smalls;
	}

	public void setSmalls(List<Small> smalls) {
		this.smalls = smalls;
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
	 * 设置：名称
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：名称
	 */
	public String getName() 
	{
		return name;
	}
}
