package io.scoutchorton.lanif.components;

import io.scoutchorton.lanif.components.Term;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class Polynomial extends JPanel {
	//Array of Terms
	AddTermButton newTermButton;

	public Polynomial() {
		this(0);
	}
	public Polynomial(int numTerms) {
		//Initalize panel utilizing the GridBag layout 
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Create and add button to add a new Term
		this.newTermButton = new AddTermButton();
		this.newTermButton.setActionCommand("add");
		c.anchor = GridBagConstraints.EAST;
		this.add(this.newTermButton);
	}

	private void AddTerm() {
		//Create new Term and contraints
		GridBagConstraints c = new GridBagConstraints();
		Term newTerm = new Term();

		//Add new Term to window
		c.anchor = GridBagConstraints.CENTER;
		this.add(newTerm, c);
		this.revalidate();
		
		System.out.println("# TERM ADDED");
	}

	private class AddTermButton extends JButton implements ActionListener {
		public AddTermButton() {
			super("+");

			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("# EVENT TRIGGERED: " + e.getActionCommand());
			if(e.getActionCommand().equals("add"))
				AddTerm();
		}
	}
}