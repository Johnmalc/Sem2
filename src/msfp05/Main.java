package msfp05;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayStack<Integer> as = new ArrayStack<Integer>(15);
		as.push(1);
		as.push(2);
		as.push(3);
		as.push(4);
		as.push(2);
		as.push(3);
		as.push(4);
		as.push(2);
//		as.push(3);
//		as.push(4);
//		as.push(2);
//		as.push(3);
//		as.push(4);
		
		as.pop();
		
	}

}
