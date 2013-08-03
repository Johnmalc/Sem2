package MVCmitObserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CounterController implements ActionListener {
	
	
	private Counter counter;
	
	public CounterController() {
		
	}
	
	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public void actionPerformed(ActionEvent arg0) {
		counter.inc();
		
	}	
}
