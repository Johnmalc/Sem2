package swing;

import javax.swing.*;

public class HelloWorldSwing {
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		JPanel sa = new JPanel();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("Hello World");
		sa.add(label);

		frame.add(sa);
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// creating and showing this application's GUI.
		createAndShowGUI();
	}
}