
package cn.huchao.service.post.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huchao.bean.BosException;
import cn.huchao.bean.OutParams;
import cn.huchao.constants.ComConstants;
import cn.huchao.dao.impl.BaseDaoImpl;
import cn.huchao.util.MapUtil;
import cn.huchao.util.PageUtil;

/**
 * @author huchao
 * @2017-10-09
 * @description 定区分区关系的Service
 */
@Service
@Transactional
public class SubareaDecidedRelaServiceImpl  {
	@Resource
	private BaseDaoImpl baseDao;

	public BaseDaoImpl getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDaoImpl baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 添加定区分区关系
	 */
	public OutParams addSubareaDecidedRela(Map<String, Object> beanIn) {
		// 重新封装参数，需要哪些参数显示出来
		String[] strArr = new String[] {"decidedZoneId","subareaId" };
		Map<String, Object> params = MapUtil.getMapFromMapByArray(beanIn, strArr);
		//
		OutParams outParams = new OutParams();
		int num = 0 ;
		if (MapUtils.isNotEmpty(params)) {
			num= getBaseDao().insert("SubareaDecidedRelaMapper.insertSubareaDecidedRela", params);
		}
		if (num != 1) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("添加失败");
		} else {
			outParams.setReturnCode(ComConstants.SUCCESS);
		}
		return outParams;
	}

	/**
	 * 条件查询定区分区关系，可选分页展示，或者不分页展示（无pageFlag或者pageFlag为false）
	 * 
	 * @throws BosException
	 */
	public OutParams querySubareaDecidedRelaByCond(Map<String, Object> beanIn) {
		// 重新封装参数，需要哪些参数显示出来
		String[] strArr = new String[] {"decidedZoneId","subareaId"};
		Map<String, Object> params = MapUtil.getMapFromMapByArray(beanIn, strArr);
		// 添加分页信息
		Map<String, Object> pageCond = PageUtil.getPageCondByArr(beanIn,
				new String[] { "pageFlag", "page", "pageSize" });
		params.putAll(pageCond);
		//
		OutParams outParams = new OutParams();
		List<Map<String, Object>> subareaDecidedRelaMapList = getBaseDao().queryForList("SubareaDecidedRelaMapper.querySubareaDecidedRelaByCond", params,
				Map.class);
		// 如果查询失败，会报异常，所以返回码不会是成功
		outParams.setReturnCode(ComConstants.SUCCESS);
		outParams.setBean(subareaDecidedRelaMapList);
		return outParams;
	}
	/**
	 * 根据SubareaDecidedRelaId修改定区分区关系的数据
	 */
	public OutParams updateBySubareaDecidedRelaId(Map<String, Object> beanIn) throws BosException {
		// 重新封装参数，需要哪些参数显示出来
		String[] strArr = new String[] {"id","decidedZoneId","subareaId"};
		Map<String, Object> params = MapUtil.getMapFromMapByArray(beanIn, strArr);
		//
		OutParams outParams = new OutParams();
		int num = getBaseDao().update("SubareaDecidedRelaMapper.updateBySubareaDecidedRelaId", params);
		if (num<1) {
			throw new BosException("修改数据失败");
		}
		// 如果修改失败，会报异常，所以返回码不会是成功
		outParams.setReturnCode(ComConstants.SUCCESS);
		return outParams;
	}
	/**
	 * 根据Id删除定区分区关系
	 */
	public OutParams deleteById(Map<String, Object> beanIn) throws BosException {
		// 重新封装参数，需要哪些参数显示出来
		String[] strArr = new String[] {"id","decidedZoneId","subareaId"};
		Map<String, Object> params = MapUtil.getMapFromMapByArray(beanIn, strArr);
		//
		OutParams outParams = new OutParams();
		int num = 0;
		if (MapUtils.isNotEmpty(params)) {
			num = getBaseDao().delete("SubareaDecidedRelaMapper.deleteById", params);
		}
		if (num < 1) {
			throw new BosException("删除数据失败");
		}
		// 如果删除失败，会报异常，所以返回码不会是成功
		outParams.setReturnCode(ComConstants.SUCCESS);
		return outParams;
	}
}
