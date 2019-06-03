package com.oil.dao;

import org.hibernate.Session;

import com.oil.module.Dba04;

public interface Dba04Dao {

	/**
	 * 根据井号、年月获取动液面信息
	 * @param sess
	 * @param jh 井号
	 * @param ny 年月（201903）
	 * @return
	 */
	Dba04 getEntityByOpt(Session sess,String jh,String ny);
}
