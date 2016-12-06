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
 *  要在web.xml中配置servlet
 */
public class HelloeServlet implements Servlet {
	/*HelloeServlet 构造函数只调用一次
	init
	service
	服务器关闭调用destroy
	*/
	public HelloeServlet(){
		System.out.println("HelloeServlet_构造函数");
	}
    //服务器关闭时调用
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
   //创建对象后调用，调用一次
	@SuppressWarnings("unchecked")
	public void init(ServletConfig arg0) throws ServletException {
		//ServletConfig封装了servlet的配置信息，并且可以获取servletContext对象
		System.out.println("HelloeServlet_init");
		String UserString=arg0.getInitParameter("user");//1，获取指定参数的值
		System.out.println("Servlet指定参数user的值为:"+UserString);
		
		//2.获取枚举参数的名称
		Enumeration<String> name=arg0.getInitParameterNames();
		while (name.hasMoreElements()) {
			String myname = (String)name.nextElement();//获取参数名
			String valueString=arg0.getInitParameter(myname);//获取参数值
			System.out.println("当前Servlet参数名为:"+myname+"，值为:"+valueString);
		}
		//3.获取Servlet的名称，少用。
		String servletnameString=arg0.getServletName();
		System.out.println("servlet的name为:"+servletnameString);
		
	  //4.servlet的context(上下文)对象，代表当前web应用的application对象。共享性。可以获取当前web应用的各种信息。
		ServletContext myContext=arg0.getServletContext();
		String parString=myContext.getInitParameter("contextPar1");//获取指定全局web参数的值
		System.out.println("当前web应用的初始化参数contextPar1的值为:"+parString);
		Enumeration<String> par=myContext.getInitParameterNames();
		while (par.hasMoreElements()) {
			String parname = (String) par.nextElement();
			System.out.println("当前web应用的初始化参数为："+parname+",值为"+myContext.getInitParameter(parname));
		}
		
	//5,获取当前web应用某文件对应于给定的虚拟路径的现实路径。(发布时的路径)
	  String path=myContext.getRealPath("/index.jsp");
	  System.out.println(path);
	  
	//6.返回当前Web应用程序的上下文路径。
	  System.out.println(myContext.getContextPath());
	  
	 //7.返回位于指定路径的资源作为InputStream对象。(读取流)
	  
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
//多次调用，实际用于请求响应的方法、
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		System.out.println("第一次请求，调用service");
		/**ServletRequest和ServletResponse两个接口的实现类是由tomcat服务器实现的。并在调用本方法时传入实例
		 * ServletRequest封装了请求信息，ServletResponse封装了响应信息。
		 * 
		*/
		System.out.println("tomcat传入请求信息   "+request+"tomcat传入响应信息    "+response);
		
		System.out.println("根据请求参数名获得值:"+request.getParameter("userInfo"));
	
		System.out.println("");
    
	}

}
