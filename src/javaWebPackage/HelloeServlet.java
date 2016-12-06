package javaWebPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/***
 * 
 * @author nkm
 *  Ҫ��web.xml������servlet
 */
public class HelloeServlet implements Servlet {
	/*HelloeServlet ���캯��ֻ����һ��
	init
	service
	�������رյ���destroy
	*/
	public HelloeServlet(){
		System.out.println("HelloeServlet_���캯��");
	}
    //�������ر�ʱ����
	public void destroy() {
		System.out.println("HelloeServlet_Destroy");

	}
   
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		System.out.println("HelloeServlet_getServletConfig");
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		System.out.println("HelloeServlet_getServletInfo");
		return null;
	}
   //�����������ã�����һ��
	@SuppressWarnings("unchecked")
	public void init(ServletConfig arg0) throws ServletException {
		//ServletConfig��װ��servlet��������Ϣ�����ҿ��Ի�ȡservletContext����
		System.out.println("HelloeServlet_init");
		String UserString=arg0.getInitParameter("user");//1����ȡָ��������ֵ
		System.out.println("Servletָ������user��ֵΪ:"+UserString);
		
		//2.��ȡö�ٲ���������
		Enumeration<String> name=arg0.getInitParameterNames();
		while (name.hasMoreElements()) {
			String myname = (String)name.nextElement();//��ȡ������
			String valueString=arg0.getInitParameter(myname);//��ȡ����ֵ
			System.out.println("��ǰServlet������Ϊ:"+myname+"��ֵΪ:"+valueString);
		}
		//3.��ȡServlet�����ƣ����á�
		String servletnameString=arg0.getServletName();
		System.out.println("servlet��nameΪ:"+servletnameString);
		
	  //4.servlet��context(������)���󣬴���ǰwebӦ�õ�application���󡣹����ԡ����Ի�ȡ��ǰwebӦ�õĸ�����Ϣ��
		ServletContext myContext=arg0.getServletContext();
		String parString=myContext.getInitParameter("contextPar1");//��ȡָ��ȫ��web������ֵ
		System.out.println("��ǰwebӦ�õĳ�ʼ������contextPar1��ֵΪ:"+parString);
		Enumeration<String> par=myContext.getInitParameterNames();
		while (par.hasMoreElements()) {
			String parname = (String) par.nextElement();
			System.out.println("��ǰwebӦ�õĳ�ʼ������Ϊ��"+parname+",ֵΪ"+myContext.getInitParameter(parname));
		}
		
	//5,��ȡ��ǰwebӦ��ĳ�ļ���Ӧ�ڸ���������·������ʵ·����(����ʱ��·��)
	  String path=myContext.getRealPath("/index.jsp");
	  System.out.println(path);
	  
	//6.���ص�ǰWebӦ�ó����������·����
	  System.out.println(myContext.getContextPath());
	  
	 //7.����λ��ָ��·������Դ��ΪInputStream����(��ȡ��)
	  
	  InputStream myInputStream=myContext.getResourceAsStream("/MyNoteBook/test.txt");
	  InputStreamReader readerInputStream=new InputStreamReader(myInputStream);
	  BufferedReader bufferedReader=new BufferedReader(readerInputStream);
	  try {
		  while (bufferedReader.readLine()!=null) {
			System.out.println(bufferedReader.readLine());
		}
		
	} catch (IOException e) {
		e.printStackTrace();
	}

	}
//��ε��ã�ʵ������������Ӧ�ķ�����
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		System.out.println("��һ�����󣬵���service");
		/**ServletRequest��ServletResponse�����ӿڵ�ʵ��������tomcat������ʵ�ֵġ����ڵ��ñ�����ʱ����ʵ��
		 * ServletRequest��װ��������Ϣ��ServletResponse��װ����Ӧ��Ϣ��
		 * 
		*/
		System.out.println("tomcat����������Ϣ   "+request+"tomcat������Ӧ��Ϣ    "+response);
		
		System.out.println("����������������ֵ:"+request.getParameter("userInfo"));
	
		System.out.println("");
    
	}

}
