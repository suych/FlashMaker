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

import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantClassAccessModifier;
import org.suych.fm.constant.ConstantClassNonAccessModifier;
import org.suych.fm.constant.ConstantFieldAccessModifier;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantOracleType;
import org.suych.fm.util.StringUtil;
import org.suych.fm.util.generate.GenerateClassUtil;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.ClassStructure;
import org.suych.fm.util.generate.model.java.FieldStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.model.model.FieldInfoModel;
import org.suych.fm.web.model.model.ResultDoubleModel;
import org.suych.fm.web.service.IDomainObjectClassService;

@Service
public class DomainObjectClassServiceImpl implements IDomainObjectClassService {

	@Override
	public void generate() {
		// 1.获得引入包名和字段结构
		ResultDoubleModel<Set<String>, List<FieldStructure>> importPackageAndFieldStructure = assembleImportPackageAndFieldStructure();
		// 2.组装方法结构
		List<MethodStructure> method = assembleMethodStructure(importPackageAndFieldStructure.second);
		// 3.组装Domain类结构
		ClassStructure doClassStructure = assembleClassStructure(importPackageAndFieldStructure.first,
				importPackageAndFieldStructure.second, method);
		// 4.按规范输出至文件
		GenerateClassUtil.generate(doClassStructure);
	}

	/**
	 * 获得引入包名和字段结构
	 * 
	 * @return
	 */
	private ResultDoubleModel<Set<String>, List<FieldStructure>> assembleImportPackageAndFieldStructure() {
		Set<String> importPackage = new HashSet<String>(); // 引用的包名
		List<FieldStructure> fieldStructure = new ArrayList<FieldStructure>(); // 字段类型/字段名/字段注释
		for (FieldInfoModel field : BaseInfo.getTableInfo().getField()) {
			FieldStructure fs = new FieldStructure();
			String data_type = parseDataType(field.getDataType());
			String data_precision = StringUtil.null2Empty(field.getDataPrecision());
			String javaType = parseJavaType(data_type, data_precision);

			// 组装字段结构
			fs.setComments(StringUtil.null2Empty(field.getComments()));
			fs.setAccessModifier(ConstantFieldAccessModifier.PRIVATE);
			fs.setJavaType(javaType);
			fs.setName(StringUtil.null2Empty(field.getColumnName()));
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
		return new ResultDoubleModel<Set<String>, List<FieldStructure>>(importPackage, fieldStructure);
	}

	/**
	 * 组装方法结构
	 * 
	 * @param field 字段结构
	 * @return
	 */
	private List<MethodStructure> assembleMethodStructure(List<FieldStructure> field) {
		List<MethodStructure> result = new ArrayList<MethodStructure>();
		// toString()方法
		MethodStructure toString = assembleToStringMethod(field);
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
	 * 组装DO类结构
	 * 
	 * @param importPackage 引入包名
	 * @param field 字段结构
	 * @param method 方法结构
	 * @return
	 */
	private ClassStructure assembleClassStructure(Set<String> importPackage, List<FieldStructure> field,
			List<MethodStructure> method) {
		ClassStructure result = new ClassStructure();
		// 组装类结构
		result.setLocalPackage(BaseInfo.getLocalPackage());
		result.setImportPackage(importPackage);
		result.setComments(StringUtil.null2Empty(BaseInfo.getTableInfo().getComments()));
		result.setAcessModifier(ConstantClassAccessModifier.PUBLIC);
		result.setNonAccessModifier(ConstantClassNonAccessModifier.DEFAULT);
		result.setName(BaseInfo.getDomainClassName());
		result.setField(field);
		result.setMethod(method);

		return result;
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

	private MethodStructure assembleToStringMethod(List<FieldStructure> field) {
		MethodStructure result = new MethodStructure();
		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure as = new AnnotationStructure();
		as.setName(ANNOTATIONS_OVERRIDE);
		annotation.add(as);
		StringBuilder methodBody = new StringBuilder();
		String className = BaseInfo.getDomainClassName();
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