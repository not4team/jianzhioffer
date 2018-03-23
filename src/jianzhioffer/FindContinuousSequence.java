package jianzhioffer;

import java.util.ArrayList;

/**
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 * 双指针问题
 * 当总和小于sum，大指针继续+
 * 否则小指针+
 * @author shixq
 */
public class FindContinuousSequence {
    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int phigh = 2, plow = 1;
        while (phigh > plow) {
            //等差数列求和公式
            int cur = (phigh + plow) * (phigh - plow + 1) / 2;
            if (cur < sum)
                phigh++;

            if (cur == sum) {
                ArrayList<Integer> res = new ArrayList<>();
                for (int i = plow; i <= phigh; i++) {
                    res.add(i);
                }
                result.add(res);
                plow++;
            }

            if (cur > sum)
                plow++;
        }
        return result;
    }

    /**
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
     * 
     * 数列满足递增，设两个头尾两个指针i和j，
     * 若ai + aj == sum，就是答案（相差越远乘积越小）
     * 若ai + aj > sum，aj肯定不是答案之一（前面已得出 i 前面的数已是不可能），j -= 1
     * 若ai + aj < sum，ai肯定不是答案之一（前面已得出 j 后面的数已是不可能），i += 1 
     * @param array
     * @param sum
     */
    public static ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int n = array.length;
        int i = 0, j = n - 1;
        while (i < j) {
            if (array[i] + array[j] == sum) {
                result.add(array[i]);
                result.add(array[j]);
                break;
            } else if (array[i] + array[j] > sum) {
                j--;
            } else {
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findContinuousSequence(200));
        int[] array = new int[] { 1, 2, 4, 7, 11, 15 };
        System.out.println(findNumbersWithSum(array, 15));
    }
}