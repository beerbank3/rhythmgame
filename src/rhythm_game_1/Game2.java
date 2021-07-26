package rhythm_game_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Game2 extends Thread {

	private Image noteBasicimage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private Image noteBasicimage2 = new ImageIcon(Main.class.getResource("../images/noteBasic2.png")).getImage();
	private Image noteRouteLineimage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image noteRouteLineimage2 = new ImageIcon(Main.class.getResource("../images/noteRouteLine2.png"))
			.getImage();
	private Image noteRouteimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteimage2 = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
	private Image judgementLineimage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image judgementLineimage2 = new ImageIcon(Main.class.getResource("../images/judgementLine2.png"))
			.getImage();
	private Image gameInfoimage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();

	private Image noteRouteSimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image judgeimage;

	private Image noteRouteS2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
	private Image noteRouteD2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
	private Image noteRouteF2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
	private Image noteRouteSpace2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
	private Image noteRouteJ2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
	private Image noteRouteK2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
	private Image noteRouteL2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();

	private Image keyPadSimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	private Image keyPadS2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	private Image keyPadD2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	private Image keyPadF2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	private Image keyPadSpace2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	private Image keyPadJ2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	private Image keyPadK2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	private Image keyPadL2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();

	private Image imagegameevent = new ImageIcon(Main.class.getResource("../images/imageevent.png")).getImage();
	private Image imagegameevent2 = new ImageIcon(Main.class.getResource("../images/imageevent2.png")).getImage();
	
	private String titleName;
	public static String difficulty;
	private String musicTitle;
	private Music gameMusic;
	public static int score;
	public static int Combo;
	public static int score2;
	public static int Combo2;

	public static boolean IMAGEevent=false;
	public static boolean IMAGEevent2=false;
	public static boolean COMBOevent=false;
	public static int imageevent = 1;
	public static int scoreevent = 1;
	public static int comboevent = 0;
	Note2 note;
	ArrayList<Note2> noteList = new ArrayList<Note2>();

	public Game2(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}
	public void screenDraw(Graphics2D g) {
		// 플레이어1
		g.drawImage(noteRouteSimage, 100, 30, null);
		g.drawImage(noteRouteDimage, 200, 30, null);
		g.drawImage(noteRouteFimage, 300, 30, null);
		g.drawImage(noteRouteSpace1image, 400, 30, null);
		g.drawImage(noteRouteJimage, 500, 30, null);
		g.drawImage(noteRouteKimage, 600, 30, null);
		g.drawImage(noteRouteLimage, 700, 30, null);
		// 플레이어2
		g.drawImage(noteRouteS2image, 900, 345, null);
		g.drawImage(noteRouteD2image, 950, 345, null);
		g.drawImage(noteRouteF2image, 1000, 345, null);
		g.drawImage(noteRouteSpace2image, 1050, 345, null);
		g.drawImage(noteRouteJ2image, 1100, 345, null);
		g.drawImage(noteRouteK2image, 1150, 345, null);
		g.drawImage(noteRouteL2image, 1200, 345, null);
		// 플레이어1
		g.drawImage(keyPadSimage, 100, 580, null);
		g.drawImage(keyPadDimage, 200, 580, null);
		g.drawImage(keyPadFimage, 300, 580, null);
		g.drawImage(keyPadSpace1image, 400, 580, null);
		g.drawImage(keyPadJimage, 500, 580, null);
		g.drawImage(keyPadKimage, 600, 580, null);
		g.drawImage(keyPadLimage, 700, 580, null);
		// 플레이어2
		g.drawImage(keyPadS2image, 900, 620, null);
		g.drawImage(keyPadD2image, 950, 620, null);
		g.drawImage(keyPadF2image, 1000, 620, null);
		g.drawImage(keyPadSpace2image, 1050, 620, null);
		g.drawImage(keyPadJ2image, 1100, 620, null);
		g.drawImage(keyPadK2image, 1150, 620, null);
		g.drawImage(keyPadL2image, 1200, 620, null);
		// 플레이어1
		g.drawImage(noteRouteLineimage, 96, 30, null);
		g.drawImage(noteRouteLineimage, 196, 30, null);
		g.drawImage(noteRouteLineimage, 296, 30, null);
		g.drawImage(noteRouteLineimage, 396, 30, null);
		g.drawImage(noteRouteLineimage, 496, 30, null);
		g.drawImage(noteRouteLineimage, 596, 30, null);
		g.drawImage(noteRouteLineimage, 696, 30, null);
		g.drawImage(noteRouteLineimage, 796, 30, null);
		// 플레이어2
		g.drawImage(noteRouteLineimage2, 898, 345, null);
		g.drawImage(noteRouteLineimage2, 948, 345, null);
		g.drawImage(noteRouteLineimage2, 998, 345, null);
		g.drawImage(noteRouteLineimage2, 1048, 345, null);
		g.drawImage(noteRouteLineimage2, 1098, 345, null);
		g.drawImage(noteRouteLineimage2, 1148, 345, null);
		g.drawImage(noteRouteLineimage2, 1198, 345, null);
		g.drawImage(noteRouteLineimage2, 1248, 345, null);

		g.drawImage(gameInfoimage, 0, 660, null);
		g.drawImage(judgementLineimage, 96, 580, null);
		g.drawImage(judgementLineimage2, 898, 620, null);
		for (int i = 0; i < noteList.size(); i++) {
			Note2 note = noteList.get(i);
			if (note.getY() > 660) {
				judgeimage = new ImageIcon(Main.class.getResource("../images/Miss.png")).getImage();
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);

		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.GRAY);
		g.drawString("S", 142, 609);// 42
		g.drawString("D", 242, 609);
		g.drawString("F", 342, 609);
		g.drawString("Bar", 427, 609);// 580
		g.drawString("J", 542, 609);// 784
		g.drawString("K", 642, 609);// 889
		g.drawString("L", 742, 609);// 993

		g.setFont(new Font("Arial", Font.PLAIN, 13));
		g.setColor(Color.GRAY);
		g.drawString("S", 920, 635);
		g.drawString("D", 970, 635);
		g.drawString("F", 1020, 635);
		g.drawString("Bar", 1067, 635);
		g.drawString("J", 1121, 635);
		g.drawString("K", 1171, 635);
		g.drawString("L", 1221, 635);

		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("" + score, 1150, 70);
		g.drawString("" + Combo, 10, 650);
		g.setFont(new Font("Elephant", Font.BOLD, 20));
		g.drawString("" + score2, 1150, 300);
		g.drawString("" + Combo2, 830, 650);
		g.drawImage(judgeimage, 267, 420, null);
		g.setFont(new Font("Elephant", Font.BOLD, 20));
		if (IMAGEevent == true)
			g.drawImage(imagegameevent, 96, 30, null);
		if (IMAGEevent2 == true)
			g.drawImage(imagegameevent2, 898, 345, null);
		/*if (comboevent > 0)
			g.drawString("comboevent : " + comboevent, 1120, 200);*/
		if (imageevent > 0)
			g.drawString("imageevent : " + imageevent, 1120, 230);
		if (scoreevent > 0)
			g.drawString("scoreevent : " + scoreevent, 1120, 260);
	}
	public void imageevent() {
		IMAGEevent=true;
		Timer Timer = new Timer();
		TimerTask Task = new TimerTask(){
			@Override
			public void run() {
				IMAGEevent=false;
			}
		};
		Timer.schedule(Task, 5000);
	}
	public void imageevent2() {
		IMAGEevent2=true;
		imageevent--;
		Timer Timer = new Timer();
		TimerTask Task = new TimerTask(){
			@Override
			public void run() {
				IMAGEevent2=false;
			}
		};
		Timer.schedule(Task, 5000);
	}
	public void scoreevent() {
		score -= 2000;
	}
	public void scoreevent2() {
		scoreevent--;
	}
	public void comboevent() {
		COMBOevent=true;
		comboevent--;
		Timer Timer = new Timer();
		TimerTask Task = new TimerTask(){
			@Override
			public void run() {
				COMBOevent=false;
			}
		};
		Timer.schedule(Task, 5000);
	}
	public void PressS() {
		judge("S");
		noteRouteSimage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSimage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("Bamboo.mp3", false).start();
	}

	public void releaseS() {
		noteRouteSimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void PressD() {
		judge("D");
		noteRouteDimage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDimage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("Bamboo.mp3", false).start();
	}

	public void releaseD() {
		noteRouteDimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void PressF() {
		judge("F");
		noteRouteFimage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFimage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("Bamboo.mp3", false).start();
	}

	public void releaseF() {
		noteRouteFimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void PressSpace() {
		judge("Space");
		noteRouteSpace1image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed1.png")).getImage();
		keyPadSpace1image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("Bamboo.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpace1image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void PressJ() {
		judge("J");
		noteRouteJimage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJimage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("Bamboo.mp3", false).start();
	}

	public void releaseJ() {
		noteRouteJimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void PressK() {
		judge("K");
		noteRouteKimage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKimage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("Bamboo.mp3", false).start();
	}

	public void releaseK() {
		noteRouteKimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void PressL() {
		judge("L");
		noteRouteLimage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLimage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("Bamboo.mp3", false).start();
	}

	public void releaseL() {
		noteRouteLimage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLimage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void PressS2() {
		noteRouteS2image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed3.png")).getImage();
		keyPadS2image = new ImageIcon(Main.class.getResource("../images/keyPadPressed2.png")).getImage();
	}

	public void releaseS2() {
		noteRouteS2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
		keyPadS2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	}

	public void PressD2() {
		noteRouteD2image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed3.png")).getImage();
		keyPadD2image = new ImageIcon(Main.class.getResource("../images/keyPadPressed2.png")).getImage();
	}

	public void releaseD2() {
		noteRouteD2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
		keyPadD2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	}

	public void PressF2() {
		noteRouteF2image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed3.png")).getImage();
		keyPadF2image = new ImageIcon(Main.class.getResource("../images/keyPadPressed2.png")).getImage();
	}

	public void releaseF2() {
		noteRouteF2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
		keyPadF2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	}

	public void PressSpace2() {
		noteRouteSpace2image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed3.png")).getImage();
		keyPadSpace2image = new ImageIcon(Main.class.getResource("../images/keyPadPressed2.png")).getImage();
	}

	public void releaseSpace2() {
		noteRouteSpace2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
		keyPadSpace2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	}

	public void PressJ2() {
		noteRouteJ2image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed3.png")).getImage();
		keyPadJ2image = new ImageIcon(Main.class.getResource("../images/keyPadPressed2.png")).getImage();
	}

	public void releaseJ2() {
		noteRouteJ2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
		keyPadJ2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	}

	public void PressK2() {
		noteRouteK2image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed3.png")).getImage();
		keyPadK2image = new ImageIcon(Main.class.getResource("../images/keyPadPressed2.png")).getImage();
	}

	public void releaseK2() {
		noteRouteK2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
		keyPadK2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	}

	public void PressL2() {
		noteRouteL2image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed3.png")).getImage();
		keyPadL2image = new ImageIcon(Main.class.getResource("../images/keyPadPressed2.png")).getImage();
	}

	public void releaseL2() {
		noteRouteL2image = new ImageIcon(Main.class.getResource("../images/noteRoute2.png")).getImage();
		keyPadL2image = new ImageIcon(Main.class.getResource("../images/keyPadBasic2.png")).getImage();
	}
	@Override
	public void run() {
		dropNotes();
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes() {
		Beat[] beats = null;
		if (titleName.equals("We Are - Jo Cohen & Sex Whales")) {
			// int startTime = 1000 - Main.REACH_TIME;
			beats = new Beat[] { new Beat(1000, "S"), new Beat(2000, "D"), new Beat(3000, "F"), };
		} else if (titleName.equals("Cradles - Sub Urban")) {
			// int startTime = 1000 - Main.REACH_TIME;
			beats = new Beat[] { new Beat(1000, "D"), new Beat(2000, "F"), new Beat(3000, "S"), };
		} else if (titleName.equals("Light It Up - RoBin Hustin x TobiMorrow")) {
			// int startTime = 1000 - Main.REACH_TIME;
			beats = new Beat[] { new Beat(1000, "F"), new Beat(2000, "S"), new Beat(3000, "D"), };
		} else if (titleName.equals("FlashLight - DJ Fresh (feat. Ellie Goulding)") && difficulty.equals("Easy")) {
			int SP = 9000;// ���ǽ�������Ʈ 9000
			double gab = 85.9;
			beats = new Beat[] { new Beat(SP, "S"), new Beat(SP + 1 * gab, "F"), new Beat(SP + 3 * gab, "Space"),
					new Beat(SP + 5 * gab, "D"), new Beat(SP + 7 * gab, "Space"), new Beat(SP + 9 * gab, "F"),
					new Beat(SP + 11 * gab, "D"), new Beat(SP + 13 * gab, "F"), new Beat(SP + 15 * gab, "F"),
					new Beat(SP + 17 * gab, "L"), new Beat(SP + 19 * gab, "D"), new Beat(SP + 21 * gab, "F"),
					new Beat(SP + 23 * gab, "F"), new Beat(SP + 25 * gab, "K"), new Beat(SP + 27 * gab, "K"),
					new Beat(SP + 29 * gab, "Space"), new Beat(SP + 31 * gab, "Space"), new Beat(SP + 33 * gab, "J"),
					new Beat(SP + 35 * gab, "J"), new Beat(SP + 37 * gab, "Space"), new Beat(SP + 39 * gab, "Space"),
					new Beat(SP + 41 * gab, "F"), new Beat(SP + 43 * gab, "F"), new Beat(SP + 45 * gab, "F"),
					new Beat(SP + 47 * gab, "D"), new Beat(SP + 49 * gab, "D"), new Beat(SP + 51 * gab, "D"),
					new Beat(SP + 53 * gab, "L"), new Beat(SP + 55 * gab, "Space"), new Beat(SP + 57 * gab, "K"),
					new Beat(SP + 59 * gab, "K"), new Beat(SP + 61 * gab, "L"), new Beat(SP + 63 * gab, "L"),
					new Beat(SP + 65 * gab, "K"), new Beat(SP + 67 * gab, "K"), new Beat(SP + 69 * gab, "Space"),
					new Beat(SP + 71 * gab, "F"), new Beat(SP + 73 * gab, "Space"), new Beat(SP + 75 * gab, "D"),
					new Beat(SP + 77 * gab, "J"), new Beat(SP + 79 * gab, "D"), new Beat(SP + 81 * gab, "S"),
					new Beat(SP + 83 * gab, "S"), new Beat(SP + 85 * gab, "F"), new Beat(SP + 87 * gab, "Space"),
					new Beat(SP + 89 * gab, "F"), new Beat(SP + 91 * gab, "D"), new Beat(SP + 93 * gab, "Space"),
					new Beat(SP + 95 * gab, "D"), new Beat(SP + 97 * gab, "J"), new Beat(SP + 99 * gab, "Space"),

					new Beat(SP + 101 * gab, "F"), new Beat(SP + 103 * gab, "Space"), new Beat(SP + 105 * gab, "D"),
					new Beat(SP + 107 * gab, "Space"), new Beat(SP + 109 * gab, "F"), new Beat(SP + 111 * gab, "D"),
					new Beat(SP + 113 * gab, "F"), new Beat(SP + 115 * gab, "F"), new Beat(SP + 117 * gab, "L"),
					new Beat(SP + 119 * gab, "D"), new Beat(SP + 121 * gab, "F"), new Beat(SP + 123 * gab, "F"),
					new Beat(SP + 125 * gab, "K"), new Beat(SP + 127 * gab, "K"), new Beat(SP + 129 * gab, "Space"),
					new Beat(SP + 131 * gab, "Space"), new Beat(SP + 133 * gab, "J"), new Beat(SP + 135 * gab, "J"),
					new Beat(SP + 137 * gab, "Space"), new Beat(SP + 139 * gab, "Space"), new Beat(SP + 141 * gab, "F"),
					new Beat(SP + 143 * gab, "F"), new Beat(SP + 145 * gab, "F"), new Beat(SP + 147 * gab, "D"),
					new Beat(SP + 149 * gab, "D"), new Beat(SP + 151 * gab, "D"), new Beat(SP + 153 * gab, "L"),
					new Beat(SP + 155 * gab, "Space"), new Beat(SP + 157 * gab, "K"), new Beat(SP + 159 * gab, "K"),
					new Beat(SP + 161 * gab, "L"), new Beat(SP + 163 * gab, "L"), new Beat(SP + 165 * gab, "K"),
					new Beat(SP + 167 * gab, "K"), new Beat(SP + 169 * gab, "Space"), new Beat(SP + 171 * gab, "F"),
					new Beat(SP + 173 * gab, "Space"), new Beat(SP + 175 * gab, "D"), new Beat(SP + 177 * gab, "J"),
					new Beat(SP + 179 * gab, "D"), new Beat(SP + 181 * gab, "S"), new Beat(SP + 183 * gab, "S"),
					new Beat(SP + 185 * gab, "F"), new Beat(SP + 187 * gab, "Space"), new Beat(SP + 189 * gab, "F"),
					new Beat(SP + 191 * gab, "D"), new Beat(SP + 193 * gab, "Space"), new Beat(SP + 195 * gab, "D"),
					new Beat(SP + 197 * gab, "J"), new Beat(SP + 199 * gab, "Space"),

					new Beat(SP + 201 * gab, "F"), new Beat(SP + 203 * gab, "Space"), new Beat(SP + 205 * gab, "D"),
					new Beat(SP + 207 * gab, "Space"), new Beat(SP + 209 * gab, "F"), new Beat(SP + 211 * gab, "D"),
					new Beat(SP + 213 * gab, "F"), new Beat(SP + 215 * gab, "F"), new Beat(SP + 217 * gab, "L"),
					new Beat(SP + 219 * gab, "D"), new Beat(SP + 221 * gab, "F"), new Beat(SP + 223 * gab, "F"),
					new Beat(SP + 225 * gab, "K"), new Beat(SP + 227 * gab, "K"), new Beat(SP + 229 * gab, "Space"),
					new Beat(SP + 231 * gab, "Space"), new Beat(SP + 233 * gab, "J"), new Beat(SP + 235 * gab, "J"),
					new Beat(SP + 237 * gab, "Space"), new Beat(SP + 239 * gab, "Space"), new Beat(SP + 239 * gab, "D"),
					new Beat(SP + 239 * gab, "K"),

					// 37초
					new Beat(SP + 255 * gab, "S"), new Beat(SP + 255 * gab, "F"), new Beat(SP + 255 * gab, "J"),
					new Beat(SP + 261 * gab, "D"), new Beat(SP + 261 * gab, "Space"), new Beat(SP + 261 * gab, "K"),
					new Beat(SP + 267 * gab, "F"), new Beat(SP + 267 * gab, "L"), new Beat(SP + 271 * gab, "S"),
					new Beat(SP + 275 * gab, "F"), new Beat(SP + 279 * gab, "J"), new Beat(SP + 283 * gab, "L"),

					new Beat(SP + 287 * gab, "F"), new Beat(SP + 287 * gab, "J"), new Beat(SP + 287 * gab, "L"),
					new Beat(SP + 293 * gab, "D"), new Beat(SP + 293 * gab, "Space"), new Beat(SP + 293 * gab, "K"),
					new Beat(SP + 299 * gab, "S"), new Beat(SP + 299 * gab, "J"), new Beat(SP + 303 * gab, "L"),
					new Beat(SP + 307 * gab, "J"), new Beat(SP + 311 * gab, "F"), new Beat(SP + 315 * gab, "S"),

					new Beat(SP + 319 * gab, "S"), new Beat(SP + 319 * gab, "F"), new Beat(SP + 319 * gab, "J"),
					new Beat(SP + 325 * gab, "D"), new Beat(SP + 325 * gab, "Space"), new Beat(SP + 131 * gab, "K"),
					new Beat(SP + 331 * gab, "F"), new Beat(SP + 331 * gab, "L"), new Beat(SP + 335 * gab, "S"),
					new Beat(SP + 335 * gab, "F"), new Beat(SP + 339 * gab, "D"), new Beat(SP + 339 * gab, "Space"),
					new Beat(SP + 343 * gab, "F"), new Beat(SP + 343 * gab, "J"), new Beat(SP + 347 * gab, "Space"),
					new Beat(SP + 347 * gab, "K"),

					new Beat(SP + 351 * gab, "F"), new Beat(SP + 351 * gab, "J"), new Beat(SP + 351 * gab, "L"),
					new Beat(SP + 357 * gab, "D"), new Beat(SP + 357 * gab, "Space"), new Beat(SP + 357 * gab, "K"),
					new Beat(SP + 363 * gab, "S"), new Beat(SP + 363 * gab, "J"), new Beat(SP + 367 * gab, "J"),
					new Beat(SP + 367 * gab, "L"), new Beat(SP + 371 * gab, "Space"), new Beat(SP + 371 * gab, "K"),
					new Beat(SP + 375 * gab, "F"), new Beat(SP + 375 * gab, "J"), new Beat(SP + 379 * gab, "D"),
					new Beat(SP + 379 * gab, "Space"),

					// 48초
					new Beat(SP + 382 * gab, "S"), new Beat(SP + 382 * gab, "F"), new Beat(SP + 382 * gab, "J"),
					new Beat(SP + 382 * gab, "L"), new Beat(SP + 386 * gab, "S"), new Beat(SP + 388 * gab, "F"),
					new Beat(SP + 392 * gab, "L"), new Beat(SP + 396 * gab, "J"), new Beat(SP + 400 * gab, "Space"),
					new Beat(SP + 402 * gab, "F"), new Beat(SP + 402 * gab, "J"), new Beat(SP + 406 * gab, "S"),
					new Beat(SP + 406 * gab, "L"),

					new Beat(SP + 418 * gab, "D"), new Beat(SP + 418 * gab, "K"), new Beat(SP + 422 * gab, "F"),
					new Beat(SP + 422 * gab, "Space"), new Beat(SP + 422 * gab, "J"),

					new Beat(SP + 436 * gab, "D"), new Beat(SP + 438 * gab, "F"), new Beat(SP + 440 * gab, "Space"),
					new Beat(SP + 442 * gab, "J"), new Beat(SP + 444 * gab, "K"),

					new Beat(SP + 452 * gab, "K"), new Beat(SP + 454 * gab, "J"), new Beat(SP + 456 * gab, "Space"),
					new Beat(SP + 458 * gab, "F"), new Beat(SP + 460 * gab, "D"),

					new Beat(SP + 468 * gab, "D"), new Beat(SP + 470 * gab, "S"), new Beat(SP + 470 * gab, "F"),
					new Beat(SP + 470 * gab, "J"), new Beat(SP + 472 * gab, "D"), new Beat(SP + 472 * gab, "Space"),
					new Beat(SP + 472 * gab, "K"), new Beat(SP + 472 * gab, "L"), new Beat(SP + 476 * gab, "L"),
					new Beat(SP + 480 * gab, "K"), new Beat(SP + 482 * gab, "J"), new Beat(SP + 484 * gab, "Space"),
					new Beat(SP + 486 * gab, "F"), new Beat(SP + 488 * gab, "D"), new Beat(SP + 490 * gab, "S"),
					new Beat(SP + 491 * gab, "D"), new Beat(SP + 492 * gab, "F"), new Beat(SP + 493 * gab, "Space"),
					new Beat(SP + 494 * gab, "J"),

					// 59초
					new Beat(SP + 500 * gab, "S"), new Beat(SP + 500 * gab, "F"), new Beat(SP + 500 * gab, "J"),
					new Beat(SP + 500 * gab, "L"), new Beat(SP + 502 * gab, "L"), new Beat(SP + 504 * gab, "J"),
					new Beat(SP + 506 * gab, "Space"),

					new Beat(SP + 510 * gab, "S"), new Beat(SP + 510 * gab, "F"), new Beat(SP + 510 * gab, "J"),
					new Beat(SP + 510 * gab, "L"), new Beat(SP + 514 * gab, "L"), new Beat(SP + 516 * gab, "J"),
					new Beat(SP + 520 * gab, "S"), new Beat(SP + 524 * gab, "F"), new Beat(SP + 528 * gab, "Space"),
					new Beat(SP + 530 * gab, "K"), new Beat(SP + 530 * gab, "L"), new Beat(SP + 534 * gab, "S"),
					new Beat(SP + 534 * gab, "D"),

					new Beat(SP + 546 * gab, "D"), new Beat(SP + 546 * gab, "F"), new Beat(SP + 550 * gab, "J"),
					new Beat(SP + 550 * gab, "K"),

					new Beat(SP + 564 * gab, "D"), new Beat(SP + 566 * gab, "D"), new Beat(SP + 568 * gab, "K"),
					new Beat(SP + 570 * gab, "F"), new Beat(SP + 572 * gab, "F"),

					new Beat(SP + 580 * gab, "L"), new Beat(SP + 582 * gab, "K"), new Beat(SP + 584 * gab, "J"),
					new Beat(SP + 586 * gab, "S"), new Beat(SP + 588 * gab, "D"),

					new Beat(SP + 596 * gab, "Space"), new Beat(SP + 598 * gab, "J"), new Beat(SP + 598 * gab, "K"),
					new Beat(SP + 598 * gab, "L"), new Beat(SP + 600 * gab, "S"), new Beat(SP + 598 * gab, "D"),
					new Beat(SP + 598 * gab, "F"), new Beat(SP + 604 * gab, "L"), new Beat(SP + 608 * gab, "K"),
					new Beat(SP + 610 * gab, "J"), new Beat(SP + 612 * gab, "Space"), new Beat(SP + 614 * gab, "F"),
					new Beat(SP + 616 * gab, "D"), new Beat(SP + 618 * gab, "S"), new Beat(SP + 619 * gab, "D"),
					new Beat(SP + 620 * gab, "F"), new Beat(SP + 621 * gab, "Space"), new Beat(SP + 622 * gab, "J"),

					new Beat(SP + 626 * gab, "J"), new Beat(SP + 626 * gab, "K"), new Beat(SP + 628 * gab, "D"),
					new Beat(SP + 628 * gab, "F"),

					// 1분10초
					new Beat(SP + 634 * gab, "S"), new Beat(SP + 634 * gab, "L"), new Beat(SP + 636 * gab, "K"),
					new Beat(SP + 638 * gab, "J"),

					new Beat(SP + 644 * gab, "D"), new Beat(SP + 644 * gab, "K"), new Beat(SP + 644 * gab, "L"),
					new Beat(SP + 646 * gab, "L"), new Beat(SP + 648 * gab, "F"), new Beat(SP + 648 * gab, "J"),
					new Beat(SP + 650 * gab, "D"),

					new Beat(SP + 654 * gab, "S"), new Beat(SP + 654 * gab, "L"), new Beat(SP + 658 * gab, "D"),
					new Beat(SP + 658 * gab, "F"),

					new Beat(SP + 662 * gab, "Space"), new Beat(SP + 662 * gab, "K"), new Beat(SP + 664 * gab, "K"),
					new Beat(SP + 666 * gab, "Space"), new Beat(SP + 666 * gab, "D"), new Beat(SP + 668 * gab, "D"),

					new Beat(SP + 670 * gab, "D"), new Beat(SP + 670 * gab, "K"), new Beat(SP + 674 * gab, "F"),
					new Beat(SP + 674 * gab, "J"),

					new Beat(SP + 678 * gab, "L"), new Beat(SP + 680 * gab, "J"), new Beat(SP + 682 * gab, "F"),
					new Beat(SP + 684 * gab, "S"),

					new Beat(SP + 686 * gab, "D"), new Beat(SP + 686 * gab, "Space"), new Beat(SP + 686 * gab, "K"),

					new Beat(SP + 690 * gab, "D"), new Beat(SP + 692 * gab, "F"), new Beat(SP + 694 * gab, "J"),
					new Beat(SP + 694 * gab, "L"),

					new Beat(SP + 698 * gab, "D"), new Beat(SP + 698 * gab, "F"), new Beat(SP + 700 * gab, "J"),
					new Beat(SP + 700 * gab, "K"),

					new Beat(SP + 706 * gab, "S"), new Beat(SP + 706 * gab, "F"), new Beat(SP + 708 * gab, "J"),
					new Beat(SP + 708 * gab, "L"),

					new Beat(SP + 712 * gab, "J"), new Beat(SP + 712 * gab, "K"), new Beat(SP + 714 * gab, "D"),
					new Beat(SP + 714 * gab, "F"),

					new Beat(SP + 721 * gab, "K"), new Beat(SP + 721 * gab, "L"), new Beat(SP + 723 * gab, "S"),
					new Beat(SP + 723 * gab, "D"),

					new Beat(SP + 727 * gab, "D"), new Beat(SP + 727 * gab, "F"), new Beat(SP + 729 * gab, "J"),
					new Beat(SP + 729 * gab, "K"),

					new Beat(SP + 737 * gab, "D"), new Beat(SP + 739 * gab, "D"), new Beat(SP + 739 * gab, "F"),

					new Beat(SP + 743 * gab, "K"), new Beat(SP + 745 * gab, "J"), new Beat(SP + 745 * gab, "K"),

					new Beat(SP + 749 * gab, "D"), new Beat(SP + 751 * gab, "F"), new Beat(SP + 753 * gab, "K"),
					new Beat(SP + 755 * gab, "J"), new Beat(SP + 757 * gab, "D"), new Beat(SP + 759 * gab, "F"),
					new Beat(SP + 761 * gab, "K"), new Beat(SP + 763 * gab, "J"),

					// 1분 21초
					new Beat(SP + 765 * gab, "S"), new Beat(SP + 765 * gab, "L"), new Beat(SP + 769 * gab, "F"),
					new Beat(SP + 769 * gab, "J"),

					new Beat(SP + 773 * gab, "D"), new Beat(SP + 775 * gab, "Space"), new Beat(SP + 777 * gab, "J"),
					new Beat(SP + 777 * gab, "K"), new Beat(SP + 779 * gab, "F"), new Beat(SP + 781 * gab, "S"),
					new Beat(SP + 781 * gab, "K"),

					new Beat(SP + 785 * gab, "J"), new Beat(SP + 785 * gab, "K"),

					new Beat(SP + 789 * gab, "D"), new Beat(SP + 789 * gab, "F"),

					new Beat(SP + 793 * gab, "Space"), new Beat(SP + 795 * gab, "F"), new Beat(SP + 795 * gab, "J"),

					new Beat(SP + 797 * gab, "D"), new Beat(SP + 797 * gab, "K"),

					new Beat(SP + 801 * gab, "S"), new Beat(SP + 801 * gab, "L"), new Beat(SP + 803 * gab, "D"),
					new Beat(SP + 803 * gab, "K"), new Beat(SP + 805 * gab, "F"), new Beat(SP + 805 * gab, "J"),

					new Beat(SP + 809 * gab, "S"), new Beat(SP + 809 * gab, "L"), new Beat(SP + 811 * gab, "D"),
					new Beat(SP + 811 * gab, "K"),

					new Beat(SP + 813 * gab, "Space"), new Beat(SP + 817 * gab, "J"),

					new Beat(SP + 821 * gab, "D"), new Beat(SP + 823 * gab, "F"), new Beat(SP + 825 * gab, "K"),
					new Beat(SP + 827 * gab, "L"),

					new Beat(SP + 833 * gab, "J"), new Beat(SP + 833 * gab, "K"), new Beat(SP + 835 * gab, "S"),
					new Beat(SP + 835 * gab, "F"),

					new Beat(SP + 839 * gab, "D"), new Beat(SP + 839 * gab, "Space"), new Beat(SP + 841 * gab, "K"),
					new Beat(SP + 841 * gab, "L"),

					new Beat(SP + 849 * gab, "Space"), new Beat(SP + 849 * gab, "L"), new Beat(SP + 851 * gab, "S"),
					new Beat(SP + 851 * gab, "D"),

					new Beat(SP + 855 * gab, "J"), new Beat(SP + 855 * gab, "K"), new Beat(SP + 857 * gab, "J"),
					new Beat(SP + 857 * gab, "K"),

					new Beat(SP + 865 * gab, "F"), new Beat(SP + 865 * gab, "Space"), new Beat(SP + 867 * gab, "S"),
					new Beat(SP + 867 * gab, "L"),

					new Beat(SP + 871 * gab, "J"), new Beat(SP + 873 * gab, "F"),

					new Beat(SP + 877 * gab, "Space"), new Beat(SP + 879 * gab, "Space"),

					new Beat(SP + 881 * gab, "F"), new Beat(SP + 882 * gab, "J"), new Beat(SP + 883 * gab, "F"),
					new Beat(SP + 884 * gab, "J"), new Beat(SP + 885 * gab, "Space"),

					// 1분 32초
					new Beat(SP + 893 * gab, "Space"),

					new Beat(SP + 897 * gab, "F"), new Beat(SP + 899 * gab, "D"), new Beat(SP + 901 * gab, "K"),
					new Beat(SP + 903 * gab, "J"), new Beat(SP + 905 * gab, "K"),

					new Beat(SP + 909 * gab, "S"), new Beat(SP + 909 * gab, "D"), new Beat(SP + 913 * gab, "D"),
					new Beat(SP + 913 * gab, "F"), new Beat(SP + 917 * gab, "J"), new Beat(SP + 917 * gab, "K"),
					new Beat(SP + 921 * gab, "K"), new Beat(SP + 921 * gab, "L"),

					new Beat(SP + 927 * gab, "S"), new Beat(SP + 929 * gab, "L"), new Beat(SP + 931 * gab, "D"),
					new Beat(SP + 931 * gab, "Space"), new Beat(SP + 933 * gab, "J"), new Beat(SP + 935 * gab, "F"),
					new Beat(SP + 937 * gab, "K"), new Beat(SP + 937 * gab, "L"), new Beat(SP + 939 * gab, "Space"),

					new Beat(SP + 941 * gab, "D"), new Beat(SP + 941 * gab, "F"), new Beat(SP + 945 * gab, "K"),
					new Beat(SP + 945 * gab, "L"), new Beat(SP + 949 * gab, "F"), new Beat(SP + 949 * gab, "Space"),
					new Beat(SP + 949 * gab, "J"), new Beat(SP + 953 * gab, "S"), new Beat(SP + 953 * gab, "Space"),
					new Beat(SP + 953 * gab, "L"),

					new Beat(SP + 957 * gab, "D"), new Beat(SP + 957 * gab, "F"), new Beat(SP + 959 * gab, "D"),
					new Beat(SP + 959 * gab, "F"),

					new Beat(SP + 963 * gab, "J"), new Beat(SP + 963 * gab, "K"), new Beat(SP + 965 * gab, "J"),
					new Beat(SP + 965 * gab, "K"),

					new Beat(SP + 969 * gab, "Space"), new Beat(SP + 971 * gab, "Space"),

					new Beat(SP + 973 * gab, "S"), new Beat(SP + 973 * gab, "D"), new Beat(SP + 975 * gab, "S"),
					new Beat(SP + 975 * gab, "D"),

					new Beat(SP + 979 * gab, "K"), new Beat(SP + 979 * gab, "L"), new Beat(SP + 981 * gab, "K"),
					new Beat(SP + 981 * gab, "L"),

					new Beat(SP + 985 * gab, "Space"), new Beat(SP + 987 * gab, "Space"),

					new Beat(SP + 989 * gab, "S"), new Beat(SP + 989 * gab, "L"),

					new Beat(SP + 993 * gab, "D"), new Beat(SP + 993 * gab, "K"),

					new Beat(SP + 997 * gab, "F"), new Beat(SP + 997 * gab, "J"),

					new Beat(SP + 1001 * gab, "Space"),

					new Beat(SP + 1005 * gab, "S"), new Beat(SP + 1007 * gab, "S"), new Beat(SP + 1009 * gab, "L"),
					new Beat(SP + 1011 * gab, "L"),

					new Beat(SP + 1013 * gab, "D"), new Beat(SP + 1014 * gab, "F"), new Beat(SP + 1015 * gab, "K"),
					new Beat(SP + 1016 * gab, "J"), new Beat(SP + 1017 * gab, "D"), new Beat(SP + 1018 * gab, "F"),
					new Beat(SP + 1019 * gab, "K"), new Beat(SP + 1020 * gab, "J"),

					// 1분 43초
					new Beat(SP + 1021 * gab, "D"), new Beat(SP + 1021 * gab, "F"), new Beat(SP + 1025 * gab, "K"),
					new Beat(SP + 1027 * gab, "L"), new Beat(SP + 1029 * gab, "S"), new Beat(SP + 1029 * gab, "F"),

					new Beat(SP + 1033 * gab, "D"), new Beat(SP + 1035 * gab, "J"), new Beat(SP + 1037 * gab, "D"),
					new Beat(SP + 1039 * gab, "K"), new Beat(SP + 1041 * gab, "F"), new Beat(SP + 1043 * gab, "L"),
					new Beat(SP + 1045 * gab, "Space"),

					new Beat(SP + 1049 * gab, "D"), new Beat(SP + 1049 * gab, "Space"), new Beat(SP + 1051 * gab, "F"),
					new Beat(SP + 1051 * gab, "J"), new Beat(SP + 1053 * gab, "Space"), new Beat(SP + 1053 * gab, "K"),

					new Beat(SP + 1057 * gab, "L"), new Beat(SP + 1059 * gab, "J"), new Beat(SP + 1061 * gab, "S"),
					new Beat(SP + 1061 * gab, "F"),

					new Beat(SP + 1065 * gab, "S"), new Beat(SP + 1067 * gab, "F"), new Beat(SP + 1069 * gab, "Space"),
					new Beat(SP + 1071 * gab, "J"), new Beat(SP + 1073 * gab, "L"), new Beat(SP + 1075 * gab, "D"),
					new Beat(SP + 1077 * gab, "Space"), new Beat(SP + 1079 * gab, "K"), new Beat(SP + 1081 * gab, "J"),
					new Beat(SP + 1081 * gab, "L"), new Beat(SP + 1083 * gab, "D"), new Beat(SP + 1083 * gab, "K"),
					new Beat(SP + 1085 * gab, "S"), new Beat(SP + 1085 * gab, "F"),

					new Beat(SP + 1089 * gab, "J"), new Beat(SP + 1091 * gab, "F"), new Beat(SP + 1093 * gab, "L"),
					new Beat(SP + 1095 * gab, "K"), new Beat(SP + 1097 * gab, "S"), new Beat(SP + 1099 * gab, "J"),
					new Beat(SP + 1101 * gab, "D"), new Beat(SP + 1100 * gab, "Space"), new Beat(SP + 1100 * gab, "K"),

					new Beat(SP + 1105 * gab, "D"), new Beat(SP + 1107 * gab, "K"), new Beat(SP + 1109 * gab, "F"),
					new Beat(SP + 1111 * gab, "J"), new Beat(SP + 1112 * gab, "Space"), new Beat(SP + 1113 * gab, "F"),
					new Beat(SP + 1115 * gab, "S"), new Beat(SP + 1114 * gab, "Space"), new Beat(SP + 1114 * gab, "L"),

					new Beat(SP + 1119 * gab, "S"), new Beat(SP + 1121 * gab, "J"), new Beat(SP + 1123 * gab, "D"),
					new Beat(SP + 1122 * gab, "Space"), new Beat(SP + 1125 * gab, "K"), new Beat(SP + 1127 * gab, "F"),
					new Beat(SP + 1126 * gab, "Space"), new Beat(SP + 1129 * gab, "J"), new Beat(SP + 1131 * gab, "S"),
					new Beat(SP + 1130 * gab, "F"),

					// 1분 53초
					new Beat(SP + 1135 * gab, "Space"), new Beat(SP + 1137 * gab, "Space"),
					new Beat(SP + 1139 * gab, "F"), new Beat(SP + 1139 * gab, "J"), new Beat(SP + 1141 * gab, "F"),
					new Beat(SP + 1141 * gab, "J"), new Beat(SP + 1143 * gab, "D"), new Beat(SP + 1143 * gab, "K"),
					new Beat(SP + 1145 * gab, "S"), new Beat(SP + 1145 * gab, "L"),

					new Beat(SP + 1149 * gab, "F"),

					new Beat(SP + 1153 * gab, "Space"), new Beat(SP + 1153 * gab, "L"), new Beat(SP + 1155 * gab, "J"),
					new Beat(SP + 1155 * gab, "K"),

					new Beat(SP + 1159 * gab, "S"), new Beat(SP + 1159 * gab, "F"), new Beat(SP + 1159 * gab, "L"),

					new Beat(SP + 1163 * gab, "Space"),

					new Beat(SP + 1167 * gab, "Space"), new Beat(SP + 1169 * gab, "J"), new Beat(SP + 1169 * gab, "K"),

					new Beat(SP + 1173 * gab, "D"), new Beat(SP + 1173 * gab, "F"),

					new Beat(SP + 1177 * gab, "S"), new Beat(SP + 1179 * gab, "F"), new Beat(SP + 1181 * gab, "Space"),
					new Beat(SP + 1181 * gab, "K"),

					new Beat(SP + 1185 * gab, "J"), new Beat(SP + 1187 * gab, "Space"), new Beat(SP + 1189 * gab, "D"),

					new Beat(SP + 1193 * gab, "L"), new Beat(SP + 1195 * gab, "Space"), new Beat(SP + 1197 * gab, "S"),
					new Beat(SP + 1197 * gab, "D"), new Beat(SP + 1199 * gab, "J"), new Beat(SP + 1201 * gab, "Space"),
					new Beat(SP + 1203 * gab, "F"), new Beat(SP + 1205 * gab, "K"), new Beat(SP + 1207 * gab, "D"),
					new Beat(SP + 1208 * gab, "J"), new Beat(SP + 1209 * gab, "D"), new Beat(SP + 1211 * gab, "S"),
					new Beat(SP + 1213 * gab, "F"), new Beat(SP + 1213 * gab, "J"),

					new Beat(SP + 1219 * gab, "D"), new Beat(SP + 1221 * gab, "D"), new Beat(SP + 1223 * gab, "J"),
					new Beat(SP + 1225 * gab, "Space"), new Beat(SP + 1227 * gab, "L"), new Beat(SP + 1229 * gab, "F"),
					new Beat(SP + 1229 * gab, "J"),

					new Beat(SP + 1235 * gab, "S"), new Beat(SP + 1235 * gab, "L"), new Beat(SP + 1237 * gab, "D"),
					new Beat(SP + 1237 * gab, "K"), new Beat(SP + 1239 * gab, "F"), new Beat(SP + 1239 * gab, "J"),

					new Beat(SP + 1243 * gab, "D"), new Beat(SP + 1243 * gab, "Space"), new Beat(SP + 1243 * gab, "K"),

					new Beat(SP + 1247 * gab, "F"), new Beat(SP + 1248 * gab, "J"), new Beat(SP + 1249 * gab, "F"),

					new Beat(SP + 1251 * gab, "K"), new Beat(SP + 1252 * gab, "D"), new Beat(SP + 1253 * gab, "K"),

					new Beat(SP + 1255 * gab, "S"), new Beat(SP + 1257 * gab, "J"), new Beat(SP + 1259 * gab, "F"),
					new Beat(SP + 1259 * gab, "Space"), new Beat(SP + 1261 * gab, "L"), new Beat(SP + 1263 * gab, "K"),
					new Beat(SP + 1265 * gab, "Space"), new Beat(SP + 1267 * gab, "J"), new Beat(SP + 1269 * gab, "D"),
					new Beat(SP + 1269 * gab, "F"), new Beat(SP + 1271 * gab, "J"), new Beat(SP + 1271 * gab, "K"),
					new Beat(SP + 1273 * gab, "D"), new Beat(SP + 1273 * gab, "F"), new Beat(SP + 1275 * gab, "J"),
					new Beat(SP + 1275 * gab, "K"), new Beat(SP + 1277 * gab, "S"), new Beat(SP + 1277 * gab, "Space"),
					new Beat(SP + 1277 * gab, "L"),

					// 2분 5초
					new Beat(SP + 1281 * gab, "D"), new Beat(SP + 1283 * gab, "D"), new Beat(SP + 1283 * gab, "Space"),
					new Beat(SP + 1283 * gab, "K"),

					new Beat(SP + 1287 * gab, "J"), new Beat(SP + 1289 * gab, "J"), new Beat(SP + 1289 * gab, "K"),

					new Beat(SP + 1297 * gab, "K"), new Beat(SP + 1299 * gab, "D"), new Beat(SP + 1299 * gab, "Space"),
					new Beat(SP + 1299 * gab, "K"),

					new Beat(SP + 1303 * gab, "D"), new Beat(SP + 1305 * gab, "D"), new Beat(SP + 1305 * gab, "F"),

					new Beat(SP + 1313 * gab, "J"), new Beat(SP + 1313 * gab, "L"), new Beat(SP + 1315 * gab, "J"),
					new Beat(SP + 1315 * gab, "L"),

					new Beat(SP + 1319 * gab, "S"), new Beat(SP + 1319 * gab, "F"), new Beat(SP + 1321 * gab, "S"),
					new Beat(SP + 1321 * gab, "F"),

					new Beat(SP + 1329 * gab, "D"), new Beat(SP + 1329 * gab, "K"), new Beat(SP + 1331 * gab, "D"),
					new Beat(SP + 1331 * gab, "K"),

					new Beat(SP + 1335 * gab, "F"), new Beat(SP + 1335 * gab, "J"), new Beat(SP + 1337 * gab, "F"),
					new Beat(SP + 1337 * gab, "J"),

					new Beat(SP + 1340 * gab, "Space"), new Beat(SP + 1342 * gab, "J"), new Beat(SP + 1344 * gab, "S"),
					new Beat(SP + 1344 * gab, "F"), new Beat(SP + 1346 * gab, "K"),

					new Beat(SP + 1348 * gab, "D"), new Beat(SP + 1350 * gab, "J"), new Beat(SP + 1352 * gab, "D"),
					new Beat(SP + 1342 * gab, "Space"), new Beat(SP + 1342 * gab, "K"), new Beat(SP + 1354 * gab, "J"),

					new Beat(SP + 1356 * gab, "L"), new Beat(SP + 1358 * gab, "Space"), new Beat(SP + 1360 * gab, "S"),
					new Beat(SP + 1360 * gab, "F"), new Beat(SP + 1360 * gab, "J"), new Beat(SP + 1362 * gab, "Space"),

					new Beat(SP + 1364 * gab, "D"), new Beat(SP + 1364 * gab, "F"), new Beat(SP + 1366 * gab, "K"),
					new Beat(SP + 1368 * gab, "S"), new Beat(SP + 1370 * gab, "J"),

					new Beat(SP + 1372 * gab, "D"), new Beat(SP + 1372 * gab, "J"), new Beat(SP + 1372 * gab, "L"),
					new Beat(SP + 1374 * gab, "S"), new Beat(SP + 1376 * gab, "Space"), new Beat(SP + 1376 * gab, "K"),
					new Beat(SP + 1378 * gab, "J"),

					new Beat(SP + 1380 * gab, "S"), new Beat(SP + 1382 * gab, "J"), new Beat(SP + 1384 * gab, "D"),
					new Beat(SP + 1384 * gab, "Space"), new Beat(SP + 1384 * gab, "L"), new Beat(SP + 1386 * gab, "K"),

					new Beat(SP + 1388 * gab, "Space"), new Beat(SP + 1390 * gab, "D"), new Beat(SP + 1392 * gab, "F"),
					new Beat(SP + 1392 * gab, "J"), new Beat(SP + 1392 * gab, "K"), new Beat(SP + 1394 * gab, "S"),

					new Beat(SP + 1396 * gab, "Space"), new Beat(SP + 1396 * gab, "L"), new Beat(SP + 1398 * gab, "F"),
					new Beat(SP + 1400 * gab, "S"), new Beat(SP + 1400 * gab, "F"), new Beat(SP + 1400 * gab, "J"),
					new Beat(SP + 1402 * gab, "J"),

					// 2분 15초
					new Beat(SP + 1404 * gab, "S"), new Beat(SP + 1404 * gab, "Space"), new Beat(SP + 1404 * gab, "L"),

					new Beat(SP + 1408 * gab, "Space"), new Beat(SP + 1410 * gab, "D"), new Beat(SP + 1410 * gab, "F"),
					new Beat(SP + 1412 * gab, "J"), new Beat(SP + 1414 * gab, "F"), new Beat(SP + 1416 * gab, "J"),

					new Beat(SP + 1420 * gab, "S"), new Beat(SP + 1420 * gab, "Space"), new Beat(SP + 1422 * gab, "F"),
					new Beat(SP + 1424 * gab, "J"), new Beat(SP + 1424 * gab, "L"),

					new Beat(SP + 1428 * gab, "F"), new Beat(SP + 1428 * gab, "L"), new Beat(SP + 1430 * gab, "K"),
					new Beat(SP + 1432 * gab, "S"), new Beat(SP + 1432 * gab, "D"),

					new Beat(SP + 1438 * gab, "J"), new Beat(SP + 1440 * gab, "F"), new Beat(SP + 1442 * gab, "F"),
					new Beat(SP + 1444 * gab, "J"), new Beat(SP + 1444 * gab, "L"), new Beat(SP + 1446 * gab, "S"),
					new Beat(SP + 1448 * gab, "K"), new Beat(SP + 1450 * gab, "D"),

					new Beat(SP + 1452 * gab, "J"), new Beat(SP + 1452 * gab, "K"), new Beat(SP + 1454 * gab, "S"),
					new Beat(SP + 1456 * gab, "K"), new Beat(SP + 1456 * gab, "L"), new Beat(SP + 1458 * gab, "Space"),
					new Beat(SP + 1460 * gab, "D"), new Beat(SP + 1460 * gab, "F"), new Beat(SP + 1462 * gab, "J"),
					new Beat(SP + 1464 * gab, "S"), new Beat(SP + 1464 * gab, "Space"), new Beat(SP + 1466 * gab, "K"),

					new Beat(SP + 1468 * gab, "S"), new Beat(SP + 1468 * gab, "F"), new Beat(SP + 1468 * gab, "J"),
					new Beat(SP + 1470 * gab, "J"), new Beat(SP + 1472 * gab, "J"),

					new Beat(SP + 1474 * gab, "D"), new Beat(SP + 1474 * gab, "Space"), new Beat(SP + 1474 * gab, "K"),
					new Beat(SP + 1476 * gab, "F"), new Beat(SP + 1478 * gab, "F"),

					new Beat(SP + 1480 * gab, "J"), new Beat(SP + 1480 * gab, "K"), new Beat(SP + 1482 * gab, "F"),
					new Beat(SP + 1482 * gab, "D"),

					new Beat(SP + 1484 * gab, "J"), new Beat(SP + 1484 * gab, "K"), new Beat(SP + 1484 * gab, "L"),
					new Beat(SP + 1486 * gab, "Space"), new Beat(SP + 1488 * gab, "Space"),

					new Beat(SP + 1490 * gab, "S"), new Beat(SP + 1490 * gab, "D"), new Beat(SP + 1490 * gab, "F"),
					new Beat(SP + 1492 * gab, "Space"), new Beat(SP + 1494 * gab, "Space"),

					new Beat(SP + 1496 * gab, "D"), new Beat(SP + 1496 * gab, "F"), new Beat(SP + 1498 * gab, "J"),
					new Beat(SP + 1498 * gab, "K"),

					new Beat(SP + 1500 * gab, "S"), new Beat(SP + 1502 * gab, "L"), new Beat(SP + 1504 * gab, "D"),
					new Beat(SP + 1506 * gab, "K"),

					new Beat(SP + 1508 * gab, "D"), new Beat(SP + 1508 * gab, "F"), new Beat(SP + 1510 * gab, "J"),
					new Beat(SP + 1510 * gab, "K"), new Beat(SP + 1512 * gab, "D"), new Beat(SP + 1512 * gab, "F"),
					new Beat(SP + 1514 * gab, "J"), new Beat(SP + 1514 * gab, "K"),

					new Beat(SP + 1516 * gab, "F"), new Beat(SP + 1517 * gab, "J"), new Beat(SP + 1518 * gab, "F"),
					new Beat(SP + 1519 * gab, "J"), new Beat(SP + 1520 * gab, "D"), new Beat(SP + 1521 * gab, "K"),
					new Beat(SP + 1522 * gab, "D"), new Beat(SP + 1523 * gab, "K"),

					new Beat(SP + 1524 * gab, "S"), new Beat(SP + 1525 * gab, "D"), new Beat(SP + 1526 * gab, "F"),
					new Beat(SP + 1527 * gab, "Space"), new Beat(SP + 1528 * gab, "L"), new Beat(SP + 1529 * gab, "K"),
					new Beat(SP + 1530 * gab, "J"), new Beat(SP + 1531 * gab, "Space"),

					new Beat(SP + 1532 * gab, "S"), new Beat(SP + 1532 * gab, "D"), new Beat(SP + 1532 * gab, "F"), };
		}
		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note2 note = new Note2(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (i == beats.length) {
				try {
					Thread.sleep(5000);
					ChatClient.gameEnd(); //게임종료이벤트 2인용
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void getgameEvent() {
		int x = (int) (Math.random() * 2 + 1);
		switch (x) {
		case 1:
			imageevent++;
			break;
		case 2:
			scoreevent++;
			break;
		}
	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note2 note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		if (judge.equals("Miss")) {
			judgeimage = new ImageIcon(Main.class.getResource("../images/Miss.png")).getImage();
		} else if (judge.equals("Late")) {
			judgeimage = new ImageIcon(Main.class.getResource("../images/Late.png")).getImage();
		} else if (judge.equals("Good")) {
			judgeimage = new ImageIcon(Main.class.getResource("../images/Good.png")).getImage();
		} else if (judge.equals("Great")) {
			judgeimage = new ImageIcon(Main.class.getResource("../images/Great.png")).getImage();
		} else if (judge.equals("Perfect")) {
			judgeimage = new ImageIcon(Main.class.getResource("../images/Perfect.png")).getImage();
		} else if (judge.equals("Early")) {
			judgeimage = new ImageIcon(Main.class.getResource("../images/Early.png")).getImage();
		}
	}

}
