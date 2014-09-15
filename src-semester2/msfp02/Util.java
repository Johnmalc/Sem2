package msfp02;

import java.util.*;

public class Util {

	public static int countNumber(List<Integer> list, int number) {

		int occurrences = Collections.frequency(list, number);

		return occurrences;

	}

}
