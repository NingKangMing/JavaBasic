package xmlTest;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class ReadxmlTest
{
	public static void main(String[] args)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse("src/pet2.xml");

			NodeList dogList = doc.getElementsByTagName("dog");
			System.out.println("����" + dogList.getLength() + "��dog�ڵ�");
			for (int i = 0; i < dogList.getLength(); i++)
			{
				Node dog = dogList.item(i);
				Element elem = (Element) dog;
				System.out.println("id:" + elem.getAttribute("id"));
				for (Node node = dog.getFirstChild(); node != null; node = node.getNextSibling())
				{
					if (node.getNodeType() == Node.ELEMENT_NODE)
					{
						String name = node.getNodeName();
						String value = node.getFirstChild().getNodeValue();
						System.out.print(name + ":" + value + "\t");
					}
				}
				System.out.println();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
