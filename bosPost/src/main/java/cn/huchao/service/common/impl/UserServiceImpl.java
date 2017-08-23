package cn.huchao.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huchao.bean.OutParams;
import cn.huchao.constants.ComConstants;
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
public class UserServiceImpl implements IUserService {
	@Resource
	private BaseDaoImpl baseDao;

	public BaseDaoImpl getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDaoImpl baseDao) {
		this.baseDao = baseDao;
	}

	public String getService() {
		String test = baseDao.test();
		System.out.println(test);
		return "Service";
	}

	/**
	 * 根据条件查询用户信息
	 */
	public OutParams getUserInfoByCond(Map<String, Object> bean) {
		// 重新封装参数，需要哪些参数显示出来
		Map<String, Object> params = new HashMap<>();
		params.put("username", bean.get("userName"));
		//
		OutParams outParams = new OutParams();
		List<Map<String, Object>> userMapList = getBaseDao().queryForList("", params, Map.class);
		if (CollectionUtils.isNotEmpty(userMapList)) {
			outParams.setReturnCode(ComConstants.SUCCESS);
			outParams.setBean(userMapList);
		} else {
			outParams.setReturnCode(ComConstants.FAIL);
		}
		return outParams;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserByUserName(String userName) {
		Map<String, Object> params = new HashMap<>();
		params.put("userName", userName);
		Map<String, Object> userMap = (Map<String, Object>) getBaseDao()
				.queryForObject("UserMapper.getUserInfoByUserName", params);
		return userMap;
	}
}
