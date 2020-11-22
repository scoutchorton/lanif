package io.scoutchorton.lanif.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Term extends JPanel implements MouseListener {
	/**
	 * Fields
	 */
	//Exponent
	protected int exponent;
	protected JSpinner exponentArea;

	//Coefficient
	protected int coefficient;
	protected JSpinner coefficientArea;

	//Variable
	protected String variable;
	protected JLabel variableArea;

	/**
	 * Constructors
	 */
	public Term() {
		this("x");
	}
	public Term(String var) {
		//Initalize panel utilizing the GridBag layout 
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Initalize model variables
		this.exponent = 1;
		this.coefficient = 1;
		this.variable = var;

		//Initalize view variables
		SpinnerNumberModel exponentNumberModel = new SpinnerNumberModel();
		exponentNumberModel.setValue(this.exponent);
		this.exponentArea = new JSpinner(exponentNumberModel);

		SpinnerNumberModel coefficientNumberModel = new SpinnerNumberModel();
		coefficientNumberModel.setValue(this.coefficient);
		this.coefficientArea = new JSpinner(coefficientNumberModel);
		
		this.variableArea = new JLabel(this.variable);
		
		//Set half height font
		Font exponentFont = exponentArea.getFont();
		Font newExponentFont = new Font(exponentFont.getName(), exponentFont.getStyle(), (int)(exponentFont.getSize() * 0.75));
		exponentArea.setFont(newExponentFont);

		//Add components to JPanel
		c.anchor = GridBagConstraints.LINE_END;
		this.add(this.coefficientArea, c);

		c.insets = new Insets(0, 10, 0, 5);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(this.variableArea, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		this.add(this.exponentArea, c);
	}

	/**
	 * MouseListener
	 */
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked: " + e.getComponent().toString());
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