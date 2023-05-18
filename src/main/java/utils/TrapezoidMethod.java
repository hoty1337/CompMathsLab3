package utils;

import interfaces.UtilsInterface;

public class TrapezoidMethod {

	public static double trapezoidMethod(UtilsInterface.Func f, double a, double b, int n){
		double h = ((b - a) / n);
		double result = (f.apply(a) + f.apply(b)) / 2;
		for (int i = 1; i < n; i++) {
			result += f.apply(a + h * i);
		}
		return h * result;
	}
}
