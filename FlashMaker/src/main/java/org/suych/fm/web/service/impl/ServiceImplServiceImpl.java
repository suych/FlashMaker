package org.suych.fm.web.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_AUTOWIRED;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_SERVICE;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantClassAccessModifier;
import org.suych.fm.constant.ConstantFieldAccessModifier;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.FileNameTool;
import org.suych.fm.util.generate.GenerateClassUtil;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.ClassStructure;
import org.suych.fm.util.generate.model.java.FieldStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.IServiceImplService;
import org.suych.fm.web.service.strategy.serviceimpl.ServiceImplMethodFactory;

@Service
public class ServiceImplServiceImpl implements IServiceImplService {

	@Autowired
	ServiceImplMethodFactory factory;

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
		List<MethodStructure> result = new ArrayList<MethodStructure>();
		// 1.List<DO类名> list()
		result.add(factory.assemble(ConstantStrategyComponentName.SERVICE_IMPL_LIST));
		// 2.DO类名 getByPrimaryKey(String id);
		result.add(factory.assemble(ConstantStrategyComponentName.SERVICE_IMPL_GET_BY_PRIMARYKEY));
		// 3.void save(DO类名 DO类名首字母小写);
		result.add(factory.assemble(ConstantStrategyComponentName.SERVICE_IMPL_SAVE));
		// 4.void saveSelective(DO类名 DO类名首字母小写);
		result.add(factory.assemble(ConstantStrategyComponentName.SERVICE_IMPL_SAVE_SELECTIVE));
		// 5.void updateByPrimaryKeySelective(DO类名 DO类名首字母小写);
		result.add(factory.assemble(ConstantStrategyComponentName.SERVICE_IMPL_UPDATE_BY_PRIMARYKEY_SELECTIVE));
		// 6.void removeByPrimaryKeys(List<String> ids);
		result.add(factory.assemble(ConstantStrategyComponentName.SERVICE_IMPL_REMOVE_BY_PRIMARYKEYS));
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

}
