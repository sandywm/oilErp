package com.oil.service.impl;

import org.hibernate.Session;

import com.oil.dao.Dba04Dao;
import com.oil.exception.WEBException;
import com.oil.factory.DaoFactory;
import com.oil.module.Dba04;
import com.oil.service.Dba04Manager;
import com.oil.tools.HibernateUtil;
import com.oil.util.Constants;

public class Dba04ManagerImpl implements Dba04Manager{

	Dba04Dao dbDao = null;
	@Override
	public Dba04 getEntityByOpt(String jh, String ny) throws WEBException {
		// TODO Auto-generated method stub
		try {
			dbDao = (Dba04Dao) DaoFactory.instance(null).getDao(Constants.DAO_DBA_04_INFO);
			Session sess = HibernateUtil.currentSession();
			return dbDao.getEntityByOpt(sess, jh, ny);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据井号、年月获取动液面信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
