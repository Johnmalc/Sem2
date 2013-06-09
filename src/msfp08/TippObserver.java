package msfp08;

import java.util.Observable;
import java.util.Observer;

public class TippObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		if (((Tipp) o).isWinner() == true || ((Tipp) o).getTrials() > 5) {
			System.out.println("You won after " + ((Tipp) o).getTrials()
					+ " with the number " + ((Tipp) o).getNumber());
		} else {
			System.out.println("You lose after 5 trials with the number "
					+ ((Tipp) o).getNumber());
		}
	}

}
