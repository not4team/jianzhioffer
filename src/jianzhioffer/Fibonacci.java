package jianzhioffer;

/**
 * 斐波那契数列 1,1,2,3,5,8,13,21
 * 
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * 
 * 第n级台阶只能从n-1级台阶跳一步，或者从第n-2级台阶跳2步，假设n-1级台阶有f(n-1)种跳法，n-2级台阶有f(n-2)种跳法，f(n) = f(n-1) + f(n-2)
 * 
 * @author shixq
 *
 */
public class Fibonacci {
	/**
	 * 递归方法（递归就意味着大量的函数调用。函数调用的局部状态是用栈来记录的。所以，这样就可能浪费大量的空间，如果递归太深的话还有可能导致堆栈溢出。）
	 * 
	 * @param n
	 * @return
	 */
	public int Fibonacci(int n) {
		if (n <= 0)
			return 0;
		if (n <= 2)
			return 1;
		return Fibonacci(n - 1) + Fibonacci(n - 2);
	}

	/**
	 * 迭代方法（递归都可以用迭代来代替，迭代的效率比递归要高，并且在空间消耗上也比较小）
	 * 
	 * @param n
	 * @return
	 */
	public int Fibonacci1(int n) {
		if (n <= 0)
			return 0;
		if (n <= 2)
			return 1;
		int pre = 1;
		int prepre = 1;
		int result = 0;
		while (n-- > 2) {
			result = pre + prepre;
			prepre = pre;
			pre = result;
		}
		return result;
	}

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法 1,2,3,5,8,13
	 * f(n) = f(n-1) + f(n-2)
	 * @param target
	 * @return
	 */
	public int JumpFloor(int target) {
		if (target <= 0)
			return 0;
		if (target == 1)
			return 1;
		else if (target == 2)
			return 2;
		int pre = 2;
		int prepre = 1;
		int result = 0;
		while (target-- > 2) {
			result = pre + prepre;
			prepre = pre;
			pre = result;
		}
		return result;
	}

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法
	 * f(n) = 2*f(n-1)
	 * 每个台阶都有跳与不跳两种情况（除了最后一个台阶），最后一个台阶必须跳。所以共用2^(n-1)中情况 
	 * f(n) = 2^(n-1)
	 * @param target
	 * @return
	 */
	public int JumpFloorII(int target) {
//		int result = (int) Math.pow(2, target - 1);
//		return result;

		if (target <= 0) {
			return -1;
		} else if (target == 1) {
			return 1;
		} else {
			return 2 * JumpFloorII(target - 1);
		}
	}
}
