package com.oil.dao;

import org.hibernate.classic.Session;

import com.oil.module.SzInfo;

public interface SzInfoDao {
	
	SzInfo get(Session sess,Integer id);
}
