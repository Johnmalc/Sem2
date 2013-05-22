package msfp07;

public class Fraction {
	/*
	 * http://www.cs.berkeley.edu/~jrs/4/lec/15
	 */

	public int zah;
	public int nen;
	public int neuZah;
	public int neuNen;
	public int reduceNovy;

	public Fraction() {

	}

	/**
	 * @param zah
	 * @param nen
	 */
	public Fraction(int zah, int nen) {
		this.zah = zah;
		this.nen = nen;
	}

	@Override
	public String toString() {
		return "Bruch " + zah + ": " + nen + " oder " + neuZah + ": " + neuNen;
	}

	public Fraction multiply(Fraction ob, Fraction ob2) {
		neuNen = ob.nen * ob2.nen;
		neuZah = ob.zah * ob2.zah;
		return new Fraction(neuZah, neuNen);
	}

	public void reduce() {
		int divisor = GCD(neuZah, neuNen);
		neuNen = neuNen / divisor;
		neuZah = neuZah / divisor;
		if (neuZah < 0) {
			neuNen = -neuNen;
			neuZah = -neuZah;
		}
	}

	public int GCD(int neuZah, int neuNen) {
		if (neuNen == 0)
			return neuZah;
		return GCD(neuNen, neuZah % neuNen);
	}
}
