package jianzhioffer;

public class UglyNumber {

	/**
	 * 把只包含因子2、3和5的数称作丑数（Ugly Number）。 例如6、8都是丑数，但14不是，因为它包含因子7。
	 * 习惯上我们把1当做是第一个丑数。 求按从小到大的顺序的第N个丑数。
	 * 
	 * 要注意，后面的丑数是有前一个丑数乘以2，3，5中的一个得来。因此可以用动态规划去解
	 * 同时注意一下，题目意思应该是质数因子，而不是因子，因为8的因子有1，2，4，8 
	 * @param index
	 * @return
	 */
	public static int GetUglyNumber_Solution(int index) {
		if (index <= 0)
			return 0;
		if (index == 1)
			return 1;
		int[] array = new int[index];
		array[0] = 1;
		int t2 = 0, t3 = 0, t5 = 0;
		for (int i = 1; i < index; i++) {
			array[i] = Math.min(array[t2] * 2, Math.min(array[t3] * 3, array[t5] * 5));
			if (array[i] == array[t2] * 2)
				t2++;
			if (array[i] == array[t3] * 3)
				t3++;
			if (array[i] == array[t5] * 5)
				t5++;
		}
		return array[index - 1];
	}
	
	public static void main(String[] args) {
		System.out.println(GetUglyNumber_Solution(8));
	}
}
