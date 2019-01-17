package lxx.linearAlgebra.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties prop;
	static {
		try {
			prop = new Properties();
			prop.load(new FileInputStream(PropertiesUtil.class.getResource("/").getPath()+"database.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("���������ļ�ʧ��");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �������ݿ�����ȡ������
	public static String getDriverProperties(String key) {
		return prop.getProperty(key + "driverClass");
	}

	// �������ݿ�����ȡ url
	public static String getUrlProperties(String key) {
		return prop.getProperty(key + "url");
	}

	// ��ȡ�û���
	public static String getUsernameProperties(String key) {
		return prop.getProperty(key + "username");
	}

	// ��ȡ����
	public static String getPasswordProperties(String key) {
		return prop.getProperty(key + "password");
	}
}
