package utils;

import interfaces.UtilsInterface;

import java.util.List;

public class TrapezoidMethod {

	public static double trapezoidMethod(UtilsInterface.Func f, double left, double right, int n, List<Double> points, double type){
		double h = ((right - left) / n);
		double result = (f.apply(left) + f.apply(right)) / 2;
		for (int i = 1; i < n; i++) {
			result += f.apply(left + h * i);
		}
		return h * result;
	}
}
