package jianzhioffer;

import java.util.ArrayList;

/**
 * 矩阵
 * 
 * @author shixq
 *
 */
public class Matrix {

	/**
	 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下矩阵： 
	 * 1 2 3 4 
	 * 5 6 7 8 
	 * 9 10 11 12 
	 * 13 14 15 16 
	 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
	 * 
	 * 可以模拟魔方逆时针旋转的方法，一直做取出第一行的操作 例如 
	 * 1 2 3 
	 * 4 5 6 
	 * 7 8 9
	 * 输出并删除第一行后，再进行一次逆时针旋转，就变成：
	 * 6 9 
	 * 5 8 
	 * 4 7 
	 * 继续重复上述操作即可。
	 * 
	 * @param matrix
	 * @return
	 */
	public static ArrayList<Integer> printMatrix(int[][] matrix) {
		ArrayList<Integer> list = new ArrayList<>();
		int row = matrix.length;
		while(row > 0) {
			for (int i = 0; i < matrix[0].length; i++) {
				list.add(matrix[0][i]);
			}
			if(row == 1) {
				break;
			}
			matrix = reverse(matrix);
			row = matrix.length;
		}
		return list;
	}

	public static int[][] reverse(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] result = new int[col][row - 1];
		for (int i = col - 1; i >= 0; i--) {
			for (int j = 1; j < row; j++) {
				result[col - 1 - i][j - 1] = matrix[j][i];
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		System.out.println(printMatrix(matrix));
	}
}
