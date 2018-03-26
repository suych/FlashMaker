package org.suych.fm.util.generate;

import static org.suych.fm.constant.ConstantJavaSyntax.ASTERISK;
import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.EXTENDS;
import static org.suych.fm.constant.ConstantJavaSyntax.INTERFACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.PRIVATE;
import static org.suych.fm.constant.ConstantJavaSyntax.PROTECTED;
import static org.suych.fm.constant.ConstantJavaSyntax.PUBLIC;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.suych.fm.constant.ConstantInterfaceAccessModifier;
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.util.StringUtil;
import org.suych.fm.util.generate.model.java.InterfaceStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;

public class GenerateInterfaceUtil extends GenerateCommonUtil {

	public static void generate(InterfaceStructure is) {
		File file = getFile(is);
		try (FileWriter fw = new FileWriter(file)) {
			// 输出至文件
			print2File(fw, is);
			fw.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void print2File(FileWriter fw, InterfaceStructure is) throws IOException {
		// 1.本地包名
		printLocalPackage(fw, is.getLocalPackage());
		// 2.引入包名
		printImportPackage(fw, is.getImportPackage());
		// 3.接口注释
		printComments(fw, is.getComments(), "");
		// 4.修饰符+接口名+继承
		printModifierAndInterfaceNameAndExtends(fw, is);
		// 5.方法
		printMethod(fw, is);
		fw.write(RIGHT_BRACE);
	}

	/**
	 * 输出修饰符、接口名及继承的接口
	 * 
	 * @param fw
	 * @param is
	 * @throws IOException
	 */
	private static void printModifierAndInterfaceNameAndExtends(FileWriter fw, InterfaceStructure is)
			throws IOException {
		String name = is.getName();
		ConstantInterfaceAccessModifier accessModifier = is.getAcessModifier();
		if (accessModifier == ConstantInterfaceAccessModifier.PUBLIC) {
			fw.write(PUBLIC + SPACE);
		}
		fw.write(INTERFACE + SPACE + name + SPACE);
		if (is.getExtendsInterface() != null && is.getExtendsInterface()) {
			String baseClassName = is.getBaseInterfaceName();
			fw.write(EXTENDS + SPACE + baseClassName + SPACE);
		}
		fw.write(LEFT_BRACE + RETURN_NEWLINE);
		fw.write(RETURN_NEWLINE);
	}

	/**
	 * 输出方法信息
	 * 
	 * @param fw
	 * @param is
	 * @throws IOException
	 */
	private static void printMethod(FileWriter fw, InterfaceStructure is) throws IOException {
		List<MethodStructure> methodStructures = is.getMethod();
		if (methodStructures == null) {
			return;
		}
		for (MethodStructure method : methodStructures) {
			// 注释
			String comments = StringUtil.null2Empty(method.getComments());
			if (!"".equals(comments)) {
				fw.write(TAB + SLASH + ASTERISK + ASTERISK + RETURN_NEWLINE);
				fw.write(TAB + SPACE + ASTERISK + SPACE + comments + RETURN_NEWLINE);
				fw.write(TAB + SPACE + ASTERISK + SLASH + RETURN_NEWLINE);
			}
			// 访问控制修饰符
			fw.write(TAB);
			ConstantMethodAccessModifier methodAccessModifier = method.getAccessModifier();
			if (methodAccessModifier == ConstantMethodAccessModifier.PUBLIC) {
				fw.write(PUBLIC + SPACE);
			} else if (methodAccessModifier == ConstantMethodAccessModifier.PRIVATE) {
				fw.write(PRIVATE + SPACE);
			} else if (methodAccessModifier == ConstantMethodAccessModifier.PROTECTED) {
				fw.write(PROTECTED + SPACE);
			}
			// 返回值
			String returnValue = method.getReturnValue();
			fw.write(returnValue + SPACE);
			// 方法名
			String methodName = method.getName();
			fw.write(methodName);
			// 参数<类型，参数名>
			fw.write(LEFT_BRACKET);
			List<ParamterStructure> parameters = method.getParameter();
			if (parameters != null && parameters.size() > 0) {
				for (int i = 0, j = parameters.size(); i < j; i++) {
					ParamterStructure parameter = parameters.get(i);
					String javaType = parameter.getType();
					String fieldName = parameter.getName();
					fw.write(javaType + SPACE + fieldName);
					if (i != j - 1) {
						fw.write(COMMA + SPACE);
					}
				}
			}
			fw.write(RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);
			fw.write(RETURN_NEWLINE);
		}
	}
}
