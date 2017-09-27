package com.yd.ydbi.common.response;

import java.io.Serializable;

/**
 * @author kangyuanjia
 * @createTime 2017-03-08 08:50:15
 * @describe
 */
public class CustomResponseMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	public int errorCode = 0;
	public String msg;
	public Object data;

	public CustomResponseMsg() {
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	@Override
    public String toString() {
        return String.valueOf(errorCode);
    }
}
