// Source file: c:/dice/Core/DiceGame.java

package m2.dice.dicegame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import m2.dice.dice.DieI;
import m2.dice.hs.Entry;
import m2.dice.hs.HighScoreI;
import m2.dice.player.PlayerI;

public class DiceGame extends Observable {
	private HighScoreI hs;

	// Un seul dice
	private DieI d;

	private PlayerI p;

	public int turn = 0;

	public DiceGame(HighScoreI hs, DieI d, PlayerI p) {
		this.hs = hs;
		this.d = d;
		this.p = p;
	}

	public DieI getD() {
		return d;
	}

	public PlayerI getPlayer() {
		return p;
	}

	public void play() {
		if (turn < 10) {
			if (d.roll() + d.roll() == 7) {
				p.setScore(p.getScore() + 10);
			}
			turn++;
			if (turn == 10) {
				hs.add(new Entry(p.getName(), p.getScore()));
			}
			this.setChanged();
			this.notifyObservers();
		}
	}

	class DiceGameController extends JButton implements Observer {

		public DiceGameController() {
			this.setText("play");
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DiceGame.this.play();
				}
			});
		}

		public void update(Observable o, Object arg) {
			this.setText("Play : Turn:" + turn);
			this.setEnabled(turn < 10);
		}

	}

	public JComponent display() {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.add(BorderLayout.EAST, d.display());
		jp.add(BorderLayout.WEST, d.display());
		jp.add(BorderLayout.SOUTH, p.display());
		jp.add(BorderLayout.CENTER, hs.display());

		DiceGameController dgc = new DiceGameController();
		this.addObserver(dgc);
		jp.add(BorderLayout.NORTH, dgc);
		return jp;
	}

}
