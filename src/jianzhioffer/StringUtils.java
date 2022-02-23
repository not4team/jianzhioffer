package jianzhioffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 
 * @author shixq
 *
 */
public class StringUtils {

    /**
     * 字符串相加
     * 
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }

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
     * 
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
     * 必须满足两个条件
     * 1. 除0外没有重复的数
     * 2. max - min < 5
     */
    public boolean isContinuous(int[] numbers) {
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
            // 判断是否有重复的数
            if (((flag >> number) & 1) == 1)
                return false;
            // 按位标记
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

    /**
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。
     * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     * 
     * 当模式中的第二个字符不是“*”时：
     * 1、如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
     * 2、如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。而当模式中的第二个字符是“*”时：
     * 如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
     * 1、模式后移2字符，相当于x*被忽略；
     * 2、字符串后移1字符，模式后移2字符；
     * 3、字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位；
     */
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        int strIndex = 0;
        int patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        // 有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        // pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        // 模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
                    || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex, pattern, patternIndex + 2)// 模式后移2，视为x*匹配0个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)// 视为模式匹配1个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex);// *匹配1个，再匹配str中的下一个
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        // 模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
                || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }

    /**
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
     * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是
     */
    public boolean isNumeric(char[] str) {
        // 标记符号、小数点、e是否出现过
        boolean sign = false, decimal = false, hasE = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'e' || str[i] == 'E') {
                if (i == str.length - 1)
                    return false; // e后面一定要接数字
                if (hasE)
                    return false; // 不能同时存在两个e
                hasE = true;
            } else if (str[i] == '+' || str[i] == '-') {
                // 第二次出现+-符号，则必须紧接在e之后
                if (sign && str[i - 1] != 'e' && str[i - 1] != 'E')
                    return false;
                // 第一次出现+-符号，且不是在字符串开头，则也必须紧接在e之后
                if (!sign && i > 0 && str[i - 1] != 'e' && str[i - 1] != 'E')
                    return false;
                sign = true;
            } else if (str[i] == '.') {
                // e后面不能接小数点，小数点不能出现两次
                if (hasE || decimal)
                    return false;
                decimal = true;
            } else if (str[i] < '0' || str[i] > '9') // 不合法字符
                return false;
        }
        return true;
    }

    /**
     * 无重复字符的最长子串
     * 这道题主要用到思路是：滑动窗口
     * 什么是滑动窗口？
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，
     * 这时候不满足要求。所以，我们要移动这个队列！如何移动？
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     * 时间复杂度：O(n)
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;

    }

    public static void main(String[] args) {
        System.out.println(StringUtils.replaceSpace1(new StringBuffer("We Are Happy")));
        System.out.println(LeftRotateString("abcdefg", 2));
        System.out.println(ReverseSentence("student. a am I"));
    }
}
