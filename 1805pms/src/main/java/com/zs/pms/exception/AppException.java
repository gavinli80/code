package com.zs.pms.exception;

/**
 * 业务异常
 * 
 * @author gavin
 *
 */
public class AppException extends Exception {
	// 异常码
	private int errcode;
	// 异常信息
	private String errMsg;

	/**
	 * 构造函数初始化
	 * 
	 * @param errcode
	 * @param errmsg
	 */
	public AppException(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errMsg = errmsg;

	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
