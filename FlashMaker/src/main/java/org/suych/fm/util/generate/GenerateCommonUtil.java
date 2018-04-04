package org.suych.fm.util.generate;

import static org.suych.fm.constant.ConstantJavaSyntax.ASTERISK;
import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.IMPORT;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.PACKAGE;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.suych.fm.constant.ConfigureContainer;
import org.suych.fm.constant.ConstantJavaSyntax;
import org.suych.fm.constant.ConstantSuffix;
import org.suych.fm.util.StringUtil;
import org.suych.fm.util.generate.model.BaseStructure;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.ClassStructure;
import org.suych.fm.util.generate.model.java.ImportPackageStructure;
import org.suych.fm.util.generate.model.java.InterfaceStructure;
import org.suych.fm.util.generate.model.xml.XmlStructure;

class GenerateCommonUtil {

	protected static File getFile(BaseStructure handle) {
		String outputPath = ConfigureContainer.constantMap.get("file.output.path");
		String fileName = handle.getName();
		String filePath = outputPath;
		if (handle instanceof ClassStructure) {
			String localPackage = ((ClassStructure) handle).getLocalPackage();
			String packagePath = localPackage.replace(ConstantJavaSyntax.POINT, ConstantJavaSyntax.SLASH)
					+ ConstantJavaSyntax.SLASH;
			filePath += packagePath + fileName + ConstantSuffix.JAVA_FILE.getType();
		} else if (handle instanceof InterfaceStructure) {
			String localPackage = ((InterfaceStructure) handle).getLocalPackage();
			String packagePath = localPackage.replace(ConstantJavaSyntax.POINT, ConstantJavaSyntax.SLASH)
					+ ConstantJavaSyntax.SLASH;
			filePath += packagePath + fileName + ConstantSuffix.JAVA_FILE.getType();
		} else if (handle instanceof XmlStructure) {
			filePath += fileName + ConstantSuffix.XML.getType();
		}
		File file = new File(filePath);
		if (!file.exists()) {
			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 打印本地包名
	 * 
	 * @param fw
	 * @param localPackage
	 * @throws IOException
	 */
	protected static void printLocalPackage(FileWriter fw, String localPackage) throws IOException {
		if (!"".equals(localPackage)) {
			fw.write(PACKAGE + SPACE + localPackage + SEMICOLON + RETURN_NEWLINE);
			fw.write(RETURN_NEWLINE);
		}
	}

	/**
	 * 打印引入包名
	 * 
	 * @param fw
	 * @param importPackage
	 * @throws IOException
	 */
	protected static void printImportPackage(FileWriter fw, ImportPackageStructure importPackage) throws IOException {
		if (importPackage == null) {
			return;
		}
		Set<String> javaPackage = importPackage.getJavaPackage();
		Set<String> threePartyPackage = importPackage.getThreePartyPackage();
		Set<String> customPackage = importPackage.getCustomPackage();
		if (javaPackage != null && javaPackage.size() > 0) {
			for (String packageName : javaPackage) {
				fw.write(IMPORT + SPACE + packageName + SEMICOLON + RETURN_NEWLINE);
			}
			fw.write(RETURN_NEWLINE);
		}
		if (threePartyPackage != null && threePartyPackage.size() > 0) {
			for (String packageName : threePartyPackage) {
				fw.write(IMPORT + SPACE + packageName + SEMICOLON + RETURN_NEWLINE);
			}
			fw.write(RETURN_NEWLINE);
		}
		if (customPackage != null && customPackage.size() > 0) {
			for (String packageName : customPackage) {
				fw.write(IMPORT + SPACE + packageName + SEMICOLON + RETURN_NEWLINE);
			}
			fw.write(RETURN_NEWLINE);
		}
	}

	/**
	 * 打印注释
	 * 
	 * @param fw
	 * @param comments
	 * @throws IOException
	 */
	protected static void printComments(FileWriter fw, String comments, String indent) throws IOException {
		if (!"".equals(StringUtil.null2Empty(comments))) {
			fw.write(indent + SLASH + ASTERISK + ASTERISK + RETURN_NEWLINE);
			fw.write(indent + SPACE + ASTERISK + SPACE + comments + RETURN_NEWLINE);
			fw.write(indent + SPACE + ASTERISK + SLASH + RETURN_NEWLINE);
		}
	}

	/**
	 * 打印注解
	 * 
	 * @param fw
	 * @param annotations
	 * @param indent
	 * @throws IOException
	 */
	protected static void printAnnotation(FileWriter fw, List<AnnotationStructure> annotations, String indent)
			throws IOException {
		if (annotations != null) {
			for (AnnotationStructure annotation : annotations) {
				String name = annotation.getName();
				fw.write(indent + name);
				String annotationValue = StringUtil.null2Empty(annotation.getValue());
				if (!"".equals(annotationValue)) {
					// 直接赋值
					fw.write(LEFT_BRACKET);
					fw.write(annotationValue);
					fw.write(RIGHT_BRACKET);
				} else {
					// 属性赋值
					Map<String, String> attribute = annotation.getAttribute();
					if (attribute != null && attribute.size() > 0) {
						fw.write(LEFT_BRACKET);
						int i = 0;
						int entryNum = attribute.entrySet().size();
						for (Entry<String, String> entry : attribute.entrySet()) {
							String key = entry.getKey();
							String value = entry.getValue();
							fw.write(key + SPACE + EQUAL_SIGN + SPACE + value);
							if (i != entryNum - 1) {
								fw.write(COMMA + SPACE);
							}
							i++;
						}
						fw.write(RIGHT_BRACKET);
					}
				}
				fw.write(RETURN_NEWLINE);
			}
		}
	}
}
