package rhythm_game_1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		if (RhythmGame.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			RhythmGame.game.PressS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			RhythmGame.game.PressD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			RhythmGame.game.PressF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			RhythmGame.game.PressSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			RhythmGame.game.PressJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			RhythmGame.game.PressK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			RhythmGame.game.PressL();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (RhythmGame.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			RhythmGame.game.releaseS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			RhythmGame.game.releaseD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			RhythmGame.game.releaseF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			RhythmGame.game.releaseSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			RhythmGame.game.releaseJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			RhythmGame.game.releaseK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			RhythmGame.game.releaseL();
		}
	}

}
