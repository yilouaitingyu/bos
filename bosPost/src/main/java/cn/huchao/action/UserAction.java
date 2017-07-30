package cn.huchao.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huchao.util.StringUtil;
import cn.huchao.util.VerifyCodeUtils;

/**
 * 2017年7月24日 huchao
 * 
 * @description 用户相关的控制层
 */
@Controller
@RequestMapping("/user/")
public class UserAction extends BaseMethod{
	Logger logger = LoggerFactory.getLogger(UserAction.class);

	/**
	 * 2017年7月24日 huchao
	 * 
	 * @description 用户登录
	 * @return
	 */
	@RequestMapping("login.action")
	@ResponseBody
	public String login(HttpSession session,HttpServletResponse response,HttpServletRequest request) {
		String str = request.getParameter("aaa");
		response.setContentType("application/json;charset=UTF-8");//防止数据传递乱码
		System.out.println(str);
		 String  verCode= StringUtil.clearBlank(session.getAttribute("verCode"));
		 if (verCode.isEmpty()) {
			return "验证码不为空,hhhhhh";
		}else{
			return "验证码为空，aaaaaa";
		}
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
