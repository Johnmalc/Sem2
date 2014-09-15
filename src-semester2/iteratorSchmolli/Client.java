package iteratorSchmolli;

import java.util.Iterator;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Collection<Integer> c = new Collection<Integer>();
		Iterator<Integer> it = c.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
