package net.csdn.blog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
 * 
 * @author kangming.ning 
 * 
 */
public class CloneTest {

	public static void main(String[] args) throws CloneNotSupportedException
    {
        ShadowClone c1 = new ShadowClone();
        //��c1��ֵ
        c1.setA(100) ;
        c1.setB("clone1") ;
        c1.setC(new int[]{1000}) ;
        
        System.out.println("��¡ǰ: c1.a="+c1.getA() );
        System.out.println("��¡ǰ: c1.b="+c1.getB() );
        System.out.println("��¡ǰ: c1.c[0]="+c1.getC()[0]);
        System.out.println("-----------") ;
        
        //��¡������c2,����c2������A,B,C�����޸�
        
        ShadowClone c2 = (ShadowClone) c1.clone();
        
        //��c2�����޸�
        c2.setA(50) ;
        c2.setB("clone2");
        int []a = c2.getC() ;
        a[0]=500 ;
        c2.setC(a);
        
        System.out.println("��¡��: c1.a="+c1.getA() );
        System.out.println("��¡��: c1.b="+c1.getB() );
        System.out.println("��¡��: c1.c[0]="+c1.getC()[0]);
        System.out.println("---------------") ;
        
        System.out.println("��¡��: c2.a=" + c2.getA());
        System.out.println("��¡��: c2.b=" + c2.getB());
        System.out.println("��¡��: c2.c[0]=" + c2.getC()[0]);
        
        //���������,��ָ�޸��˿�¡��Ķ���c2.c��ֵ,��c1.c��ֵҲ�ı���,��c2��ֵ���.
        //�������ǳ��¡�ó�����:���������ǿ��Ա���¡��,����������ֻ��copy��ַ,��û��copy�����ַָ��Ķ����ֵ,��ʹ��������ַָ��ͬһֵ,�޸�����һ��,��Ȼ��һ��Ҳ�ͱ���.
       // �ɴ˿ɼ�,ǳ��¡ֻ�ʺϿ�¡��������,�����������;Ͳ���ʵ�ֿ�¡��.
        //�������ö�������л�������л���������ȿ�¡
    }
	
	 // �����л��뷴���л�ʵ�����¡
    public Object deepClone(Object src)
    {
        Object o = null;
        try
        {
            if (src != null)
            {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(src);
                oos.close();
                ByteArrayInputStream bais = new ByteArrayInputStream(baos
                        .toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                o = ois.readObject();
                ois.close();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return o;
    }
}
