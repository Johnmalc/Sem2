package msfp09;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 1; i <= 10; i++) {
			for (int s = 1; s <= 10; s++) {
				set.add(i);
			}
		}
		int k = 0;
		for (Integer u : set) {
			 k+=u; // 55
			// o++; // 10
		}
		System.out.println(k);
	}

}
