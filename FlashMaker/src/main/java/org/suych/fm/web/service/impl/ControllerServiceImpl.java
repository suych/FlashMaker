package org.suych.fm.web.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_AUTOWIRED;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_PATH_VARIABLE;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_REQUEST_MAPPING;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_REQUEST_PARAM;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_REST_CONTROLLER;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantClassAccessModifier;
import org.suych.fm.constant.ConstantFieldAccessModifier;
import org.suych.fm.constant.ConstantFieldNonAccessModifier;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantParameterType;
import org.suych.fm.constant.ConstantParameterValue;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.PropertyUtils;
import org.suych.fm.util.StringUtil;
import org.suych.fm.util.generate.GenerateClassUtil;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.ClassStructure;
import org.suych.fm.util.generate.model.java.FieldStructure;
import org.suych.fm.util.generate.model.java.ImportPackageStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.IControllerService;
import org.suych.fm.web.service.strategy.controller.ControllerMethodFactory;

@Service
public class ControllerServiceImpl implements IControllerService {

	@Autowired
	ControllerMethodFactory factory;

	@Override
	public void generate() {
		// 1.组装引入包名
		ImportPackageStructure importPackage = assembleImportPackage();
		// 2.组装字段
		List<FieldStructure> field = assembleField();
		// 3.组装方法结构
		List<MethodStructure> method = assembleMethod();
		// 4.组装类结构
		ClassStructure cs = assembleClass(importPackage, field, method);
		// 5.按规范输出至文件
		GenerateClassUtil.generate(cs);
	}

	private ImportPackageStructure assembleImportPackage() {
		ImportPackageStructure result = new ImportPackageStructure();
		Set<String> javaPackage = assembleJavaPackage();
		Set<String> threePartyPackage = assembleThreePartyPackage();
		Set<String> customPackage = assembleCustomPackage();
		result.setJavaPackage(javaPackage);
		result.setThreePartyPackage(threePartyPackage);
		result.setCustomPackage(customPackage);
		return result;
	}

	private Set<String> assembleJavaPackage() {
		Set<String> result = new LinkedHashSet<String>();
		result.add(ConstantImportPackage.ARRAYLIST);
		result.add(ConstantImportPackage.LIST);
		// 是否使用自定义参数
		if (!Boolean.valueOf(PropertyUtils.getProperty("controller.method.specifed.param.use"))) {
			// 默认参数引入包
			result.add(ConstantImportPackage.HTTP_SERVLET_REQUEST);
		}
		return result;
	}

	private Set<String> assembleThreePartyPackage() {
		Set<String> result = new LinkedHashSet<String>();
		if (Boolean.valueOf(PropertyUtils.getProperty("controller.logger"))) {
			result.add(ConstantImportPackage.SLF4J_LOGGER);
			result.add(ConstantImportPackage.SLF4J_LOGGERFACTORY);
		}
		result.add(ConstantImportPackage.SPRING_AUTOWIRED);
		result.add(ConstantImportPackage.SPRING_MEDIATYPE);

		// 方法http类型
		String httpTypeSwitch = StringUtil.null2Empty(PropertyUtils.getProperty("controller.method.http.type"));
		if ("".equals(httpTypeSwitch)) {
			// 默认@RequestMethod
			result.add(ConstantImportPackage.SPRING_REQUEST_METHOD);
		} else {
			String[] httpType = httpTypeSwitch.split(",");
			int httpTypeLength = httpType.length;
			if (httpTypeLength > 1) {
				// 多个method属性@RequestMethod
				result.add(ConstantImportPackage.SPRING_REQUEST_METHOD);
			} else {
				// 配置单个method属性
				if (ConstantParameterValue.GET.equalsIgnoreCase(httpType[0])) {
					// @GetMapping
					result.add(ConstantImportPackage.SPRING_GET_MAPPING);
				} else if (ConstantParameterValue.POST.equalsIgnoreCase(httpType[0])) {
					// @PostMapping
					result.add(ConstantImportPackage.SPRING_POST_MAPPING);
				} else if (ConstantParameterValue.PUT.equalsIgnoreCase(httpType[0])) {
					// @PutMapping
					result.add(ConstantImportPackage.SPRING_PUT_MAPPING);
				} else if (ConstantParameterValue.DELETE.equalsIgnoreCase(httpType[0])) {
					// @DeleteMapping
					result.add(ConstantImportPackage.SPRING_DELETE_MAPPING);
				}
			}
		}

		// 是否使用自定义参数
		if (Boolean.valueOf(PropertyUtils.getProperty("controller.method.specifed.param.use"))) {
			// 参数个数
			int count = Integer.valueOf(PropertyUtils.getProperty("controller.method.specifed.param.count"));
			// 注解名称
			String[] annotationNameArray = StringUtil
					.null2Empty(PropertyUtils.getProperty("controller.method.specifed.param.annotation.name"))
					.split(",");
			for (int i = 0; i < count; i++) {
				String annotationName = annotationNameArray[i];
				if (ANNOTATIONS_PATH_VARIABLE.equals(annotationName)) {
					result.add(ConstantImportPackage.SPRING_PATH_VARIABLE);
				} else if (ANNOTATIONS_REQUEST_PARAM.equals(annotationName)) {
					result.add(ConstantImportPackage.SPRING_REQUEST_PARAM);
				}
			}

		}

		result.add(ConstantImportPackage.SPRING_REQUEST_MAPPING);
		result.add(ConstantImportPackage.SPRING_REST_CONTROLLER);
		return result;
	}

	private Set<String> assembleCustomPackage() {
		Set<String> result = new LinkedHashSet<String>();
		result.add(ConstantImportPackage.PAGE_INFO);
		result.add(BaseInfo.getDomainClassImportPath()); // DO类包名
		result.add(BaseInfo.getServiceInterfaceImportPath());// Service接口包名
		return result;
	}

	private List<FieldStructure> assembleField() {
		List<FieldStructure> result = new ArrayList<FieldStructure>();
		// 是否组装LOGGER字段
		if (Boolean.valueOf(PropertyUtils.getProperty("controller.logger"))) {
			FieldStructure logger = assembleFieldLogger();
			result.add(logger);
		}
		// 组装Service接口字段
		FieldStructure field = assembleFieldServiceInterface();
		result.add(field);
		return result;
	}

	private FieldStructure assembleFieldLogger() {
		FieldStructure result = new FieldStructure();
		result.setAccessModifier(ConstantFieldAccessModifier.PRIVATE);
		result.setNonAccessModifier(
				Arrays.asList(ConstantFieldNonAccessModifier.STATIC, ConstantFieldNonAccessModifier.FINAL));
		result.setJavaType(ConstantParameterType.LOGGER);
		result.setName(ConstantParameterName.LOGGER);
		result.setInitialization(Boolean.TRUE);
		result.setInitializationValue("LoggerFactory.getLogger(" + BaseInfo.getControllerName() + ".class)");
		return result;
	}

	private FieldStructure assembleFieldServiceInterface() {
		FieldStructure result = new FieldStructure();
		// 注解
		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure autowired = new AnnotationStructure();
		autowired.setName(ANNOTATIONS_AUTOWIRED);
		annotation.add(autowired);
		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantFieldAccessModifier.PRIVATE);
		result.setJavaType(BaseInfo.getServiceInterfaceName());
		result.setName(BaseInfo.getServiceInterfaceFieldName());
		return result;
	}

	private List<MethodStructure> assembleMethod() {
		List<MethodStructure> result = new ArrayList<MethodStructure>();
		// 1.String list()
		result.add(factory.assemble(ConstantStrategyComponentName.CONTROLLER_LIST));
		// 2.String getByPrimaryKey(String id);
		result.add(factory.assemble(ConstantStrategyComponentName.CONTROLLER_GET_BY_PRIMARYKEY));
		// 3.String save(DO类名 DO类名首字母小写);
		result.add(factory.assemble(ConstantStrategyComponentName.CONTROLLER_SAVE));
		// 4.String saveSelective(DO类名 DO类名首字母小写);
		result.add(factory.assemble(ConstantStrategyComponentName.CONTROLLER_SAVE_SELECTIVE));
		// 5.String updateByPrimaryKeySelective(DO类名 DO类名首字母小写);
		result.add(factory.assemble(ConstantStrategyComponentName.CONTROLLER_UPDATE_BY_PRIMARYKEY_SELECTIVE));
		// 6.String removeByPrimaryKeys(List<String> ids);
		result.add(factory.assemble(ConstantStrategyComponentName.CONTROLLER_REMOVE_BY_PRIMARYKEYS));
		return result;
	}

	private ClassStructure assembleClass(ImportPackageStructure importPackage, List<FieldStructure> field,
			List<MethodStructure> method) {
		ClassStructure result = new ClassStructure();
		List<AnnotationStructure> annotation = assembleClassAnnotation();
		// 组装类结构
		result.setLocalPackage(BaseInfo.getControllerLocalPackage());
		result.setImportPackage(importPackage);
		result.setAnnotation(annotation);
		result.setAcessModifier(ConstantClassAccessModifier.PUBLIC);
		result.setName(BaseInfo.getControllerName());
		result.setField(field);
		result.setMethod(method);

		return result;
	}

	private List<AnnotationStructure> assembleClassAnnotation() {
		List<AnnotationStructure> result = new ArrayList<AnnotationStructure>();
		AnnotationStructure annotation1 = new AnnotationStructure();
		annotation1.setName(ANNOTATIONS_REST_CONTROLLER);
		AnnotationStructure annotation2 = new AnnotationStructure();
		annotation2.setName(ANNOTATIONS_REQUEST_MAPPING);
		annotation2.setValue(SLASH + BaseInfo.getControllerRequestMappingName());
		result.add(annotation1);
		result.add(annotation2);
		return result;
	}
}
