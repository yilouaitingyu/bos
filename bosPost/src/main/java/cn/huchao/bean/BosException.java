package cn.huchao.bean;

/**
 * @author huchao
 * @2017年9月4日
 * @description
 * 本项目的自定义异常
 */
public class BosException extends Exception {

	private static final long serialVersionUID = -5913410376885921968L;

	/**
	 * @param message
	 */
	public BosException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BosException(String message, Throwable cause) {
		super(message, cause);
	}

}
