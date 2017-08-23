package cn.huchao.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huchao.bean.OutParams;
import cn.huchao.constants.ComConstants;
import cn.huchao.service.common.IUserService;
import cn.huchao.service.common.impl.UserServiceImpl;
import cn.huchao.util.JsonUtil;
import cn.huchao.util.StringUtil;
import cn.huchao.util.VerifyCodeUtils;

/**
 * 2017年7月24日 huchao
 * 
 * @description 用户相关的控制层
 */
@Controller
@RequestMapping("/user/")
public class UserAction extends BaseMethod {
	Logger logger = LoggerFactory.getLogger(UserAction.class);
	@Resource
	public IUserService UserService;

	public IUserService getUserService() {
		return UserService;
	}

	public void setUserService(IUserService userService) {
		UserService = userService;
	}

	/**
	 * 2017年7月24日 huchao
	 * 
	 * @description 用户登录
	 * @return
	 */
	@RequestMapping("login.action")
	@ResponseBody
	public String login(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("application/json;charset=UTF-8");// 防止数据传递乱码
		OutParams outParams = new OutParams();
		//先校验验证码是否正确
		String verCode = StringUtil.clearBlank(session.getAttribute("verCode"));
		String checkcode = StringUtil.clearBlank(request.getParameter("checkcode"));
		if (!verCode.equalsIgnoreCase(checkcode)) {
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("验证码不正确");
			return JsonUtil.convertObject2Json(outParams);
		} 
		String userName = StringUtil.clearBlank(request.getParameter("userName"));
		String password = StringUtil.clearBlank(request.getParameter("password"));
		Map<String, Object> userMap = UserService.getUserByUserName(userName);
		if (MapUtils.isNotEmpty(userMap)) {
			if (!password.equals(userMap.get("password"))) {
				//密码不正确
				outParams.setReturnCode(ComConstants.FAIL);
				outParams.setReturnMsg("密码不正确");
				return JsonUtil.convertObject2Json(outParams);
			}
		}else{
			outParams.setReturnCode(ComConstants.FAIL);
			outParams.setReturnMsg("用户名不正确");
			return JsonUtil.convertObject2Json(outParams);
			//用户名不正确
		}
		outParams.setReturnCode(ComConstants.SUCCESS);
		return JsonUtil.convertObject2Json(outParams);
	}

	/**
	 * 2017年7月24日 huchao
	 * 
	 * @description 获取验证码
	 */
	@RequestMapping("authImage.action")
	public void getAuthImage(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = request.getSession(true);
		// 删除以前的
		session.removeAttribute("verCode");
		session.setAttribute("verCode", verifyCode.toLowerCase());
		// 生成图片
		int w = 75, h = 30;
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			VerifyCodeUtils.outputImage(w, h, sos, verifyCode);
		} catch (IOException e) {
			logger.error("验证码输出错误", e);
		} finally {
			if (sos != null) {
				try {
					sos.close();
				} catch (IOException e) {
					logger.error("关流错误", e);
				}
			}
		}

	}

}
