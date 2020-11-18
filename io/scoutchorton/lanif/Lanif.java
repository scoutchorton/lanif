package io.scoutchorton.lanif;

import javax.swing.JFrame;
import javax.swing.JPanel;

import io.scoutchorton.lanif.components.Polynomial;

public class Lanif extends JFrame {
	public Lanif(String title) {
		super(title);
	}

	public static void initGUI() {
		//Create window and panel to add elements to
		Lanif win = new Lanif("LANIF");
		JPanel contentPane = new JPanel();

		//Create and add Polynomial
		Polynomial defPolynomial = new Polynomial();
		contentPane.add(defPolynomial);

		//Set up and open window
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setContentPane(contentPane);
		win.pack();
		win.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}
}