package msfp09;

import java.util.*;

public class BeorseTicker implements Observer {

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("nejeu : "+ ((Boerse) arg0).getTicker());
	}

}
