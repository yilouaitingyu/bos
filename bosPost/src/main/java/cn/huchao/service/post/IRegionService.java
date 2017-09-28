/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code: 
 * 2017-09-28
 */

package cn.huchao.service.post;

import java.util.Map;

import cn.huchao.bean.BosException;
import cn.huchao.bean.OutParams;

/**
 * @author huchao
 * @2017-09-28
 * @description
 *  地区的业务接口
 */
public interface IRegionService {
	/**
	 * @description
	 *	增加地区
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-09-28
	 * @author huchao
	 */
	public OutParams addRegion(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description
	 *	根据条件查询地区
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-09-28
	 * @author huchao
	 */
	public OutParams queryRegionByCond(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description
	 *	根据地区Id修改地区
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-09-28
	 * @author huchao
	 */
	public OutParams updateByRegionId(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description 根据Id删除地区
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-09-28
	 * @author huchao
	 */
	public OutParams deleteById(Map<String, Object> beanIn) throws BosException;
}
