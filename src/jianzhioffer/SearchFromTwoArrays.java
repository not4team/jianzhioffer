package jianzhioffer;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数
 * 
 * @author shixq
 *
 * 思路 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增， 因此从左下角开始查找，当要查找数字比左下角数字大时。右移
 * 要查找数字比左下角数字小时，上移
 */
public class SearchFromTwoArrays {
	public boolean Find(int target, int[][] array) {
		int rowCount = array.length;
		int colCount = array[0].length;
		System.out.println("rowCount:" + rowCount + ",colCount:" + colCount);
		int i,j;
		for (i = rowCount - 1, j = 0; i >= 0 && j < colCount;) {
			if(target == array[i][j]) {
				return true;
			}else if(target < array[i][j]) {
				i--;
				continue;
			}else if(target > array[i][j]) {
				j++;
				continue;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[][] array = new int[][]{{1,2,3},{7,8,9},{10,12,16}};
		SearchFromTwoArrays o = new SearchFromTwoArrays();
		System.out.println(o.Find(5, array));
	}
}
