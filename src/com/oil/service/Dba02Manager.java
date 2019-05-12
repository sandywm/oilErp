package com.oil.service;

import java.util.List;

import com.oil.exception.WEBException;
import com.oil.module.Dba02;

public interface Dba02Manager {

	List<Dba02> listSjInfo(String jh,String sDate,String eDate,boolean hgFlag) throws WEBException;
	
	/**
	 * 根据井号、时间段分页获取水井列表
	 * @param jh 井号
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<Dba02> listSjPageInfoByOpt(String jh,String sDate,String eDate,Integer pageNo,Integer pageSize) throws WEBException;
	
	/**
	 * 根据井号、时间段获取水井记录条数
	 * @param jh 井号
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @return
	 * @throws WEBException
	 */
	Integer getSjCountByOpt(String jh,String sDate,String eDate) throws WEBException;
}
