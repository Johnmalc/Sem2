package msfp09;

public class Aktie {
	private int id;
	private String name;

	public Aktie(String name, int id) {
		this.id = id;
		this.name = name;
	}

	public boolean equals(Object obj) {
		Aktie other = (Aktie) obj;
		if (id != other.id)
			return false;
		else
			return true;
	}

	@Override
	public String toString() {
		return id + " " + name;
	}

}
