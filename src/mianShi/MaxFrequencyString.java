package mianShi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxFrequencyString {

	 public static void findFrequencyString (String str) { 
	        Collection<Integer> al=new ArrayList<Integer>(); 
	        Map<String,Integer> map=new HashMap<String,Integer>(); 
	        String tempStr = str; 
	        String[] stringArray = str.split(""); 
	        //�����洢�ַ�����ÿ�����ֹ����ַ����Ҳ�������ظ��� 
	        Set<String> set = new HashSet<String>(); 
	        int stringLength = stringArray.length; 
	        for (int i = 0; i < stringLength; i++) { 
	            set.add(stringArray[i]); 
	        } 
	        //�Ƶ�set�е�һ�����ַ� 
	        set.remove(""); 
	         
	        System.out.println(set); 
	        int count = 0; 
	        boolean flag = true; 
	         
	        for (String s : set) { 
	             
	            while (flag) { 
	                //����������� 
	                if (tempStr.indexOf(s) != -1) { 
	                    //��¼�����ַ��ĵ�ǰλ�� 
	                    int index = tempStr.indexOf(s); 
	                    //���ַ�������һ��������ʼ 
	                    tempStr = tempStr.substring(index+1); 
	                    //����������ڣ���¼ֵ��1 
	                    count++; 
	                     
	                } else { 
	                    //������������ڣ���ֵfalse�˳�ѭ�� 
	                    flag = false; 
	                } 
	                 
	            } 
	            //Ϊ��ִ����һѭ�� 
	            flag = true; 
	            //��ֵ����map�����ַ�����Ƶ�ʶ�Ӧ 
	            map.put(s,count); 
	            //��Ϊ����һ�����������ֵ���㣬�����ַ������ԭ�����ַ��� 
	            count = 0; 
	            tempStr = str; 
	             
	        } 
	        //��map��ֵ��Ϊһ���б� 
	        al= map.values(); 
	        //Ȼ��תΪ���� 
	        Integer[] stringCount =al.toArray(new Integer[]{}); 
	        int countLength=stringCount.length; 
	        //���������� 
	        Arrays.sort(stringCount); 
	        //�õ��������ֵ 
	        int max=stringCount[countLength-1]; 
	        for(String s: set){ 
	            for(int i=0;i<countLength;i++){ 
	                //���map��ֵ�����ֵ��ͬ,����� 
	                if(map.get(s)==max){ 
	                    System.out.println(s + ":" + max); 
	                } 
	            } 
	             
	        } 
	    }
	    public static void main(String[] args) {
	    	findFrequencyString("�칤����λ���ҵĽ����Ż����ҵĹ��������������û�����������ע�û����������칤�����뷢չ�ĸ��������ϻ�Ա����ͨ���ڱ��ഫ��֪�칤��������Ϊ�칤������ʵ�û�");
	    }

}
