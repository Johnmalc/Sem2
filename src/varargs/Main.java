package varargs;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	static int max(int... array) {
		if (array == null || array.length == 0)
			throw new IllegalArgumentException("Illegal array!");
		int currentMax = Integer.MIN_VALUE;
		System.out.println(currentMax);
		for (int e : array)
			if (e > currentMax)
				currentMax = e;
		System.out.println(currentMax);
		return currentMax;
	}

	public static void main(String[] args) {
		int[] de = {4, 5};
		int[] di = {10};
		System.out.println(max(1, 2, 9, 3)); // 9
		// System.out.println(test1("jkb", di));
		System.out.println(test2(de));
		printGreeting("dsfsadf", "sdfSAD", "DSAfds"); // if nothing > no
														// greeting, but also no
														// error etc.
		System.out.println(mul(2, 6, 3));
		AS[] sd = AS.values();
		for (AS s : sd) {
			System.out.println(s.ordinal());
		}
		System.out.println();
		PriorityQueue<String> da = new PriorityQueue<String>();
		da.add("SDf");		
	}
	private static int test2(int[] c) {
		return c[0];
	}

	public static int test1(String t, int[] b) {
		return b[0] + b[1];
	}
	public static void printGreeting(String... names) {
		for (String n : names) {
			System.out.println("Hello " + n + ". ");
		}
	}
	public static double mul(double... a) {
		double des = 1.0;
		for (double s : a) {
			des *= s;
		}
		return des;
	}
	public enum AS {
		SD, cfg;
	}

}
