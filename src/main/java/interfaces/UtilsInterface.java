package interfaces;

import java.util.List;

public interface UtilsInterface {

	class Result {
		public int n;
		public double fLeftRight;
	}

	interface Func {
		double apply(double x);
	}

	interface IntgFunc {
		double integrate(Func f, double left, double right, int n,
		                 List<Double> points, double type);
	}

	interface FDiscontinuities {
		boolean findDiscontinuities(Func f, double left, double right, int n,
		                            List<Double> points, double type);
	}

}
