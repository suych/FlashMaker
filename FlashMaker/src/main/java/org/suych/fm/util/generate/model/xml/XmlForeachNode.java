package org.suych.fm.util.generate.model.xml;

/**
 * foreach节点
 */
public class XmlForeachNode {

	/**
	 * collection属性
	 */
	private String collection;

	/**
	 * item属性
	 */
	private String item;

	/**
	 * separator属性
	 */
	private String separator;

	/**
	 * sql语句
	 */
	private String text;

	@Override
	public String toString() {
		return "XmlForeachNode [collection=" + collection + ", item=" + item + ", separator=" + separator + ", text="
				+ text + "]";
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
