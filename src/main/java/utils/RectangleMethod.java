package utils;

import interfaces.UtilsInterface;

import java.util.List;

public class RectangleMethod {

	public static double rectangleMethod(UtilsInterface.Func f, double left, double right, int n, List<Double> points, double type) {
		double h = (right - left) / n, sum = 0;

		for(int i = 0; i < n; i++) {
			double x = left + (i + type) * h;
			if(points.contains(x)) {
				sum += (f.apply(x + h / 4) + f.apply(x - h / 4)) / 2;
			} else {
				sum += f.apply(x);
			}
		}
		return sum * h;
	}
}
