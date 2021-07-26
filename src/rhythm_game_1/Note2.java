package rhythm_game_1;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note2 extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private Image noteBasicImage2 = new ImageIcon(Main.class.getResource("../images/noteBasic2.png")).getImage();
	private int Speed = Main.NOTE_SPEED;
	private int a, b = 0, x, y = 580 - (1000 / Main.SLEEP_TIME * Speed) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	public static int combo[] = new int[6];
	public static int MaxCombo;
	KeyListener2 key;
	public String getNoteType() {
		return noteType;
	}
	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	}

	public Note2(String noteType) {
		if (noteType.equals("S")) {
			x = 100;
			a = 900;
		} else if (noteType.equals("D")) {
			x = 200;
			a = 950;
		} else if (noteType.equals("F")) {
			x = 300;
			a = 1000;
		} else if (noteType.equals("Space")) {
			x = 400;
			a = 1050;
		} else if (noteType.equals("J")) {
			x = 500;
			a = 1100;
		} else if (noteType.equals("K")) {
			x = 600;
			a = 1150;
		} else if (noteType.equals("L")) {
			x = 700;
			a = 1200;
		}
		this.noteType = noteType;
	}

	/*
	 * 판정부분에서 키를 눌렀을때 정답이 아닌 부분이 같이눌러졌다면 점수 -2000
	 */
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
		if (y > 30) {
			g.drawImage(noteBasicImage2, a, b, null);
		}
	}

	public void drop() {
		y += Speed;
		b += Speed / 2;
		if (y > 660) {
			System.out.println("Miss");
			Maxcombo();
			Game2.Combo = 0;
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
			Game2.score += 49;
			Game2.Combo += 1;
			combo[3]++;
			if(Game2.COMBOevent==true)
				key.comboevent();
			if (Game2.Combo % 100 == 0 && Game2.Combo != 0)
				Game2.getgameEvent();
			Maxcombo();
			close();
			return "Late";
		} else if (y >= 630) {
			System.out.println("Good");
			Game2.score += 103;
			Game2.Combo += 1;
			combo[2]++;
			if(Game2.COMBOevent==true)
				key.comboevent();
			if (Game2.Combo % 100 == 0 && Game2.Combo != 0)
				Game2.getgameEvent();
			Maxcombo();
			close();
			return "Good";
		} else if (y >= 620) {
			System.out.println("Great");
			Game2.score += 199;
			combo[1]++;
			Game2.Combo += 1;
			if(Game2.COMBOevent==true)
				key.comboevent();
			if (Game2.Combo % 100 == 0 && Game2.Combo != 0)
				Game2.getgameEvent();
			Maxcombo();
			close();
			return "Great";
		} else if (y >= 580) {
			System.out.println("Perfect");
			combo[0]++;
			Game2.score += 347;
			Game2.Combo += 1;
			if(Game2.COMBOevent==true)
				key.comboevent();
			if (Game2.Combo % 100 == 0 && Game2.Combo != 0)
				Game2.getgameEvent();
			Maxcombo();
			close();
			return "Perfect";
		} else if (y >= 540) {
			System.out.println("Great");
			Game2.score += 199;
			combo[1]++;
			Game2.Combo += 1;
			if(Game2.COMBOevent==true)
				key.comboevent();
			if (Game2.Combo % 100 == 0 && Game2.Combo != 0)
				Game2.getgameEvent();
			Maxcombo();
			close();
			return "Great";
		} else if (y >= 530) {
			System.out.println("Good");
			Game2.score += 103;
			Game2.Combo += 1;
			if(Game2.COMBOevent==true)
				key.comboevent();
			if (Game2.Combo % 100 == 0 && Game2.Combo != 0)
				Game2.getgameEvent();
			Maxcombo();
			combo[2]++;
			close();
			return "Good";
		} else if (y >= 515) {
			System.out.println("Early");
			Game2.score += 49;
			Game2.Combo += 1;
			if(Game2.COMBOevent==true)
				key.comboevent();
			if (Game2.Combo % 100 == 0 && Game2.Combo != 0)
				Game2.getgameEvent();
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
		if (Game2.Combo >= MaxCombo)
			MaxCombo = Game2.Combo;
	}
}
