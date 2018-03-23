package org.suych.fm.web.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.LIST;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.STRING;
import static org.suych.fm.constant.ConstantJavaSyntax.VOID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantInterfaceAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.tool.FileNameTool;
import org.suych.fm.util.generate.GenerateInterfaceUtil;
import org.suych.fm.util.generate.model.java.InterfaceStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.IServiceInterfaceService;

@Service
public class ServiceInterfaceServiceImpl implements IServiceInterfaceService {

	@Override
	public void generate() {
		// 1.组装方法结构
		List<MethodStructure> method = assembleMethodStructure();
		// 2.组装Mapper接口结构
		InterfaceStructure serviceInterface = assembleMapperInterface(method);
		// 3.按规范输出至文件
		GenerateInterfaceUtil.generate(serviceInterface);
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
	 * 组装接口结构
	 * 
	 * @param method
	 * @return
	 */
	private InterfaceStructure assembleMapperInterface(List<MethodStructure> method) {
		InterfaceStructure result = new InterfaceStructure();
		String localPackage = BaseInfo.getLocalPackage();
		Set<String> importPackage = new HashSet<String>();
		importPackage.add(ConstantImportPackage.LIST);
		String domainPackage = localPackage + POINT + BaseInfo.getDomainClassName(); // DO文件包名
		importPackage.add(domainPackage);

		result.setLocalPackage(localPackage);
		result.setImportPackage(importPackage);
		result.setAcessModifier(ConstantInterfaceAccessModifier.PUBLIC);
		result.setName(BaseInfo.getServiceInterfaceName());
		result.setMethod(method);
		return result;
	}

	private MethodStructure assembleMethodList() {
		MethodStructure result = new MethodStructure();
		String domainClassName = BaseInfo.getDomainClassName();
		String returnValue = LIST + LEFT_ANGLE_BRACKETS + domainClassName + RIGHT_ANGLE_BRACKETS;
		Map<String, String> parameter = new HashMap<String, String>();
		result.setReturnValue(returnValue);
		result.setName(ConstantMethodName.LIST);
		result.setParameter(parameter);
		return result;
	}

	private MethodStructure assembleMethodGetById() {
		MethodStructure result = new MethodStructure();
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(STRING, ConstantParameterName.ID);
		result.setReturnValue(BaseInfo.getDomainClassName());
		result.setName(ConstantMethodName.GET_BY_ID);
		result.setParameter(parameter);
		return result;
	}

	private MethodStructure assembleMethodSave() {
		MethodStructure result = new MethodStructure();
		String domainClassName = BaseInfo.getDomainClassName();
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(domainClassName, FileNameTool.firstLetterToLowerCase(domainClassName));
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.SAVE);
		result.setParameter(parameter);
		return result;
	}

	private MethodStructure assembleMethodUpdateById() {
		MethodStructure result = new MethodStructure();
		String domainClassName = BaseInfo.getDomainClassName();
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(domainClassName, FileNameTool.firstLetterToLowerCase(domainClassName));
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.UPDATE_BY_ID);
		result.setParameter(parameter);
		return result;
	}

	private MethodStructure assembleMethodRemoveByIds() {
		MethodStructure result = new MethodStructure();
		Map<String, String> parameter = new HashMap<String, String>();
		String type = LIST + LEFT_ANGLE_BRACKETS + STRING + RIGHT_ANGLE_BRACKETS;
		parameter.put(type, ConstantParameterName.IDS);
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.REMOVE_BY_IDS);
		result.setParameter(parameter);
		return result;
	}

}
