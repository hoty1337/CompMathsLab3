import interfaces.UtilsInterface;
import utils.RectangleMethod;
import utils.SimpsonMethod;
import utils.TrapezoidMethod;
import utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static final double MIN_FOR_ZERO = 1e-6;

	private static double f1(double x) {
		return 1 / x;
	}

	private static double f2(double x) {
		return Math.sin(x);
	}

	private static double f3(double x) {
		return 3 * x * x - 2 * x + 13;
	}

	private static double f4(double x) {
		return Math.abs(x);
	}

	private static double f5(double x) {
		return Math.sin(x) / x;
	}

	private static double f6(double x) {
		return 3 * x * x * x - 2 * x * x - 7 * x - 8;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("1)1/x\n2)sin(x)\n3)3*x^2-2*x+13\n4)|x|\n5)sin(x)/x\n6)3*x^3-2*x^2-7*x-8");
		int choice = scan.nextInt();
		choice--;
		System.out.println("Choose method:\n1) Rectangle\n2) Trapezoid\n3) Simpson");
		int method = scan.nextInt();

		List<UtilsInterface.Func> fVector = new ArrayList<>();
		fVector.add(Main::f1);
		fVector.add(Main::f2);
		fVector.add(Main::f3);
		fVector.add(Main::f4);
		fVector.add(Main::f5);
		fVector.add(Main::f6);

		double eps, left, right;
		System.out.print("Left is ");
		left = scan.nextDouble();
		System.out.print("Right is ");
		right = scan.nextDouble();
		System.out.print("Epsilon is ");
		eps = scan.nextDouble();
		left = (left == 0 ? MIN_FOR_ZERO : left);
		right = (right == 0 ? MIN_FOR_ZERO : right);

		UtilsInterface.Result r1 = new UtilsInterface.Result();
		UtilsInterface.Result r2 = new UtilsInterface.Result();
		UtilsInterface.Result r3 = new UtilsInterface.Result();

		boolean show;
		if (method == 1) {
			show = Utils.countRunge(fVector.get(choice), RectangleMethod::rectangleMethod,
					Utils::findDiscontinuities, left, right, eps, r1, 1, 0);
			show &= Utils.countRunge(fVector.get(choice), RectangleMethod::rectangleMethod,
					Utils::findDiscontinuities, left, right, eps, r2, 1, 0.5);
			show &= Utils.countRunge(fVector.get(choice), RectangleMethod::rectangleMethod,
					Utils::findDiscontinuities, left, right, eps, r3, 1, 1);

			if (show) {
				System.out.println("Left = " + r1.fLeftRight + "\nCentral = " + r2.fLeftRight + "\nRight = " + r3.fLeftRight);
			} else {
				System.out.println("Irreparable function break.");
			}
		} else if (method == 2) {
			double res = TrapezoidMethod.trapezoidMethod(fVector.get(choice), left, right, 4);
			System.out.println(res);
		} else if (method == 3) {
			double res = SimpsonMethod.simpsonMethod(fVector.get(choice), left, right, 4);
			System.out.println(res);
		}
	}
}
