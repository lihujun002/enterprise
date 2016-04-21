package org.tiger.framework.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 封装request获取参数
 * @author lihj17
 *
 */
public class ReqUtils {

	public static String getString(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		return value != null ? value : "";
	}

	public static int getInt(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (StringUtils.isBlank(value)) {
			return -1;
		} else {
			try {
				return NumberUtils.toInt(value);
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
	}

	public static long getLong(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (StringUtils.isBlank(value)) {
			return -1L;
		} else {
			try {
				return NumberUtils.toLong(value);
			} catch (Exception e) {
				e.printStackTrace();
				return -1L;
			}
		}
	}
}
