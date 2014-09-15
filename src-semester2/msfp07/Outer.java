package msfp07;

public class Outer {
	private static int g = 99;

	public Outer() {

	}

	public static class Inner {
		public static void ac() {
			Outer.g = -1;
		}
	}
}
