package cn.huchao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.Properties;

/**
 * @author huchao
 * @description
 * @date 2017年7月3日
 * 获取配置文件信息的工具类
 */
public class PropertiesUtil {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static Properties props;
	static {
		loadProps();
	}

	synchronized static private void loadProps() {
		logger.info("开始加载properties文件内容.......");
		props = new Properties();
		InputStream in = null;
		InputStream in2 = null;
		try {
			//此处填写配置文件的信息，可以加载多个配置文件信息，注意多个配置文件时，里面的键值要用文件名标识，避免重复
			// 第一种，通过类加载器进行获取properties文件流
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream("config/system.properties");
			in2 = PropertiesUtil.class.getClassLoader().getResourceAsStream("config/sys.properties");
			// 第二种，通过类进行获取properties文件流
			// in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
			props.load(in);
			props.load(in2);
		} catch (FileNotFoundException e) {
			logger.error("config/system.properties文件未找到");
		} catch (IOException e) {
			logger.error("出现IOException");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("config/system.properties文件流关闭出现异常");
			}
			try {
				if (null != in2) {
					in2.close();
				}
			} catch (IOException e) {
				logger.error("config/system.properties文件流关闭出现异常");
			}
		}
		logger.info("加载properties文件内容完成...........");
		logger.info("properties文件内容：" + props);
	}

	public static String getProperty(String key) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key, defaultValue);
	}
	public static void main(String[] args) {
		String property = getProperty("sys_peizhi2");
		System.out.println(property);
	}
}
