package io.scoutchorton.lanif.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Term extends JPanel {
	protected int exponent;
	//protected JSpinner.NumberEditor exponentArea;
	protected JSpinner exponentArea;
	protected int coefficient;
	//protected JSpinner.NumberEditor coefficientArea;
	protected JSpinner coefficientArea;
	protected String variable;
	protected JLabel variableArea;

	public Term() {
		this("x");
	}
	public Term(String var) {
		//Initalize model variables
		this.exponent = 1;
		this.coefficient = 1;
		this.variable = var;

		//Initalize view variables
		SpinnerNumberModel exponentNumberModel = new SpinnerNumberModel(this.exponent, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		this.exponentArea = new JSpinner(exponentNumberModel);
		SpinnerNumberModel coefficientNumberModel = new SpinnerNumberModel(this.coefficient, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		this.coefficientArea = new JSpinner(coefficientNumberModel);
		this.variableArea = new JLabel(this.variable);
		
		//Add components to JPanel
		this.add(this.exponentArea);
		this.add(this.variableArea);
		this.add(this.coefficientArea);
	}
}