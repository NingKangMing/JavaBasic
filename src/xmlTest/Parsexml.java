package xmlTest;

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
        //将解析出来的allresource下的resourceitem放在list中
        List list  = root.elements("resourceitem");
        //创建source存放每一个resourceitem中资源
        List<XmlBean> source = new ArrayList<XmlBean>();
        //将resourceitem中的各项解析出来，通过XmlBean存放到source中
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
