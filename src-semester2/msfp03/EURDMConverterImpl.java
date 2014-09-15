package msfp03;

public class EURDMConverterImpl implements EURDMConverter {
	public double rateAB;

	public EURDMConverterImpl(double rate) {
		this.rateAB = rate;
	}

	public double convertEUR2DM(double amount) {
		return amount * rateAB;
	}

	@Override
	public double convertDM2EU(double amount) {
		return amount / rateAB;
	}
	public static void main(String[] args) {
		Converter a = new Converter(1.95583);
		System.out.println(a.convertAtoB(65651));
		System.out.println(a.convertBtoA(65651));
	}
}
