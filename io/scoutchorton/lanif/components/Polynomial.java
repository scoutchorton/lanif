package io.scoutchorton.lanif.components;

import io.scoutchorton.lanif.components.Term;
import io.scoutchorton.lanif.components.Term.TermView;
import io.scoutchorton.lanif.components.Term.TermEditor;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Window;

import java.util.ArrayList;

public class Polynomial extends JPanel implements MouseListener {
	/**
	 * Fields
	 */
	private ArrayList<Term> terms;
	private Term selectedTerm;
	public Boolean termSelected;
	private String variable;

	/**
	 * Constructors
	 */
	public Polynomial() {
		this(0, "x");
	}
	public Polynomial(String variable) {
		this(0, variable);
	}
	public Polynomial(int numTerms) {
		this(numTerms, "x");
	}
	public Polynomial(int numTerms, String var) {
		//Initalize panel
		super();

		//Initalize fields
		terms = new ArrayList<Term>();
		selectedTerm = null;
		variable = var;

		//Add a Term to give the Polynomial some content
		for(int index = 0; index < numTerms; index++) {
			addTerm();
		}

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
		Term newTerm = new Term(variable);

		//Add new Term to window
		terms.add(newTerm);
		add(newTerm.view);
		newTerm.view.setBorder(BorderFactory.createEmptyBorder());
		revalidate();

		//Update window size on change between zero and one term
		if(terms.size() <= 1) {
			Window win = SwingUtilities.getWindowAncestor(this);
			if(win != null) win.pack();
		}
	}
	public void selectTerm(Term selected) {
		//Reset border of currently selected Term
		if(selectedTerm != null)
			selectedTerm.view.setBorder(BorderFactory.createEmptyBorder());

		//Deselect the Term if already selected
		if(selectedTerm == selected) {
			selectedTerm = null;
			termSelected = false;
		//Set the currently selected term and apply a border
		} else {
			selectedTerm = selected;
			termSelected = true;
			selectedTerm.view.setBorder(BorderFactory.createLoweredBevelBorder());
		}
	}
	//Returns true if a Term is selected
	public void editTerm() {
		if(this.selectedTerm != null)
			selectedTerm.new TermEditor();
	}
	//Returns true if a Term is selected
	public void removeTerm() {
		if(selectedTerm != null) {
			//Remove Term
			remove(selectedTerm.view);
			terms.remove(selectedTerm);

			//Update view
			revalidate();
			
			//Deselect Term
			termSelected = false;
		}
	}
	public void updateVariable(String newVariable) {
		//Update Polynomial variable
		variable = newVariable;

		//Update each Term's variable
		for(Term term : terms)
			term.updateVariable(variable);
	}
	public Double eval(int value) {
		Double sum = (double)0;

		//Find sum of each Term being evaluated
		for(Term term : terms)
			sum += term.eval(value);

		return sum;
	}

	/**
	 * MouseListener
	 */
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}