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
	        //用来存储字符串里每个出现过的字符，且不会出现重复的 
	        Set<String> set = new HashSet<String>(); 
	        int stringLength = stringArray.length; 
	        for (int i = 0; i < stringLength; i++) { 
	            set.add(stringArray[i]); 
	        } 
	        //移掉set中的一个空字符 
	        set.remove(""); 
	         
	        System.out.println(set); 
	        int count = 0; 
	        boolean flag = true; 
	         
	        for (String s : set) { 
	             
	            while (flag) { 
	                //如果索引存在 
	                if (tempStr.indexOf(s) != -1) { 
	                    //记录出现字符的当前位置 
	                    int index = tempStr.indexOf(s); 
	                    //让字符串从下一索引处开始 
	                    tempStr = tempStr.substring(index+1); 
	                    //如果索引存在，记录值加1 
	                    count++; 
	                     
	                } else { 
	                    //如果索引不存在，赋值false退出循环 
	                    flag = false; 
	                } 
	                 
	            } 
	            //为了执行下一循环 
	            flag = true; 
	            //把值存入map，让字符与其频率对应 
	            map.put(s,count); 
	            //因为是上一结果，所以让值归零，并且字符串变回原来的字符串 
	            count = 0; 
	            tempStr = str; 
	             
	        } 
	        //将map的值变为一个列表 
	        al= map.values(); 
	        //然后转为数组 
	        Integer[] stringCount =al.toArray(new Integer[]{}); 
	        int countLength=stringCount.length; 
	        //按升序排序 
	        Arrays.sort(stringCount); 
	        //得到数组最大值 
	        int max=stringCount[countLength-1]; 
	        for(String s: set){ 
	            for(int i=0;i<countLength;i++){ 
	                //如果map的值与最大值相同,则输出 
	                if(map.get(s)==max){ 
	                    System.out.println(s + ":" + max); 
	                } 
	            } 
	             
	        } 
	    }
	    public static void main(String[] args) {
	    	findFrequencyString("天工网定位于我的建设门户、我的工作社区，倾听用户的声音，关注用户的需求是天工生存与发展的根基，网上会员大都是通过口碑相传得知天工网，并成为天工网的忠实用户");
	    }

}
