package socketAndThread;
/**
 * 
 * @author Administrator
 *  ���������������
 *
 */
public class ServerBooter {
   public static void main(String args[]){
	   System.out.println("������");
	   TalkServer server=new TalkServer();
	   server.start();//���ü�������
   }
}
