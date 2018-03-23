package jianzhioffer;

import java.util.LinkedHashMap;

public class FirstNotRepeatingChar {
	/**
	 * 在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
	 * 
	 * @param str
	 * @return
	 */
	public static int FirstNotRepeatingChar(String str) {
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
		char[] chars = str.toCharArray();
		int index = -1;
		if(str != null) {
			for (int i = 0; i < chars.length; i++) {
				Integer count = map.get(chars[i]);
				if (count != null) {
					count++;
					map.put(chars[i], count);
				} else {
					map.put(chars[i], 1);
				}
			}
			for (int j = 0; j < chars.length; j++) {
				if (map.get(chars[j]) == 1) {
					index = j;
					break;
				}
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		String str = "google";
		System.out.println(FirstNotRepeatingChar(str));
	}
}
