package MVCohneObserver;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Create Model */
		Counter counter = new Counter();
		/* Create View */
		CounterView view = new CounterView(counter);
		/* Create Controller */
		CounterController controller = new CounterController(counter);

		/* Relate View and Controller */
		view.addClickListener(controller);
		controller.setView(view);
	}
}
