package com.oil.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.oil.dao.Dba01Dao;
import com.oil.module.Dba01;
import com.oil.tools.CommonTools;
import com.oil.util.Constants;

@SuppressWarnings("unchecked")
public class Dba01DaoImpl implements Dba01Dao{

	@Override
	public List<Dba01> findPageInfoByOpt(Session sess, String jh, String sDate,
			String eDate, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from Dba01 as db01 where 1=1";
		if(!jh.equals("")){
			if(Constants.DATA_BASE_INFO.equals("oracle")){
				hql += " and db01.jh = '"+jh+"' and db01.rq >= to_date('"+sDate+"','yyyy-mm-dd') and db01.rq <= to_date('"+eDate+"','yyyy-mm-dd')";
			}else{
				hql += " and db01.jh = '"+jh+"' and date(db01.rq) >= '"+sDate+"' and date(db01.rq) <= '"+eDate+"'";
			}
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		} 
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, String jh, String sDate,
			String eDate) {
		// TODO Auto-generated method stub
		String hql = "select count(db01.jh) from Dba01 as db01 where 1=1";
		if(!jh.equals("")){
			if(Constants.DATA_BASE_INFO.equals("oracle")){
				hql += " and db01.jh = '"+jh+"' and db01.rq >= to_date('"+sDate+"','yyyy-mm-dd') and db01.rq <= to_date('"+eDate+"','yyyy-mm-dd')";
			}else{
				hql += " and db01.jh = '"+jh+"' and date(db01.rq) >= '"+sDate+"' and date(db01.rq) <= '"+eDate+"'";
			}
		}
		Object count_obj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(count_obj);
	}

	@Override
	public List<Dba01> findValidInfoByOpt(Session sess, String jh,
			String sDate, String eDate) {
		// TODO Auto-generated method stub
		String hql = " from Dba01 as db01 where db01.scsj = 24";
		if(!jh.equals("")){
			if(Constants.DATA_BASE_INFO.equals("oracle")){
				hql += " and db01.jh = '"+jh+"' and db01.rq >= to_date('"+sDate+"','yyyy-mm-dd') and db01.rq <= to_date('"+eDate+"','yyyy-mm-dd')";
			}else{
				hql += " and db01.jh = '"+jh+"' and date(db01.rq) >= '"+sDate+"' and date(db01.rq) <= '"+eDate+"'";
			}
		}
		return sess.createQuery(hql).list();
	}

}
