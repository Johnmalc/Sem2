package mspf03;

public class EURDMConverterImpl implements EURDMConverter {

	@Override
	public double convertEUR2DM(double amount) {
		return amount*1.95583;
	}

	@Override
	public double convertDM2EU(double amount) {
		return amount/1.95583;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Converter a = new Converter(1.95583);
		System.out.println(a.convertAtoB(65651));
		System.out.println(a.convertBtoA(65651));
	}

}
