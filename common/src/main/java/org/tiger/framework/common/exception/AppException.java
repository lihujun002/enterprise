package org.tiger.framework.common.exception;

import java.text.MessageFormat;

/**
 * 
 * @author zoulei
 *
 */
public class AppException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2166347216219675858L;
	
	String msg;
	Code errorCode;

	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	public Code getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Code errorCode) {
		this.errorCode = errorCode;
	}

	public AppException() {
	}

	public AppException(String message,Code errorCode) {
		super(message);
		this.msg = message;
		this.errorCode = errorCode;
	}

	public AppException(Code errorCode) {
		super(errorCode.getMsg());
		this.msg = errorCode.getMsg();
		this.errorCode = errorCode;
	}
	
	public AppException(Code errorCode, Object ... arguments) {
        super(MessageFormat.format(errorCode.getMsg(), arguments));
        this.msg = MessageFormat.format(errorCode.getMsg(), arguments);
        this.errorCode = errorCode;
    }
	
	public AppException(String message) {
		msg = message;
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
	public AppException(Code errorCode, Throwable cause, Object ... arguments) {
        super(MessageFormat.format(errorCode.getMsg(), arguments),cause);
        this.msg = MessageFormat.format(errorCode.getMsg(), arguments);
        this.errorCode = errorCode;
    }

}
