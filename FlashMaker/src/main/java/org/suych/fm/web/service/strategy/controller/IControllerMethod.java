package org.suych.fm.web.service.strategy.controller;

import java.util.ArrayList;
import java.util.List;

import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantParameterType;
import org.suych.fm.util.PropertyUtils;
import org.suych.fm.util.StringUtil;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;

/**
 * 组装Controller类方法接口
 */
public interface IControllerMethod {

	/**
	 * 组装方法
	 * 
	 * @return
	 */
	MethodStructure assemble();

	/**
	 * 组装参数
	 * 
	 * @return
	 */
	default List<ParamterStructure> assembleParameter() {
		List<ParamterStructure> result = new ArrayList<ParamterStructure>();
		// 是否使用自定义参数
		if (Boolean.valueOf(PropertyUtils.getProperty("controller.method.specifed.param.use"))) {
			// 参数个数
			int count = Integer.valueOf(PropertyUtils.getProperty("controller.method.specifed.param.count"));
			// 注解名称
			String[] annotationNameArray = StringUtil
					.null2Empty(PropertyUtils.getProperty("controller.method.specifed.param.annotation.name"))
					.split(",");
			// 注解值
			String[] annotationValueArray = StringUtil
					.null2Empty(PropertyUtils.getProperty("controller.method.specifed.param.annotation.value"))
					.split(",");
			// 参数类型
			String[] typeArray = StringUtil
					.null2Empty(PropertyUtils.getProperty("controller.method.specifed.param.type")).split(",");
			// 参数名称
			String[] nameArray = StringUtil
					.null2Empty(PropertyUtils.getProperty("controller.method.specifed.param.name")).split(",");
			for (int i = 0; i < count; i++) {
				String annotationName = annotationNameArray[i];
				String annotationValue = annotationValueArray[i];
				String type = typeArray[i];
				String name = nameArray[i];
				ParamterStructure param = new ParamterStructure();
				AnnotationStructure annotation = new AnnotationStructure();
				annotation.setName(annotationName);
				annotation.setValue(annotationValue);
				param.setType(type);
				param.setName(name);
				param.setAnnotation(annotation);
				result.add(param);
			}
		} else {
			// 默认参数
			ParamterStructure defaultParam = new ParamterStructure();
			defaultParam.setType(ConstantParameterType.HTTP_SERVLET_REQUEST);
			defaultParam.setName(ConstantParameterName.REQUEST);
			result.add(defaultParam);
		}
		return result;
	}
}
