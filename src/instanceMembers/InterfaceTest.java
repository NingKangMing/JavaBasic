package instanceMembers;
import instanceMembers.InterfaceTest.Face;
/*С��*/
public class InterfaceTest {
	
interface Face{
	String name="����";   
	   int age=20;
	   String School="����ʦ��ѧԺ";
	   void show();
	   void f();
}

interface Face1
{
	String name="����";
	int age=20;
	String school="����ʦ��ѧԺ";
	void show();
	void f();
}

public static void main(String[] args){
	Face obj = new Test5();
	obj.show();
	//obj.test5Method(); //��̬ �˾䱨�� �Ƿ���Ȩִ��һ������Ҫ��ָ�����������  ִ���ĸ�������������Ķ���
	Face obj1 = new Test51();
	obj1.show();
}

}

class Test5 implements Face
{

	public void show() {
		System.out.println(this+"��ӡ��");
	}

	public void f() {
		
	}
	
	public void test5Method() {
		System.out.println("test5��Ա������");
	}
  
}

class Test51 implements Face
{
	public void show()
	{
		
		System.out.println(this+"������  "+name+"  ���䣺  "+age+"ѧУ��  "+ School);
	}
	public void f() {
		
	};
}
