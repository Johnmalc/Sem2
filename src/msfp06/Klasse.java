package msfp06;

import java.util.Random;

import msfp06.Ampleschalter.Schalter;

public class Klasse implements AmSch {
	Schalter schlat;

	public Klasse() {
		schlat = Schalter.RED;
	}

	@Override
	public void druckeSchal() {
		// 1 possibility
		// for (int i = 0; i < 6; i++) {
		// if (Schalter.Rot == schlat) {
		// schlat = Schalter.Grun;
		// System.out.println(schlat);
		// } else {
		// schlat = Schalter.RED;
		// System.out.println(schlat);
		// }
		// }
		// 2 another
		Random ro = new Random();
		int zah = ro.nextInt(2);
		if (zah == 1) {
			schlat = Schalter.GRUN;
			System.out.println(schlat);
		}else {
			schlat = Schalter.RED;
			System.out.println(schlat);
		}
	}

	@Override
	public Schalter getStatus() {
		return schlat;
	}

}
