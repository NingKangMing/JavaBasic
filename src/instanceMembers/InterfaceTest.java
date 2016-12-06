package instanceMembers;
import instanceMembers.InterfaceTest.Face;
/*小敏*/
public class InterfaceTest {
	
interface Face{
	String name="张三";   
	   int age=20;
	   String School="岭南师范学院";
	   void show();
	   void f();
}

interface Face1
{
	String name="张三";
	int age=20;
	String school="岭南师范学院";
	void show();
	void f();
}

public static void main(String[] args){
	Face obj = new Test5();
	obj.show();
	//obj.test5Method(); //多态 此句报错 是否有权执行一个方法要看指针变量的类型  执行哪个方法看堆里面的对象
	Face obj1 = new Test51();
	obj1.show();
}

}

class Test5 implements Face
{

	public void show() {
		System.out.println(this+"打印！");
	}

	public void f() {
		
	}
	
	public void test5Method() {
		System.out.println("test5成员方法！");
	}
  
}

class Test51 implements Face
{
	public void show()
	{
		
		System.out.println(this+"姓名：  "+name+"  年龄：  "+age+"学校：  "+ School);
	}
	public void f() {
		
	};
}
