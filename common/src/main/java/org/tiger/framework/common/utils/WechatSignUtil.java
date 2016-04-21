package org.tiger.framework.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 微信签名验证工具类
 * 
 * @author lihj17
 *
 */
public class WechatSignUtil {

	/**
	 * jsapi_ticket签名 jsapi_ticket是公众号用于调用微信JSAPI的临时票据。
	 * 正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取。
	 * 由于获取jsapi_ticket的api调用次数非常有限
	 * ，频繁刷新jsapi_ticket会导致api调用受限，影响自身业务，开发者必须在自己的服务全局缓存jsapi_ticket。
	 * 
	 * 签名生成规则如下：参与签名的字段包括noncestr（随机字符串）, 有效的jsapi_ticket, timestamp（时间戳）,
	 * url（当前网页的URL，不包含#及其后面部分） 。 对所有待签名参数按照字段名的ASCII
	 * 码从小到大排序（字典序）后，使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1。
	 * 这里需要注意的是所有参数名均为小写字符。对string1作sha1加密，字段名和字段值都采用原始值，不进行URL
	 * 转义。即signature=sha1(string1)。
	 * 
	 * @param jsapi_ticket
	 * @param url
	 * @return
	 */
	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUIDGenerator.generateUUID();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 服务器校验
	 * @param signature 签名串
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @param token 公众号服务器配置的token
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
		// 6a4ea3d9c82943b5b56a9bab09f1ad08 为 公众号服务器配置的token
		String[] array = { nonce, timestamp, token };
		Arrays.sort(array);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
		}
		String str = sb.toString();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			str = byteToHex(md.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (signature.equals(str)) {
			return true;
		}

		return false;
	}

}
