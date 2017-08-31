package cn.huchao.service.post.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huchao.bean.OutParams;
import cn.huchao.constants.ComConstants;
import cn.huchao.dao.impl.BaseDaoImpl;
import cn.huchao.service.post.IStaffService;

/**
 * @author huchao
 * @2017年8月31日
 * @description 取派员的Service
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
	@Resource
	private BaseDaoImpl baseDao;

	public BaseDaoImpl getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDaoImpl baseDao) {
		this.baseDao = baseDao;
	}
	/**
	 * 添加取派员
	 */
	public OutParams addStaff(Map<String, Object> beanIn) {
		// 重新封装参数，需要哪些参数显示出来
		Map<String, Object> params = new HashMap<>();
		params.put("staffId", beanIn.get("staffId"));
		params.put("staffName", beanIn.get("staffName"));
		params.put("staffPhone", beanIn.get("staffPhone"));
		params.put("station", beanIn.get("station"));
		params.put("haspda", beanIn.get("haspda"));
		params.put("standard", beanIn.get("standard"));
		//
		OutParams outParams = new OutParams();
		int num = getBaseDao().insert("StaffMapper.insertStaff", params);
		if (num != 1) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("添加失败");
		} else {
			outParams.setReturnCode(ComConstants.SUCCESS);
		}
		return outParams;
	}
}
