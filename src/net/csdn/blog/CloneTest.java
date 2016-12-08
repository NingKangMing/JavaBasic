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
        //对c1赋值
        c1.setA(100) ;
        c1.setB("clone1") ;
        c1.setC(new int[]{1000}) ;
        
        System.out.println("克隆前: c1.a="+c1.getA() );
        System.out.println("克隆前: c1.b="+c1.getB() );
        System.out.println("克隆前: c1.c[0]="+c1.getC()[0]);
        System.out.println("-----------") ;
        
        //克隆出对象c2,并对c2的属性A,B,C进行修改
        
        ShadowClone c2 = (ShadowClone) c1.clone();
        
        //对c2进行修改
        c2.setA(50) ;
        c2.setB("clone2");
        int []a = c2.getC() ;
        a[0]=500 ;
        c2.setC(a);
        
        System.out.println("克隆后: c1.a="+c1.getA() );
        System.out.println("克隆后: c1.b="+c1.getB() );
        System.out.println("克隆后: c1.c[0]="+c1.getC()[0]);
        System.out.println("---------------") ;
        
        System.out.println("克隆后: c2.a=" + c2.getA());
        System.out.println("克隆后: c2.b=" + c2.getB());
        System.out.println("克隆后: c2.c[0]=" + c2.getC()[0]);
        
        //问题出现了,我指修改了克隆后的对象c2.c的值,但c1.c的值也改变了,与c2的值相等.
        //以下针对浅克隆得出结论:基本类型是可以被克隆的,但引用类型只是copy地址,并没有copy这个地址指向的对象的值,这使得两个地址指向同一值,修改其中一个,当然另一个也就变了.
       // 由此可见,浅克隆只适合克隆基本类型,对于引用类型就不能实现克隆了.
        //此问题用对象的序列化与把序列化来进行深度克隆
    }
	
	 // 用序列化与反序列化实现深克隆
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
