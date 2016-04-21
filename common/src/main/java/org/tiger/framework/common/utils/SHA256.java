package org.tiger.framework.common.utils;

import java.security.MessageDigest;

import org.springframework.util.Assert;

/**
 * 
 * @author lihj17
 *
 */
public class SHA256 {
	public static String encrypt(String strSrc) {
		Assert.hasText(strSrc, "Parameter strSrc must not empty.");
		String strDes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bt = strSrc.getBytes();
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDes;
	}

	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

}
