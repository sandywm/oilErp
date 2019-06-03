package com.oil.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.oil.dao.Dba04Dao;
import com.oil.module.Dba04;

public class Dba04DaoImpl implements Dba04Dao{

	@SuppressWarnings("unchecked")
	@Override
	public Dba04 getEntityByOpt(Session sess, String jh, String ny) {
		// TODO Auto-generated method stub
		String hql = " from Dba04 as db where db.jh = '"+jh+"' and db.ny = '"+ny+"'";
		List<Dba04> dbList = sess.createQuery(hql).list();
		if(dbList.size() > 0){
			return dbList.get(0);
		}
		return null;
	}

}
