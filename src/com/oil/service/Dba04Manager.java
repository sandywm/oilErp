package com.oil.service;

import com.oil.exception.WEBException;
import com.oil.module.Dba04;

public interface Dba04Manager {

	/**
	 * 根据井号、年月获取动液面信息
	 *@param jh 井号
	 * @param ny 年月（201903）
	 * @returns
	 * @throws WEBException
	 */
	Dba04 getEntityByOpt(String jh,String ny) throws WEBException;
}
