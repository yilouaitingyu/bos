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
import cn.huchao.service.post.IStaffService;
import cn.huchao.util.JsonUtil;
import cn.huchao.util.ListUtil;
import cn.huchao.util.MapUtil;

/**
 * @author huchao
 * @2017年8月31日
 * @description 取派员的增删改查Action
 */
@Controller
@RequestMapping("/staff/")
public class StaffAction {
	@Resource
	private IStaffService staffService;
	Logger logger = LoggerFactory.getLogger(StaffAction.class);

	public IStaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(IStaffService staffService) {
		this.staffService = staffService;
	}

	/**
	 * @description 增加取派员
	 * @return
	 * @2017年8月31日
	 * @author huchao
	 */
	@RequestMapping("add.action")
	@ResponseBody
	public String add(HttpServletResponse response, HttpServletRequest request) {
		// String staffId =
		// StringUtil.clearBlank(request.getAttribute("staffId"));
		// 获取参数值，
		Map<String, Object> params = ListUtil.getMapFromReq(request.getParameterMap());
		OutParams outParams = new OutParams();
		try {
			outParams = staffService.addStaff(params);
		} catch (BosException e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("添加取派员错误");
			logger.error("添加取派员错误", e);
		}
		return JsonUtil.convertObject2Json(outParams);
	}

	/**
	 * @description 查询所有的取派员信息
	 * @param response
	 * @param request
	 * @return
	 * @2017年9月4日
	 * @author huchao
	 */
	@RequestMapping("queryAll.action")
	@ResponseBody
	public String queryAll(HttpServletResponse response, HttpServletRequest request) {
		// 获取参数值，
		Map<String, Object> params = MapUtil.getMapFromReq(request.getParameterMap());
		//params.put("pageFlag", "true");
		OutParams outParams = new OutParams();
		try {
			outParams = staffService.queryStaffByCond(params);
		} catch (BosException e) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("查询取派员错误");
			logger.error("queryAll查询取派员信息失败", e);
		}
		return JsonUtil.convertObject2Json(outParams);
	}

}
