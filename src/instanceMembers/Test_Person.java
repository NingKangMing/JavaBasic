package instanceMembers;
/**
 * 类和对象
 * @content 
 * 1，实例成员变量和特定的对象绑定，不同的实例变量具有不同的实例变量，互不影响
 * 2，new操作符的本质是到堆中申请内存并将所申请的内存单元的首地址(一个长整形数据)返回
 * 3，相同内容的不同字符串变量在内存中对应了同一个对象
 * @author Ning
 *  展现引用变量保存的是对象的地址，在栈里面。对象保存在堆里面。不同对象之间的实例成员变量互不影响。
 *  引用变量实质是一个指针，保存对象的地址,强引用类型
 */
public class Test_Person {
	
	
	static Person p=new Person();//申请栈空间，创建引用变量，即指针变量。
	public static void main(String args[]){
		  
		  
		   p.name="宁康明";
		  System.out.println(Person.myname(2)); //静态语句先执行。
		   p.age="23";
		   p.sex="男";
		   p.show();
		   Person p1=new Person();//再创实例
		   p1.name="呵呵";
		   p1.age="21";
		   p1.sex="女";
		   p1.show();
		   Person p2=new Person();
		   p2=p1;//把p1的地址赋值给p2,那么，两个引用变量指向的对象相同了
		   p2.age="20";
		   p1.show();
		   p2.show();//结果一样，因为指向同一个对象
		   System.out.println(p.equals(p1));//比效对象是否是同一个,false
		   System.out.println(p2.equals(p1));//true
		   System.out.println(p2==p1);//地址一样，true
		   Person p3=new Person();//申请栈空间，创建引用变量
		   p3.name="宁康明";
		   p3.age="23";
		   p3.sex="男";
		   System.out.println(p3==p);//false
		   System.out.println(p.equals(p3));//地址不同,false
		   
		   Person p4=new Person();
		   p4.age="20";
		   p4=null;//地址没有了，所以原本申请的地址没有对象指向(引用),java垃圾回收器会把那个对象删除
		  // p3.show();//报空指针错误
		}
	
}

