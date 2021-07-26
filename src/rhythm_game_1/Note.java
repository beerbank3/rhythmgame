package rhythm_game_1;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	public static int combo [] = new int[6];
	public static int MaxCombo;
	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	}

	public Note(String noteType) {
		if (noteType.equals("S")) {
			x = 100;
		} else if (noteType.equals("D")) {
			x = 200;
		} else if (noteType.equals("F")) {
			x = 300;
		} else if (noteType.equals("Space")) {
			x = 400;
		} else if (noteType.equals("J")) {
			x = 500;
		} else if (noteType.equals("K")) {
			x = 600;
		} else if (noteType.equals("L")) {
			x = 700;
		}
		this.noteType = noteType;
	}

	/*
	 * 판정부분에서 키를 눌렀을때 정답이 아닌 부분이 같이눌러졌다면 점수 -2000*/
	public void screenDraw(Graphics2D g) {
			g.drawImage(noteBasicImage, x, y, null);
	}

	public void drop() {
		y += Main.NOTE_SPEED;
		if (y > 660) {
			System.out.println("Miss");
			Maxcombo();
			Game.Combo=0;
			combo[5]++;
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public String judge() {
		if (y >= 645) {
			System.out.println("Late");
			Maxcombo();
			Game.score+=49;
			Game.Combo+=1;
			combo[3]++;
			Maxcombo();
			close();
			return "Late";
		} else if (y >= 630) {
			System.out.println("Good");
			Game.score+=103;
			Game.Combo+=1;
			combo[2]++;
			Maxcombo();
			close();
			return "Good";
		} else if (y >= 620) {
			System.out.println("Great");
			Game.score+=199;
			combo[1]++;
			Game.Combo+=1;
			Maxcombo();
			close();
			return "Great";
		} else if (y >= 580) {
			System.out.println("Perfect");
			combo[0]++;
			Game.score+=347;
			Game.Combo+=1;
			Maxcombo();
			close();
			return "Perfect";
		} else if (y >= 540) {
			System.out.println("Great");
			Game.score+=199;
			combo[1]++;
			Game.Combo+=1;
			Maxcombo();
			close();
			return "Great";
		} else if (y >= 530) {
			System.out.println("Good");
			Game.score+=103;
			Game.Combo+=1;
			Maxcombo();
			combo[2]++;
			close();
			return "Good";
		} else if (y >= 515) {
			System.out.println("Early");
			Game.score+=49;
			Game.Combo+=1;
			Maxcombo();
			combo[4]++;
			close();
			return "Early";
		}
		return "none";
	}

	public int getY() {
		return y;
	}
	public void Maxcombo() {
		if(Game.Combo>=MaxCombo)
			MaxCombo=Game.Combo;
	}
}
