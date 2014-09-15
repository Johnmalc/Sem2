package observerSampleJavaAPI;

import java.util.Observer;
import java.util.Observable;

public class NameArrayObserver implements Observer {

	public NameArrayObserver() {
	}

	public void update(Observable o, Object obj) {
		System.out.println("Name changed at index: " + obj);
		System.out.println("New Name -> "
				+ ((NameArrayObservable) o).getName((Integer) obj));
	}

}
