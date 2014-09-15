package AufgabenKnihaJava;

/*
 * "Grundkurs Programmieren in Java - (6. Auflage, 2011)"
 * 2001-2011, Carl Hanser Verlag
 * Loesungsvorschlag zu Aufgabe 11.2 (Version 3.0)
 * (c) 2001-2011 D. Ratz, J. Scheffler, D. Seese, J. Wiesenberger
 *
 * Methoden zum Spiegeln von int-Feldern -
 * einmal mit und einmal ohne Seiten-Effekte
 *
 */

interface Tier2 {
}
interface HausTier2 extends Tier2 {
}
interface WildTier2 extends Tier2 {
}
class Katze2 implements Tier2 {
	public String toString() {
		return getClass().getName();
	}
}
class HausKatze extends Katze2 implements HausTier2 {
}
class WildKatze extends Katze2 implements WildTier2 {
}
public class Tierleben {
	/*
	 * public static void gibAus(Object Tier2) { System.out.println("Objekt: " +
	 * Tier2); }
	 */
	/*
	 * public static void gibAus(Katze2 Tier2) { System.out.println("Katze2: " +
	 * Tier2); }
	 */
	public static <T> void gibAus(T Tier2) {
		System.out.println("Unbekannt: " + Tier2);
	}
	public static <T extends Tier2> void gibAus(T Tier2) {
		System.out.println("Tier2: " + Tier2);
	}
	public static <T extends WildKatze> void gibAus(T Tier2) {
		System.out.println("HausTier2: " + Tier2);
	}
	public static void main(String[] args) {
		gibAus("Amoebe");
		gibAus(new Katze2());
		gibAus(new HausKatze());
		gibAus(new WildKatze());
	}
}
