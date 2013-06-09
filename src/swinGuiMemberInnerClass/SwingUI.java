package swinGuiMemberInnerClass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SwingUI {
	private String labelPrefix = "Number of Clicks: ";
	private int counter = 0;
	private JFrame frame = new JFrame("Swing Counter");
	private JPanel pane = new JPanel(new GridLayout(0, 1));
	private JButton button = new JButton("I'm a Swing button!");
	private JLabel label = new JLabel(labelPrefix + "0 ");

	public SwingUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		button.addActionListener(new MyListener());
		pane.add(button);
		pane.add(label);
		frame.getContentPane().add(pane, BorderLayout.CENTER);
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public class MyListener implements ActionListener {
		int counter;

		public void actionPerformed(ActionEvent e) {
			SwingUI.this.counter++;
			SwingUI.this.label.setText(SwingUI.this.labelPrefix
					+ SwingUI.this.counter);
		}
	}

}
