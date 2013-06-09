package msfp08;

import java.util.Observable;

public class Tipp extends Observable {
	private int number;
	private boolean running, winner;
	private int trials;
	private int maxTrials;

	public Tipp() {
		this.number = (int) (Math.random() * 100) % 10 + 1;
		this.addObserver(new TippObserver());
		this.running = true;
		this.winner = false;
		this.trials = 0;
		this.maxTrials = 5;
	}

	public void test(int number) {
		if (!running) {
			throw new RuntimeException("chybicka ... RE");
		}
		trials++;
		if (this.number == number) {
			setChanged();
			running = false;
			winner = true;
			notifyObservers();
		} else if (maxTrials <= trials) {
			setChanged();
			running = false;
			winner = false;
			notifyObservers();
		}
	}
	public void restart() {
		this.number= (int) (Math.random() * 100) % 10 + 1;
		this.trials=0;
		this.maxTrials=5;
		this.addObserver(new TippObserver());
		this.running= true;
		this.winner=false;
	}
	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @return the winner
	 */
	public boolean isWinner() {
		return winner;
	}

	/**
	 * @return the trials
	 */
	public int getTrials() {
		return trials;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

}
