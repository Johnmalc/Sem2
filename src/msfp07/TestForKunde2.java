package msfp07;

import java.util.*;

public class TestForKunde2 {

	public static void main(String[] args) {
		Comparator<Kunde2> comparator = new Kunde2();
		PriorityQueue<Kunde2> da = new PriorityQueue<Kunde2>(10, comparator);

		da.add(new Kunde2(1, "a"));
		da.add(new Kunde2(2, "g"));
		da.add(new Kunde2(2, "f"));
		da.add(new Kunde2(4, "c"));
		da.add(new Kunde2(3, "d"));
		da.add(new Kunde2(5, "e"));
		int u = 0;
		while (u < 7) {
			u++;
			System.out.println(da.poll());
		}
	}
}
