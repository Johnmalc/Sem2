package MVCmitObserver;

import java.util.Observable;

/*Model contains data and logic*/


public class Counter extends Observable {
	
	private int counter = 0;
	
	public void inc() {		
		counter++;
		setChanged();
		notifyObservers(counter);
	}
	
	public int getCounter() {
		return counter;
	}

}
