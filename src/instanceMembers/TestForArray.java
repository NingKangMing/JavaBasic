package instanceMembers;

public class TestForArray {
	public static void main(String args[]){
		//ц╟ещ
		int[]a={5,2,6,9,8,7,12,15,1,4}; 
		for(int i=0;i<a.length-1;i++)
		{
			for (int j = 0; j < a.length-i-1; j++) {
				if(a[j]>a[j+1])
				{
					int c=a[j];
					a[j]=a[j+1];
				    a[j+1]=c;
				}
			}
		}
		for(int myint:a)
		{
		  System.out.println(myint);
		  
		}
		String aString="q";
		String bString="q";
		if(aString==bString)
		{
		  System.out.println("222");
		}
	}
}
