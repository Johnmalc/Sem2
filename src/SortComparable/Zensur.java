package SortComparable;

import java.util.SortedSet;
import java.util.TreeSet;

public class Zensur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SortedSet<String> words = new TreeSet<String>();

		words.add("frohlich");
		words.add("friede");
		words.add("affe");
		words.add("gut");

		System.out.println(words);
		words.subSet("f", "g").clear();
		System.out.println(words);
	}

}
