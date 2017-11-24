package cn.huchao.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huchao.bean.BosException;
import cn.huchao.bean.OutParams;
import cn.huchao.constants.ComConstants;
import cn.huchao.service.post.IDecidedZoneService;
import cn.huchao.util.JsonUtil;
import cn.huchao.util.ListUtil;
import cn.huchao.util.MapUtil;
import cn.huchao.util.StringUtil;

/**
 * @author huchao
 * @version 1.0
 * @description 定区的增删改查
 *  
 */
@Controller
@RequestMapping("/decidedZone/")
public class DecidedZoneAction {
	@Resource
	private IDecidedZoneService decidedZoneService;
	Logger logger = LoggerFactory.getLogger(DecidedZoneAction.class);

	public IDecidedZoneService getDecidedZoneService() {
		return decidedZoneService;
	}

	public void setDecidedZoneService(IDecidedZoneService decidedZoneService) {
		this.decidedZoneService = decidedZoneService;
	}

	/**
	 * @description 增加定区
	 * @return
	 * @2017-10-09
	 * @author huchao
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("add.action")
	@ResponseBody
	public String add(HttpServletResponse response, HttpServletRequest request) {
		// String decidedZoneId =
		// StringUtil.clearBlank(request.getAttribute("decidedZoneId"));
		// 获取参数值，
		Map<String, Object> params = ListUtil.getMapFromReq(request.getParameterMap());
		String subareaIds = StringUtil.clearBlank(params.get("subareaIds"));
		List<String> subareaIdsList = JsonUtil.convertJson2Object(subareaIds, List.class);
		Map<String, Object> map =new HashMap<>();
		
		OutParams outParams = new OutParams();
		try {
			outParams = decidedZoneService.addDecidedZone(params);
		} catch (Exception e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("添加定区错误");
			logger.error("添加定区错误", e);
		}
		//添加定区
		for (String subareaId : subareaIdsList) {
			map.put("subareaId", subareaId);
			map.put("decidedZoneId", params.get("decidedZoneId"));
			try {
				decidedZoneService.addSubareaDecidedRela(map);
			} catch (BosException e) {
				outParams.setReturnCode(ComConstants.FAIL);
				outParams.setReturnMsg("添加定区错误");
				logger.error("添加定区分区关系错误", e);
				return JsonUtil.convertObject2Json(outParams);
			}
		}
		return JsonUtil.convertObject2Json(outParams);
	}

	/**
	 * @description 查询所有的定区信息
	 * @param response
	 * @param request
	 * @return
	 * @2017-10-09
	 * @author huchao
	 */
	@RequestMapping("queryAll.action")
	@ResponseBody
	public String queryAll(HttpServletResponse response, HttpServletRequest request) {
		// 获取参数值，
		Map<String, Object> params = MapUtil.getMapFromReq(request.getParameterMap());
		// params.put("pageFlag", "true");
		String subareaIds = StringUtil.clearBlank(params.get("subareaIds"));
		List<String> subareaIdList = JsonUtil.convertJson2Object(subareaIds, List.class);
		params.put("subareaIdList", subareaIdList);
		OutParams outParams = new OutParams();
		try {
			outParams = decidedZoneService.queryDecidedZoneByCond(params);
		} catch (BosException e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("查询定区错误");
			logger.error("queryAll查询定区信息失败", e);
		}
		return JsonUtil.convertObject2Json(outParams);
	}
	/**
	 * @description
	 *	修改定区数据
	 * @param response
	 * @param request
	 * @return
	 * @2017-10-09
	 * @author huchao
	 */
	@RequestMapping("updateDecidedZone.action")
	@ResponseBody
	public String update(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> params = MapUtil.getMapFromReq(request.getParameterMap());
		OutParams outParams = new OutParams();
		try {
			outParams=decidedZoneService.updateByDecidedZoneId(params);
		} catch (Exception e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("修改定区错误");
			logger.error("修改定区信息失败", e);
		}
		return JsonUtil.convertObject2Json(outParams);
	}
	/**
	 * @description
	 *	删除定区数据
	 * @param response
	 * @param request
	 * @return
	 *@2017-10-09
	 *@author huchao
	 */
	@RequestMapping("deleteDecidedZone.action")
	@ResponseBody
	public String delete(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> params = MapUtil.getMapFromReq(request.getParameterMap());
		OutParams outParams = new OutParams();
		try {
			outParams=decidedZoneService.deleteById(params);
		} catch (Exception e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("删除定区错误");
			logger.error("删除定区信息失败", e);
		}
		return JsonUtil.convertObject2Json(outParams);
	}
}