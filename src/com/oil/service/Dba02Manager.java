package com.oil.service;

import java.util.List;

import org.hibernate.Session;

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
	
	/**
	 * 获取指定井号、指定时间段的有效注水天数
	 * @param jh 井号
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @return
	 */
	List<Dba02> listValideZsInfoByOpt(String jh,String sDate,String eDate) throws WEBException;
	
	/**
	 * 根据井号、注水方式、时间段获取合格的水井记录列表
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-22 下午09:27:51
	 * @param jh 井号
	 * @param zsfs 注水方式
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<Dba02> listSjInfoByOpt(String jh,String zsfs,String sDate,String eDate) throws WEBException;
	
	/**
	 * 根据井号、注水方式、时间段获取水井记录列表（scsj大于0）
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-22 下午09:28:41
	 * @param jh 井号
	 * @param zsfs 注水方式
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<Dba02> listValideZsInfoByOpt(String jh,String zsfs,String sDate,String eDate) throws WEBException;
}
