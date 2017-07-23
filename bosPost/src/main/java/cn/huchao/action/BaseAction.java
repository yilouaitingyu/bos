package cn.huchao.action;

import java.net.URI;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.huchao.service.base.BaseServiceImpl;

/**
 * 2017年7月17日 huchao
 * 
 * @description Action基类
 */
@Controller
@RequestMapping("/base/")
public class BaseAction {
	@Resource
	private BaseServiceImpl baseService;

	public BaseServiceImpl getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseServiceImpl baseService) {
		this.baseService = baseService;
	}



	/**
	 * 2017年7月22日  huchao
	 *  @description 获取当前登录的用户
	 * @return
	 */
	@RequestMapping("login.action")
	public String getLoginUser(){
		String service = baseService.getService();
		System.out.println(service);
		
		return "/html/login/login.html";
	}
	@RequestMapping("page_*_*.action")
	public String forward(HttpServletRequest request){
		String uri = request.getRequestURI();
		String[] split = uri.split("/");
		String string = split[split.length-1];
		String substring = string.substring(0, string.length()-7);
		String[] split2 = substring.split("_");
		
		System.out.println(uri);
		String str1=split2[1];
		String str2=split2[2];
		
		String url="/WEB-INF/pages/"+str1+"/"+str2+".jsp";
		return "forward:"+url;
	}

}
