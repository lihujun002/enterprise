package org.tiger.framework.common.cache;

/**
 * 
 * @author lihj17
 *        
 */
public class CacheConstant
{
    
    /**
     * key_loginAccount形式 缓存七天 value是user对象
     */
    public static final String CACHE_USER_ACCOUNT = "CACHE_USER_ACCOUNT_";
    
    /**
     * 缓存两小时 key_sessionID value是session对象
     */
    public static final String CACHE_USER_SESSION = "CACHE_USER_SESSION_";
    
    /**
     * 缓存两小时 key_loginAccount_appId value是sessionId
     */
    public static final String CACHE_ACCOUNT_APPID_SESSIONID = "CACHE_ACCOUNT_APPID_SESSIONID_%s_%s";
    
    /**
     * 获取所有用户列表 缓存两小时 key_Id value 是用户朋友列表 列表键值对形式key:userId,value:User对象
     */
    public static final String CACHE_USER_ALL_FRIENDS = "CACHE_USER_ALL_FRIENDS_";
    
    /**
     * 缓存pushToken 缓存两小时 key_USERId value 是UserTOKEN
     */
    public static final String CACHE_USER_PUSHTOKEN_APPID = "CACHE_USER_PUSHTOKEN_APPID__%s_%s";
    
    /**
     * n用户登录时候的登录ID
     * 
     * 缓存五分钟
     */
    public static final String CACHE_USER_LOGINID = "CACHE_USER_LOGINID_";
    
    /**
     * 缓存用户信息 缓存两小时 key_USERId value 是User对象
     */
    public static final String CACHE_USER_Id = "CACHE_USER_Id_";
    
    public static final String CACHE_THIRD_USER_ACCOUNT = "CACHE_THIRD_USER_ACCOUNT_";
    
    public static final String CACHE_THIRD_USER_APPLIANCE = "CACHE_THIRD_USER_APPLIANCE_";
    
    public static final String CACHE_THIRD_USER_APPLIANCE_USER = "CACHE_THIRD_USER_APPLIANCE_USER";
    
    /** 单用户家电用户关系 **/
    public static final String CACHE_SINGLE_USER_APPLIANCE = "CACHE_SINGLE_USER_APPLIANCE";
    
    public static final String CACHE_USER_RULE = "CACHE_USER_RULE_";
    
    /**
     * 所有的家电管家类型
     */
    public static final String CACHE_APP_STEWARD_ALL = "u_steward_0xFF";
    
    /**
     * 所有的家电类型
     */
    public static final String CACHE_APPLIANCE_TYPE_ALL = "CACHE_APPLIANCE_TYPE_ALL_0xFF";
    
    /**
     * 所有的管家对应家电类型
     */
    public static final String CACHE_STEWARD_APPLIANCE_TYPE_ALL = "CACHE_STEWARD_APPLIANCE_TYPE_ALL_0xFF";
    
    /**
     * 获取用户的家庭列表 缓存1小时 key_userId value是MAP存的是键值对键是userhomegroupID，值是userhomegroup对象
     */
    public static final String CACHE_USER_HOMEGROUPS = "CACHE_USER_HOMEGROUPS_";
    
    /**
     * 获取家庭的成员列表 缓存1小时 key_HomegroupId value是MAP存的是键值对键是userId，值是userhomegroup对象
     */
    public static final String CACHE_HOMEGROUP_USERS = "CACHE_HOMEGROUP_USERS_";
    
    /**
     * 获取homegroup对象 缓存1小时 key_homegroupId value是homegroup对象
     */
    public static final String CACHE_HOME_GROUP_ID = "CACHE_HOME_GROUP_ID_";
    
    /**
     * 获取homegroup对象 缓存1小时 key_homegroupId value是homegroup对象
     */
    public static final String CACHE_PUSER_HOME_GROUP_ID = "CACHE_PUSER_HOME_GROUP_ID_";
    
    /**
     * 获取homegroup对象 缓存1小时 key_homegroupnumber value是homegroup对象
     */
    public static final String CACHE_HOME_GROUP_NUM = "CACHE_HOME_GROUP_NUM_";
    
    /**
     * 邮箱激活 缓存1小时 key_activeId activeID是SHA256.encrypt(mail.getMailAddress()+时间戳) value是mail.getMailAddress()
     */
    public static final String CACHE_ACCOUNT_ACTIVCE = "CACHE_ACCOUNT_ACTIVCE_";
    
    /**
     * 短信验证码_mobiel UserManager.buildRequestUrl 失效时间五分钟
     */
    public static final String CACHE_SMS_CODE = "CACHE_SMS_CODE_";
    
    /**
     * KEY_mobiel 手机密码重置的短信resetID 失效时间五分钟
     */
    public static final String CACHE_SMS_PWD_RESETID = "CACHE_SMS_PWD_RESETID_";
    
    public static final String CACHE_APP_INFO = "a_info_";
    
    public static final String CACHE_USER_ROLE = "u_role";
    
    public static final String CACHE_APPLIANCE_ACTIVE_STATUS = "ali_s_a_";
    
    /**
     * 获取家电信息,SN 缓存2小时 key_sn value是appliance对象
     */
    public static final String CACHE_APPLIANCE_SN = "CACHE_APPLIANCE_SN_";
    
    /**
     * 获取家电信息,ID 缓存2小时 key_ID value是appliance对象
     */
    public static final String CACHE_APPLIANCE_ID = "CACHE_APPLIANCE_ID_";
    
    /**
     * 获取家庭绑定的家电列表 缓存2小时 key_homegroupid value是MAP map键值对 键设备ID，值appliance对象
     */
    public static final String CACHE_HOMEGROUP_APPLIANCES = "CACHE_HOMEGROUP_APPLIANCES_";
    
    /**
     * 获取用户绑定的家电列表 缓存2小时 key_userid value是MAP map键值对 键设备ID，值UserAppliance对象
     */
    public static final String CACHE_USER_APPLIANCES = "CACHE_USER_APPLIANCES_";
    
    /**
     * 获取家电所绑定的用户 缓存2小时 key_applianceid value是UserAppliance
     */
    public static final String CACHE_APPLIANCE_BIND_USER = "CACHE_APPLIANCE_BIND_USER_";
    
    /**
     * 缓存 用户家电关系
     * 
     */
    public static final String CACHE_USER_APPLIANCE_USER_ID = "CACHE_USER_APPLIANCE_USER_ID_";
    
    public static final String CACHE_USER_APPLIANCE_APPLIANCE_ID = "CACHE_USER_APPLIANCE_APPLIANCE_ID_";
    
    /**
     * 获取家电所绑定的所有用户 缓存2小时 key_applianceid value是MAP map键值对 键userid，值userappliance对象
     */
    public static final String CACHE_APPLIANCE_BIND_All_USER = "CACHE_APPLIANCE_BIND_All_USER_";
    
    public static final String CACHE_APPLIANCE_MSG_ID = "appli_m_i_";
    
    /**
     * 获取pubsub返回的处理结果 缓存一分钟 key_applianceid_msgid value是pubsub返回的处理结果
     */
    public static final String CACHE_APPLIANCE_REVEIVE_MSG = "CACHE_APPLIANCE_MSG_";
    
    public static final String CACHE_VERSION_UPDATE = "version_update_";
    
    public static final String CACHE_APP_SERVER_INFO = "a_server_info_";
    
    public static final String CACHE_MESSAGE_ID = "message_id";
    
    public static final String DEFAULT_SEPARATOR = "_";
    
    /**
     * KEY_homegroupid_userId 家长发送添加成员邀请 失效时间7tian天
     */
    public static final String HOMEGROUP_ADD_MEMBER = "HOMEGROUP_ADD_MEMBER_";
    
    /**
     * KEY_homegroupid_userId 14. 成员请求加入家庭 失效时间7tian天
     */
    public static final String MEMBER_ADD_HOMEGROUP = "MEMBER_ADD_HOMEGROUP_";
    
    /**
     * 缓存用户的language key_userId_appid
     */
    public static final String CACHE_USER_LANGUAGE_APPID = "CACHE_USER_LANGUAGE_APPID_";
    
    /**
     * 缓存用户的clientType key_userId_appid
     */
    public static final String CACHE_USER_CLIENTTYPE_APPID = "CACHE_USER_CLIENTTYPE_APPID_";
    
    /**
     * 缓存用户的src key_userId
     */
    public static final String CACHE_USER_SRC = "CACHE_USER_SRC_";
    
    /**
     * 缓存SN的ApplianceModelRel对象 key_sn
     */
    public static final String CACHE_APPLIANCE_MODEL_REL = "CACHE_APPLIANCE_MODEL_REL_";
    
    public static final String CACHE_PLAT_INFO = "CACHE_PLAT_INFO_";
    
    /**
     * 缓存用户密码修改的随机码 1分钟失效 key_random
     */
    public static final String CACHE_USER_RANDOM = "CACHE_USER_RANDOM_";
    
    /**
     * 缓存用户电商登录申请码 5分钟失效
     */
    public static final String CACHE_USER_LOGINCODE = "CACHE_USER_LOGINCODE_";
    
    /**
     * 缓存设备固定参数信息 PAC滤芯使用寿命 失效1天
     */
    public static final String CACHE_DEVICE_PARAMS_PACFILTER = "CACHE_DEVICE_PARAMS_PACFILTER_";
    
    /**
     * 缓存设备固定参数信息 RO滤芯使用寿命 失效1天
     */
    public static final String CACHE_DEVICE_PARAMS_ROFILTER = "CACHE_DEVICE_PARAMS_ROFILTER_";
    
    /**
     * 缓存设备固定参数信息 C滤芯使用寿命 失效1天
     */
    public static final String CACHE_DEVICE_PARAMS_CFILTER = "CACHE_DEVICE_PARAMS_CFILTER_";
    
    /**
     * 缓存微信access_token
     */
    public static final String CACHE_WECHAT_ACCESS_TOKEN = "CACHE_WECHAT_ACCESS_TOKEN_";
    
    /**
     * 缓存微信js_ticket
     */
    public static final String CACHE_WECHAT_JS_TICKET = "CACHE_WECHAT_JS_TICKET_";
    
    /**
     * 微信设备是否授权
     */
    public static final String CACHE_WECHAT_DEVICE_AUTH_STATUS = "CACHE_AUTH_STATUS_";
    
    /**
     * 设备运行状态最后的上报时间
     */
    public static final String CACHE_DEVICE_STATUS_LASTTIME = "CACHE_DEVICE_STATUS_LASTTIME_";
    
    /**
     * 设备固定参数最后的上报时间
     */
    public static final String CACHE_DEVICE_PARAMS_LASTTIME = "CACHE_DEVICE_PARAMS_LASTTIME_";
    
    /**
     * 微信设备SN
     */
    public static final String CACHE_WECHAT_SN = "CACHE_WECHAT_SN_";
    
    /**
     * 微信用户openId
     */
    public static final String CACHE_WECHAT_OPENID = "CACHE_WECHAT_OPENID_";
}
