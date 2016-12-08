package xmlTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**��ȡ����xml�ļ�
 * @author NingKangMing*/
public class Readcity {
	
	public static void read() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/LocList.xml"));      
        Element root = document.getRootElement();
        //������������Location�µ�State����list��
        
        //��ȡState��ö����
        Iterator iterState = root.elementIterator("State");
        //ö��ʡ��State
        while (iterState.hasNext()) {
        	//��õ�ǰԪ��
        	Element StateEle = (Element) iterState.next();
        	//��ȡ����ֵ
        	String stateName = StateEle.attributeValue("Name");
        	String stateCode = StateEle.attributeValue("Code");
         	         
        	System.out.println("Name:" + stateName);
        	System.out.println("Code:" + stateCode);
        	
        	//ö�ٵ�ǰ�ڵ��µ��ֽڵ�
        	Iterator iterCity = StateEle.elementIterator("City");
        	 while (iterCity.hasNext()) {
             	//��õ�ǰԪ��
             	Element CityEle = (Element) iterCity.next();
             	//��ȡ����ֵ
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
