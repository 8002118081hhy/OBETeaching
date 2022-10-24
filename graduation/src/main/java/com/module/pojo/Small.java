package com.module.pojo;
import java.util.Date;



/**
 * 小毕业要求表 small
 * 
 * @author administrator
 * @date 2022-04-05 18:13:52
 */
public class Small
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 小毕业要求编号 */
	private String name;
	/** 小毕业要求w值 */
	private Double w;
	/** 所属大毕业要求 */
	private Integer bigid;

	private Big big;

	public Big getBig() {
		return big;
	}

	public void setBig(Big big) {
		this.big = big;
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
	 * 设置：小毕业要求编号
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：小毕业要求编号
	 */
	public String getName() 
	{
		return name;
	}
	/**
	 * 设置：小毕业要求w值
	 */
	public void setW(Double w) 
	{
		this.w = w;
	}
	
	/**
	 * 获取：小毕业要求w值
	 */
	public Double getW() 
	{
		return w;
	}
	/**
	 * 设置：所属大毕业要求
	 */
	public void setBigid(Integer bigid) 
	{
		this.bigid = bigid;
	}
	
	/**
	 * 获取：所属大毕业要求
	 */
	public Integer getBigid() 
	{
		return bigid;
	}
}
