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

import cn.huchao.bean.OutParams;
import cn.huchao.service.post.IStaffService;
import cn.huchao.util.JsonUtil;
import cn.huchao.util.ListUtil;

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
		OutParams outParams = staffService.addStaff(params);
		return JsonUtil.convertObject2Json(outParams);
	}
}
