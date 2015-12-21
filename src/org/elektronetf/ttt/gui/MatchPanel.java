package org.elektronetf.ttt.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.elektronetf.ttt.Match;

public class MatchPanel extends JPanel { // TODO SUCKS ASS
	public MatchPanel() {
		super(new BorderLayout());
		
		comboBox = new JComboBox<>();
		comboBox.setEditable(false);
		comboBox.addItemListener((evt) -> ((CardLayout) cards.getLayout()).show(cards, (String) evt.getItem()));
		add(comboBox, BorderLayout.PAGE_START);
		
		cards = new JPanel(new CardLayout());
		add(cards, BorderLayout.CENTER);
	}
	
	public void bindMatches(List<Match> matches) {
		comboBox.setModel(new DefaultComboBoxModel<>(new Vector<>(matches)));
		cards.removeAll();
		for (Match match : matches) {
			cards.add(new PointsPanel(match), match.toString());
		}
	}
	
//	public void submit() {
//		int cardIndex 
//		for (int i = 0; i < 3; i++) {
//			match.setScore(i, points.get(i).get(0), points.get(i).get(1));
//		}
//		match.finish();
//	}
	

	private JComboBox<Match> comboBox;
	private JPanel cards;
	
	private static class PointsPanel extends JPanel {
		private final Match match;
		
		public PointsPanel(Match match) {
			this.match = match;
			
		}
	}
	
	private static class PointsTextField extends JTextField {
		public PointsTextField() {
			super("0", 2);
			setInputVerifier(new InputVerifier() {
				@Override
				public boolean verify(JComponent input) {
					try {
						((PointsTextField) input).getPoints();
					} catch (NumberFormatException e) {
						return false;
					}
					return true;
				}
			});
		}
		
		public int getPoints() {
			return new Integer(getText());
		}
	}
}
