package org.suych.fm.util.generate;

import static org.suych.fm.constant.ConstantJavaSyntax.ASTERISK;
import static org.suych.fm.constant.ConstantJavaSyntax.IMPORT;
import static org.suych.fm.constant.ConstantJavaSyntax.PACKAGE;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.suych.fm.constant.ConfigureContainer;
import org.suych.fm.constant.ConstantSuffix;
import org.suych.fm.util.StringUtil;
import org.suych.fm.util.generate.model.BaseStructure;
import org.suych.fm.util.generate.model.java.ClassStructure;
import org.suych.fm.util.generate.model.java.InterfaceStructure;
import org.suych.fm.util.generate.model.xml.XmlStructure;

class GenerateCommonUtil {

	protected static File getFile(BaseStructure handle) {
		String outputPath = ConfigureContainer.constantMap.get("file.output.path");
		String fileName = handle.getName();
		String pathName = outputPath + fileName;
		if (handle instanceof ClassStructure || handle instanceof InterfaceStructure) {
			pathName += ConstantSuffix.JAVA_FILE.getType();
		} else if (handle instanceof XmlStructure) {
			pathName += ConstantSuffix.XML.getType();
		}
		File file = new File(pathName);
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

	protected static void printLocalPackage(FileWriter fw, String localPackage) throws IOException {
		if (!"".equals(localPackage)) {
			fw.write(PACKAGE + SPACE + localPackage + SEMICOLON + RETURN_NEWLINE);
			fw.write(RETURN_NEWLINE);
		}
	}

	protected static void printImportPackage(FileWriter fw, Set<String> importPackages) throws IOException {
		if (importPackages != null && importPackages.size() > 0) {
			for (String importPackage : importPackages) {
				fw.write(IMPORT + SPACE + importPackage + SEMICOLON + RETURN_NEWLINE);
			}
			fw.write(RETURN_NEWLINE);
		}
	}

	protected static void printComments(FileWriter fw, String comments) throws IOException {
		if (!"".equals(StringUtil.null2Empty(comments))) {
			fw.write(SLASH + ASTERISK + ASTERISK + RETURN_NEWLINE);
			fw.write(SPACE + ASTERISK + SPACE + comments + RETURN_NEWLINE);
			fw.write(SPACE + ASTERISK + SLASH + RETURN_NEWLINE);
		}
	}
}
