package instanceMembers;
/**
 * ��Ͷ���
 * @content 
 * 1��ʵ����Ա�������ض��Ķ���󶨣���ͬ��ʵ���������в�ͬ��ʵ������������Ӱ��
 * 2��new�������ı����ǵ����������ڴ沢����������ڴ浥Ԫ���׵�ַ(һ������������)����
 * 3����ͬ���ݵĲ�ͬ�ַ����������ڴ��ж�Ӧ��ͬһ������
 * @author Ning
 *  չ�����ñ���������Ƕ���ĵ�ַ����ջ���档���󱣴��ڶ����档��ͬ����֮���ʵ����Ա��������Ӱ�졣
 *  ���ñ���ʵ����һ��ָ�룬�������ĵ�ַ,ǿ��������
 */
public class Test_Person {
	
	
	static Person p=new Person();//����ջ�ռ䣬�������ñ�������ָ�������
	public static void main(String args[]){
		  
		  
		   p.name="������";
		  System.out.println(Person.myname(2)); //��̬�����ִ�С�
		   p.age="23";
		   p.sex="��";
		   p.show();
		   Person p1=new Person();//�ٴ�ʵ��
		   p1.name="�Ǻ�";
		   p1.age="21";
		   p1.sex="Ů";
		   p1.show();
		   Person p2=new Person();
		   p2=p1;//��p1�ĵ�ַ��ֵ��p2,��ô���������ñ���ָ��Ķ�����ͬ��
		   p2.age="20";
		   p1.show();
		   p2.show();//���һ������Ϊָ��ͬһ������
		   System.out.println(p.equals(p1));//��Ч�����Ƿ���ͬһ��,false
		   System.out.println(p2.equals(p1));//true
		   System.out.println(p2==p1);//��ַһ����true
		   Person p3=new Person();//����ջ�ռ䣬�������ñ���
		   p3.name="������";
		   p3.age="23";
		   p3.sex="��";
		   System.out.println(p3==p);//false
		   System.out.println(p.equals(p3));//��ַ��ͬ,false
		   
		   Person p4=new Person();
		   p4.age="20";
		   p4=null;//��ַû���ˣ�����ԭ������ĵ�ַû�ж���ָ��(����),java��������������Ǹ�����ɾ��
		  // p3.show();//����ָ�����
		}
	
}

