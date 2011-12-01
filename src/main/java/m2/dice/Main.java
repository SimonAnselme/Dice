//Source file: c:/dice/Core/Main.java

package m2.dice;

import javax.swing.JFrame;

import m2.dice.dice.DieI;
import m2.dice.dice.DieMock;
import m2.dice.dicegame.DiceGame;
import m2.dice.hs.HighScoreFactory;
import m2.dice.hs.HighScoreI;
import m2.dice.player.Player;
import m2.dice.player.PlayerI;

class Main {

	/**
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @roseuid 3878BDBE0210
	 */
	public static void main(String args[]) throws Exception {

		HighScoreFactory kit = (HighScoreFactory) Class.forName("hs.SrKit")
				.newInstance();
		HighScoreI hs = kit.createHighScore();

		DieI d = new DieMock();

		PlayerI p = new Player("momo");

		DiceGame dg = new DiceGame(hs, d, p);

		JFrame f = new JFrame("DiceGame");
		f.add(dg.display());
		f.pack();
		f.setVisible(true);

	}

}
