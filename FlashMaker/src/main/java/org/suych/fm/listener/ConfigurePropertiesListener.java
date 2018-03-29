package org.suych.fm.listener;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.suych.fm.constant.ConfigureContainer;
import org.suych.fm.util.AnnotationUtils;

public class ConfigurePropertiesListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		List<String> allPropertiesList = AnnotationUtils.getClassName("constant");
		for (String propertiesName : allPropertiesList) {
			ResourceBundle resource_bundle = ResourceBundle.getBundle(propertiesName);
			Enumeration<String> keys = resource_bundle.getKeys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = "";
				try {
					value = new String(resource_bundle.getString(key).getBytes("UTF-8"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				ConfigureContainer.constantMap.put(key, value);
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

}
