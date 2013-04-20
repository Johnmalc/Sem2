package mspf03;

public class Converter {

	private double rateAB;

	public Converter(double rateAB) {
		this.rateAB = rateAB;
	}

	public double convertAtoB(double amount) {
		return rateAB * amount;
	}

	public double convertBtoA(double amount) {
		return amount / rateAB;
	}
	
}
