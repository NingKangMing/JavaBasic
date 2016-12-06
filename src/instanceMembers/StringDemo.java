package instanceMembers;
/**
 * 
 * @author Ning
 * @content
 * 1,String是一个特殊的类。
 * 2.字符串对象构建的方法有两种。一，直接赋值。二，new操作.区别是，java语言对使用直接赋值创建的字符串对象，只要字符串内容相同
 * ，在内存中就会作为同一个对象来保存。而用new操作则会重新创建一个对象。。。。
 * ==比效的是两个对象的地址是否相同。对于String,equals比效的是两个对象的内容是否相同,其它类型则比效是否为同一对象
 * 3，文库：
 * （1）对于字符串变量来说，使用“= =”和“equals()”方法比较字符串时，其比较方法不同。 
 * “= =”比较两个变量本身的值，即两个对象在内存中的首地址。
 * “equals()”比较字符串中所包含的内容是否相同。
 * （2）对于非字符串变量来说，"= ="和"equals"方法的作用是相同的都是用来比较其对象在堆内存的首地址，即用来比较两个引用变量是否指向同一个对象。
 * 
 * 4，运算符“==”比较的是两个变量是否引用同一对象。即比较地址，“equals()”比较字符串中所包含的内容是否相同。即值
 */
public class StringDemo {
	public static void main(String args[]){
	
		String a="a";
		String b="a";
		String f=new String("a");
		String g=new String("a");
		System.out.println(f);
		System.out.println(a==b);//用同一对象保存，所以两个指针的值相等,指向同一对象.true
		System.out.println(a.equals(b));//内容相同，true
		System.out.println(b==f);//false,两个对象的首地址不同,两个指针指向的对象不同
		System.out.println(b.equals(f));//内容相同，true
		System.out.println(b.equals(g));//内容相同，true
		System.out.println(f==g);//false,在内存的首地址不同
		System.out.println(f.equals(g));//true
	
		//如果两个String hashCode相等，那么肯定equals。
		//如果自定义的对象重写了hashCode方法，有可能hashcode相等，equals却不一定相等，“==”比较也不一定相等。
		f.hashCode();
		/*
	    int inta=1;
	    int intb=1;
	    int intc=2;
	    System.out.println(inta==intb);//true
	    System.out.println(inta==intc);//false
	    */
	}
}
