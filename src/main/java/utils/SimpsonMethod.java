package utils;

import interfaces.UtilsInterface;

public class SimpsonMethod {
	public static double simpsonMethod(UtilsInterface.Func f, double a, double b, int n) {
		double h = (b - a) / n;
		double result = f.apply(a) + f.apply(b);
		double temp = 0;
		for(int i = 1; i < n; i += 2) {
			temp += f.apply(a + h * i);
		}
		result += 4 * temp;
		temp = 0;
		for(int i = 2; i < n - 1; i += 2) {
			temp += f.apply(a + h * i);
		}
		result += 2 * temp;
		return result * h / 3;
	}
}
