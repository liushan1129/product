package com.utils;

import java.security.MessageDigest;

/**
 * MD5 加密
 * @author liushan
 * @version 2016-07-11
 *
 */
public class MD5 {

	 /**
     * MD5值计算
     * <p>
     * MD5的算法在RFC1321 中定义: 在RFC 1321中，给出了Test suite用来检验你的实现是否正确： MD5 ("") =
     * d41d8cd98f00b204e9800998ecf8427e MD5 ("a") =
     * 0cc175b9c0f1b6a831c399e269772661 MD5 ("abc") =
     * 900150983cd24fb0d6963f7d28e17f72 MD5 ("message digest") =
     * f96b697d7cb7938d525a2f31aaf161d0 MD5 ("abcdefghijklmnopqrstuvwxyz") =
     * c3fcd3d76192e4007dfb496cca67e13b
     *
     * @param res
     *            源字符串
     * @return md5值
     */
    public final static String Md5(String res) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = res.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String dd = new String(str);
            return dd;
        } catch (Exception e) {
            return null;
        }
    }

    public static String TMD5(String str) {
//    	System.out.println(Md5(Md5(Md5(str))));
        return Md5(Md5(Md5(str)));
    }
    public static void main(String[] args) {
    	String str = "JJlink2@4$7&";
    	String str1 = TMD5(str);
    	System.out.println(str1);
	}

}
