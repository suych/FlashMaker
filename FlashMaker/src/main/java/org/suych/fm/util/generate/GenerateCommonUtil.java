package org.suych.fm.util.generate;

import static org.suych.fm.constant.ConstantJavaSyntax.ASTERISK;
import static org.suych.fm.constant.ConstantJavaSyntax.IMPORT;
import static org.suych.fm.constant.ConstantJavaSyntax.PACKAGE;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.suych.fm.util.StringUtil;

class GenerateCommonUtil {

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
