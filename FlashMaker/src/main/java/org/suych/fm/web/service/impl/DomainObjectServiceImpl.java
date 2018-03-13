package org.suych.fm.web.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_OVERRIDE;
import static org.suych.fm.constant.ConstantJavaSyntax.ASTERISK;
import static org.suych.fm.constant.ConstantJavaSyntax.BIGDECIMAL;
import static org.suych.fm.constant.ConstantJavaSyntax.BOOLEAN;
import static org.suych.fm.constant.ConstantJavaSyntax.BYTE;
import static org.suych.fm.constant.ConstantJavaSyntax.BYTE_ARRAY;
import static org.suych.fm.constant.ConstantJavaSyntax.CLASS;
import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.DOUBLE_QUOTATION;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.FLOAT;
import static org.suych.fm.constant.ConstantJavaSyntax.IMPORT;
import static org.suych.fm.constant.ConstantJavaSyntax.INTEGER;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_SQUARE_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.LONG;
import static org.suych.fm.constant.ConstantJavaSyntax.PACKAGE;
import static org.suych.fm.constant.ConstantJavaSyntax.PLUS_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.PRIVATE;
import static org.suych.fm.constant.ConstantJavaSyntax.PUBLIC;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_SQUARE_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SHORT;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.STRING;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;
import static org.suych.fm.constant.ConstantJavaSyntax.THIS;
import static org.suych.fm.constant.ConstantJavaSyntax.TIMESTAMP;
import static org.suych.fm.constant.ConstantJavaSyntax.VOID;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suych.fm.constant.ConfigureContainer;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantMethod;
import org.suych.fm.constant.ConstantOracleType;
import org.suych.fm.constant.ConstantSuffix;
import org.suych.fm.utils.StringUtil;
import org.suych.fm.web.mapper.TableInfoMapper;
import org.suych.fm.web.model.bo.FieldInfoBO;
import org.suych.fm.web.model.domain.FieldInfoDO;
import org.suych.fm.web.model.domain.TableInfoDO;
import org.suych.fm.web.model.dto.DomainObjectDTO;
import org.suych.fm.web.model.dto.ResultDoubleDTO;
import org.suych.fm.web.service.IDomainObjectService;

@Service
public class DomainObjectServiceImpl implements IDomainObjectService {

	@Autowired
	TableInfoMapper tableInfoMapper;

	@Override
	public ResultDoubleDTO<Set<String>, List<FieldInfoBO>> getImportPackageAndlistTableInfo(String tableName) {
		Set<String> importPackage = new HashSet<String>(); // 引用的包名
		List<FieldInfoBO> fieldInfoBOs = new ArrayList<FieldInfoBO>(); // 字段类型/字段名/字段注释
		List<FieldInfoDO> fieldInfoDOs = tableInfoMapper.listFieldInfo(tableName.toUpperCase());
		assembleImportPackageAndFieldInfo(importPackage, fieldInfoBOs, fieldInfoDOs);
		return new ResultDoubleDTO<Set<String>, List<FieldInfoBO>>(importPackage, fieldInfoBOs);
	}

	@Override
	public TableInfoDO getTableInfo(String tableName) {
		return tableInfoMapper.getTableInfo(tableName.toUpperCase());
	}

	@Override
	public DomainObjectDTO assembleDO(String localPackage, Set<String> importPackage, TableInfoDO tableInfo,
			String tableName, List<FieldInfoBO> fieldInfo) {
		DomainObjectDTO result = new DomainObjectDTO();
		result.setLocalPackage(StringUtil.null2Empty(localPackage));
		result.setImportPackage(importPackage);
		if (tableInfo != null) {
			result.setClassComments(StringUtil.null2Empty(tableInfo.getComments()));
		} else {
			result.setClassComments("");
		}
		String className = assembleClassName(tableName);
		result.setClassName(className);
		result.setFieldInfo(fieldInfo);
		return result;
	}

	@Override
	public void generateJavaFile(DomainObjectDTO doDTO) {
		FileWriter fw = null;
		try {
			String pathName = ConfigureContainer.constantMap.get("file.output.path") + doDTO.getClassName()
					+ ConstantSuffix.JAVA_FILE;
			File file = new File(pathName);
			if (!file.exists()) {
				File parentFile = file.getParentFile();
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				file.createNewFile();
			}
			fw = new FileWriter(file);
			// 输出至文件
			print2File(doDTO, fw);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void assembleImportPackageAndFieldInfo(Set<String> importPackage, List<FieldInfoBO> fieldInfoBOs,
			List<FieldInfoDO> fieldInfoDOs) {
		for (FieldInfoDO t : fieldInfoDOs) {
			FieldInfoBO r = new FieldInfoBO();
			r.setColumnName(StringUtil.null2Empty(t.getColumn_name()).toLowerCase());
			r.setComments(StringUtil.null2Empty(t.getComments()));
			String data_type = parseDataType(t.getData_type());
			String data_precision = StringUtil.null2Empty(t.getData_precision());
			String javaType = parseJavaType(data_type, data_precision);
			r.setJavaType(javaType);
			importPackage.add(javaType);
			fieldInfoBOs.add(r);
		}
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

	private String assembleClassName(String tableName) {
		String result = "";
		if (tableName.length() > 1) {
			String classNameFirstChar = tableName.substring(0, 1).toUpperCase(); // 类名首字母大写
			String classNameOtherChar = tableName.substring(1);
			result = classNameFirstChar + classNameOtherChar;
		} else {
			result = tableName.toUpperCase();
		}
		result += ConstantSuffix.DOMAIN_OBJECT_CLASS_NAME;
		return result;
	}

	private void print2File(DomainObjectDTO doDTO, FileWriter fw) throws IOException {
		String localPackage = doDTO.getLocalPackage();
		Set<String> importPackage = doDTO.getImportPackage();
		String classComments = doDTO.getClassComments();
		String className = doDTO.getClassName();
		List<FieldInfoBO> fieldInfo = doDTO.getFieldInfo();
		// 1.本地包名
		printLocalPackage(fw, localPackage);
		fw.write(RETURN_NEWLINE);
		// 2.引入包名
		printImportPackage(fw, importPackage);
		fw.write(RETURN_NEWLINE);
		// 3.表注释
		printClassComments(fw, classComments);
		// 4.类名
		printClassName(fw, className);
		// 5.修饰符+字段类型+字段名称
		printField(fw, fieldInfo);
		fw.write(RETURN_NEWLINE);
		// 6.toString()方法
		printToString(fw, className, fieldInfo);
		fw.write(RETURN_NEWLINE);
		// 7.get/set方法
		printGetSet(fw, fieldInfo);
		fw.write(RIGHT_BRACE);
	}

	private void printLocalPackage(FileWriter fw, String localPackage) throws IOException {
		fw.write(PACKAGE + SPACE + localPackage + SEMICOLON + RETURN_NEWLINE);
	}

	private void printImportPackage(FileWriter fw, Set<String> importPackage) throws IOException {
		for (String ip : importPackage) {
			if (TIMESTAMP.equals(ip)) {
				fw.write(IMPORT + SPACE + ConstantImportPackage.TIMESTAMP + SEMICOLON + RETURN_NEWLINE);
			} else if (BIGDECIMAL.equals(ip)) {
				fw.write(IMPORT + SPACE + ConstantImportPackage.BIGDECIMAL + SEMICOLON + RETURN_NEWLINE);
			}
		}
	}

	private void printClassComments(FileWriter fw, String classComments) throws IOException {
		if (!"".equals(classComments)) {
			fw.write(SLASH + ASTERISK + ASTERISK + RETURN_NEWLINE);
			fw.write(SPACE + ASTERISK + SPACE + classComments + RETURN_NEWLINE);
			fw.write(SPACE + ASTERISK + SLASH + RETURN_NEWLINE);
		}
	}

	private void printClassName(FileWriter fw, String className) throws IOException {
		fw.write(PUBLIC + SPACE + CLASS + SPACE + className + SPACE + LEFT_BRACE + RETURN_NEWLINE);
	}

	private void printField(FileWriter fw, List<FieldInfoBO> fieldInfo) throws IOException {
		for (FieldInfoBO t : fieldInfo) {
			fw.write(RETURN_NEWLINE);
			String columnName = t.getColumnName();
			String javaType = t.getJavaType();
			String comments = t.getComments();
			if (!"".equals(comments)) {
				fw.write(TAB + SLASH + ASTERISK + ASTERISK + RETURN_NEWLINE);
				fw.write(TAB + SPACE + ASTERISK + SPACE + comments + RETURN_NEWLINE);
				fw.write(TAB + SPACE + ASTERISK + SLASH + RETURN_NEWLINE);
			}
			fw.write(TAB + PRIVATE + SPACE + javaType + SPACE + columnName + SEMICOLON + RETURN_NEWLINE);
		}
	}

	private void printToString(FileWriter fw, String className, List<FieldInfoBO> fieldInfo) throws IOException {
		fw.write(TAB + ANNOTATIONS_OVERRIDE + RETURN_NEWLINE);
		fw.write(TAB + PUBLIC + SPACE + STRING + SPACE + ConstantMethod.TO_STRING + LEFT_BRACKET + RIGHT_BRACKET + SPACE
				+ LEFT_BRACE + RETURN_NEWLINE);
		fw.write(TAB + TAB + RETURN + SPACE + DOUBLE_QUOTATION + className + SPACE + LEFT_SQUARE_BRACKET);
		for (int i = 0, j = fieldInfo.size(); i < j; i++) {
			String columnName = fieldInfo.get(i).getColumnName();
			if (i != j - 1) {
				fw.write(columnName + EQUAL_SIGN + DOUBLE_QUOTATION + SPACE + PLUS_SIGN + SPACE + columnName + SPACE
						+ PLUS_SIGN + SPACE + DOUBLE_QUOTATION + COMMA + SPACE);
			} else {
				fw.write(columnName + EQUAL_SIGN + DOUBLE_QUOTATION + SPACE + PLUS_SIGN + SPACE + columnName + SPACE
						+ PLUS_SIGN + SPACE + DOUBLE_QUOTATION + RIGHT_SQUARE_BRACKET + DOUBLE_QUOTATION + SEMICOLON
						+ RETURN_NEWLINE);
			}
		}
		fw.write(TAB + RIGHT_BRACE + RETURN_NEWLINE);
	}

	private void printGetSet(FileWriter fw, List<FieldInfoBO> fieldInfo) throws IOException {
		for (FieldInfoBO t : fieldInfo) {
			String columnName = t.getColumnName();
			String javaType = t.getJavaType();
			String columnNameFirstChar = columnName.substring(0, 1).toUpperCase(); // 字段首字母大写
			String columnNameOtherChar = columnName.substring(1);
			String getMethod = ConstantMethod.GET + columnNameFirstChar + columnNameOtherChar;
			String setMethod = ConstantMethod.SET + columnNameFirstChar + columnNameOtherChar;
			// get方法
			fw.write(TAB + PUBLIC + SPACE + javaType + SPACE + getMethod + LEFT_BRACKET + RIGHT_BRACKET + SPACE
					+ LEFT_BRACE + RETURN_NEWLINE);
			fw.write(TAB + TAB + RETURN + SPACE + columnName + SEMICOLON + RETURN_NEWLINE);
			fw.write(TAB + RIGHT_BRACE + RETURN_NEWLINE);
			fw.write(RETURN_NEWLINE);
			// set方法
			fw.write(TAB + PUBLIC + SPACE + VOID + SPACE + setMethod + LEFT_BRACKET + javaType + SPACE + columnName
					+ RIGHT_BRACKET + SPACE + LEFT_BRACE + RETURN_NEWLINE);
			fw.write(TAB + TAB + THIS + POINT + columnName + SPACE + EQUAL_SIGN + SPACE + columnName + SEMICOLON
					+ RETURN_NEWLINE);
			fw.write(TAB + RIGHT_BRACE + RETURN_NEWLINE);
			fw.write(RETURN_NEWLINE);
		}
	}
}
