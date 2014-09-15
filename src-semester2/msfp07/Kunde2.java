package msfp07;

import java.util.*;

public class Kunde2 implements Comparator<Kunde2> {
	private String anfrage;
	private int prio;

	/**
	 * @param anfrage
	 * @param prio
	 */
	public Kunde2() {

	}

	public Kunde2(int prio, String anfrage) {
		this.anfrage = anfrage;
		this.prio = prio;
	}

	@Override
	public String toString() {
		return prio + ": " + anfrage;
	}

	public String getAnfrage() {
		return anfrage;
	}

	public void setAnfrage(String anfrage) {
		this.anfrage = anfrage;
	}

	public int getPrio() {
		return prio;
	}

	public void setPrio(int prio) {
		this.prio = prio;
	}

	@Override
	public int compare(Kunde2 o1, Kunde2 o2) {
		if (o1.getPrio() < o2.getPrio()) {
			return -1;
		}
		if (o1.getPrio() > o2.getPrio()) {
			return 1;
		}
		return 0;
	}
}