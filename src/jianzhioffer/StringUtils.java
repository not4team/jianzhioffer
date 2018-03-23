package jianzhioffer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 
 * @author shixq
 *
 */
public class StringUtils {

	public static String replaceSpace(StringBuffer str) {
		return str.toString().replaceAll(" ", "%20");
	}

	/*
	 * 问题1：替换字符串，是在原来的字符串上做替换，还是新开辟一个字符串做替换！
	 * 问题2：在当前字符串替换，怎么替换才更有效率（不考虑java里现有的replace方法）。
	 * 从前往后替换，后面的字符要不断往后移动，要多次移动，所以效率低下
	 * 从后往前，先计算需要多少空间，然后从后往前移动，则每个字符只为移动一次，这样效率更高一点。
	 */
	public static String replaceSpace1(StringBuffer str) {
		int spacenum = 0;// spacenum为计算空格数
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ')
				spacenum++;
		}
		int indexold = str.length() - 1; // indexold为为替换前的str下标
		int newlength = str.length() + spacenum * 2;// 计算空格转换成%20之后的str长度
		int indexnew = newlength - 1;// indexold为为把空格替换为%20后的str下标
		str.setLength(newlength);// 使str的长度扩大到转换成%20之后的长度,防止下标越界
		for (; indexold >= 0; --indexold) {
			if (str.charAt(indexold) == ' ') { //
				str.setCharAt(indexnew--, '0');
				str.setCharAt(indexnew--, '2');
				str.setCharAt(indexnew--, '%');
			} else {
				str.setCharAt(indexnew--, str.charAt(indexold));
			}
		}
		return str.toString();
	}

	/**
	 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
	 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
	 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
	 * 
	 * 回溯法
	 * @param str
	 * @return
	 */
	public ArrayList<String> permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str != null && str.length() > 0) {
			permutationHelper(str.toCharArray(), 0, res);
			Collections.sort(res);
		}
		return res;
	}

	public void permutationHelper(char[] cs, int i, ArrayList<String> list) {
		if (i == cs.length - 1) {
			String val = String.valueOf(cs);
			if (!list.contains(val)) {
				list.add(val);
			}
		} else {
			for (int j = i; j < cs.length; ++j) {
				swap(cs, i, j);
				permutationHelper(cs, i + 1, list);
				swap(cs, i, j);
			}
		}
	}

	public static void swap(char[] cs, int i, int j) {
		char temp = cs[i];
		cs[i] = cs[j];
		cs[j] = temp;
	}

	/**
	 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
	 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
	 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
	 */
	public static String LeftRotateString(String str, int n) {
		if (str == null || str.length() == 0) {
			return "";
		}
		n = n % str.length();
		String str1 = str + str;
		return str1.substring(n, str.length() + n);
	}

	/**
	 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
	 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
	 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
	 * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
	 */
	public static String ReverseSentence(String str) {
		if (str == null || str.trim().length() == 0) {
			return str;
		}
		String[] strArray = str.split(" ");
		StringBuilder strBuilder = new StringBuilder();
		for (int i = strArray.length; i > 0; i--) {
			strBuilder.append(strArray[i - 1]);
			if (i > 1) {
				strBuilder.append(" ");
			}
		}
		return strBuilder.toString();
	}

	/**
	 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
	 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
	 * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,
	 * 并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
	 * LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。为了方便起见,你可以认为大小王是0。
	 * 
	 *  必须满足两个条件
	 * 1. 除0外没有重复的数
	 * 2. max - min < 5 
	 */
	public boolean isContinuous(int [] numbers) {
		 if (numbers.length != 5)
			return false;
		int min = 14;
		int max = -1;
		int flag = 0;
		for (int i = 0; i < numbers.length; i++) {
			int number = numbers[i];
			if (number < 0 || number > 13)
				return false;
			if (number == 0)
				continue;
			//判断是否有重复的数
			if (((flag >> number) & 1) == 1)
				return false;
			//按位标记
			flag |= (1 << number);
			if (number > max)
				max = number;
			if (number < min)
				min = number;
			if (max - min >= 5)
				return false;
		}
		return true;
    }

	public static void main(String[] args) {
		System.out.println(StringUtils.replaceSpace1(new StringBuffer("We Are Happy")));
		System.out.println(LeftRotateString("abcdefg", 2));
		System.out.println(ReverseSentence("student. a am I"));
	}
}
