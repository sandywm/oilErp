package com.oil.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.oil.dao.Dba02Dao;
import com.oil.module.Dba02;
import com.oil.tools.CommonTools;

@SuppressWarnings("unchecked")
public class Dba02DaoImpl implements Dba02Dao{

	@Override
	public List<Dba02> findSjInfo(Session sess,String jh,String sDate,String eDate,boolean hgFlag) {
		// TODO Auto-generated method stub
		String hql = " from Dba02 as db02 where db02.rpzsl > 0";
		if(!jh.equals("")){
			//oracle格式
			//hql += " and db02.jh = '"+jh+"' and db02.rq >= to_date('"+sDate+"','yyyy-mm-dd') and db02.rq <= to_date('"+eDate+"','yyyy-mm-dd')";
			//mysql格式
			hql += " and db02.jh = '"+jh+"' and date(db02.rq) >= '"+sDate+"' and date(db02.rq) <= '"+eDate+"'";
			if(hgFlag){
				hql += " and (db02.rzsl / db02.rpzsl) >= 0.9 and  (db02.rzsl / db02.rpzsl) <= 1.1";
			}
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<Dba02> findSjInfoByOpt(Session sess, String jh, String sDate,
			String eDate, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from Dba02 as db02 where 1=1";
		if(!jh.equals("")){
			//hql += " and db02.jh = '"+jh+"' and db02.rq >= to_date('"+sDate+"','yyyy-mm-dd') and db02.rq <= to_date('"+eDate+"','yyyy-mm-dd')";
			//mysql格式
			hql += " and db02.jh = '"+jh+"' and date(db02.rq) >= '"+sDate+"' and date(db02.rq) <= '"+eDate+"'";
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		} 
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getSjCountByOpt(Session sess, String jh, String sDate,
			String eDate) {
		// TODO Auto-generated method stub
		String hql = "select count(db02.jh) from Dba02 as db02 where 1=1";
		if(!jh.equals("")){
			//hql += " and db02.jh = '"+jh+"' and db02.rq >= to_date('"+sDate+"','yyyy-mm-dd') and db02.rq <= to_date('"+eDate+"','yyyy-mm-dd')";
			//mysql格式
			hql += " and db02.jh = '"+jh+"' and date(db02.rq) >= '"+sDate+"' and date(db02.rq) <= '"+eDate+"'";
		}
		Object count_obj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(count_obj);
	}

	@Override
	public List<Dba02> findValideZsInfoByOpt(Session sess, String jh, String sDate,
			String eDate) {
		// TODO Auto-generated method stub
		String hql = " from Dba02 as db02 where db02.scsj > 0";
		//hql += " and db02.jh = '"+jh+"' and db02.rq >= to_date('"+sDate+"','yyyy-mm-dd') and db02.rq <= to_date('"+eDate+"','yyyy-mm-dd')";
		hql += " and db02.jh = '"+jh+"' and date(db02.rq) >= '"+sDate+"' and date(db02.rq) <= '"+eDate+"'";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<Dba02> findSjInfoByOpt(Session sess, String jh, String zsfs,
			String sDate, String eDate) {
		// TODO Auto-generated method stub
		String hql = " from Dba02 as db02 where db02.rpzsl > 0 and db02.zsfs = '"+zsfs+"'";
		if(!jh.equals("")){
			//oracle格式
			//hql += " and db02.jh = '"+jh+"' and db02.rq >= to_date('"+sDate+"','yyyy-mm-dd') and db02.rq <= to_date('"+eDate+"','yyyy-mm-dd')";
			//mysql格式
			hql += " and db02.jh = '"+jh+"' and date(db02.rq) >= '"+sDate+"' and date(db02.rq) <= '"+eDate+"'";
			hql += " and (db02.rzsl / db02.rpzsl) >= 0.9 and  (db02.rzsl / db02.rpzsl) <= 1.1";
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<Dba02> findValideZsInfoByOpt(Session sess, String jh,
			String zsfs, String sDate, String eDate) {
		// TODO Auto-generated method stub
		String hql = " from Dba02 as db02 where db02.scsj > 0 and db02.zsfs = '"+zsfs+"'";
		//hql += " and db02.jh = '"+jh+"' and db02.rq >= to_date('"+sDate+"','yyyy-mm-dd') and db02.rq <= to_date('"+eDate+"','yyyy-mm-dd')";
		hql += " and db02.jh = '"+jh+"' and date(db02.rq) >= '"+sDate+"' and date(db02.rq) <= '"+eDate+"'";
		return sess.createQuery(hql).list();
	}

}
