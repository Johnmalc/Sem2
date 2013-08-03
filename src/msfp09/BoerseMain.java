package msfp09;

public class BoerseMain {

	public static void main(String[] args) {
		
		Boerse b = new Boerse("dzfdf");
		
		Aktie a = new Aktie("gds", 5);
		Aktie c = new Aktie("s", 0);
		
		BeorseTicker as = new BeorseTicker();

		b.addak(a, 654.5);
		b.addak(c, 14.8);
		b.addObserver(as);
		b.setKurs(a, 15614.61);
		

		
		
		
	}

}
