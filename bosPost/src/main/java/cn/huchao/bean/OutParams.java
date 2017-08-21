package cn.huchao.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huchao
 * @2017年8月21日
 * @description 标准输出参数bean
 */
public class OutParams extends Entity {
	private static final long serialVersionUID = 2636615980627016752L;
	private String returnCode;
	private String returnMsg;
	private Map<String, Object> params = new HashMap<String, Object>();
	private List<Map<String, Object>> bean = new ArrayList<Map<String, Object>>();

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public List<Map<String, Object>> getBean() {
		return bean;
	}

	public void setBean(List<Map<String, Object>> bean) {
		this.bean = bean;
	}

}
