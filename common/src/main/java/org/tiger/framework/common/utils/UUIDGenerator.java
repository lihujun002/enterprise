package org.tiger.framework.common.utils;

import java.util.UUID;

/**
 * UUID生成器
 * 
 * @author lihj17
 *
 */
public class UUIDGenerator {
	/**
	 * 生成32位UUID，不含“-”符
	 * 
	 * @return
	 */
	public static final String generateUUID() {
		String uuid = UUID.randomUUID().toString().toLowerCase();
		return uuid.replaceAll("-", "");
	}
}
