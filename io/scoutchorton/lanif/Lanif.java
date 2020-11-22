package io.scoutchorton.lanif;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.scoutchorton.lanif.components.Polynomial;

public class Lanif extends JFrame {
	/**
	 * Constructors
	 */
	public Lanif(String title) {
		super(title);
	}

	/**
	 * Methods
	 */
	public static void initGUI() {
		//Create window and panel to add elements to
		Lanif win = new Lanif("LANIF");
		Box contentPane = new Box(BoxLayout.Y_AXIS);

		//Create and add Polynomial
		Polynomial defPolynomial = new Polynomial();
		contentPane.add(defPolynomial);

		//Create and add menu
		MenuButtons menu = new Lanif.MenuButtons(defPolynomial);
		contentPane.add(menu);

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

	/**
	 * Subclasses
	 */
	static private class MenuButtons extends JPanel implements ActionListener {
		/**
		 * Fields
		 */
		private JButton addTerm;
		private JButton editTerm;
		private JButton removeTerm;
		private Polynomial polyref;

		/**
		 * Constructors
		 */
		MenuButtons(Polynomial polynomialRef) {
			this.polyref = polynomialRef;

			//Initalize components
			this.addTerm = new JButton("Add Term");
			this.addTerm.addActionListener(this);
			this.addTerm.setActionCommand("add_term");

			this.editTerm = new JButton("Edit Term");
			this.editTerm.setEnabled(false);
			this.editTerm.addActionListener(this);
			this.editTerm.setActionCommand("remove_term");

			this.removeTerm = new JButton("Remove Term");
			this.removeTerm.setEnabled(false);
			this.removeTerm.addActionListener(this);
			this.removeTerm.setActionCommand("remove_term");

			//Add components to JPanel
			this.add(this.addTerm);
			this.add(this.editTerm);
			this.add(this.removeTerm);
		}

		/**
		 * ActionListener
		 */
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "add_term") {
				this.polyref.AddTerm();
			}
		}
	}
}