package org.suych.fm.web.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_OVERRIDE;
import static org.suych.fm.constant.ConstantJavaSyntax.BIGDECIMAL;
import static org.suych.fm.constant.ConstantJavaSyntax.BOOLEAN;
import static org.suych.fm.constant.ConstantJavaSyntax.BYTE;
import static org.suych.fm.constant.ConstantJavaSyntax.BYTE_ARRAY;
import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.DOUBLE_QUOTATION;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.FLOAT;
import static org.suych.fm.constant.ConstantJavaSyntax.INTEGER;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_SQUARE_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.LIST;
import static org.suych.fm.constant.ConstantJavaSyntax.LONG;
import static org.suych.fm.constant.ConstantJavaSyntax.PLUS_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_SQUARE_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SHORT;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.STRING;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;
import static org.suych.fm.constant.ConstantJavaSyntax.THIS;
import static org.suych.fm.constant.ConstantJavaSyntax.TIMESTAMP;
import static org.suych.fm.constant.ConstantJavaSyntax.VOID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suych.fm.constant.ConstantClassAccessModifier;
import org.suych.fm.constant.ConstantClassNonAccessModifier;
import org.suych.fm.constant.ConstantFieldAccessModifier;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantOracleType;
import org.suych.fm.constant.ConstantSuffix;
import org.suych.fm.tool.CommonTool;
import org.suych.fm.util.StringUtil;
import org.suych.fm.util.generate.GenerateClassUtil;
import org.suych.fm.util.generate.model.ClassStructure;
import org.suych.fm.util.generate.model.FieldStructure;
import org.suych.fm.util.generate.model.MethodStructure;
import org.suych.fm.web.mapper.TableInfoMapper;
import org.suych.fm.web.model.domain.FieldInfoDO;
import org.suych.fm.web.model.domain.TableInfoDO;
import org.suych.fm.web.model.dto.ResultDoubleDTO;
import org.suych.fm.web.service.IDomainObjectService;

@Service
public class DomainObjectServiceImpl implements IDomainObjectService {

	@Autowired
	TableInfoMapper tableInfoMapper;

	@Override
	public ClassStructure generate(String tableName, String localPackage) {
		// 1.获得引入包名和字段结构(查询数据库)
		ResultDoubleDTO<Set<String>, List<FieldStructure>> importPackageAndFieldStructure = getImportPackageAndFieldStructure(
				tableName);
		// 2.获得表信息(查询数据库)
		TableInfoDO tableInfo = getTableInfo(tableName);
		// 3.组装方法结构
		List<MethodStructure> method = assembleMethodStructure(tableName, importPackageAndFieldStructure.second);
		// 4.组装DO类结构
		ClassStructure doClassStructure = assembleDOClassStructure(localPackage, importPackageAndFieldStructure.first,
				tableInfo, tableName, importPackageAndFieldStructure.second, method);
		// 5.按规范输出至文件
		GenerateClassUtil.generate(doClassStructure);
		return doClassStructure;
	}

	/**
	 * 获得引入包名和字段结构(查询数据库)
	 * 
	 * @param tableName 数据库表名
	 * @return
	 */
	private ResultDoubleDTO<Set<String>, List<FieldStructure>> getImportPackageAndFieldStructure(String tableName) {
		List<FieldInfoDO> fieldInfoDO = tableInfoMapper.listFieldInfo(tableName.toUpperCase());
		return parseImportPackageAndFieldStructure(fieldInfoDO);
	}

	private List<MethodStructure> assembleMethodStructure(String tableName, List<FieldStructure> field) {
		List<MethodStructure> result = new ArrayList<MethodStructure>();
		// toString()方法
		MethodStructure toString = assembleToStringMethod(tableName, field);
		result.add(toString);
		// get/set方法
		for (FieldStructure t : field) {
			String fieldName = t.getName();
			String javaType = t.getJavaType();
			String fieldNameFirstChar = fieldName.substring(0, 1).toUpperCase(); // 字段首字母大写
			String fieldNameOtherChar = fieldName.substring(1);
			String getMethodName = ConstantMethodName.GET + fieldNameFirstChar + fieldNameOtherChar;
			String setMethodName = ConstantMethodName.SET + fieldNameFirstChar + fieldNameOtherChar;

			MethodStructure get = assembleGetMethod(fieldName, javaType, getMethodName);
			result.add(get);

			MethodStructure set = assembleSetMethod(fieldName, javaType, setMethodName);
			result.add(set);
		}
		return result;
	}

	/**
	 * 获得表信息(查询数据库)
	 * 
	 * @param tableName
	 * @return
	 */
	private TableInfoDO getTableInfo(String tableName) {
		return tableInfoMapper.getTableInfo(tableName.toUpperCase());
	}

	/**
	 * 组装DO类结构
	 * 
	 * @param localPackage 本地包名
	 * @param importPackage 引入包名
	 * @param tableName 表名
	 * @param field 字段结构
	 * @param method 方法结构
	 * @return
	 */
	private ClassStructure assembleDOClassStructure(String localPackage, Set<String> importPackage,
			TableInfoDO tableInfo, String tableName, List<FieldStructure> field, List<MethodStructure> method) {
		ClassStructure result = new ClassStructure();
		String comments = "";
		if (tableInfo != null) {
			comments = StringUtil.null2Empty(tableInfo.getComments());
		}
		String className = CommonTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.CLASS_DOMAIN_OBJECT);

		// 组装类结构
		result.setLocalPackage(localPackage);
		result.setImportPackage(importPackage);
		result.setComments(comments);
		result.setAcessModifier(ConstantClassAccessModifier.PUBLIC);
		result.setNonAccessModifier(ConstantClassNonAccessModifier.DEFAULT);
		result.setName(className);
		result.setField(field);
		result.setMethod(method);

		return result;
	}

	private ResultDoubleDTO<Set<String>, List<FieldStructure>> parseImportPackageAndFieldStructure(
			List<FieldInfoDO> fieldInfoDOs) {
		Set<String> importPackage = new HashSet<String>(); // 引用的包名
		List<FieldStructure> fieldStructure = new ArrayList<FieldStructure>(); // 字段类型/字段名/字段注释
		for (FieldInfoDO fieldDO : fieldInfoDOs) {
			FieldStructure fs = new FieldStructure();
			String data_type = parseDataType(fieldDO.getData_type());
			String data_precision = StringUtil.null2Empty(fieldDO.getData_precision());
			String javaType = parseJavaType(data_type, data_precision);

			// 组装字段结构
			fs.setComments(StringUtil.null2Empty(fieldDO.getComments()));
			fs.setAccessModifier(ConstantFieldAccessModifier.PRIVATE);
			fs.setJavaType(javaType);
			fs.setName(StringUtil.null2Empty(fieldDO.getColumn_name()).toLowerCase());
			fieldStructure.add(fs);

			// 组装引入包名
			if (TIMESTAMP.equals(javaType)) {
				importPackage.add(ConstantImportPackage.TIMESTAMP);
			} else if (BIGDECIMAL.equals(javaType)) {
				importPackage.add(ConstantImportPackage.BIGDECIMAL);
			} else if (LIST.equals(javaType)) {
				importPackage.add(ConstantImportPackage.LIST);
			}

		}
		return new ResultDoubleDTO<Set<String>, List<FieldStructure>>(importPackage, fieldStructure);
	}

	private String parseDataType(String data_type) {
		data_type = data_type.toUpperCase();
		Pattern pattern_type = Pattern.compile(".*?(?=\\()");
		Matcher matcher_type = pattern_type.matcher(data_type);
		return matcher_type.find() ? matcher_type.group(0) : data_type;
	}

	private String parseJavaType(String data_type, String data_precision) {
		if (ConstantOracleType.VARCHAR2.equals(data_type) || ConstantOracleType.CHAR.equals(data_type)
				|| ConstantOracleType.NVARCHAR2.equals(data_type) || ConstantOracleType.CLOB.equals(data_type)) {
			return STRING;
		}
		if (ConstantOracleType.DATE.equals(data_type) || ConstantOracleType.TIMESTAMP.equals(data_type)) {
			return TIMESTAMP;
		}
		if (ConstantOracleType.BLOB.equals(data_type)) {
			return BYTE_ARRAY;
		}
		if (ConstantOracleType.INTEGER.equals(data_type)) {
			return INTEGER;
		}
		if (ConstantOracleType.LONG.equals(data_type)) {
			return LONG;
		}
		if (ConstantOracleType.FLOAT.equals(data_type)) {
			return FLOAT;
		}

		if (ConstantOracleType.NUMBER.equals(data_type)) {
			if ("".equals(data_precision)) {
				return BIGDECIMAL;
			} else {
				int i = Integer.valueOf(data_precision);
				if (i == 1) {
					return BOOLEAN;
				} else if (i == 2) {
					return BYTE;
				} else if (3 <= i && i <= 4) {
					return SHORT;
				} else if (5 <= i && i <= 9) {
					return INTEGER;
				} else if (10 <= i && i <= 18) {
					return LONG;
				} else if (19 <= i && i <= 38) {
					return BIGDECIMAL;
				}
			}
		}
		return "UnknowType"; // 其他类型暂未UnknowType，方便后续扩展
	}

	private MethodStructure assembleToStringMethod(String tableName, List<FieldStructure> field) {
		MethodStructure result = new MethodStructure();
		List<String> annotation = new ArrayList<String>();
		annotation.add(ANNOTATIONS_OVERRIDE);
		StringBuilder methodBody = new StringBuilder();
		String className = CommonTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.CLASS_DOMAIN_OBJECT);
		methodBody.append(TAB + TAB + RETURN + SPACE + DOUBLE_QUOTATION + className + SPACE + LEFT_SQUARE_BRACKET);
		for (int i = 0, j = field.size(); i < j; i++) {
			String fieldName = field.get(i).getName();
			if (i != j - 1) {
				methodBody.append(fieldName + EQUAL_SIGN + DOUBLE_QUOTATION + SPACE + PLUS_SIGN + SPACE + fieldName
						+ SPACE + PLUS_SIGN + SPACE + DOUBLE_QUOTATION + COMMA + SPACE);
			} else {
				methodBody.append(fieldName + EQUAL_SIGN + DOUBLE_QUOTATION + SPACE + PLUS_SIGN + SPACE + fieldName
						+ SPACE + PLUS_SIGN + SPACE + DOUBLE_QUOTATION + RIGHT_SQUARE_BRACKET + DOUBLE_QUOTATION
						+ SEMICOLON + RETURN_NEWLINE);
			}
		}
		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(STRING);
		result.setName(ConstantMethodName.TO_STRING);
		result.setMethodBody(methodBody.toString());
		return result;
	}

	private MethodStructure assembleGetMethod(String fieldName, String javaType, String getMethodName) {
		MethodStructure result = new MethodStructure();
		String methodBody = TAB + TAB + RETURN + SPACE + fieldName + SEMICOLON + RETURN_NEWLINE;
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(javaType);
		result.setName(getMethodName);
		result.setMethodBody(methodBody);
		return result;
	}

	private MethodStructure assembleSetMethod(String fieldName, String javaType, String setMethodName) {
		MethodStructure result = new MethodStructure();
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(javaType, fieldName);
		String methodBody = TAB + TAB + THIS + POINT + fieldName + SPACE + EQUAL_SIGN + SPACE + fieldName + SEMICOLON
				+ RETURN_NEWLINE;
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(VOID);
		result.setName(setMethodName);
		result.setParameter(parameter);
		result.setMethodBody(methodBody);
		return result;
	}

}
