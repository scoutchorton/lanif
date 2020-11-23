package io.scoutchorton.lanif;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		Lanif win = new Lanif("Lanif");
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
			polyref = polynomialRef;

			//Initalize components
			addTerm = new JButton("Add Term");
			addTerm.addActionListener(this);
			addTerm.setActionCommand("add_term");

			editTerm = new JButton("Edit Term");
			editTerm.addActionListener(this);
			editTerm.setActionCommand("edit_term");

			removeTerm = new JButton("Remove Term");
			removeTerm.addActionListener(this);
			removeTerm.setActionCommand("remove_term");

			//Add components to JPanel
			add(addTerm);
			add(editTerm);
			add(removeTerm);
		}

		/**
		 * ActionListener
		 */
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "add_term") {
				polyref.addTerm();
			} else if(e.getActionCommand() == "edit_term") {
				Boolean success = polyref.editTerm();

				//Display an error when nothing is selected
				if(!success)
					JOptionPane.showMessageDialog(this, "No term is selected to edit.", "Lanif Errro", JOptionPane.ERROR_MESSAGE);
			} else if(e.getActionCommand() == "remove_term") {
				Boolean success = polyref.removeTerm();

				//Display an error when nothing is selected
				if(!success)
					JOptionPane.showMessageDialog(this, "No term is selected to remove.", "Lanif Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}