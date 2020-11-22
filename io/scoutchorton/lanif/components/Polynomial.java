package io.scoutchorton.lanif.components;

import io.scoutchorton.lanif.components.Term;

import javax.swing.JPanel;

//import java.awt.GridBagLayout;
//import java.awt.GridBagConstraints;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;

import java.util.ArrayList;

public class Polynomial extends JPanel {// implements MouseListener {
	/**
	 * Fields
	 */
	ArrayList<Term> terms;

	/**
	 * Constructors
	 */
	public Polynomial() {
		this(0);
	}
	public Polynomial(int numTerms) {
		//Initalize panel
		super();

		//Initalize fields
		this.terms = new ArrayList<Term>();

		//Add event listeners
		//this.addMouseListener(this);
	}

	/**
	 * Methods
	 */
	public void AddTerm() {
		//Create new Term and contraints
		Term newTerm = new Term();

		//Add new Term to window
		this.add(newTerm);
		this.terms.add(newTerm);
		this.revalidate();
	}
	/*
	private void SelectTerm(Term selected) {

	}
	*/

	/**
	 * MouseListener
	 */
	/*
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked: " + e.getComponent().toString());
	}
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered: " + e.getComponent().toString());
	}
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited: " + e.getComponent().toString());
	}
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed: " + e.getComponent().toString());
	}
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased: " + e.getComponent().toString());
	}
	*/
}