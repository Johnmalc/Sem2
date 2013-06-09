package observerSample;

public class NameArrayObservable implements Observable {

	private Observer obs;
	private String[] names;

	public NameArrayObservable() {
		names = new String[10];
	}

	public void addObserver(Observer o) {
		obs = o;
	}

	public void deleteObserver(Observer o) {
		obs = null;
	}

	public void setName(String name, int index) {
		names[index] = name;
		if (obs != null)
			obs.update(this, index);
	}

	public String getName(int index) {
		return names[index];
	}
}
