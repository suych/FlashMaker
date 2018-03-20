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
import org.suych.fm.constant.ConfigureContainer;
import org.suych.fm.constant.ConstantSuffix;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlDeleteNode;
import org.suych.fm.util.generate.model.xml.XmlForeachNode;
import org.suych.fm.util.generate.model.xml.XmlInsertNode;
import org.suych.fm.util.generate.model.xml.XmlSelectNode;
import org.suych.fm.util.generate.model.xml.XmlStructure;
import org.suych.fm.util.generate.model.xml.XmlUpdateNode;

public class GenerateXmlUtil {

	public static void generate(XmlStructure xs) {
		FileWriter fw = null;
		try {
			String pathName = ConfigureContainer.constantMap.get("file.output.path") + xs.getName()
					+ ConstantSuffix.XML.getType();
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
			print2File(fw, xs);
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

	private static void print2File(FileWriter fw, XmlStructure xs) throws IOException {
		// 创建XML文档树
		Document document = DocumentHelper.createDocument();
		// 添加文档类型说明
		document.addDocType(xs.getRootNodeName(), xs.getDocType(), null);
		// 创建根节点mapper
		Element mapperElement = document.addElement(xs.getRootNodeName());
		mapperElement.addAttribute("namespace", xs.getNamespace());

		List<XmlCommonNode> node = xs.getNode();
		for (XmlCommonNode xmlCommonNode : node) {
			if (xmlCommonNode instanceof XmlSelectNode) {
				XmlSelectNode selectNode = (XmlSelectNode) xmlCommonNode;
				Element selectElement = mapperElement.addElement("select");
				selectElement.addAttribute("id", selectNode.getId());
				selectElement.addAttribute("resultType", selectNode.getResultType());
				selectElement.setText(selectNode.getText());
			} else if (xmlCommonNode instanceof XmlInsertNode) {
				XmlInsertNode insertNode = (XmlInsertNode) xmlCommonNode;
				Element insertElement = mapperElement.addElement("insert");
				insertElement.addAttribute("id", insertNode.getId());
				insertElement.addAttribute("parameterType", insertNode.getParameterType());
				insertElement.setText(insertNode.getText());
			} else if (xmlCommonNode instanceof XmlUpdateNode) {
				XmlUpdateNode updateNode = (XmlUpdateNode) xmlCommonNode;
				Element updateElement = mapperElement.addElement("update");
				updateElement.addAttribute("id", updateNode.getId());
				updateElement.addAttribute("parameterType", updateNode.getParameterType());
				updateElement.setText(updateNode.getText());
			} else if (xmlCommonNode instanceof XmlDeleteNode) {
				XmlDeleteNode deleteNode = (XmlDeleteNode) xmlCommonNode;
				Element deleteElement = mapperElement.addElement("delete");
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
		}
		// 不会过滤回车换行
		OutputFormat outputFormat = new OutputFormat();
		// 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
		outputFormat.setEncoding("UTF-8");
		// 是否生产xml头 false-生成
		outputFormat.setSuppressDeclaration(false);
		// 设置是否缩进 true-是
		outputFormat.setIndent(true);
		// 以四个空格方式实现缩进
		outputFormat.setIndentSize(4);
		// 设置是否换行
		outputFormat.setNewlines(true);
		// xmlWriter是用来把XML文档写入字符串的(工具)
		XMLWriter xmlWriter = new XMLWriter(fw, outputFormat);
		xmlWriter.setEscapeText(false);
		try {
			// 把创建好的XML文档写入字符串
			xmlWriter.write(document);
			// 打印字符串,即是XML文档
			fw.flush();
		} finally {
			xmlWriter.close();
		}

	}
}
