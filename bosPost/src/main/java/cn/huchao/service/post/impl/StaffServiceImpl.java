package cn.huchao.service.post.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huchao.bean.BosException;
import cn.huchao.bean.OutParams;
import cn.huchao.constants.ComConstants;
import cn.huchao.dao.impl.BaseDaoImpl;
import cn.huchao.service.post.IStaffService;
import cn.huchao.util.MapUtil;
import cn.huchao.util.PageUtil;

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
		String[] strArr = new String[] { "staffId", "staffName", "staffPhone", "station", "haspda", "standard" };
		Map<String, Object> params = MapUtil.getMapFromMapByArray(beanIn, strArr);
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

	/**
	 * 条件查询取派员，可选分页展示，或者不分页展示（无pageFlag或者pageFlag为false）
	 * 
	 * @throws BosException
	 */
	public OutParams queryStaffByCond(Map<String, Object> beanIn) {
		// 重新封装参数，需要哪些参数显示出来
		String[] strArr = new String[] { "staffId", "staffName", "staffPhone", "station", "haspda", "standard" };
		Map<String, Object> params = MapUtil.getMapFromMapByArray(beanIn, strArr);
		// 添加分页信息
		Map<String, Object> pageCond = PageUtil.getPageCondByArr(beanIn,
				new String[] { "pageFlag", "page", "pageSize" });
		params.putAll(pageCond);
		//
		OutParams outParams = new OutParams();
		List<Map<String, Object>> staffMapList = getBaseDao().queryForList("StaffMapper.queryStaffByCond", params,
				Map.class);
		// 如果查询失败，会报异常，所以返回码不会是成功
		outParams.setReturnCode(ComConstants.SUCCESS);
		outParams.setBean(staffMapList);
		return outParams;
	}
	/**
	 * 根据StaffId修改取派员的数据
	 */
	public OutParams updateByStaffId(Map<String, Object> beanIn) throws BosException {
		// 重新封装参数，需要哪些参数显示出来
		String[] strArr = new String[] { "staffId", "staffName", "staffPhone", "station", "haspda", "standard" };
		Map<String, Object> params = MapUtil.getMapFromMapByArray(beanIn, strArr);
		//
		OutParams outParams = new OutParams();
		int num = getBaseDao().update("StaffMapper.updateByStaffId", params);
		if (num<1) {
			throw new BosException("修改数据失败");
		}
		// 如果修改失败，会报异常，所以返回码不会是成功
		outParams.setReturnCode(ComConstants.SUCCESS);
		return outParams;

	}

}
