package instanceMembers;

public class Person {
	
	   public String name;
	   public String age;
	   public String sex;
	   
	   public Person(){
		   super();
	   }
	   
       public Person(String name,String age){
		   super();
		   this.name=name;
		   this.age=age;
	   }

	public void show(){
	  System.out.println("名字为"+name);
	  System.out.println("年龄为"+age);
	  System.out.println("性别为"+sex);
	}
	
	public static int myname(int a) {
		int b=0;
		int c=8;
		for(int i=0;i<10;i++){
			
		}
		return b+c+a;
	}
	
	static public  void main(String[] args1){
		//System.out.println(Person.myname(4));
		System.out.println(args1);
		Person person=new Person();
		int hashCode = person.hashCode();
		System.out.println(hashCode);
	}
	
}
