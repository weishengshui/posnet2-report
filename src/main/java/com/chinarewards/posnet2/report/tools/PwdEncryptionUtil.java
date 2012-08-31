package com.chinarewards.posnet2.report.tools;

import java.security.MessageDigest;

/**
 * 
 * @author weishengshui
 * 密码加密工具
 */
public class PwdEncryptionUtil {
	
	
	public static String encryption(String password){
		/**
		 * MD5加密
		 */
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(password.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return  null;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for(byte b:byteArray){
			String tmp =Integer.toHexString(0xFF & b); 
			if(tmp.length()==1){
				md5StrBuff.append("0"+tmp);
			}else{
				md5StrBuff.append(tmp);
			}
		}
		return md5StrBuff.toString();
	}
}
