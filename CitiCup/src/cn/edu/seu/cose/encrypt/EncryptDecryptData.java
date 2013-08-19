package cn.edu.seu.cose.encrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptDecryptData {

	/**
	 * @param args
	 */
	String part1 = "486135738435";
	String part2 = "337846125153";
	public byte[] encrypt(String str)throws 
	InvalidKeyException, NoSuchAlgorithmException,IllegalBlockSizeException, 
	BadPaddingException,NoSuchPaddingException, InvalidKeySpecException {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = (part1.substring(3, 8) + part2.substring(4,10)).getBytes();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		// 现在，获取数据并加密
		byte data[] = str.getBytes();
		// 正式执行加密操作
		byte[] encryptedData = cipher.doFinal(data);
		return encryptedData;
	}	
	public String decrypt( byte[] encryptedData)throws
	IllegalBlockSizeException, BadPaddingException,InvalidKeyException, 
	NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeySpecException {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = (part1.substring(3, 8) + part2.substring(4,10)).getBytes();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		// 正式执行解密操作
		byte decryptedData[] = cipher.doFinal(encryptedData);
		return new String(decryptedData);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeySpecException {
		EncryptDecryptData ed = new EncryptDecryptData();
		String str = "hi.baidu.com/badpeas"; // 待加密数据
		// 2.1 >>> 调用加密方法
		byte[] encryptedData = ed.encrypt(str);
		// 3.1 >>> 调用解密方法
		System.out.println("加密后===>" + encryptedData);
		System.out.println("解密后===>" + new String(ed.decrypt(encryptedData)));
	}
}
