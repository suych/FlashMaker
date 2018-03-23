package org.suych.fm.web.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_ATTRIBUTE_READONLY;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_AUTOWIRED;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_OVERRIDE;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_SERVICE;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_TRANSACTIONAL;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.LIST;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.STRING;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;
import static org.suych.fm.constant.ConstantJavaSyntax.TRUE;
import static org.suych.fm.constant.ConstantJavaSyntax.VOID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantClassAccessModifier;
import org.suych.fm.constant.ConstantFieldAccessModifier;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.tool.FileNameTool;
import org.suych.fm.util.generate.GenerateClassUtil;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.ClassStructure;
import org.suych.fm.util.generate.model.java.FieldStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.IServiceImplService;

@Service
public class ServiceImplServiceImpl implements IServiceImplService {

	@Override
	public void generate() {
		// 1.组装引入包名
		Set<String> importPackage = assembleImportPackage();
		// 2.组装字段
		List<FieldStructure> field = assembleFieldStructure();
		// 3.组装方法结构
		List<MethodStructure> method = assembleMethodStructure();
		// 4.组装类结构
		ClassStructure cs = assembleClassStructure(importPackage, field, method);
		// 5.按规范输出至文件
		GenerateClassUtil.generate(cs);
	}

	/**
	 * 组装引入包名
	 * 
	 * @return
	 */
	private Set<String> assembleImportPackage() {
		Set<String> result = new HashSet<String>();
		result.add(ConstantImportPackage.LIST);
		result.add(ConstantImportPackage.SPRING_AUTOWIRED);
		result.add(ConstantImportPackage.SPRING_SERVICE);
		result.add(ConstantImportPackage.SPRING_TRANSACTIONAL);
		String localPackage = BaseInfo.getLocalPackage();
		String domainClassPackage = localPackage + POINT + BaseInfo.getDomainClassName(); // DO类包名
		String mapperInterfacePackage = localPackage + POINT + BaseInfo.getMapperInterfaceName(); // Mapper接口包名
		String serviceInterfacePackage = localPackage + POINT + BaseInfo.getServiceInterfaceName(); // Service接口包名
		result.add(domainClassPackage);
		result.add(mapperInterfacePackage);
		result.add(serviceInterfacePackage);
		return result;
	}

	/**
	 * 组装字段结构
	 * 
	 * @return
	 */
	private List<FieldStructure> assembleFieldStructure() {
		List<FieldStructure> result = new ArrayList<FieldStructure>();
		FieldStructure field = new FieldStructure();
		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure autowired = new AnnotationStructure();
		autowired.setName(ANNOTATIONS_AUTOWIRED);
		annotation.add(autowired);
		String mapperInterfaceName = BaseInfo.getMapperInterfaceName();
		field.setAnnotation(annotation);
		field.setAccessModifier(ConstantFieldAccessModifier.PRIVATE);
		field.setJavaType(mapperInterfaceName);
		field.setName(FileNameTool.firstLetterToLowerCase(mapperInterfaceName));
		result.add(field);
		return result;
	}

	/**
	 * 组装方法结构
	 * 
	 * @return
	 */
	private List<MethodStructure> assembleMethodStructure() {
		// 5个方法
		List<MethodStructure> result = new ArrayList<MethodStructure>();

		// 1.List<DO类名> list()
		MethodStructure m1 = assembleMethodList();

		// 2.DO类名 getById(String id);
		MethodStructure m2 = assembleMethodGetById();

		// 3.void save(DO类名 DO类名首字母小写);
		MethodStructure m3 = assembleMethodSave();

		// 4.void updateById(DO类名 DO类名首字母小写);
		MethodStructure m4 = assembleMethodUpdateById();

		// 5.void removeByIds(List<String> ids);
		MethodStructure m5 = assembleMethodRemoveByIds();

		result.add(m1);
		result.add(m2);
		result.add(m3);
		result.add(m4);
		result.add(m5);
		return result;
	}

	/**
	 * 组装类结构
	 * 
	 * @return
	 */
	private ClassStructure assembleClassStructure(Set<String> importPackage, List<FieldStructure> field,
			List<MethodStructure> method) {
		ClassStructure result = new ClassStructure();

		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure service = new AnnotationStructure();
		service.setName(ANNOTATIONS_SERVICE);
		annotation.add(service);

		List<String> interfaceName = new ArrayList<String>();
		interfaceName.add(BaseInfo.getServiceInterfaceName());

		// 组装类结构
		result.setLocalPackage(BaseInfo.getLocalPackage());
		result.setImportPackage(importPackage);
		result.setAnnotation(annotation);
		result.setAcessModifier(ConstantClassAccessModifier.PUBLIC);
		result.setName(BaseInfo.getServiceImplName());
		result.setImplementInterface(true);
		result.setInterfaceName(interfaceName);
		result.setField(field);
		result.setMethod(method);

		return result;
	}

	private MethodStructure assembleMethodList() {
		MethodStructure result = new MethodStructure();

		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure transactional = new AnnotationStructure();
		transactional.setName(ANNOTATIONS_TRANSACTIONAL);
		Map<String, String> attribute = new HashMap<String, String>();
		attribute.put(ANNOTATIONS_ATTRIBUTE_READONLY, TRUE);
		transactional.setAttribute(attribute);
		AnnotationStructure override = new AnnotationStructure();
		override.setName(ANNOTATIONS_OVERRIDE);
		annotation.add(transactional);
		annotation.add(override);

		String returnValue = LIST + LEFT_ANGLE_BRACKETS + BaseInfo.getDomainClassName() + RIGHT_ANGLE_BRACKETS;
		String methodName = ConstantMethodName.LIST;

		String mapperInterfaceName = BaseInfo.getMapperInterfaceName();
		String methodBody = TAB + TAB + RETURN + SPACE + FileNameTool.firstLetterToLowerCase(mapperInterfaceName)
				+ POINT + ConstantMethodName.LIST + LEFT_BRACKET + RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE;

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(returnValue);
		result.setName(methodName);
		result.setMethodBody(methodBody);
		return result;
	}

	private MethodStructure assembleMethodGetById() {
		MethodStructure result = new MethodStructure();

		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure transactional = new AnnotationStructure();
		transactional.setName(ANNOTATIONS_TRANSACTIONAL);
		Map<String, String> attribute = new HashMap<String, String>();
		attribute.put(ANNOTATIONS_ATTRIBUTE_READONLY, TRUE);
		transactional.setAttribute(attribute);
		AnnotationStructure override = new AnnotationStructure();
		override.setName(ANNOTATIONS_OVERRIDE);
		annotation.add(transactional);
		annotation.add(override);

		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(STRING, ConstantParameterName.ID);

		String mapperInterfaceName = BaseInfo.getMapperInterfaceName();
		String methodBody = TAB + TAB + RETURN + SPACE + FileNameTool.firstLetterToLowerCase(mapperInterfaceName)
				+ POINT + ConstantMethodName.GET_BY_ID + LEFT_BRACKET + ConstantParameterName.ID + RIGHT_BRACKET
				+ SEMICOLON + RETURN_NEWLINE;

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(BaseInfo.getDomainClassName());
		result.setName(ConstantMethodName.GET_BY_ID);
		result.setParameter(parameter);
		result.setMethodBody(methodBody);
		return result;
	}

	private MethodStructure assembleMethodSave() {
		MethodStructure result = new MethodStructure();

		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure transactional = new AnnotationStructure();
		transactional.setName(ANNOTATIONS_TRANSACTIONAL);
		AnnotationStructure override = new AnnotationStructure();
		override.setName(ANNOTATIONS_OVERRIDE);
		annotation.add(transactional);
		annotation.add(override);

		Map<String, String> parameter = new HashMap<String, String>();
		String domainClassName = BaseInfo.getDomainClassName();
		parameter.put(domainClassName, FileNameTool.firstLetterToLowerCase(domainClassName));

		String mapperInterfaceName = BaseInfo.getMapperInterfaceName();
		String methodBody = TAB + TAB + FileNameTool.firstLetterToLowerCase(mapperInterfaceName) + POINT
				+ ConstantMethodName.SAVE + LEFT_BRACKET + FileNameTool.firstLetterToLowerCase(domainClassName)
				+ RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE;

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.SAVE);
		result.setParameter(parameter);
		result.setMethodBody(methodBody);
		return result;
	}

	private MethodStructure assembleMethodUpdateById() {
		MethodStructure result = new MethodStructure();

		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure transactional = new AnnotationStructure();
		transactional.setName(ANNOTATIONS_TRANSACTIONAL);
		AnnotationStructure override = new AnnotationStructure();
		override.setName(ANNOTATIONS_OVERRIDE);
		annotation.add(transactional);
		annotation.add(override);

		Map<String, String> parameter = new HashMap<String, String>();
		String domainClassName = BaseInfo.getDomainClassName();
		parameter.put(domainClassName, FileNameTool.firstLetterToLowerCase(domainClassName));

		String mapperInterfaceName = BaseInfo.getMapperInterfaceName();
		String methodBody = TAB + TAB + FileNameTool.firstLetterToLowerCase(mapperInterfaceName) + POINT
				+ ConstantMethodName.UPDATE_BY_ID + LEFT_BRACKET + FileNameTool.firstLetterToLowerCase(domainClassName)
				+ RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE;

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.UPDATE_BY_ID);
		result.setParameter(parameter);
		result.setMethodBody(methodBody);
		return result;
	}

	private MethodStructure assembleMethodRemoveByIds() {
		MethodStructure result = new MethodStructure();

		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure transactional = new AnnotationStructure();
		transactional.setName(ANNOTATIONS_TRANSACTIONAL);
		AnnotationStructure override = new AnnotationStructure();
		override.setName(ANNOTATIONS_OVERRIDE);
		annotation.add(transactional);
		annotation.add(override);

		Map<String, String> parameter = new HashMap<String, String>();
		String type = LIST + LEFT_ANGLE_BRACKETS + STRING + RIGHT_ANGLE_BRACKETS;
		parameter.put(type, ConstantParameterName.IDS);

		String mapperInterfaceName = BaseInfo.getMapperInterfaceName();
		String methodBody = TAB + TAB + FileNameTool.firstLetterToLowerCase(mapperInterfaceName) + POINT
				+ ConstantMethodName.REMOVE_BY_IDS + LEFT_BRACKET + ConstantParameterName.IDS + RIGHT_BRACKET
				+ SEMICOLON + RETURN_NEWLINE;

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.REMOVE_BY_IDS);
		result.setParameter(parameter);
		result.setMethodBody(methodBody);
		return result;
	}
}
