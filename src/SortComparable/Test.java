package SortComparable;

import java.util.*;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Student> fd = new LinkedList<Student>();
		Student s1 = new Student(111, "pok");
		Student s2 = new Student(11, "j");
		fd.add(s2);
		fd.add(s1);

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

		Collections.sort(fd);
		for (int u=0; u<10; u++){
			System.out.println(fd);
		}
	}
}
