package observerSample;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		NameArrayObservable na = new NameArrayObservable();
		NameArrayObserver nav = new NameArrayObserver();
		na.addObserver(nav);
		Scanner sc = new Scanner(System.in);

		System.out.print("Index (0-9): ");
		int index = sc.nextInt();
		System.out.print("Name: ");
		String name = sc.next();
		na.setName(name, index);

	}

}
