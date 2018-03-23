package jianzhioffer;

public class InversePairs {

	/**
	 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
	 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
	 * 输入描述:题目保证输入的数组中没有的相同的数字数据范围： 
	 * 对于%50的数据,size<=10^4 
	 * 对于%75的数据,size<=10^5
	 * 对于%100的数据,size<=2*10^5
	 * 
	 * 使用归并排序的思想进行处理
	 * 
	 * @param array
	 * @return
	 */
	public static int inversePairs(int[] array) {

		if (array.length <= 1)
			return 0;// 如果少于等于1个元素，直接返回0
		int[] copy = new int[array.length];
		// 调用递归函数求解结果
		int count = inversePairCore(array, copy, 0, array.length - 1);
		copy = null;// 删除临时数组
		return count;
	}

	static int inversePairCore(int[] data, int[] copy, int start, int end) {
		if (start == end) {
			copy[start] = data[start];
			return 0;
		}
		// 将数组拆分成两部分
		int length = (end - start) / 2;// 这里使用的下标法，下面要用来计算逆序个数；也可以直接使用mid=（start+end）/2
		// 分别计算左边部分和右边部分
		int left = inversePairCore(data, copy, start, start + length) % 1000000007;
		int right = inversePairCore(data, copy, start + length + 1, end) % 1000000007;
		// 进行逆序计算
		int i = start + length;// 前一个数组的最后一个下标
		int j = end;// 后一个数组的下标
		int index = end;// 辅助数组下标，从最后一个算起
		int count = 0;
		while (i >= start && j >= start + length + 1) {
			if (data[i] > data[j]) {
				copy[index--] = data[i--];
				System.out.println("j " + j + " start " + start + " length " + length);
				// 统计长度
				count += j - start - length;
				if (count >= 1000000007)// 数值过大求余
					count %= 1000000007;
			} else {
				copy[index--] = data[j--];
			}
		}
		//添加剩下的前半部分到copy中 
		for (; i >= start; --i) {
			System.out.println("left index " + index + " i " + i);
			copy[index--] = data[i];
		}
		//添加剩下的后半部分到copy中 
		for (; j >= start + length + 1; --j) {
			System.out.println("right index " + index + " j " + j);
			copy[index--] = data[j];
		}
		// 排序
		for (int k = start; k <= end; k++) {
			data[k] = copy[k];
		}
		// 返回最终的结果
		return (count + left + right) % 1000000007;
	}
	
	public static void main(String[] args) {
		int[] array = new int[] {1,2,3,0};
		System.out.println(inversePairs(array));
	}
}
