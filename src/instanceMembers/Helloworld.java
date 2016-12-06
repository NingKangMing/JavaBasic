package instanceMembers;
/**
 * 
 * @author Ning
 *  静态语句块比main的处理还要靠前。一般用于程序的初始化
 */
public class Helloworld {
	
	static{
		  System.out.println("静态语句块先处理!不管位置如何");
	  }
	
	public static void main(String args[]){
		   
		   System.out.println("Hello,World!");
		   
		}
	
	 static{
		  System.out.println("第二句！");
	  }
	 
}
