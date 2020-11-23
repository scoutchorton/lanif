package io.scoutchorton.lanif.components;

import io.scoutchorton.lanif.components.Term;
import io.scoutchorton.lanif.components.Term.TermView;
import io.scoutchorton.lanif.components.Term.TermEditor;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//import java.awt.GridBagLayout;
//import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

public class Polynomial extends JPanel implements MouseListener {
	/**
	 * Fields
	 */
	private ArrayList<Term> terms;
	private Term selectedTerm;

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
		terms = new ArrayList<Term>();
		selectedTerm = null;

		//Add event listeners
		addMouseListener(this);
	}

	/**
	 * Methods
	 */
	public void addTerm() {
		//Force last Term in array to display plus sign
		if(terms.size() > 0) {
			Term lastTerm = terms.get(terms.size() - 1);
			lastTerm.view.update();
		}

		//Create new Term and contraints
		Term newTerm = new Term();

		//Add new Term to window
		terms.add(newTerm);
		add(newTerm.view);
		newTerm.view.setBorder(BorderFactory.createEmptyBorder());
		revalidate();

		//Update window size on change between zero and one term
		if(terms.size() <= 1)
			SwingUtilities.getWindowAncestor(this).pack();
	}
	public void selectTerm(Term selected) {
		//Reset border of currently selected term
		if(selectedTerm != null)
			selectedTerm.view.setBorder(BorderFactory.createEmptyBorder());
		
		//Set the currently selected term and apply a border
		selectedTerm = selected;
		selectedTerm.view.setBorder(BorderFactory.createLoweredBevelBorder());
	}
	//Returns true if a term is selected
	public Boolean editTerm() {
		if(this.selectedTerm != null) {
			selectedTerm.new TermEditor();
			return true;
		} else {
			return false;
		}
	}
	//Returns true if a term is selected
	public Boolean removeTerm() {
		if(this.selectedTerm != null) {
			//this.selectedTerm.
			return true;
		} else {
			return false;
		}
	}

	/**
	 * MouseListener
	 */
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Polynomial clicked!");
		//System.out.println("mouseClicked: " + e.getComponent().toString());
		//System.out.println("mouseClicked: " + e.getComponent().getParent().toString());
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}