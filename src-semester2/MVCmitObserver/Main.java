package MVCmitObserver;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/* Create View */
		CounterView view = new CounterView();
		/* Create Controller */
		CounterController controller = new CounterController();
		/* Create Model */
		Counter counter = new Counter();

		/* Put Pieces together */
		controller.setCounter(counter);
		view.addClickListener(controller);
		counter.addObserver(view);
	}
}
