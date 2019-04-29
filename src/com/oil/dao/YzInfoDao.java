package com.oil.dao;

import org.hibernate.classic.Session;

import com.oil.module.YzInfo;

public interface YzInfoDao {

	YzInfo get(Session sess,Integer id);
}
