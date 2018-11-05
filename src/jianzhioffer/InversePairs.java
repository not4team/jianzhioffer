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
	 * 输入1,2,3,4,5,6,7,0 输出7
	 * 
	 * 使用归并排序的思想进行处理
	 * 
	 * (a) 把长度为4的数组分解成两个长度为2的子数组；
	 * (b) 把长度为2的数组分解成两个成都为1的子数组；
	 * (c) 把长度为1的子数组 合并、排序并统计逆序对 ；
	 * (d) 把长度为2的子数组合并、排序，并统计逆序对；
	 * 
	 * 在上图（a）和（b）中，我们先把数组分解成两个长度为2的子数组，再把这两个子数组分别拆成两个长度为1的子数组。接下来一边合并相邻的子数组，
	 * 一边统计逆序对的数目。在第一对长度为1的子数组{7}、{5}中7大于5，因此（7,5）组成一个逆序对。同样在第二对长度为1的子数组{6}、{4}中也有
	 * 逆序对（6,4）。由于我们已经统计了这两对子数组内部的逆序对，因此需要把这两对子数组 排序 如上图（c）所示， 以免在以后的统计过程中再重复统计。
	 * 接下来我们统计两个长度为2的子数组子数组之间的逆序对。合并子数组并统计逆序对的过程如下图如下图所示。
	 * 
	 * 我们先用两个指针分别指向两个子数组的末尾，并每次比较两个指针指向的数字。如果第一个子数组中的数字大于第二个数组中的数字，则构成逆序对，
	 * 并且逆序对的数目等于第二个子数组中剩余数字的个数，如下图（a）和（c）所示。如果第一个数组的数字小于或等于第二个数组中的数字，则不构成逆序对，
	 * 如图b所示。每一次比较的时候，我们都把较大的数字从后面往前复制到一个辅助数组中，确保 辅助数组（记为copy） 中的数字是递增排序的。在把较大的
	 * 数字复制到辅助数组之后，把对应的指针向前移动一位，接下来进行下一轮比较。
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
				count += j - start - length;//count=count+j-(start+length+1)+1;
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
