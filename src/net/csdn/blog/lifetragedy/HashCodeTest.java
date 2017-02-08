package net.csdn.blog.lifetragedy;
/** 
 * 
 * @author kangming.ning 
 * 
 */
public class HashCodeTest {
	
	
	/**
	 * <<      :     左移运算符，num << 1,相当于num乘以2

       >>      :     右移运算符，num >> 1,相当于num除以2

        >>>    :    按二进制形式把所有的数字向右移动对应位数，低位移出(舍弃)，
                                              高位的空位补零。对于正数来说和带符号右移相同，对于负数来说不同。 其他结构和>>相似。
        
	 * 1,左移n位相当于乘以2的n次方。，右移n位相当于除以2的n次方。这里是取商哈，余数就不要了。
	 * 
	 * 0x1表示16进制的1
	 * 
	 * 
	 * */
	public static void main(String[] s) {
		
		/*System.out.println(4>>1);//4向右移1位，结果为2
		System.out.println(4>>>1);//2
		System.out.println(4<<1);*///4向左移1位结果为8
		System.out.println(0x54);// 5*16¹+4*16º=84
	}

}
