package io.scoutchorton.lanif;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Document;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import io.scoutchorton.lanif.components.Polynomial;

public class Lanif extends JFrame implements ActionListener {
	/**
	 * Fields
	 */
	Polynomial poly;

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
		Box contentPane = new Box(BoxLayout.X_AXIS);

		//Create and add Box for editing Polynomials
		JPanel editor = new JPanel(new GridBagLayout());
		contentPane.add(editor);

		//Setup contraints
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		
		//Create and add variable modification
		Box varEditor = new Box(BoxLayout.X_AXIS);
		editor.add(varEditor);
		
		JLabel varLabel = new JLabel("Variable");
		varEditor.add(varLabel);
		
		JTextField varArea = new JTextField("x", 1);
		varEditor.add(varArea, c);
		varArea.addActionListener(win);
		varArea.setActionCommand("variable_update");

		//Create and add Polynomial
		win.poly = new Polynomial(1);
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		editor.add(win.poly, c);

		//Create and add menu
		EditActions menu = win.new EditActions();
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		editor.add(menu, c);

		//Create and add EvalBox
		EvalBox eval = win.new EvalBox();
		contentPane.add(eval);

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
	 * Action listener
	 */
	public void actionPerformed(ActionEvent e) {
		//Check when variable get updated
		if(e.getActionCommand() == "variable_update")
			this.poly.updateVariable(((JTextField)e.getSource()).getText());
	}
	 
	/**
	 * Inner classes
	 */
	private class EditActions extends JPanel implements ActionListener {
		/**
		 * Fields
		 */
		private JButton addTerm;
		private JButton editTerm;
		private JButton removeTerm;

		/**
		 * Constructors
		 */
		EditActions() {
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
				Lanif.this.poly.addTerm();
			} else if(e.getActionCommand() == "edit_term") {
				//Display an error when nothing is selected
				if(!Lanif.this.poly.termSelected)
					JOptionPane.showMessageDialog(this, "No term is selected to edit.", "Lanif Error", JOptionPane.ERROR_MESSAGE);
				else
					Lanif.this.poly.editTerm();
			} else if(e.getActionCommand() == "remove_term") {
				//Display an error when nothing is selected
				if(!Lanif.this.poly.termSelected)
					JOptionPane.showMessageDialog(this, "No term is selected to remove.", "Lanif Error", JOptionPane.ERROR_MESSAGE);
				else {
					int status = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this term?", "Remove Term", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(status == JOptionPane.OK_OPTION)
						Lanif.this.poly.removeTerm();
				}
			}
		}
	}

	private class EvalBox extends Box implements ActionListener {
		/**
		 * Fields
		 */
		private JSpinner lowSpinner;
		private JSpinner upSpinner;
		private JSpinner incSpinner;
		private JTextArea output;
		private JButton eval;
		
		/**
		 * Constructors
		 */
		EvalBox() {
			//Initalize JPanel
			super(BoxLayout.Y_AXIS);

			//Initalize and add evaluate button
			eval = new JButton("Evaluate");
			add(eval);
			eval.addActionListener(this);
			eval.setActionCommand("eval");

			//Initalize and add lower bound components
			JPanel lowPanel = new JPanel();
			add(lowPanel);
			lowPanel.add(new JLabel("Lower bound"));
			lowSpinner = new JSpinner();
			lowPanel.add(lowSpinner);
			lowSpinner.setValue(1);

			//Inialize and add upper bound components
			JPanel upPanel = new JPanel();
			add(upPanel);
			upPanel.add(new JLabel("Upper bound"));
			upSpinner = new JSpinner();
			upPanel.add(upSpinner);
			upSpinner.setValue(1);

			//Inialize and add increment components
			JPanel incPanel = new JPanel();
			add(incPanel);
			incPanel.add(new JLabel("Increment"));
			incSpinner = new JSpinner();
			incPanel.add(incSpinner);
			incSpinner.setValue(1);

			//Initalize and add output textarea
			output = new JTextArea();
			output.setEditable(false);
			add(output);
		}

		/**
		 * Action listener
		 */
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "eval") {
				//Get valeues from JSpinners
				int lower = (int)lowSpinner.getValue();
				int upper = (int)upSpinner.getValue();
				int increment = (int)incSpinner.getValue();

				//Clear textarea
				output.setText("");

				//Output to textarea
				for(int index = lower; index <= upper; index += increment) {
					if(index != lower)
						output.append("\n");
					Double value = Lanif.this.poly.eval(index);
					output.append(index + " => " + value);
				}
			}
		}
	}
}