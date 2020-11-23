package io.scoutchorton.lanif.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	 * Subclasses
	 */
	public class TermView extends JLabel implements MouseListener {
		/**
		 * Fields
		 */
		private final Term term = Term.this;

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
			//Component target = e.getComponent();
			Container targetParent = this.getParent();

			//Select Term if it's part of a Polynomial
			if(targetParent.getClass() == Polynomial.class)
				((Polynomial)targetParent).selectTerm(this.term);
		}
		public void mouseEntered(MouseEvent e) {
			//System.out.println("mouseEntered: " + e.getComponent().toString());
		}
		public void mouseExited(MouseEvent e) {
			//System.out.println("mouseExited: " + e.getComponent().toString());
		}
		public void mousePressed(MouseEvent e) {
			//System.out.println("mousePressed: " + e.getComponent().toString());
		}
		public void mouseReleased(MouseEvent e) {
			//System.out.println("mouseReleased: " + e.getComponent().toString());
		}
	}

	public class TermEditor extends JFrame implements ActionListener {
		/**
		 * Fields
		 */
		//private JPanel contentPane;
		private final Term term = Term.this;

		/**
		 * Constructors
		 */
		TermEditor() {
			//Initalize components
			super("Term Editor");
			Box contentPane = new Box(BoxLayout.Y_AXIS);

			//Initalize and add main editor
			JPanel editor = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			contentPane.add(editor);

			//Initalize and add save/cancel buttons
			JPanel buttons = new JPanel();
			contentPane.add(buttons);

			//Set up and display window
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setContentPane(contentPane);
			pack();
			setVisible(true);
		}
	}
}