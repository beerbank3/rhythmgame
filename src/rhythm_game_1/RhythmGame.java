package rhythm_game_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RhythmGame extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;

	public Image background = new ImageIcon(Main.class.getResource("../images/Rhythm.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/Button.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Button2.png"));
	private ImageIcon startbasicButton = new ImageIcon(Main.class.getResource("../images/startbutton2.png"));
	private ImageIcon startEnteredButton = new ImageIcon(Main.class.getResource("../images/startbutton.png"));
	private ImageIcon exitbasicButton = new ImageIcon(Main.class.getResource("../images/exitbutton2.png"));
	private ImageIcon exitEnteredButton = new ImageIcon(Main.class.getResource("../images/exitbutton.png"));

	private ImageIcon easybasicButton = new ImageIcon(Main.class.getResource("../images/Easy.png"));
	private ImageIcon normalbasicButton = new ImageIcon(Main.class.getResource("../images/Normal.png"));
	private ImageIcon hardbasicButton = new ImageIcon(Main.class.getResource("../images/Hard.png"));
	private ImageIcon easyEnteredButton = new ImageIcon(Main.class.getResource("../images/Easy2.png"));
	private ImageIcon normalEnteredButton = new ImageIcon(Main.class.getResource("../images/Normal2.png"));
	private ImageIcon hardEnteredButton = new ImageIcon(Main.class.getResource("../images/Hard2.png"));

	private ImageIcon LeftbasicButton = new ImageIcon(Main.class.getResource("../images/left.png"));
	private ImageIcon LeftEnteredButton = new ImageIcon(Main.class.getResource("../images/left2.png"));
	private ImageIcon RightbasicButton = new ImageIcon(Main.class.getResource("../images/right.png"));
	private ImageIcon RightEnteredButton = new ImageIcon(Main.class.getResource("../images/right2.png"));

	private static ImageIcon backbasicButton = new ImageIcon(Main.class.getResource("../images/Back.png"));
	private static ImageIcon backEnteredButton = new ImageIcon(Main.class.getResource("../images/Back2.png"));

	private ImageIcon StopButton = new ImageIcon(Main.class.getResource("../images/stop.png"));
	private ImageIcon reStartButton = new ImageIcon(Main.class.getResource("../images/restart.png"));
	private static ImageIcon EndButton = new ImageIcon(Main.class.getResource("../images/gameend.png"));

	public static Image gameendInfo = new ImageIcon(Main.class.getResource("../images/gameendInfo.png")).getImage();
	public static Image scoreBase = new ImageIcon(Main.class.getResource("../images/scoreBase.png")).getImage();
	public static ImageIcon main = new ImageIcon(Main.class.getResource("../images/main.png"));
	public static ImageIcon main2 = new ImageIcon(Main.class.getResource("../images/Main2.png"));

	private Image GreatImage = new ImageIcon(Main.class.getResource("../images/Great.png")).getImage();
	private Image MissImage = new ImageIcon(Main.class.getResource("../images/Miss.png")).getImage();
	private Image LateImage = new ImageIcon(Main.class.getResource("../images/Late.png")).getImage();
	private Image GoodImage = new ImageIcon(Main.class.getResource("../images/Good.png")).getImage();
	private Image PerfectImage = new ImageIcon(Main.class.getResource("../images/Perfect.png")).getImage();
	private Image EarlyImage = new ImageIcon(Main.class.getResource("../images/Early.png")).getImage();
	private Image SCOREImage = new ImageIcon(Main.class.getResource("../images/SCORE.png")).getImage();
	private Image COMBOImage = new ImageIcon(Main.class.getResource("../images/COMBO.png")).getImage();
	private Image scoreBaseLineImage = new ImageIcon(Main.class.getResource("../images/scoreBaseLine.png")).getImage();

	private ImageIcon multiplayEnteredButton = new ImageIcon(Main.class.getResource("../images/multiplay1.png"));
	private ImageIcon multiplaybasicButton = new ImageIcon(Main.class.getResource("../images/multiplay2.png"));
	private JButton multiplayButton = new JButton(multiplaybasicButton);

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startbasicButton);
	private JButton quitButton = new JButton(exitbasicButton);
	private JButton rightButton = new JButton(RightbasicButton);
	private JButton leftButton = new JButton(LeftbasicButton);

	private JButton easyButton = new JButton(easybasicButton);
	private JButton normalButton = new JButton(normalbasicButton);
	private JButton hardButton = new JButton(hardbasicButton);

	private JButton stopButton = new JButton(StopButton);
	private JButton restartButton = new JButton(reStartButton);
	private static JButton endButton = new JButton(EndButton);
	private static JButton backButton = new JButton(backbasicButton);
	private static JButton Homemain = new JButton(main);
	private int mouseX, mouseY;
	private Music introMusic = new Music("intromusic.mp3", true);

	private boolean isMainScreen = false;
	static boolean isGameScreen = false; // private
	static boolean isEndScreen = false;
	static boolean isMultiScreen = false;

	private JButton up = new JButton("UP");
	private JButton down = new JButton("DOWN");

	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private int nowSelected = 0;

	private JPanel panelMain;
	private String name;
	private Socket socket;
	public static Game game;
	public static Multigame multi;
	
	public RhythmGame(String ID, Socket socket) {
		trackList.add(new Track("We Are name.png", "We are(intro).png", "We are main.png", "We Are Cut.mp3",
				"We Are.mp3", "We Are - Jo Cohen & Sex Whales"));
		trackList.add(new Track("Cradles.png", "Cradles (intro).png", "Cradles main.png", "Cradles Cut.mp3",
				"Cradles.mp3", "Cradles - Sub Urban"));
		trackList.add(new Track("Light It Up.png", "Light It Up(intro).png", "Light It Up main.png",
				"Light It Up Cut.mp3", "Light It Up.mp3", "Light It Up - RoBin Hustin x TobiMorrow"));
		trackList.add(new Track("FlashLight name.png", "FlashLight(intro).jpg", "FlashLight main.png",
				"FlashLight Cut.mp3", "FlashLight.mp3", "FlashLight - DJ Fresh (feat. Ellie Goulding)"));

		this.socket = socket;
		this.name = ID;
		setUndecorated(true);
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		addKeyListener(new KeyListener());
		introMusic.start();

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);

		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Boom.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(30, 520, 250, 70);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);

		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startEnteredButton);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startbasicButton);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				enterMain();
				// ���ӽ����̺�Ʈ
			}
		});
		add(startButton);

		quitButton.setBounds(30, 600, 250, 70);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);

		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(exitEnteredButton);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(exitbasicButton);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("No.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);

		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(LeftEnteredButton);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(LeftbasicButton);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				selectLeft();//
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);

		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(RightEnteredButton);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(RightbasicButton);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				selectRight();//
			}
		});
		add(rightButton);

		easyButton.setVisible(false);
		easyButton.setBounds(600, 580, 250, 50);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);

		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyEnteredButton);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easybasicButton);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);

		normalButton.setVisible(false);
		normalButton.setBounds(750, 580, 250, 50);
		normalButton.setBorderPainted(false);
		normalButton.setContentAreaFilled(false);
		normalButton.setFocusPainted(false);

		normalButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				normalButton.setIcon(normalEnteredButton);
				normalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				normalButton.setIcon(normalbasicButton);
				normalButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Normal");
			}
		});
		add(normalButton);

		hardButton.setVisible(false);
		hardButton.setBounds(925, 580, 250, 50);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);

		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardEnteredButton);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardbasicButton);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 30);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);

		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backEnteredButton);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backbasicButton);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				BackMain();
			}
		});
		add(backButton);

		Homemain.setVisible(false);
		Homemain.setBounds(20, 640, 100, 60);
		Homemain.setBorderPainted(false);
		Homemain.setContentAreaFilled(false);
		Homemain.setFocusPainted(false);

		Homemain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Homemain.setIcon(main2);
				Homemain.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				Homemain.setIcon(main);
				Homemain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				HomeMain();
			}
		});
		add(Homemain);

		endButton.setVisible(false);
		endButton.setBounds(20, 100, 60, 30);
		endButton.setBorderPainted(false);
		endButton.setContentAreaFilled(false);
		endButton.setFocusPainted(false);

		endButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				endButton.setIcon(EndButton);
				endButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				endButton.setIcon(EndButton);
				endButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				gameEnd();
			}
		});
		add(endButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		multiplayButton.setBounds(900, 520, 300, 100);
		multiplayButton.setBorderPainted(false);
		multiplayButton.setContentAreaFilled(false);
		multiplayButton.setFocusPainted(false);
		multiplayButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				multiplayButton.setIcon(multiplayEnteredButton);
				multiplayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				multiplayButton.setIcon(multiplaybasicButton);
				multiplayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				gameMulti();
			}

		});
		add(multiplayButton);
		up.setFont(new Font("함초롱돋음", Font.BOLD, 10));
		up.setForeground(Color.WHITE);
		up.setBounds(0, 130, 100, 30);
		up.setBorderPainted(false);
		up.setContentAreaFilled(false);
		up.setFocusPainted(false);
		up.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				up.setForeground(Color.LIGHT_GRAY);
			}
			public void mouseExited(MouseEvent e) {
				up.setForeground(Color.WHITE);
			}
			public void mousePressed(MouseEvent e) {
				setvolume.volume += 0.01;
				try {
					setvolume.setvolume(setvolume.volume);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println("볼륨 업");
			}
		});
		add(up);
		down.setFont(new Font("함초롱돋음", Font.BOLD, 10));
		down.setForeground(Color.WHITE);
		down.setBounds(0, 160, 100, 30);
		down.setBorderPainted(false);
		down.setContentAreaFilled(false);
		down.setFocusPainted(false);
		down.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				down.setForeground(Color.LIGHT_GRAY);
			}
			public void mouseExited(MouseEvent e) {
				down.setForeground(Color.WHITE);
			}
			public void mousePressed(MouseEvent e) {
				setvolume.volume -= 0.01;
				try {
					setvolume.setvolume(setvolume.volume);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println("볼륨 다운");
			}
		});
		add(down);

		up.setVisible(true);
		down.setVisible(true);
	}

	// multiplayButton
	// multiplayEnteredButton
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if (isMainScreen) {
			g.drawImage(selectedImage, 320, 150, null);
			g.drawImage(titleImage, 320, 100, null);
		}
		if (isGameScreen) {
			game.screenDraw(g);
		}
		if (isEndScreen) {
			EndGame(g);
		}
		if (isMultiScreen) {
			Multigame.screenDraw(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();//
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);//
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);//
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		normalButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(true);
		endButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);
		requestFocus();
		game.score = 0;
		game.Combo = 0;
	}

	public void BackMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		normalButton.setVisible(true);
		hardButton.setVisible(true);
		backButton.setVisible(false);
		endButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/background.jpg")).getImage();
		selectTrack(nowSelected);
		isGameScreen = false;

		game.close();
		game.score = 0;
		game.Combo = 0;
		for (int i = 0; i < 6; i++) {
			Note.combo[i] = 0;
		}
	}

	public void HomeMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		normalButton.setVisible(true);
		hardButton.setVisible(true);
		backButton.setVisible(false);
		endButton.setVisible(false);
		Homemain.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/background.jpg")).getImage();
		selectTrack(nowSelected);
		isEndScreen = false;
		isMultiScreen = false;
		Note.MaxCombo = 0;
		for (int i = 0; i < 6; i++) {
			Note.combo[i] = 0;
		}
		database.xox = 0;
	}

	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/background.jpg")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		normalButton.setVisible(true);
		hardButton.setVisible(true);
		endButton.setVisible(false);
		multiplayButton.setVisible(false);
		introMusic.close();
		selectTrack(0);
		game.score = 0;
		game.Combo = 0;
	}

	public static void gameEnd() {
		isGameScreen = false;
		backButton.setVisible(false);
		Homemain.setVisible(true);
		game.close();
		endButton.setVisible(false);
		isEndScreen = true;
	}

	public void EndGame(Graphics2D g) {
		background = new ImageIcon(Main.class.getResource("../images/Endbackground.jpg")).getImage();
		g.drawImage(background, 0, 0, null);
		g.drawImage(scoreBase, 100, 150, null);
		g.drawImage(gameendInfo, 0, 0, null);
		g.drawImage(scoreBaseLineImage, 100, 225, null);
		g.drawImage(scoreBaseLineImage, 100, 459, null);

		g.drawImage(SCOREImage, 25, 150, null);
		g.drawImage(COMBOImage, 25, 465, null);

		g.drawImage(PerfectImage, 0, 220, null);
		g.drawImage(GreatImage, 290, 220, null);
		g.drawImage(GoodImage, -20, 300, null);
		g.drawImage(EarlyImage, 290, 300, null);
		g.drawImage(LateImage, -30, 380, null);
		g.drawImage(MissImage, 290, 380, null);

		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("" + Note.combo[0], 280, 278);
		g.drawString("" + Note.combo[1], 550, 278);
		g.drawString("" + Note.combo[2], 280, 358);
		g.drawString("" + Note.combo[3], 550, 358);
		g.drawString("" + Note.combo[4], 280, 438);
		g.drawString("" + Note.combo[5], 550, 438);
		g.drawString("" + Game.score, 450, 200);
		g.drawString("" + Note.MaxCombo, 450, 515);

		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString(trackList.get(nowSelected).getTitleName(), 20, 90);
		g.drawString(game.difficulty, 1150, 90);

		if (database.xox == 0)
			database.insertScore(trackList.get(nowSelected).getTitleName(), game.difficulty, Game.score, Note.MaxCombo,
					Note.combo[0], Note.combo[1], Note.combo[2], Note.combo[3], Note.combo[4], Note.combo[5],name);

		g.setFont(new Font("궁서체", Font.PLAIN, 80));
		g.setColor(Color.WHITE);
		g.drawString("게임 결과", 900, 200);

		// getTitleName()

		// String name,String difficulty,int score,int combo,int perfect,int great,int
		// good,int early,int late,int miss
	}

	public void gameMulti() {
		introMusic.close();
		background = new ImageIcon(Main.class.getResource("../images/multibasic.jpg")).getImage();
		startButton.setVisible(false);
		quitButton.setVisible(false);
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		backButton.setVisible(false);
		Homemain.setVisible(false);
		endButton.setVisible(false);

		isMultiScreen = true;
		multiplayButton.setVisible(false);
		MainChat p = new MainChat(socket, name,this);
		p.setBounds(0, 0, 1280, 720);
		add(p);
		multi = new Multigame(name, socket);
	}
}
