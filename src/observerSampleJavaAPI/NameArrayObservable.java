package observerSampleJavaAPI;

import java.util.Observable;

public class NameArrayObservable extends Observable {

	private String[] names;

	public NameArrayObservable() {
		names = new String[10];
	}

	public void setName(String name, int index) {
		names[index] = name;
		setChanged();
		notifyObservers(index);
	}

	public String getName(int index) {
		return names[index];
	}
}
