package msfp07;

public class TestForFraction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fraction da = new Fraction(2, 5);
		Fraction das = new Fraction(3, 10);
		Fraction novy = new Fraction();
		novy.multiply(da, das);
		System.out.println(novy.toString());
		novy.reduce();
		System.out.println(novy.toString());
	}
}
