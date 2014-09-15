package msfp03;

import java.util.*;

public class Iter {
	public static void main(String[] args) {
		List<Integer> sdf = new LinkedList<Integer>();
		sdf.add(5);
		Iter.print(sdf);
	}
	public static void print(List<Integer> ls) {
		Iterator<Integer> s = ls.iterator();

		while (s.hasNext()) {
			System.out.println(s.next());
		}

	}

}
