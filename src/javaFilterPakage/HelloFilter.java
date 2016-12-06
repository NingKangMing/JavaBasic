package javaFilterPakage;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/** Filter将请求和响应信息拦截下来作一定处理后才把数据放走 相当于半路跑出个强盗 其结构配置和servlet一样*/
public class HelloFilter implements Filter {

	public void destroy() {
		System.out.println("HelloFilterDestroy");

	}
    /**
     * The doFilter method of the Filter is called by the container each time a request/response pair 
     * is passed through the chain due to a client request for a resource at the end of the chain. 
     * The FilterChain passed in to this method allows the Filter to pass on the request and response to 
     * the next entity in the chain. 
     * */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 System.out.println("HelloFilterdoFilter");
		 //把请求响应对 往下放行 首先把请求资源交给下一个Filter 如果是最后一个Filter则把请求资源给到目标servlet或jsp
		 /**Causes the next filter in the chain to be invoked, or if the calling filter is the last 
		  * filter in the chain, causes the resource at the end of the chain to be invoked.*/
        // chain.doFilter(request,response);//终于看见page1了，呵呵
		 String initString=filterConfig.getInitParameter("user");
		 String usernameString=request.getParameter("userName");
		 if (!initString.equals(usernameString)) {
			request.getRequestDispatcher("/page1.jsp").forward(request,response);
			return;
		}
		 
	}
    private FilterConfig filterConfig;
	public void init(FilterConfig arg0) throws ServletException {
		 System.out.println("HelloFilterInit");
		 this.filterConfig=arg0;

	}

}
