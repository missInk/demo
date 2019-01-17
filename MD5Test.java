package lxx.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {

	public static String password(String inStr) {
		MessageDigest md5 = null;
		try {
			// ��ȡMD5����
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// ���ַ��������е�ÿһ���ַ�ת��Ϊ�ַ�����
		char[] charArray = inStr.toCharArray();
		// ����һ�����Ⱥ��ַ�����һ�����ֽ�����
		byte[] byteArray = new byte[charArray.length];
		// �����ַ����飬�õ�ÿһ���ַ�
		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		// ��MD5���������ֽ��������ժҪ���õ�һ��ժҪ�ֽ�����
		byte[] md5Bytes = md5.digest(byteArray);
		// ��ժҪ���������ÿһ���ֽ�ת����16���ƣ�����ƴ��һ��͵õ���MD5ֵ
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
