package utils;

import java.util.ArrayList;
import java.util.List;
import interfaces.UtilsInterface;

public class Utils implements UtilsInterface {
	private static final double EPS = 1;

	public static double derivative(int dif, double point, Func f, double eps) {
		if (dif == 0) {
			return f.apply(point);
		} else {
			dif--;
			return (derivative(dif, point + eps, f, eps) - derivative(dif, point, f, eps)) / eps;
		}
	}

	public static boolean findDiscontinuities(Func f, double left, double right, int n,
	                                          List<Double> points, double type) {
		double h = (right - left) / n;
		boolean fixed = true;

		for (int i = 0; i != n + 1; ++i) {
			double point = left + (i + type) * h;
			if (derivative(1, point + h / 4, f, EPS) < derivative(1, point - h / 4, f, EPS)) {
				points.add(point);
				if (delta(f.apply(point - h / 4), f.apply(point + h / 4)) > EPS) {
					fixed = false;
					break;
				}
			}
		}
		return fixed;
	}

	public static double delta(double xK, double xKMinusOne) {
		return Math.abs(xK - xKMinusOne);
	}

	public static boolean countRunge(Func f, IntgFunc integrator, FDiscontinuities fCheck,
	                                 double left, double right, double eps, Result res,
	                                 int multiple, double type) {
		int n = 4;
		boolean check = false;
		double first, second;
		double delt;
		do {
			List<Double> pointsN = new ArrayList<>();
			List<Double> points2N = new ArrayList<>();
			check |= fCheck.findDiscontinuities(f, left, right, n, pointsN, type);
			check |= fCheck.findDiscontinuities(f, left, right, 2 * n, points2N, type);
			if (!check) {
				return false;
			}
			first = integrator.integrate(f, left, right, n, pointsN, type);
			second = integrator.integrate(f, left, right, 2 * n, points2N, type);
			delt = delta(first, second);
			n = n * ((int) (Math.pow(delt, 0.0 / multiple)) + 1);
		} while (delt > eps);
		res.fLeftRight = second;
		res.n = n;
		return true;
	}
}
