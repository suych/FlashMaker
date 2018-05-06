package org.suych.fm.web.service.strategy.controller;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_DELETE_MAPPING;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_GET_MAPPING;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_POST_MAPPING;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_PUT_MAPPING;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_REQUEST_MAPPING;
import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.DOUBLE_QUOTATION;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantParameterType;
import org.suych.fm.constant.ConstantParameterValue;
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
	 * 组装方法注解
	 * 
	 * @param methodName
	 * @return
	 */
	default List<AnnotationStructure> assembleMethodAnnotation(String methodName) {
		List<AnnotationStructure> result = new ArrayList<AnnotationStructure>();
		AnnotationStructure methodAnnotation = new AnnotationStructure();
		// 默认@RequestMapping
		methodAnnotation.setName(ANNOTATIONS_REQUEST_MAPPING);
		// 注解属性
		Map<String, String> attribute = new LinkedHashMap<String, String>();
		String value = DOUBLE_QUOTATION + SLASH + methodName + DOUBLE_QUOTATION;
		attribute.put(ConstantParameterName.VALUE, value); // value属性

		StringBuilder method = new StringBuilder();
		String httpTypeSwitch = StringUtil.null2Empty(PropertyUtils.getProperty("controller.method.http.type"));
		if ("".equals(httpTypeSwitch)) {
			// 默认method属性
			method.append(LEFT_BRACE + SPACE + ConstantParameterType.REQUEST_METHOD + POINT
					+ ConstantParameterValue.POST + COMMA + SPACE + ConstantParameterType.REQUEST_METHOD + POINT
					+ ConstantParameterValue.GET + SPACE + RIGHT_BRACE);
			attribute.put(ConstantParameterName.METHOD, method.toString());// method属性
		} else {
			String[] httpType = httpTypeSwitch.split(",");
			int httpTypeLength = httpType.length;
			if (httpTypeLength > 1) {
				// 配置多个method属性
				method.append(LEFT_BRACE + SPACE);
				for (int i = 0; i < httpTypeLength; i++) {
					String type = httpType[i];
					if (ConstantParameterValue.GET.equalsIgnoreCase(type)) {
						method.append(ConstantParameterType.REQUEST_METHOD + POINT + ConstantParameterValue.GET);
					} else if (ConstantParameterValue.POST.equalsIgnoreCase(type)) {
						method.append(ConstantParameterType.REQUEST_METHOD + POINT + ConstantParameterValue.POST);
					} else if (ConstantParameterValue.DELETE.equalsIgnoreCase(type)) {
						method.append(ConstantParameterType.REQUEST_METHOD + POINT + ConstantParameterValue.DELETE);
					} else if (ConstantParameterValue.PUT.equalsIgnoreCase(type)) {
						method.append(ConstantParameterType.REQUEST_METHOD + POINT + ConstantParameterValue.PUT);
					}
					if (i != httpTypeLength - 1) {
						method.append(COMMA + SPACE);
					}
				}
				method.append(SPACE + RIGHT_BRACE);
				attribute.put(ConstantParameterName.METHOD, method.toString());// method属性
			} else {
				// 配置单个method属性
				if (ConstantParameterValue.GET.equalsIgnoreCase(httpType[0])) {
					// @GetMapping
					methodAnnotation.setName(ANNOTATIONS_GET_MAPPING);
				} else if (ConstantParameterValue.POST.equalsIgnoreCase(httpType[0])) {
					// @PostMapping
					methodAnnotation.setName(ANNOTATIONS_POST_MAPPING);
				} else if (ConstantParameterValue.PUT.equalsIgnoreCase(httpType[0])) {
					// @PutMapping
					methodAnnotation.setName(ANNOTATIONS_PUT_MAPPING);
				} else if (ConstantParameterValue.DELETE.equalsIgnoreCase(httpType[0])) {
					// @DeleteMapping
					methodAnnotation.setName(ANNOTATIONS_DELETE_MAPPING);
				}
			}
		}

		attribute.put(ConstantParameterName.PRODUCES, ConstantParameterValue.APPLICATION_JSON_UTF8_VALUE);// produces属性

		methodAnnotation.setAttribute(attribute);
		result.add(methodAnnotation);
		return result;
	}

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
