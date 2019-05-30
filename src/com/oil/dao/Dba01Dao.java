package com.oil.dao;

import java.util.List;

import org.hibernate.Session;

import com.oil.module.Dba01;

public interface Dba01Dao {

	/**
	 * 根据井号、时间段分页获取油井记录列表
	 * @param sess
	 * @param jh 井号
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Dba01> findPageInfoByOpt(Session sess,String jh,String sDate,String eDate,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据井号、时间段获取油井记录条数
	 * @param sess
	 * @param jh 井号
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	Integer getCountByOpt(Session sess,String jh,String sDate,String eDate);
}
