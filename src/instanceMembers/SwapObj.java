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
		 System.out.println("p1:"+person1.name+"p2"+person2.name);//������ ֤����ֵ����(�ǵ�ַ����)
		 //����ֵ����ȥ(���õ�һ������)�������swap��ı�����״̬ ��ô����ĵ����ö����״̬Ҳ�ı�
		 //��Ϊ����ȥ�ĸ�����ԭ����ָ��ͬһ����ַ
	 }
	 
	 
	public static void swap(Person p1,Person p2){
		 
		 Person temp=p1;
		 p1=p2;
		 p2=temp;
		 System.out.println("now inner p1:"+p1.name+" p2:"+p2.name);//�ڲ��ɹ�����������
	 }

}
