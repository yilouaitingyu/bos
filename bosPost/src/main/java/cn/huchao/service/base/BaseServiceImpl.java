package cn.huchao.service.base;

import cn.huchao.dao.impl.BaseDaoImpl;

/**
 * 2017年7月17日 huchao
 * 
 * @description
 *
 */
public class BaseServiceImpl {
	private BaseDaoImpl baseDao;

	public BaseDaoImpl getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDaoImpl baseDao) {
		this.baseDao = baseDao;
	}
}
