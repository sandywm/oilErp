package com.oil.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.oil.dao.Dba01Dao;
import com.oil.exception.WEBException;
import com.oil.factory.DaoFactory;
import com.oil.module.Dba01;
import com.oil.service.Dba01Manager;
import com.oil.tools.HibernateUtil;
import com.oil.util.Constants;

public class Dba01ManagerImpl implements Dba01Manager{

	Dba01Dao dDao = null;
	Transaction tran = null;
	@Override
	public List<Dba01> listPageInfoByOpt(String jh, String sDate, String eDate,
			Integer pageNo, Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			dDao = (Dba01Dao) DaoFactory.instance(null).getDao(Constants.DAO_DBA_01_INFO);
			Session sess = HibernateUtil.currentSession();
			return dDao.findPageInfoByOpt(sess, jh, sDate, eDate, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据井号、时间段分页获取油井记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(String jh, String sDate, String eDate)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			dDao = (Dba01Dao) DaoFactory.instance(null).getDao(Constants.DAO_DBA_01_INFO);
			Session sess = HibernateUtil.currentSession();
			return dDao.getCountByOpt(sess, jh, sDate, eDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据井号、时间段获取油井记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Dba01> listValidInfoByOpt(String jh, String sDate, String eDate)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			dDao = (Dba01Dao) DaoFactory.instance(null).getDao(Constants.DAO_DBA_01_INFO);
			Session sess = HibernateUtil.currentSession();
			return dDao.findValidInfoByOpt(sess, jh, sDate, eDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据井号、时间段获取有效油井记录列表（生产时间为24小时）时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
