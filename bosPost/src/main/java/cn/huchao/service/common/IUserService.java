package cn.huchao.service.common;

import java.util.Map;

import cn.huchao.bean.OutParams;

/**
 * 2017年7月24日  huchao
 *	@description
 *
 */
public interface IUserService {
	/**
	 * @description
	 *	根据条件查询用户信息
	 * @param bean
	 * @return
	 *@2017年8月21日
	 *@author huchao
	 */
	public OutParams getUserInfoByCond(Map<String, Object> bean);
	public Map<String, Object> getUserByUserName(String userName);
}
