package com.oil.service;

import java.util.List;

import com.oil.exception.WEBException;
import com.oil.module.Dba01;

public interface Dba01Manager {

	/**
	 * 根据井号、时间段分页获取油井记录列表
	 * @param jh 井号
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<Dba01> listPageInfoByOpt(String jh,String sDate,String eDate,Integer pageNo,Integer pageSize)throws WEBException;
	
	/**
	 * 根据井号、时间段获取油井记录条数
	 * @param jh 井号
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(String jh,String sDate,String eDate)throws WEBException;
}
