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
import org.suych.fm.util.generate.GenerateInterfaceUtil;
import org.suych.fm.util.generate.model.java.InterfaceStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.IMapperService;

@Service
public class MapperServiceImpl implements IMapperService {

	@Override
	public void generate() {
		// 1.组装方法结构
		List<MethodStructure> method = assembleMethodStructure();
		// 2.组装Mapper接口结构
		InterfaceStructure mapperInterface = assembleMapperInterface(method);
		// 3.按规范输出至文件
		GenerateInterfaceUtil.generate(mapperInterface);
	}

	private List<MethodStructure> assembleMethodStructure() {
		String domainClassName = BaseInfo.getDomainClassName();
		// 5个方法
		List<MethodStructure> result = new ArrayList<MethodStructure>();

		// 1.List<DO类名> list()
		MethodStructure m1 = new MethodStructure();
		String m1_returnValue = LIST + LEFT_ANGLE_BRACKETS + domainClassName + RIGHT_ANGLE_BRACKETS;
		String m1_methodName = ConstantMethodName.LIST;
		Map<String, String> m1_parameter = new HashMap<String, String>();
		m1.setReturnValue(m1_returnValue);
		m1.setName(m1_methodName);
		m1.setParameter(m1_parameter);

		// 2.DO类名 getById(String id);
		MethodStructure m2 = new MethodStructure();
		String m2_returnValue = domainClassName;
		String m2_methodName = ConstantMethodName.GET_BY_ID;
		Map<String, String> m2_parameter = new HashMap<String, String>();
		m2_parameter.put(STRING, ConstantParameterName.ID);
		m2.setReturnValue(m2_returnValue);
		m2.setName(m2_methodName);
		m2.setParameter(m2_parameter);

		// 3.void save(DO类名 DO类名首字母小写);
		MethodStructure m3 = new MethodStructure();
		String m3_returnValue = VOID;
		String m3_methodName = ConstantMethodName.SAVE;
		Map<String, String> m3_parameter = new HashMap<String, String>();
		m3_parameter.put(domainClassName, firstLetterToLowerCase(domainClassName));
		m3.setReturnValue(m3_returnValue);
		m3.setName(m3_methodName);
		m3.setParameter(m3_parameter);

		// 4.void updateById(DO类名 DO类名首字母小写);
		MethodStructure m4 = new MethodStructure();
		String m4_returnValue = VOID;
		String m4_methodName = ConstantMethodName.UPDATE_BY_ID;
		Map<String, String> m4_parameter = new HashMap<String, String>();
		m4_parameter.put(domainClassName, firstLetterToLowerCase(domainClassName));
		m4.setReturnValue(m4_returnValue);
		m4.setName(m4_methodName);
		m4.setParameter(m4_parameter);

		// 5.void removeByIds(List<String> ids);
		MethodStructure m5 = new MethodStructure();
		String m5_returnValue = VOID;
		String m5_methodName = ConstantMethodName.REMOVE_BY_IDS;
		Map<String, String> m5_parameter = new HashMap<String, String>();
		String type = LIST + LEFT_ANGLE_BRACKETS + STRING + RIGHT_ANGLE_BRACKETS;
		m5_parameter.put(type, ConstantParameterName.IDS);
		m5.setReturnValue(m5_returnValue);
		m5.setName(m5_methodName);
		m5.setParameter(m5_parameter);

		result.add(m1);
		result.add(m2);
		result.add(m3);
		result.add(m4);
		result.add(m5);
		return result;
	}

	private InterfaceStructure assembleMapperInterface(List<MethodStructure> method) {
		InterfaceStructure result = new InterfaceStructure();
		String localPackage = BaseInfo.getLocalPackage();
		Set<String> importPackage = new HashSet<String>();
		importPackage.add(ConstantImportPackage.LIST);
		String domainPackage = localPackage + POINT + BaseInfo.getDomainClassName(); // DO文件包名
		importPackage.add(domainPackage);
		String interfaceName = BaseInfo.getMapperInterfaceName();

		result.setLocalPackage(localPackage);
		result.setImportPackage(importPackage);
		result.setAcessModifier(ConstantInterfaceAccessModifier.PUBLIC);
		result.setName(interfaceName);
		result.setMethod(method);
		return result;
	}

	private String firstLetterToLowerCase(String param) {
		String result = "";
		if (param.length() > 1) {
			String classNameFirstChar = param.substring(0, 1).toLowerCase(); // 类名首字母小写
			String classNameOtherChar = param.substring(1);
			result = classNameFirstChar + classNameOtherChar;
		} else {
			result = param.toUpperCase();
		}
		return result;
	}

}
