package cn.huchao.service.post;

import java.util.Map;

import cn.huchao.bean.BosException;
import cn.huchao.bean.OutParams;

/**
 * @author huchao
 * @2017年8月31日
 * @description
 * 
 */
public interface IStaffService {
	/**
	 * @description
	 *	增加取派员
	 * @param beanIn
	 * @return
	 * @throws BosException
	 *@2017年9月27日
	 *@author huchao
	 */
	public OutParams addStaff(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description
	 *	根据条件查询取派员
	 * @param beanIn
	 * @return
	 * @throws BosException
	 *@2017年9月27日
	 *@author huchao
	 */
	public OutParams queryStaffByCond(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description
	 *	根据取派员Id修改取派员
	 * @param beanIn
	 * @return
	 * @throws BosException
	 *@2017年9月27日
	 *@author huchao
	 */
	public OutParams updateByStaffId(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description 根据Id删除取派员
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-09-28
	 * @author huchao
	 */
	public OutParams deleteById(Map<String, Object> beanIn) throws BosException;
}
