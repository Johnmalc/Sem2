package MVCmitObserver;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CounterView extends JFrame implements Observer {

	private JButton countButton;
	private JLabel countLabel;
	private String prefix = "Counter value: ";

	public CounterView() {
		setTitle("MVC Sample: Counter");
		countLabel = new JLabel(prefix + "0");
		countButton = new JButton("Click me!");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.add(countButton);
		panel.add(countLabel);

		add(panel, BorderLayout.CENTER);
		setVisible(true);
		pack();
	}

	public void addClickListener(ActionListener actionListener) {
		countButton.addActionListener(actionListener);
	}

	public void update(Observable arg0, Object arg1) {
		countLabel.setText(prefix + arg1);

	}

}
