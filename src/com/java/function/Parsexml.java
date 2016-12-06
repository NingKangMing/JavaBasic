package com.java.function;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class Parsexml {
	public void read() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/LocList.xml"));
        Element root = document.getRootElement();
        //������������allresource�µ�resourceitem����list��
        List list  = root.elements("resourceitem");
        //����source���ÿһ��resourceitem����Դ
        List<XmlBean> source = new ArrayList<XmlBean>();
        //��resourceitem�еĸ������������ͨ��XmlBean��ŵ�source��
        for(Iterator i = list.iterator();i.hasNext();) {
        	Element resourceitem = (Element) i.next();
        	String id = resourceitem.element("id").getText();
        	String title = resourceitem.element("title").getText();
        	String keywords = resourceitem.element("keywords").getText();
        	String kind = resourceitem.element("kind").getText();
        	String describe = resourceitem.element("describe").getText();
        	String date = resourceitem.element("date").getText();
        	String url = resourceitem.element("url").getText();
        	String author = resourceitem.element("author").getText();
        	String publisher = resourceitem.element("publisher").getText();
        	XmlBean bean = new XmlBean();
        	
        	source.add(bean);
        }
   
	}
}
