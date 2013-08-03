package msfp09;

public class Machine {

	public static void main(String[] args) {
		Machine m = new Machine(State.READY);

	}
	private State st;
	public Machine(State st) {
		this.st = st;
	}
	enum State {
		READY, HOT, FUCK;
	}

}
