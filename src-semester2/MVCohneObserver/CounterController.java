package MVCohneObserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*Controller Logic*/
public class CounterController implements ActionListener {
	
	
	private Counter counter;
	private CounterView view;
	
	public CounterController(Counter counter) {
		this.counter = counter;
	}
	
	public void setView(CounterView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent arg0) {
		counter.inc();
		view.updateView();
	}

}
