package org.tiger.framework.common;

/**
 * 常量
 * 
 * @author lihj17
 *
 */
public class Constant {

	// 事件类型：绑定设备
	public static String DEVICE_EVENT_BIND = "bind";

	// 事件类型：解除绑定
	public static String DEVICE_EVENT_UNBIND = "unbind";

	// 事件类型：订阅设备状态
	public static String DEVICE_EVENT_SUBSCRIBE_STATUS = "subscribe_status";

	// 事件类型：用户订阅
	public static String EVENT_SUBSCRIBE = "subscribe";

	// 事件类型：文本消息
	public static String MSG_TYPE_TEXT = "text";

	// 微信设备授权返回错误码：0代表授权成功
	public static String DEVICE_AUTHORIZE_SUCCESS = "0";

	// 微信设备授权返回错误码：100002代表设备已存在
	public static String DEVICE_AUTHORIZE_EXISTS = "100002";

	// 微信设备授权：已授权状态
	public static int DEVICE_AUTHORIZE_AUTH_STATUS = 1;

	// 微信设备授权：未授权状态
	public static int DEVICE_AUTHORIZE_NOT_AUTH_STATUS = 0;

	// 指令执行返回结果
	public static final String COMMAND_EXEC_RESULT = "cmdExeResult";

	/**
	 * 定时获取微信access_token 开
	 */
	public static final String TASK_SWITCH_ON = "switchOn";

	/**
	 * 定时获取微信access_token 关
	 */
	public static final String TASK_SWITCH_OFF = "switchOff";

	/**
	 * 设备状态上报类型
	 */
	public static final String REPORT_DEVICE_STATUS = "0401";

	/**
	 * 设备固定参数上报类型
	 */
	public static final String REPORT_DEVICE_FIXEDPARAMS = "0402";
	
	/**
	 * WebSocket AES 加密相量
	 */
	public static final String WEBSOCKET_AES_VI = "midea_wechat_vi_";
	

}
