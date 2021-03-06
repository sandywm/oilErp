package com.oil.dao;

import java.util.List;

import org.hibernate.Session;

import com.oil.module.Dba02;

public interface Dba02Dao {
	
	List<Dba02> findSjInfo(Session sess,String jh,String sDate,String eDate,boolean hgFlag);
	
	/**
	 * 根据井号、时间段分页获取水井列表
	 * @param sess
	 * @param jh 井号
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @param hgFlag 合格标记(true:统计合格)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Dba02> findSjInfoByOpt(Session sess,String jh,String sDate,String eDate,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据井号、时间段获取水井记录条数
	 * @param sess
	 * @param jh 井号
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @return
	 */
	Integer getSjCountByOpt(Session sess,String jh,String sDate,String eDate);
	
	/**
	 * 获取指定井号、指定时间段的有效注水天数
	 * @param sess
	 * @param jh 井号
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @return
	 */
	List<Dba02> findValideZsInfoByOpt(Session sess,String jh,String sDate,String eDate);
	
	/**
	 * 根据井号、注水方式、时间段获取合格的水井记录列表
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-22 下午09:21:29
	 * @param sess
	 * @param jh 井号
	 * @param zsfs 注水方式
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	List<Dba02> findSjInfoByOpt(Session sess,String jh,String zsfs,String sDate,String eDate);
	
	/**
	 * 根据井号、注水方式、时间段获取水井记录列表（scsj大于0）
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-22 下午09:24:31
	 * @param sess
	 * @param jh 井号
	 * @param zsfs 注水方式
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	List<Dba02> findValideZsInfoByOpt(Session sess,String jh,String zsfs,String sDate,String eDate);
	
}
