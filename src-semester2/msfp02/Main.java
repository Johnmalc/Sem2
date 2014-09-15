package msfp02;

import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> a = new LinkedList<Integer>();
		a.add(5);
		a.add(6);
		a.add(8);
		a.add(5);
		System.out.println(Util.countNumber(a, 5));
		
	}

}
