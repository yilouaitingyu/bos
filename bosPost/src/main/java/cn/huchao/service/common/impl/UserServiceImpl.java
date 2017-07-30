package cn.huchao.service.common.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huchao.dao.impl.BaseDaoImpl;
import cn.huchao.service.common.IUserService;

/**
 * 2017年7月17日 huchao
 * 
 * @description
 *
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{
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
