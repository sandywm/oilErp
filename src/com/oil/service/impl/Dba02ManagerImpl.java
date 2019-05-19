package com.oil.service.impl;

import java.util.List;

import org.hibernate.Session;

import com.oil.dao.Dba02Dao;
import com.oil.exception.WEBException;
import com.oil.factory.DaoFactory;
import com.oil.module.Dba02;
import com.oil.service.Dba02Manager;
import com.oil.tools.HibernateUtil;
import com.oil.util.Constants;

public class Dba02ManagerImpl implements Dba02Manager{

	@Override
	public List<Dba02> listSjInfo(String jh,String sDate,String eDate,boolean hgFlag) throws WEBException {
		// TODO Auto-generated method stub
		try {
			Dba02Dao dba02Dao = (Dba02Dao) DaoFactory.instance(null).getDao(Constants.DAO_DBA_02_INFO);
			Session sess = HibernateUtil.currentSession();
			return dba02Dao.findSjInfo(sess,jh,sDate,eDate,hgFlag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取DBA02记录出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Dba02> listSjPageInfoByOpt(String jh, String sDate,
			String eDate, Integer pageNo, Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			Dba02Dao dba02Dao = (Dba02Dao) DaoFactory.instance(null).getDao(Constants.DAO_DBA_02_INFO);
			Session sess = HibernateUtil.currentSession();
			return dba02Dao.findSjInfoByOpt(sess, jh, sDate, eDate, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据井号、时间段分页获取水井列表出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getSjCountByOpt(String jh, String sDate, String eDate)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			Dba02Dao dba02Dao = (Dba02Dao) DaoFactory.instance(null).getDao(Constants.DAO_DBA_02_INFO);
			Session sess = HibernateUtil.currentSession();
			return dba02Dao.getSjCountByOpt(sess, jh, sDate, eDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据井号、时间段分页获取水井记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Dba02> listValideZsInfoByOpt(String jh, String sDate, String eDate)  throws WEBException{
		// TODO Auto-generated method stub
		try {
			Dba02Dao dba02Dao = (Dba02Dao) DaoFactory.instance(null).getDao(Constants.DAO_DBA_02_INFO);
			Session sess = HibernateUtil.currentSession();
			return dba02Dao.findValideZsInfoByOpt(sess, jh, sDate, eDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定井号、指定时间段的有效注水天数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
