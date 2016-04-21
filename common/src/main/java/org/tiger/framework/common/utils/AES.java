package org.tiger.framework.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.tiger.framework.common.Constant;
import org.tiger.framework.common.exception.AppException;
import org.tiger.framework.common.exception.Code;

import sun.misc.BASE64Decoder;

public class AES
{
    public static void main(String args[])
        throws Exception
    {
        String encrypt =
            "LLkt+wBfLhRsctdxz2AOYis7LjF00tpPy+TcvVgGY0deWuWvVT3LJHnT3TAQ/ieWKO7new/W4WeGkzu2To8ad7li/xMmy7AgbnHBr1CP6XFrHPBpeeHAvWb6qwPnsGsS";
        // System.out.println(encrypt);
        System.out.println(desEncrypt(encrypt, "1234567812345678"));
    }
    
    public static String encrypt(String data, String key)
        throws Exception
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0)
            {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(Constant.WEBSOCKET_AES_VI.getBytes());
            
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            
            return new sun.misc.BASE64Encoder().encode(encrypted);
            
        }
        catch (Exception e)
        {
            throw new AppException("AES encrypt error src:" + data + " key:" + key, Code.ENCRYPT_ERROR);
        }
    }
    
    public static String desEncrypt(String data, String key)
        throws Exception
    {
        try
        {
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);
            
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(Constant.WEBSOCKET_AES_VI.getBytes());
            
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "UTF-8");
            return originalString.trim();
        }
        catch (Exception e)
        {
            throw new AppException("AES decryptSrc error src :" + data + " key:" + key, Code.DECRYPT_ERROR);
        }
    }
}
