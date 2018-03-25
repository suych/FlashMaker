package org.suych.fm.util.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlDeleteNode;
import org.suych.fm.util.generate.model.xml.XmlForeachNode;
import org.suych.fm.util.generate.model.xml.XmlIfNode;
import org.suych.fm.util.generate.model.xml.XmlIncludeNode;
import org.suych.fm.util.generate.model.xml.XmlInsertNode;
import org.suych.fm.util.generate.model.xml.XmlSelectNode;
import org.suych.fm.util.generate.model.xml.XmlSqlNode;
import org.suych.fm.util.generate.model.xml.XmlStructure;
import org.suych.fm.util.generate.model.xml.XmlTrimNode;
import org.suych.fm.util.generate.model.xml.XmlUpdateNode;

public class GenerateXmlUtil extends GenerateCommonUtil {

	public static void generate(XmlStructure xs) {
		File file = getFile(xs);
		FileWriter fw = null;
		XMLWriter xw = null;
		try {
			fw = new FileWriter(file);
			Document document = print2File(fw, xs);
			xw = getXmlWriter(fw, document);
			xw.write(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
			if (xw != null) {
				try {
					xw.close();
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		}
	}

	private static Document print2File(FileWriter fw, XmlStructure xs) throws IOException {
		// 创建XML文档树
		Document document = DocumentHelper.createDocument();
		// 添加文档类型说明
		document.addDocType(xs.getRootNodeName(), xs.getDocType(), null);
		// 创建根节点mapper
		Element mapperElement = document.addElement(xs.getRootNodeName());
		mapperElement.addAttribute("namespace", xs.getNamespace());

		List<XmlCommonNode> node = xs.getNode();
		for (XmlCommonNode xmlCommonNode : node) {
			if (xmlCommonNode instanceof XmlSqlNode) {
				assembleSqlElement(mapperElement, xmlCommonNode);
			} else if (xmlCommonNode instanceof XmlSelectNode) {
				assembleSelectElement(mapperElement, xmlCommonNode);
			} else if (xmlCommonNode instanceof XmlInsertNode) {
				assembleInsertElement(mapperElement, xmlCommonNode);
			} else if (xmlCommonNode instanceof XmlUpdateNode) {
				assembleUpdateElement(mapperElement, xmlCommonNode);
			} else if (xmlCommonNode instanceof XmlDeleteNode) {
				assembleDeleteElement(mapperElement, xmlCommonNode);
			}
		}
		return document;
	}

	private static void assembleSqlElement(Element mapperElement, XmlCommonNode xmlCommonNode) {
		XmlSqlNode sqlNode = (XmlSqlNode) xmlCommonNode;
		Element sqlElement = mapperElement.addElement("sql");
		sqlElement.addAttribute("id", sqlNode.getId());
		sqlElement.setText(sqlNode.getText());
	}

	private static void assembleSelectElement(Element mapperElement, XmlCommonNode xmlCommonNode) {
		XmlSelectNode selectNode = (XmlSelectNode) xmlCommonNode;
		Element selectElement = mapperElement.addElement("select");
		selectElement.addAttribute("id", selectNode.getId());
		selectElement.addAttribute("resultType", selectNode.getResultType());
		selectElement.addText(selectNode.getTextOne());
		if (selectNode.getInclude() != null) {
			XmlIncludeNode includeNode = selectNode.getInclude();
			Element includeElement = selectElement.addElement("include");
			includeElement.addAttribute("refid", includeNode.getRefid());
		}
		selectElement.addText(selectNode.getTextTwo());
	}

	private static void assembleInsertElement(Element mapperElement, XmlCommonNode xmlCommonNode) {
		XmlInsertNode insertNode = (XmlInsertNode) xmlCommonNode;
		Element insertElement = mapperElement.addElement("insert");
		insertElement.addAttribute("id", insertNode.getId());
		insertElement.addAttribute("parameterType", insertNode.getParameterType());
		insertElement.setText(insertNode.getText());
		if (insertNode.getTrim() != null) {
			List<XmlTrimNode> trims = insertNode.getTrim();
			for (XmlTrimNode trim : trims) {
				Element trimElement = insertElement.addElement("trim");
				String prefix = trim.getPrefix();
				String suffix = trim.getSuffix();
				String suffixOverrides = trim.getSuffixOverrides();
				String prefixOverrides = trim.getPrefixOverrides();
				List<XmlIfNode> ifNodes = trim.getIfNode();
				if (prefix != null) {
					trimElement.addAttribute("prefix", prefix);
				}
				if (suffix != null) {
					trimElement.addAttribute("suffix", suffix);
				}
				if (suffixOverrides != null) {
					trimElement.addAttribute("suffixOverrides", suffixOverrides);
				}
				if (prefixOverrides != null) {
					trimElement.addAttribute("prefixOverrides", prefixOverrides);
				}
				if (ifNodes != null) {
					for (XmlIfNode ifNode : ifNodes) {
						Element ifElement = trimElement.addElement("if");
						String test = ifNode.getTest();
						String text = ifNode.getText();
						if (test != null) {
							ifElement.addAttribute("test", test);
							ifElement.setText(text);
						}
					}
				}
			}
		}
	}

	private static void assembleUpdateElement(Element mapperElement, XmlCommonNode xmlCommonNode) {
		XmlUpdateNode updateNode = (XmlUpdateNode) xmlCommonNode;
		Element updateElement = mapperElement.addElement("update");
		updateElement.addAttribute("id", updateNode.getId());
		updateElement.addAttribute("parameterType", updateNode.getParameterType());
		updateElement.addText(updateNode.getTextOne());
		if (updateNode.getTrim() != null) {
			List<XmlTrimNode> trims = updateNode.getTrim();
			for (XmlTrimNode trim : trims) {
				Element trimElement = updateElement.addElement("trim");
				String prefix = trim.getPrefix();
				String suffix = trim.getSuffix();
				String suffixOverrides = trim.getSuffixOverrides();
				String prefixOverrides = trim.getPrefixOverrides();
				List<XmlIfNode> ifNodes = trim.getIfNode();
				if (prefix != null) {
					trimElement.addAttribute("prefix", prefix);
				}
				if (suffix != null) {
					trimElement.addAttribute("suffix", suffix);
				}
				if (suffixOverrides != null) {
					trimElement.addAttribute("suffixOverrides", suffixOverrides);
				}
				if (prefixOverrides != null) {
					trimElement.addAttribute("prefixOverrides", prefixOverrides);
				}
				if (ifNodes != null) {
					for (XmlIfNode ifNode : ifNodes) {
						Element ifElement = trimElement.addElement("if");
						String test = ifNode.getTest();
						String text = ifNode.getText();
						if (test != null) {
							ifElement.addAttribute("test", test);
							ifElement.setText(text);
						}
					}
				}
			}
		}
		updateElement.addText(updateNode.getTextTwo());
	}

	private static void assembleDeleteElement(Element mapperElement, XmlCommonNode xmlCommonNode) {
		XmlDeleteNode deleteNode = (XmlDeleteNode) xmlCommonNode;
		Element deleteElement = mapperElement.addElement("delete");
		deleteElement.addAttribute("id", deleteNode.getId());
		deleteElement.addText(deleteNode.getTextOne());
		if (deleteNode.getForeach() != null) {
			XmlForeachNode foreachNode = deleteNode.getForeach();
			Element foreachElement = deleteElement.addElement("foreach");
			foreachElement.addAttribute("collection", foreachNode.getCollection());
			foreachElement.addAttribute("item", foreachNode.getItem());
			foreachElement.addAttribute("separator", foreachNode.getSeparator());
			foreachElement.setText(foreachNode.getText());
		}
		deleteElement.addText(deleteNode.getTextTwo());
	}

	private static XMLWriter getXmlWriter(FileWriter fw, Document document) throws IOException {
		// xmlWriter是用来把XML文档写入字符串的(工具)
		XMLWriter result = new XMLWriter(fw, getOutputFormat());
		result.setEscapeText(false);
		return result;
	}

	private static OutputFormat getOutputFormat() {
		// 不会过滤回车换行
		OutputFormat result = new OutputFormat();
		// 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
		result.setEncoding("UTF-8");
		// 是否生产xml头 false-生成
		result.setSuppressDeclaration(false);
		// 设置是否缩进 true-是
		result.setIndent(true);
		// 以四个空格方式实现缩进
		result.setIndentSize(4);
		// 设置是否换行
		result.setNewlines(true);
		return result;
	}

}
