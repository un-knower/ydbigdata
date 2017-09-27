package com.yd.common.cipher;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class CIPDesUtils {

	static {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}
	
	public static Logger logger = Logger.getLogger(CIPDesUtils.class);

	private static final String Algorithm = "DESede"; // 定义加密算法,可用
														// DES,DESede,Blowfish

	private static final String keyStr = "aAbBcCeEfFgGhHiIjJkKlLmM";//"nNoOpPqQrRsStTzZyYxXwWvVuU=+1234598760-!";

	private static final String encode = "utf8";

	/*
	 * 应用系统本地自己使用,无须与其他应用交换密钥
	 */
	public static String encrypt(String message, long time) {
		String kStr = time + keyStr;
		byte[] code;
		try {
			if(kStr.length()>24){
				kStr = kStr.substring(0, 24);
			}
			code = encryptMode(kStr.getBytes(encode), message.getBytes(encode));
			return Base64.encodeBase64String(code);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}

	public static String decrypt(String message, long time) {
		String kStr = time + keyStr;
		try {
			if(kStr.length()>24){
				kStr = kStr.substring(0, 24);
			}
			byte[] code = Base64.decodeBase64(message);
			byte[] decode = decryptMode(kStr.getBytes(encode), code);
			return new String(decode, encode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String encryptInternal(String message) {
		String kStr = keyStr;
		byte[] code;
		try {
			code = encryptMode(kStr.getBytes(encode), message.getBytes(encode));
			return Base64.encodeBase64String(code);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/*
	 * 应用系统本地自己使用,无须与其他应用交换密钥
	 */
	public static String decryptInternal(String message) {
		String kStr = keyStr;
		try {
			byte[] code = Base64.decodeBase64(message);
			byte[] decode = decryptMode(kStr.getBytes(encode), code);
			return new String(decode, encode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	private static byte[] encryptMode(byte[] keybyte, byte[] src)
			throws Exception {
		// 生成密钥
		SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
		// 加密
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		return c1.doFinal(src);// 在单一方面的加密或解密

	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	private static byte[] decryptMode(byte[] keybyte, byte[] src) throws Exception {
		// 生成密钥
		SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
		// 解密
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		return c1.doFinal(src);

	}

	public static void main(String[] args) throws Exception {
		// 添加新安全算法,如果用JCE就要把它添加进去
		// byte[] enk = hex("zeromike");// 用户名
		
		String password = "abcd1234";// 密码
		System.out.println(Base64.encodeBase64String(password.getBytes()));
		System.out.println("加密前的字符串:" + password);
		long l = 1441194498741l;//System.currentTimeMillis();
		String pword = encrypt(password,l);
		System.out.println("加密后的字符串:" + pword);
		
		if("B/4ZCnpz2P5Q9kCVc5XN8g==".equals(pword)){
			System.out.println("=================equals "+l);
		}


		password = decrypt(pword,l);
		System.out.println("解密后的字符串:" + password);
	}
}
