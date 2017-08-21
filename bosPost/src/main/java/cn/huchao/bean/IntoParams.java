package cn.huchao.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huchao
 *	@2017年8月21日
 *	@description
 *	标准输入参数bean
 */
public class IntoParams extends Entity{

	
	private static final long serialVersionUID = 3362859335081925257L;
	private Map<String, Object> params =new HashMap<String, Object>();
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	

}

