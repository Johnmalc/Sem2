package msfp03;

public class ConverterMain {

	public static void main(String[] args) {
		double zahl = Double.parseDouble(args[0]);
		String na = new String(args[1]);
		
		if (na.equals("DM")){
			EURDMConverterImpl b = new EURDMConverterImpl(1.95583);
			System.out.println(b.convertDM2EU(zahl));
		}else {
			EURDMConverterImpl b = new EURDMConverterImpl(1.95583);
			System.out.println(b.convertEUR2DM(zahl));
		}

	}

}
