/**
 * 
 */
package m2.dice;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import junit.framework.TestCase;
import m2.dice.dice.DieI;
import m2.dice.dicegame.DiceGame;
import m2.dice.player.Player;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author molli
 * 
 */
public class MockGameTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link m2.dice.dicegame.DiceGame#play()}.
	 */
	public void testPlay() {

		DieI demock = createMock(DieI.class);
		expect(demock.roll()).andReturn((int) 3);
		expect(demock.roll()).andReturn((int) 4);
		replay(demock);

		DiceGame dg = new DiceGame(null, demock, new Player("momo"));

		dg.play();
		assertTrue("turn=1", dg.turn == 1);
		assertTrue("Score=10", dg.getPlayer().getScore() == 10);

		verify(demock);
	}

}
