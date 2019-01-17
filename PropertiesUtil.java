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
			System.out.println("加载配置文件失败");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 根据数据库名获取其驱动
	public static String getDriverProperties(String key) {
		return prop.getProperty(key + "driverClass");
	}

	// 根据数据库名获取 url
	public static String getUrlProperties(String key) {
		return prop.getProperty(key + "url");
	}

	// 获取用户名
	public static String getUsernameProperties(String key) {
		return prop.getProperty(key + "username");
	}

	// 获取密码
	public static String getPasswordProperties(String key) {
		return prop.getProperty(key + "password");
	}
}
