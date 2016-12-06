package instanceMembers;


import java.util.Scanner;

public class Mytest {

	//调用时传入a,和n
	public static void print(int a,int n){ 
	int sum=0;//和 
	int nextA=0; //用来保存a值每次相加的和
	for(int i=1;i<=n;i++){ 
	nextA=nextA*10+a; //一下次的a将会比前次多10倍加自身  如22和2的关系是22=2*10+2
	sum=sum+nextA; //求和
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
		System.out.println("输入符合条件的数");
//功能：实现循环输入一个数，直到符合用户要求  
    int f=0;
    while(true){
         if(scanner.hasNextInt()){
             f=scanner.nextInt();
         if(f%2!=0){
            System.out.println("输入符合条件的数");
            continue;
        }else{
           break;
       }
    }
  }
//////////////////////////////////////////////		
	
 //功能：实现a+aa+aaa+...   一直加到n个a   n和a要输入
    System.out.println("输入a");
		long a=0,n=0;
		a=scanner.nextLong();
		System.out.println("输入n");
		 n=scanner.nextLong();
	
		long sum=0;//和 
		long nextA=0; //用来保存a值每次相加的和
		for(int i=1;i<=n;i++){ 
		nextA=nextA*10+a; //一下次的a将会比前次多10倍加自身  如22和2的关系是22=2*10+2
		sum=sum+nextA; //求和
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


