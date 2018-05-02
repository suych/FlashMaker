package org.suych.fm.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);

	private static Properties props = null;
	static {
		loadProps();
	}

	synchronized static private void loadProps() {
		LOGGER.info("开始加载properties文件内容.......");
		props = new Properties();
		InputStream in = null;
		try {
			// 第一种，通过类加载器进行获取properties文件流
			in = PropertyUtils.class.getClassLoader().getResourceAsStream("constant/constant.properties");
			// 第二种，通过类进行获取properties文件流
			// in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
			props.load(in);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		LOGGER.info("加载properties文件内容完成...........");
		LOGGER.info("properties文件内容：" + props);
	}

	public static String getProperty(String key) {
		if (props == null) {
			loadProps();
		}
		return props.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		if (props == null) {
			loadProps();
		}
		return props.getProperty(key, defaultValue);
	}
}
