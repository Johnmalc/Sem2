package drinks;

public class SixPack<T extends Bottle> {
	private T[] bottles = (T[]) new Object[6];
	private int i = 0;
	public void put(T bottle) {
		if (i >= 6)
			throw new RuntimeException();
		bottles[i] = bottle;
		i++;
	}
	public double getCapacity() {

		double cap = 0;
		for (int i = 0; i < this.i; i++) {
			cap = cap + bottles[i].capacity();
		}

		return cap;

	}
}
