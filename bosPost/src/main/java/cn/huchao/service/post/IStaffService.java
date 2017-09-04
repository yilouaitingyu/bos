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
	public OutParams addStaff(Map<String, Object> beanIn) throws BosException;

	public OutParams queryStaffByCond(Map<String, Object> beanIn) throws BosException;
}
