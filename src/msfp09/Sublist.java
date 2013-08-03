package msfp09;

import java.util.*;

public class Sublist {
	public static void main(String[] args) {
		ArrayList<Integer> asd = new ArrayList<Integer>();
		for (int i = 0; i <10;i++){
			asd.add(i);
		}
		Integer x = asd.subList(0,5).subList(2, 3).subList(0, 1).get(0);
		System.out.println(x);
	}
}
