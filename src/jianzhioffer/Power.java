package jianzhioffer;
/**
 * 快速幂
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * @author shixq
 *
 */
public class Power {
	
	/**
	 * 11的二进制是1011 = 2³×1 + 2²×0 + 2¹×1 + 2º×1因此，我们将a¹¹转化为a^8 * a^0 * a^2 * a^1
	 * @param base
	 * @param exponent
	 * @return
	 */
	public static double Power(double base, int exponent) {
		double result = 1,curr = base;
		int n = exponent;
		if(exponent < 0) {
			n = -exponent;
		}
		while(n != 0) {
			//判断最低位是1还是0,如果是0的话底数的0次幂等于1
			if((n & 1) == 1)
				result *= curr;
			curr *= curr;
			n >>= 1;
		}
		return exponent > 0 ? result : 1 / result;
	}

	public static void main(String[] args) {
		System.out.println(Power(2.0,3));
	}
}
