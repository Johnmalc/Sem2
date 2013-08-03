package msfp09;

import java.util.*;

public class Boerse extends Observable {

	private String name;
	private HashMap<Aktie, Double> aktienKurse;

	public Boerse(String name) {
		this.name = name;
		aktienKurse = new HashMap<Aktie, Double>();
	}

	public void setKurs(Aktie ak, double kurs) {
		aktienKurse.put(ak, kurs);
		setChanged();
		notifyObservers(kurs);
	}
	public double getKurs(Aktie ak) {
		return aktienKurse.get(ak);
	}
	public String getTicker() {
		return aktienKurse.toString();
	}
	void addak(Aktie ak, double kurs) {
		aktienKurse.put(ak, kurs);
	}
	public String toString() {
		return name;
	}

}
