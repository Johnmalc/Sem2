package msfp08;

import java.util.Random;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tipp da = new Tipp();
		System.out.println(da.isRunning());
		System.out.println(da.isWinner());
		for (int i = 0; i < 10; i++) {
			Random s = new Random();
			int ss = s.nextInt(10);
			if (da.isRunning() == true) {
				System.out.println("test the number: " + ss);
				da.test(ss);
			}
		}
		System.out.println("sad");
		da.restart();
		System.out.println(da.isRunning());
		System.out.println(da.isWinner());
		System.out.println(da.getNumber());
		System.out.println(da.getTrials());
	}
}
