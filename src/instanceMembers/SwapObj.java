package instanceMembers;
/** 
 * 
 * @author kangming.ning 
 * 
 */
public class SwapObj {
	
	 static public void main(String[] args){
		 
		 Person person1=new Person("haha", "2");
		 Person person2=new Person("hehe", "3");
		 SwapObj.swap(person1,person2);
		 System.out.println("p1:"+person1.name+"p2"+person2.name);//将不变 证明是值传递(非地址传递)
		 //引用值传过去(引用的一个副本)后，如果在swap里改变对象的状态 那么这里的的引用对象的状态也改变
		 //因为传过去的副本跟原对象指向同一个地址
	 }
	 
	 
	public static void swap(Person p1,Person p2){
		 
		 Person temp=p1;
		 p1=p2;
		 p2=temp;
		 System.out.println("now inner p1:"+p1.name+" p2:"+p2.name);//内部成功交换了引用
	 }

}
