package org.tiger.framework.common.exception;

public enum Code {
	//================== 通用系统错误码 ==================
	ERROR("9999", "system error"), 
	
	SUCCESS("0", "Success"),
	
	//================== 业务错误码【400】开头 ==================
	ENCRYPT_ERROR("4008", "ENCRYPT_ERROR"),
		
	//================== 业务错误码【401】开头 ==================
	DECRYPT_ERROR("4010", "DECRYPT_ERROR"),
	
	//================== 业务错误码【330】开头 ==================
	TIMEOUT("3138","controll time out"),
	ILLEGAL_SIGN_ERROR("3301","Sign illegal."),
	VALUE_ILLEGAL("3004", "{0} value is illegal."),	
	
	APPLIANCE_NOT_FOUND("3127","The appliance does not exist."),
	APPLIANCE_OFFLINE("3123","The appliance is off line."),
	
	
	
	//================== 微信错误码  ==================
	
	UNBIND_ERROR("1002","wechat unbind error"),
    GET_OPENID_ERROR("1003","get openId error"),
	
	//================== 发送指令到device 错误.  ==================
    COMMAND_EXEC_FAIL("2001","command exec fail.");
    
	
	private String code;
	private String msg;

	Code(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	
	/**
	 * 通过错误码获取code对象
	 * @param errorCode
	 * @return
	 */
    public static Code getCode(String errorCode) {
        for (Code c : Code.values()) {
            if (errorCode.equals(c.getCode())) {
                return c;
            }
        }
        
        return ERROR;
    }
}