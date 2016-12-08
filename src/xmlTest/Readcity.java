package xmlTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**读取城市xml文件
 * @author NingKangMing*/
public class Readcity {
	
	public static void read() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/LocList.xml"));      
        Element root = document.getRootElement();
        //将解析出来的Location下的State放在list中
        
        //获取State的枚举器
        Iterator iterState = root.elementIterator("State");
        //枚举省份State
        while (iterState.hasNext()) {
        	//获得当前元素
        	Element StateEle = (Element) iterState.next();
        	//获取属性值
        	String stateName = StateEle.attributeValue("Name");
        	String stateCode = StateEle.attributeValue("Code");
         	         
        	System.out.println("Name:" + stateName);
        	System.out.println("Code:" + stateCode);
        	
        	//枚举当前节点下的字节点
        	Iterator iterCity = StateEle.elementIterator("City");
        	 while (iterCity.hasNext()) {
             	//获得当前元素
             	Element CityEle = (Element) iterCity.next();
             	//获取属性值
             	String cityName = CityEle.attributeValue("Name");
             	String cityCode = CityEle.attributeValue("Code");
             	System.out.println("cityName:" + cityName);
             	System.out.println("cityCode:" + cityCode);
             	
             }
        	
        }
        
       
	}
	
	public static void main(String args[]){
		
		try {
			Readcity.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
