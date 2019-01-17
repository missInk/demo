package lxx.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {

	public static String password(String inStr) {
		MessageDigest md5 = null;
		try {
			// 获取MD5对象
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将字符串对象中的每一个字符转换为字符数组
		char[] charArray = inStr.toCharArray();
		// 定义一个长度和字符数组一样的字节数组
		byte[] byteArray = new byte[charArray.length];
		// 遍历字符数组，拿到每一个字符
		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		// 把MD5这个对象对字节数组进行摘要，得到一个摘要字节数组
		byte[] md5Bytes = md5.digest(byteArray);
		// 把摘要数组里面的每一个字节转换成16进制，并且拼在一起就得到了MD5值
		StringBuilder hexValue = new StringBuilder();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int)md5Bytes[i]) & 0xff;
			if(val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static void main(String[] args) {
		System.out.println(password("abc"));
	}
	
}
