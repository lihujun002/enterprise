package org.tiger.framework.common.utils;

import java.security.MessageDigest;

import org.springframework.util.Assert;

/**
 * MD5加密
 * @author lihj17
 *
 */
public class MD5
{
    
    public static String encrypt(String strSrc)
    {
        Assert.hasText(strSrc, "Parameter strSrc must not empty.");
        String strDes = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bt = strSrc.getBytes();
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return strDes;
    }
    
    private static String bytes2Hex(byte[] bts)
    {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++)
        {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1)
            {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
    
    // // 测试主函数
    // public static void main(String args[]) {
    // String s = new String("112ssdfsSDFSDFdfdfasdfsdFFsdf3");
    // System.out.println("原始：" + s);
    // System.out.println("MD5后：" + encrypt(s));
    // System.out.println("MD5后：" + encrypt(s).length());
    //
    // }
}
