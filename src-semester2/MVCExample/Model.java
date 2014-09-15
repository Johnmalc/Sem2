package MVCExample;

import java.util.*;

public class Model extends Observable {

	private int counter = 0;

	public int getCounter() {
		return counter;
	}

	public void inc() {
		counter += 1;
		setChanged();
		notifyObservers(counter);
	}

	public void dec() {
		counter -= 1;
		setChanged();
		notifyObservers(counter);
	}

	public String counterToString() {
		return Integer.toString(this.counter);
	}

}
