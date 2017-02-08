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
		   
		   System.out.println(2<<1==2<<33);//true 移位运算按数据类型占多少位来取余后在进行移位
		}
	
	 static{
		  System.out.println("第二句！");
	  }
	 
}
