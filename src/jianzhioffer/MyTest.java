/*
 * @Author: shixq
 * @Date: 2021-11-04 14:12:26
 * @LastEditors: shixq
 * @LastEditTime: 2021-11-18 16:20:42
 * @Description: 
 */
package jianzhioffer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {
    public static MyObj test = null;

    private static void init(MyObj myTest) {
        myTest.setVal("2");
    }

    public static void main(String[] args) {
        MyObj test = new MyTest().new MyObj();
        test.setVal("1");
        init(test);
        System.out.println(test);
        Pattern pattern = Pattern.compile("\\w+(?=[^\\)]*(\\(|$))");
        Matcher matcher = pattern.matcher("S1-D3-MKZBW-ZG(指纹锁)");
        if (matcher.find()) {
            System.out.println(matcher.group(0));
        }
        System.out.println("S1-D3-MKZBW-ZG(指纹锁)".replaceAll("\\(.*?\\)", ""));
        String hpwd = String.format("%04d", 23);
        System.out.println("hpwd:" + hpwd);
    }

    class MyObj {
        private String val;

        public void setVal(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }
    }
}