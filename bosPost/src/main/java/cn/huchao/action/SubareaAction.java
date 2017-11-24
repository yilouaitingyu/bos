package cn.huchao.action;

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
import cn.huchao.service.post.ISubareaService;
import cn.huchao.util.JsonUtil;
import cn.huchao.util.ListUtil;
import cn.huchao.util.MapUtil;

/**
 * @author huchao
 * @version 1.0
 * @description 地区的增删改查
 *  
 */
@Controller
@RequestMapping("/subarea/")
public class SubareaAction {
	@Resource
	private ISubareaService subareaService;
	Logger logger = LoggerFactory.getLogger(SubareaAction.class);

	public ISubareaService getSubareaService() {
		return subareaService;
	}

	public void setSubareaService(ISubareaService subareaService) {
		this.subareaService = subareaService;
	}

	/**
	 * @description 增加地区
	 * @return
	 * @2017-09-29
	 * @author huchao
	 */
	@RequestMapping("add.action")
	@ResponseBody
	public String add(HttpServletResponse response, HttpServletRequest request) {
		// String subareaId =
		// StringUtil.clearBlank(request.getAttribute("subareaId"));
		// 获取参数值，
		Map<String, Object> params = ListUtil.getMapFromReq(request.getParameterMap());
		OutParams outParams = new OutParams();
		try {
			outParams = subareaService.addSubarea(params);
		} catch (Exception e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("添加地区错误");
			logger.error("添加地区错误", e);
		}
		return JsonUtil.convertObject2Json(outParams);
	}

	/**
	 * @description 查询所有的地区信息
	 * @param response
	 * @param request
	 * @return
	 * @2017-09-29
	 * @author huchao
	 */
	@RequestMapping("queryAll.action")
	@ResponseBody
	public String queryAll(HttpServletResponse response, HttpServletRequest request) {
		// 获取参数值，
		Map<String, Object> params = MapUtil.getMapFromReq(request.getParameterMap());
		// params.put("pageFlag", "true");
		OutParams outParams = new OutParams();
		try {
			outParams = subareaService.querySubareaByCond(params);
		} catch (BosException e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("查询地区错误");
			logger.error("queryAll查询地区信息失败", e);
		}
		return JsonUtil.convertObject2Json(outParams);
	}
	/**
	 * @description
	 *	修改地区数据
	 * @param response
	 * @param request
	 * @return
	 * @2017-09-29
	 * @author huchao
	 */
	@RequestMapping("updateSubarea.action")
	@ResponseBody
	public String update(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> params = MapUtil.getMapFromReq(request.getParameterMap());
		OutParams outParams = new OutParams();
		try {
			outParams=subareaService.updateBySubareaId(params);
		} catch (Exception e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("修改地区错误");
			logger.error("修改地区信息失败", e);
		}
		return JsonUtil.convertObject2Json(outParams);
	}
	/**
	 * @description
	 *	删除地区数据
	 * @param response
	 * @param request
	 * @return
	 *@2017-09-29
	 *@author huchao
	 */
	@RequestMapping("deleteSubarea.action")
	@ResponseBody
	public String delete(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> params = MapUtil.getMapFromReq(request.getParameterMap());
		OutParams outParams = new OutParams();
		try {
			outParams=subareaService.deleteById(params);
		} catch (Exception e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("删除地区错误");
			logger.error("删除地区信息失败", e);
		}
		return JsonUtil.convertObject2Json(outParams);
	}
}