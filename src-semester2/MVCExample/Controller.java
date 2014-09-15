package MVCExample;

import java.awt.event.*;

public class Controller implements ActionListener {
	Model mod;

	View view1;

	public Controller() {
		mod = new Model();
		// meine Views
		view1 = new View(this);

		// Observer zu den Modellen hinzufügen.
		mod.addObserver(view1);
		view1.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ACTION: " + e.getActionCommand().toString());
		if (e.getActionCommand().equalsIgnoreCase(MVCStatics.ACTION_DEC))
			mod.dec();
		if (e.getActionCommand().equalsIgnoreCase(MVCStatics.ACTION_INC))
			mod.inc();
	}
}
