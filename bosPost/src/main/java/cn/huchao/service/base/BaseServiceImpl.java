package cn.huchao.service.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huchao.dao.impl.BaseDaoImpl;

/**
 * 2017年7月17日 huchao
 * 
 * @description
 *
 */
@Service
@Transactional
public class BaseServiceImpl {
	@Resource
	private BaseDaoImpl baseDao;

	public BaseDaoImpl getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDaoImpl baseDao) {
		this.baseDao = baseDao;
	}
	public String getService(){
		String test = baseDao.test();
		System.out.println(test);
		return "Service";
	}
}
