package MVCohneObserver;

/*Model contains data and logic*/

public class Counter  {
	
	private int counter = 0;
	
	public void inc() {		
		counter++;
		
	}
	
	public int getCounter() {
		return counter;
	}

}
