package SortComparable;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Student s1 = new Student(111, "pok");
		Student s2 = new Student(11, "j");

		int c = s1.compareTo(s2);
		boolean b = false;
		if (c == 0)
			b = true;

		System.out.println("s1==s2?" + b);
		System.out.println("s1==s2?" + s1.equals(s2));
		String po = "da";
		String pos = "da";
		System.out.println(po.equals(pos));
		System.out.println(po.compareTo(pos));
	}
}
