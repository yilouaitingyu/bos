/*
 * Powered By [rapid-framework]
 * Web Site: http://blog.csdn.net/houfeng30920/article/details/52730893
 * Csdn Code: 
 * 2017-10-09
 */

package cn.huchao.service.post;

import java.util.Map;

import cn.huchao.bean.BosException;
import cn.huchao.bean.OutParams;

/**
 * @author huchao
 * @2017-10-09
 * @description
 *  定区的业务接口
 */
public interface IDecidedZoneService {
	/**
	 * @description
	 *	增加定区
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-10-09
	 * @author huchao
	 */
	public OutParams addDecidedZone(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description
	 *	根据条件查询定区
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-10-09
	 * @author huchao
	 */
	public OutParams queryDecidedZoneByCond(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description
	 *	根据定区Id修改定区
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-10-09
	 * @author huchao
	 */
	public OutParams updateByDecidedZoneId(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description 根据Id删除定区
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-10-09
	 * @author huchao
	 */
	public OutParams deleteById(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description
	 *	增加定区分区关系
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-10-09
	 * @author huchao
	 */
	public OutParams addSubareaDecidedRela(Map<String, Object> beanIn) throws BosException;
	
	/**
	 * @description
	 *	根据定区分区关系Id修改定区分区关系
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-10-09
	 * @author huchao
	 */
	public OutParams updateBySubareaDecidedRelaId(Map<String, Object> beanIn) throws BosException;
	/**
	 * @description 根据Id删除定区分区关系
	 * @param beanIn
	 * @return
	 * @throws BosException
	 * @2017-10-09
	 * @author huchao
	 */
	public OutParams deleteRelaById(Map<String, Object> beanIn) throws BosException;
}
