package io.scoutchorton.lanif.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Term {
	/**
	 * Fields
	 */
	//Model
	private int exponent;
	private int coefficient;
	private String variable;
	public TermView view;

	/**
	 * Constructors
	 */
	public Term() {
		this("x");
	}
	public Term(String var) {
		//Initalize variables
		exponent = 1;
		coefficient = 1;
		variable = var;
		view = new TermView();
	}

	/**
	 * Methods
	 */
	public void updateVariable(String newVariable) {
		variable = newVariable;
		view.update();
	}
	public Double eval(int value) {
		// -~= Maths =~-
		Double result = Math.pow((double)value, (double)exponent);
		result *= coefficient;

		return result;
	}

	/**
	 * Inner classes
	 */
	public class TermView extends JLabel implements MouseListener {
		/**
		 * Fields
		 */
		//private final Term term = Term.this;

		/**
		 * Constructors
		 */
		public TermView() {
			//Initalize JLabel
			super();

			//Display values
			update();

			//Add event listeners
			addMouseListener(this);
		}

		/**
		 * Methods
		 */
		//Update display with HTML
		public void update() {
			//Create String
			String contentString = new String();
	
			//Add data
			contentString += "<html>";
			contentString += Integer.toString(Term.this.coefficient);
			contentString += Term.this.variable;
			contentString += "<sup>";
			contentString += Integer.toString(Term.this.exponent);
			contentString += "</sup>";
			contentString += "</html>";
	
			//Set data to 
			setText(contentString);
		}

		/**
		 * MouseListener
		 */
		public void mouseClicked(MouseEvent e) {
			//Get components
			Container targetParent = this.getParent();

			//Select Term if it's part of a Polynomial
			if(targetParent.getClass() == Polynomial.class)
				((Polynomial)targetParent).selectTerm(Term.this);
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}

	public class TermEditor extends JFrame implements ActionListener {
		/**
		 * Fields
		 */
		private JSpinner exponent;
		private JSpinner coefficient;

		/**
		 * Constructors
		 */
		TermEditor() {
			//Initalize components
			super("Term Editor");
			Box contentPane = new Box(BoxLayout.Y_AXIS);

			//Initalize and add main editor to window
			JPanel editor = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			contentPane.add(editor, c);

			//Initalize and add coefficient to main editor
			SpinnerNumberModel coefficientNumberModel = new SpinnerNumberModel(Term.this.coefficient, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
			coefficientNumberModel.setValue(Term.this.coefficient);
			coefficient = new JSpinner(coefficientNumberModel);
			c.anchor = GridBagConstraints.LINE_END;
			editor.add(coefficient, c);

			//Initalize and add variable to main editor
			c.insets = new Insets(0, 10, 0, 5);
			c.fill = GridBagConstraints.BOTH;
			editor.add(new JLabel(Term.this.variable));

			//Initalize and add exponent to main editor
			SpinnerNumberModel exponentNumberModel = new SpinnerNumberModel(Term.this.exponent, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
			//exponentNumberModel.setValue(Term.this.exponent);
			exponent = new JSpinner(exponentNumberModel);

			Font exponentFont = exponent.getFont();
			Font newExponentFont = new Font(exponentFont.getName(), exponentFont.getStyle(), (int)(exponentFont.getSize() * 0.75));
			exponent.setFont(newExponentFont);

			c.insets = new Insets(0, 0, 0, 0);
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.FIRST_LINE_END;
			editor.add(exponent, c);

			//Initalize and add save/cancel buttons to window
			JPanel buttons = new JPanel();
			contentPane.add(buttons);

			//Initalize and add save button to buttons panel
			JButton save = new JButton("Save");
			save.addActionListener(this);
			save.setActionCommand("save");
			buttons.add(save);
			
			//Initalize and add cancel to buttons panel
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(this);
			cancel.setActionCommand("cancel");
			buttons.add(cancel);

			//Set up and display window
			setContentPane(contentPane);
			pack();
			setVisible(true);
		}

		/**
		 * Methods
		 */
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "cancel") {
				//Close window without saving
				dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			} else if(e.getActionCommand() == "save") {
				//Update term variables and view
				Term.this.exponent = (int)exponent.getValue();
				Term.this.coefficient = (int)coefficient.getValue();
				Term.this.view.update();

				//Close window
				dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
}