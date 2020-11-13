package io.scoutchorton.lanif;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.scoutchorton.lanif.components.Term;

public class Lanif extends JFrame {
	public Lanif(String title) {
		super(title);
	}

	public static void initGUI() {
		Lanif win = new Lanif("LANIF");
		JPanel contentPane = new JPanel();

		Term t1 = new Term();
		Term t2 = new Term();
		Term t3 = new Term();
		Term t4 = new Term();
		contentPane.add(t1);
		contentPane.add(new JLabel("+"));
		contentPane.add(t2);
		contentPane.add(new JLabel("+"));
		contentPane.add(t3);
		contentPane.add(new JLabel("+"));
		contentPane.add(t4);

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