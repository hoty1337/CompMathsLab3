package utils;

import interfaces.UtilsInterface;

import java.util.List;

public class SimpsonMethod {
	public static double simpsonMethod(UtilsInterface.Func f, double left, double right, int n, List<Double> points, double type) {
		double h = (right - left) / n;
		double result = f.apply(left) + f.apply(right);
		double temp = 0;
		for(int i = 1; i < n; i += 2) {
			temp += f.apply(left + h * i);
		}
		result += 4 * temp;
		temp = 0;
		for(int i = 2; i < n - 1; i += 2) {
			temp += f.apply(left + h * i);
		}
		result += 2 * temp;
		return result * h / 3;
	}
}
