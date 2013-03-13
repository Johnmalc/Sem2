package malcjohn;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Pair<String, Integer> p1 = new Pair<String, Integer>("1", 2);

		System.out.println(p1.getOne());
		System.out.println(p1.getTwo());

		Pair p2 = new Pair<String, String>("a", "b");// Zuweisung zum Raw-Type
														// m�glich! Aber
														// gef�hrlich!
		p2.setOne(12);// Jetzt kann ich �ber die Raw-Type-Variable ein int
						// speichern! Oje ...

		Pair<String, String> p3 = (Pair<String, String>) p2;// Cast zur�ck OK

		String s = p3.getOne();// Abfrage f�hrt jetzt zu -> ClassCastException

		// Fazit: Verzichten Sie wenn m�glich auf die Verwendung des Raw-Type!
		// Er macht den Code unsicher!!!
	}

}
