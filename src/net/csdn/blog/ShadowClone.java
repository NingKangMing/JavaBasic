package net.csdn.blog;
/** 
 * 
 * @author kangming.ning 
 * 
 */
public class ShadowClone implements Cloneable{
	
	 // ��������
    private int a;
    // �ǻ�������
    private String b;
    // �ǻ�������
    private int[] c;
    // ��дObject.clone()����,����protected��Ϊpublic
    @Override
    public Object clone()
    {
        ShadowClone sc = null;
        try
        {
            sc = (ShadowClone) super.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return sc;
    }
    public int getA()
    {
        return a;
    }
    public void setA(int a)
    {
        this.a = a;
    }
    public String getB()
    {
        return b;
    }
    public void setB(String b)
    {
        this.b = b;
    }
    public int[] getC()
    {
        return c;
    }
    public void setC(int[] c)
    {
        this.c = c;
    }

}
