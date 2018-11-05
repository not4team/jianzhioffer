package jianzhioffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Array {

	/**
	 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
	 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
	 * 
	 * 采用阵地攻守的思想： 第一个数字作为第一个士兵，守阵地；count = 1；遇到相同元素，count++;
	 * 遇到不相同元素，即为敌人，同归于尽,count--；
	 * 当遇到count为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上的士兵，有可能是主元素。
	 * 再加一次循环，记录这个士兵的个数看是否大于数组一般即可。
	 * 
	 * @param array
	 * @return
	 */
	public static int MoreThanHalfNum_Solution(int[] array) {
		int n = array.length;
		if (n == 0) {
			return 0;
		}

		int num = array[0], count = 1;
		for (int i = 1; i < n; i++) {
			if (array[i] == num) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				num = array[i];
				count = 1;
			}
		}
		// Verifying
		count = 0;
		for (int i = 0; i < n; i++) {
			if (array[i] == num) {
				count++;
			}
		}
		if (count * 2 > n) {
			return num;
		}
		return 0;
	}

	/**
	 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
	 * 
	 * @param input
	 * @param k
	 * @return
	 */
	public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int length = input.length;
		if (k > length || k == 0) {
			return result;
		}
		//优先队列，最大堆，堆顶是最大的一个数
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		for (int i = 0; i < length; i++) {
			if (maxHeap.size() != k) {
				maxHeap.offer(input[i]);
			} else if (maxHeap.peek() > input[i]) {
				Integer temp = maxHeap.poll();
				temp = null;
				maxHeap.offer(input[i]);
			}
		}
		for (Integer integer : maxHeap) {
			result.add(integer);
		}
		return result;
	}

	/**
	 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,
	 * 他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
	 * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},
	 * 连续子向量的最大和为8(从第0个开始,到第3个为止)。你会不会被他忽悠住？(子向量的长度至少是1)
	 * 
	 * @param array
	 * @return
	 */
	public int FindGreatestSumOfSubArray(int[] array) {
		// 动态规划 保存中间计算结果
		if (array.length == 0) {
			return 0;
		}
		int sum = array[0], tempsum = array[0]; // 注意初始值 不能设为0 防止只有负数
		for (int i = 1; i < array.length; i++) { // 从1开始 因为0的情况在初始化时完成了
			// tempsum<0时表示之前的正数不足以弥补以后的负数,tempsum就要从下一位数开始
			tempsum = (tempsum < 0) ? array[i] : tempsum + array[i];
			sum = (tempsum > sum) ? tempsum : sum;
		}
		return sum;
	}

	/**
	 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
	 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
	 * 
	 * @param numbers
	 * @return
	 */
	public static String printMinNumber(int[] numbers) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				String str1 = o1 + "" + o2;
				String str2 = o2 + "" + o1;
				return str1.compareTo(str2);
			}
		});
		System.out.println(list);
		String result = "";
		for (int item : list) {
			result += item;
		}
		return result;
	}

	/**
	 * 统计一个数字在排序数组中出现的次数。
	 * 
	 * 二分查找
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public int GetNumberOfK(int[] array, int k) {
		int length = array.length;
		if (length == 0) {
			return 0;
		}
		int firstK = getFirstK(array, k, 0, length - 1);
		int lastK = getLastK(array, k, 0, length - 1);
		if (firstK != -1 && lastK != -1) {
			return lastK - firstK + 1;
		}
		return 0;
	}

	// 递归写法
	private int getFirstK(int[] array, int k, int start, int end) {
		if (start > end) {
			return -1;
		}
		int mid = (start + end) >> 1;
		if (array[mid] > k) {
			return getFirstK(array, k, start, mid - 1);
		} else if (array[mid] < k) {
			return getFirstK(array, k, mid + 1, end);
		} else if (mid - 1 >= 0 && array[mid - 1] == k) {
			return getFirstK(array, k, start, mid - 1);
		} else {
			return mid;
		}
	}

	// 循环写法
	private int getLastK(int[] array, int k, int start, int end) {
		int length = array.length;
		int mid = (start + end) >> 1;
		while (start <= end) {
			if (array[mid] > k) {
				end = mid - 1;
			} else if (array[mid] < k) {
				start = mid + 1;
			} else if (mid + 1 < length && array[mid + 1] == k) {
				start = mid + 1;
			} else {
				return mid;
			}
			mid = (start + end) >> 1;
		}
		return -1;
	}

	/**
	 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
	 * num1,num2分别为长度为1的数组。传出参数将num1[0],num2[0]设置为返回结果
	 * 
	 * 异或运算符（^）运算规则：0^0=0； 0^1=1； 1^0=1； 1^1=0； 按位或运算符（|）运算规则：0|0=0； 0|1=1； 1|0=1；
	 * 1|1=1； 按位与运算符（&）运算规则：0&0=0; 0&1=0; 1&0=0; 1&1=1; 取反运算符（~）运算规则：~1=0； ~0=1；
	 * 左移运算符（<<）将一个运算对象的各二进制位全部左移若干位（左边的二进制位丢弃，右边补0）。若左移时舍弃的高位不包含1，则每左移一位，相当于该数乘以2。
	 * 右移运算符（>>）将一个数的各二进制位全部右移若干位，正数左补0，负数左补1，右边丢弃。左补0 or 补1 得看被移数是正还是负。
	 * 无符号右移运算符（>>>）右移后左边空出的位用零来填充。移出右边的位被丢弃。
	 * 
	 * 可以用位运算实现，如果将所有所有数字相异或，则最后的结果肯定是那两个只出现一次的数字异或的结果， 所以根据异或的结果1所在的最低位，把数字分成两半，
	 * 每一半里都还有只出现一次的数据和成对出现的数据这样继续对每一半相异或则可以分别求出两个只出现一次的数字
	 * 
	 * @param array
	 * @param num1
	 * @param num2
	 */
	public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
		if (array.length < 2) {
			return;
		}
		int size = array.length;
		int temp = array[0];
		// 找出两个只出现一次的数字异或的结果
		for (int i = 1; i < size; i++) {
			temp = temp ^ array[i];
		}
		if (temp == 0) {
			return;
		}
		int index = 0;
		// 异或的结果1所在的最低位
		while ((temp & 1) == 0) {
			temp = temp >> 1;
			++index;
		}
		for (int i = 0; i < size; i++) {
			if (IsBit(array[i], index)) { //数组分成两组，该位是1的数和不是1的数
				num1[0] ^= array[i];
			} else {
				num2[0] ^= array[i];
			}
		}
	}

	/**
	 * 判断该位是不是1
	 */
	private boolean IsBit(int num, int index) {
		num = num >> index;
		return (num & 1) == 1;
	}

	/**
	 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
	 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
	 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
	 * 
	 * 当一个数字被访问过后，可以设置对应位上的数 - n，之后再遇到相同的数时，会发现对应位上的数已经小于0了，那么直接返回这个数即可。
	 */
	// Parameters:
	// numbers: an array of integers
	// length: the length of array numbers
	// duplication: (Output) the duplicated number in the array number,length of
	// duplication array is 1,so using duplication[0] = ? in implementation;
	// Here duplication like pointor in C/C++, duplication[0] equal *duplication in
	// C/C++
	// 这里要特别注意~返回任意重复的一个，赋值duplication[0]
	// Return value: true if the input is valid, and there are some duplications in
	// the array number
	// otherwise false
	public boolean duplicate(int numbers[], int length, int[] duplication) {
		for (int i = 0; i < length; i++) {
			int index = numbers[i];
			if (index < 0) {
				index += length;
			}
			if (numbers[index] < 0) {
				duplication[0] = index;
				return true;
			}
			numbers[index] = numbers[index] - length;

		}
		return false;
	}

	/**
	 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
	 * 
	 * B[i]的意义是A数组不包括i位置的所有乘积，分为 i左边的元素乘积和 i右边的所有元素乘积。第一个for计算i左边的乘积，第二个for计算右边的。
	 * 初始化B[0]=1，是因为0左边没有元素，所以乘积为1。
	 * 
	 * B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]
	 * 从左到右算 B[i]=A[0]*A[1]*...*A[i-1]
	 * 从右到左算B[i]*=A[i+1]*...*A[n-1] 
	 */
	public int[] multiply(int[] A) {
		int[] B = new int[A.length];
		int temp = 1;
		for (int i = 0; i < A.length; temp *= A[i++]) {
			B[i] = temp;
		}
		temp = 1;
		for (int i = A.length - 1; i >= 0; temp *= A[i--]) {
			B[i] *= temp;
		}
		return B;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 1, 2, 3, 2, 2, 2, 5, 4, 2 };
		int[] array1 = new int[] { 1 };
		System.out.println(MoreThanHalfNum_Solution(array));
		System.out.println(MoreThanHalfNum_Solution(array1));
		int[] array2 = new int[] { 3, 32, 321 };
		printMinNumber(array2);
	}
}
