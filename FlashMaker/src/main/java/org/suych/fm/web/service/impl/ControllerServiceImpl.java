package org.suych.fm.web.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_AUTOWIRED;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_REQUEST_MAPPING;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_REST_CONTROLLER;
import static org.suych.fm.constant.ConstantJavaSyntax.DOUBLE_QUOTATION;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantClassAccessModifier;
import org.suych.fm.constant.ConstantFieldAccessModifier;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantStrategyComponentName;
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

		Set<String> javaPackage = new LinkedHashSet<String>();
		javaPackage.add(ConstantImportPackage.ARRAYLIST);
		javaPackage.add(ConstantImportPackage.LIST);
		javaPackage.add(ConstantImportPackage.HTTP_SERVLET_REQUEST);

		Set<String> threePartyPackage = new LinkedHashSet<String>();
		threePartyPackage.add(ConstantImportPackage.SPRING_AUTOWIRED);
		threePartyPackage.add(ConstantImportPackage.SPRING_REQUEST_MAPPING);
		threePartyPackage.add(ConstantImportPackage.SPRING_REQUEST_METHOD);
		threePartyPackage.add(ConstantImportPackage.SPRING_REST_CONTROLLER);

		Set<String> customPackage = new LinkedHashSet<String>();
		customPackage.add(ConstantImportPackage.PAGE_INFO);
		customPackage.add(BaseInfo.getDomainClassImportPath()); // DO类包名
		customPackage.add(BaseInfo.getServiceInterfaceImportPath());// Service接口包名

		result.setJavaPackage(javaPackage);
		result.setThreePartyPackage(threePartyPackage);
		result.setCustomPackage(customPackage);

		return result;
	}

	private List<FieldStructure> assembleField() {
		List<FieldStructure> result = new ArrayList<FieldStructure>();
		FieldStructure field = new FieldStructure();
		// 注解
		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure autowired = new AnnotationStructure();
		autowired.setName(ANNOTATIONS_AUTOWIRED);
		annotation.add(autowired);
		field.setAnnotation(annotation);
		field.setAccessModifier(ConstantFieldAccessModifier.PRIVATE);
		field.setJavaType(BaseInfo.getServiceInterfaceName());
		field.setName(BaseInfo.getServiceInterfaceFieldName());
		result.add(field);
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
		// 注解
		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure annotation1 = new AnnotationStructure();
		annotation1.setName(ANNOTATIONS_REST_CONTROLLER);
		AnnotationStructure annotation2 = new AnnotationStructure();
		annotation2.setName(ANNOTATIONS_REQUEST_MAPPING);
		annotation2.setValue(DOUBLE_QUOTATION + SLASH + BaseInfo.getControllerRequestMappingName() + DOUBLE_QUOTATION);
		annotation.add(annotation1);
		annotation.add(annotation2);
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
}
