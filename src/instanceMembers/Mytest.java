package instanceMembers;


import java.util.Scanner;

public class Mytest {

	//����ʱ����a,��n
	public static void print(int a,int n){ 
	int sum=0;//�� 
	int nextA=0; //��������aֵÿ����ӵĺ�
	for(int i=1;i<=n;i++){ 
	nextA=nextA*10+a; //һ�´ε�a�����ǰ�ζ�10��������  ��22��2�Ĺ�ϵ��22=2*10+2
	sum=sum+nextA; //���
	if(i!=n){ 
	System.out.print(nextA+"+"); 
	}else{ 
	System.out.print(nextA+"="); 
	} 
	} 
	System.out.println(sum); 
	} 

	public static void main(String[] args){
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("���������������");
//���ܣ�ʵ��ѭ������һ������ֱ�������û�Ҫ��  
    int f=0;
    while(true){
         if(scanner.hasNextInt()){
             f=scanner.nextInt();
         if(f%2!=0){
            System.out.println("���������������");
            continue;
        }else{
           break;
       }
    }
  }
//////////////////////////////////////////////		
	
 //���ܣ�ʵ��a+aa+aaa+...   һֱ�ӵ�n��a   n��aҪ����
    System.out.println("����a");
		long a=0,n=0;
		a=scanner.nextLong();
		System.out.println("����n");
		 n=scanner.nextLong();
	
		long sum=0;//�� 
		long nextA=0; //��������aֵÿ����ӵĺ�
		for(int i=1;i<=n;i++){ 
		nextA=nextA*10+a; //һ�´ε�a�����ǰ�ζ�10��������  ��22��2�Ĺ�ϵ��22=2*10+2
		sum=sum+nextA; //���
		if(i!=n){ 
		System.out.print(nextA+"+"); 
		}else{ 
		System.out.print(nextA+"="); 
		} 
		} 
		System.out.println();
		System.out.println(sum); 
	}
}


