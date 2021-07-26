package rhythm_game_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;

	private Image GreatImage = new ImageIcon(Main.class.getResource("../images/Great.png")).getImage();
	private Image MissImage = new ImageIcon(Main.class.getResource("../images/Miss.png")).getImage();
	private Image LateImage = new ImageIcon(Main.class.getResource("../images/Late.png")).getImage();
	private Image GoodImage = new ImageIcon(Main.class.getResource("../images/Good.png")).getImage();
	private Image PerfectImage = new ImageIcon(Main.class.getResource("../images/Perfect.png")).getImage();
	private Image EarlyImage = new ImageIcon(Main.class.getResource("../images/Early.png")).getImage();
	private Image SCOREImage = new ImageIcon(Main.class.getResource("../images/SCORE.png")).getImage();
	private Image COMBOImage = new ImageIcon(Main.class.getResource("../images/COMBO.png")).getImage();
	private Image scoreBaseLineImage = new ImageIcon(Main.class.getResource("../images/scoreBaseLine.png")).getImage();

	public static Image gameendInfo = new ImageIcon(Main.class.getResource("../images/gameendInfo.png")).getImage();
	public static Image scoreBase = new ImageIcon(Main.class.getResource("../images/scoreBase.png")).getImage();

	public Image background = new ImageIcon(Main.class.getResource("../images/Rhythm.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/Button.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Button2.png"));

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

	private ImageIcon gamestartbasicButton = new ImageIcon(Main.class.getResource("../images/gamestart.png"));
	private ImageIcon gamestartEnteredButton = new ImageIcon(Main.class.getResource("../images/gamestart2.png"));
	private ImageIcon gamereadybasicButton = new ImageIcon(Main.class.getResource("../images/gameready.png"));
	private ImageIcon gamereadyEnteredButton = new ImageIcon(Main.class.getResource("../images/gameready1.png"));

	private Image gamereadybasic1image = new ImageIcon(Main.class.getResource("../images/gameready22.png")).getImage();
	private Image gamereadyEntered11image = new ImageIcon(Main.class.getResource("../images/gameready2.png"))
			.getImage();

	private Image player1image = new ImageIcon(Main.class.getResource("../images/player1.png")).getImage();
	private Image player2image = new ImageIcon(Main.class.getResource("../images/player2.png")).getImage();

	private JButton rightButton = new JButton(RightbasicButton);
	private JButton leftButton = new JButton(LeftbasicButton);

	private JButton easyButton = new JButton(easybasicButton);
	private JButton normalButton = new JButton(normalbasicButton);
	private JButton hardButton = new JButton(hardbasicButton);

	private JButton gamestartButton = new JButton(gamestartbasicButton);
	private JButton gamereadyButton = new JButton(gamereadybasicButton);
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private int mouseX, mouseY;

	private boolean MainScreen = true;
	static boolean GameScreen = false;
	static boolean EndScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Image selectedImage;
	Music selectedMusic;
	static int nowSelected = 0;
	int gameready1, gameready2;
	public static Game2 game;
	String gameset = "nothing";
	// 채팅방
	String player1 = "", player2 = "";
	JTextField sendTF;
	JLabel la_msg;
	JLabel RoomTitle;
	JLabel titleName;
	JLabel mode;
	JTextArea ta;
	JScrollPane sp_ta, sp_list;
	JList<String> li_inwon;
	JButton bt_exit, gameroom;
	BufferedReader in;
	BufferedWriter out;
	String ID, TitleNAME;

	public ChatClient(Socket socket, String ID) {
		trackList.add(new Track("We Are name.png", "We are(intro).png", "We are main.png", "We Are Cut.mp3",
				"We Are.mp3", "We Are - Jo Cohen & Sex Whales"));
		trackList.add(new Track("Cradles.png", "Cradles (intro).png", "Cradles main.png", "Cradles Cut.mp3",
				"Cradles.mp3", "Cradles - Sub Urban"));
		trackList.add(new Track("Light It Up.png", "Light It Up(intro).png", "Light It Up main.png",
				"Light It Up Cut.mp3", "Light It Up.mp3", "Light It Up - RoBin Hustin x TobiMorrow"));
		trackList.add(new Track("FlashLight name.png", "FlashLight(intro).jpg", "FlashLight main.png",
				"FlashLight Cut.mp3", "FlashLight.mp3", "FlashLight - DJ Fresh (feat. Ellie Goulding)"));

		this.ID = ID;
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		// setBackground(new Color(0, 0, 0, 0));
		sendTF = new JTextField(15);
		la_msg = new JLabel("Message");
		la_msg.setForeground(Color.WHITE);
		ta = new JTextArea();
		ta.setLineWrap(true);// TextArea 가로길이를 벗어나는 text발생시 자동 줄바꿈
		li_inwon = new JList<String>();
		sp_ta = new JScrollPane(ta);// 메세지창
		sp_list = new JScrollPane(li_inwon);// 인원리스트
		bt_exit = new JButton("나가기");
		gameroom = new JButton("방으로");
		RoomTitle = new JLabel("방 제목");
		titleName = new JLabel(trackList.get(nowSelected).getTitleName());
		mode = new JLabel("난이도");
		RoomTitle.setForeground(Color.WHITE);
		titleName.setForeground(Color.WHITE);
		mode.setForeground(Color.WHITE);
		RoomTitle.setFont(new Font("궁서체", Font.BOLD, 20));
		titleName.setFont(new Font("궁서체", Font.BOLD, 15));
		mode.setFont(new Font("궁서체", Font.BOLD, 20));
		li_inwon.setFont(new Font("궁서체", Font.BOLD, 30));

		addKeyListener(new KeyListener2(socket, ID));

		sp_list.setBorder(null);
		li_inwon.setBackground(new Color(255, 0, 0, 0));
		li_inwon.setForeground(Color.WHITE);
		sp_list.setBackground(new Color(255, 0, 0, 0));

		RoomTitle.setBounds(10, 60, 150, 40);
		titleName.setBounds(10, 100, 300, 40);
		mode.setBounds(10, 140, 60, 30);
		sp_ta.setBounds(10, 375, 290, 290);
		la_msg.setBounds(10, 670, 60, 30);
		sendTF.setBounds(70, 670, 230, 30);
		sp_list.setBounds(1100, 60, 150, 77);// 인원
		bt_exit.setBounds(1140, 670, 120, 30); // 나가기 버튼
		add(sp_ta);
		add(la_msg);
		add(sendTF);
		add(RoomTitle);
		add(titleName);
		add(mode);
		add(sp_list);
		add(bt_exit);
		sp_ta.setVisible(true);
		la_msg.setVisible(true);
		sendTF.setVisible(true);
		bt_exit.setVisible(true);
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			// in: 서버메시지 읽기객체 서버-----msg------>클라이언트
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sendTF.requestFocus();
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
		leftButton.setBounds(320, 580, 60, 60);
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
				sendMsg("501|");
			}
		});

		rightButton.setBounds(400, 580, 60, 60);
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
				sendMsg("502|");
			}
		});

		easyButton.setBounds(500, 600, 150, 50);
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
				sendMsg("510|");
			}
		});

		normalButton.setBounds(650, 600, 150, 50);
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
				sendMsg("511|");
			}
		});

		hardButton.setBounds(825, 600, 150, 50);
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
				sendMsg("512|");
			}
		});
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
		gamestartButton.setBounds(975, 600, 200, 100);
		gamestartButton.setBorderPainted(false);
		gamestartButton.setContentAreaFilled(false);
		gamestartButton.setFocusPainted(false);
		gamestartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gamestartButton.setIcon(gamestartEnteredButton);
				gamestartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				gamestartButton.setIcon(gamestartbasicButton);
				gamestartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				String Musicname[] = titleName.getText().split(" -");
				sendMsg("600|" + titleName.getText() + "#" + mode.getText() + "#" + Musicname[0]);
			}
		});
		gamereadyButton.setBounds(975, 600, 200, 100);
		gamereadyButton.setBorderPainted(false);
		gamereadyButton.setContentAreaFilled(false);
		gamereadyButton.setFocusPainted(false);
		gamereadyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gamereadyButton.setIcon(gamereadyEnteredButton);
				gamereadyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				gamereadyButton.setIcon(gamereadybasicButton);
				gamereadyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				sendMsg("601|");
			}
		});
		gameroom.setBounds(1100, 670, 120, 30);
		gameroom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				gameroom();
			}
		});
		add(gameroom);
		gameroom.setVisible(false);
	}// 생성자

	public void sendMsg(String msg) {// 서버에게 메시지 보내기
		try {
			out.write((msg + "\n"));
			out.newLine();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// sendMsg

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if (MainScreen) {
			g.drawImage(selectedImage, 320, 150, null);
			g.drawImage(titleImage, 320, 100, null);
			g.drawImage(player1image, 990, 60, null);
			g.drawImage(player2image, 990, 98, null);
			if (gameready2 == 1)
				g.drawImage(gamereadyEntered11image, 1100, 98, null);
			else
				g.drawImage(gamereadybasic1image, 1100, 98, null);
		}
		if (!player1.equals("")) {
			if (player1.equals(ID)) {
				add(leftButton);
				add(rightButton);
				add(easyButton);
				add(normalButton);
				add(hardButton);
				add(gamestartButton);
				
			}
			else
				add(gamereadyButton);
		}
		if (GameScreen) {
			game.screenDraw(g);
		}
		if (EndScreen) {
			EndGame(g);
		}
		paintComponents(g);
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
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);//
	}

	public void gamecomboset() {
		Note2.combo[0] = 0;
		Note2.combo[1] = 0;
		Note2.combo[2] = 0;
		Note2.combo[3] = 0;
		Note2.combo[4] = 0;
		Note2.combo[5] = 0;
		Note2.MaxCombo = 0;
		game.score = 0;
		game.Combo = 0;
		game.score2 = 0;
		game.Combo2 = 0;
		game.imageevent = 0;
		game.scoreevent = 0;
	}

	public void gameStart(String titlename, String difficulty, String Musicname) {
		if (selectedMusic != null)
			selectedMusic.close();
		MainScreen = false;
		if (player1.equals(ID))
			gamestartButton.setVisible(false);
		else
			gamereadyButton.setVisible(false);
		sp_list.setVisible(false);
		sp_ta.setVisible(false);
		la_msg.setVisible(false);
		RoomTitle.setVisible(false);
		titleName.setVisible(false);
		mode.setVisible(false);
		sendTF.setVisible(false);
		bt_exit.setVisible(false);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		normalButton.setVisible(false);
		hardButton.setVisible(false);
		TitleNAME = titleName.getText();
		background = new ImageIcon(Main.class.getResource("../images/" + Musicname + " main.png")).getImage();
		game = new Game2(titlename, difficulty, Musicname + ".mp3");
		GameScreen = true;
		game.start();
		sendMsg("601|");
		setFocusable(true);
		requestFocus();
		gamecomboset();
	}

	public void gameroom() {
		EndScreen = false;
		background = new ImageIcon(Main.class.getResource("../images/Rhythm.png")).getImage();
		sendMsg("201|");
		if (player1.equals(ID))
			gamestartButton.setVisible(true);
		else
			gamereadyButton.setVisible(true);
		sp_list.setVisible(true);
		sp_ta.setVisible(true);
		la_msg.setVisible(true);
		RoomTitle.setVisible(true);
		titleName.setVisible(true);
		mode.setVisible(true);
		sendTF.setVisible(true);
		bt_exit.setVisible(true);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		normalButton.setVisible(true);
		hardButton.setVisible(true);
		gameroom.setVisible(false);
		MainScreen = true;
	}

	public static void gameEnd() {
		GameScreen = false;
		game.close();
		EndScreen = true;
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
		g.drawString("" + Note2.combo[0], 280, 278);
		g.drawString("" + Note2.combo[1], 550, 278);
		g.drawString("" + Note2.combo[2], 280, 358);
		g.drawString("" + Note2.combo[3], 550, 358);
		g.drawString("" + Note2.combo[4], 280, 438);
		g.drawString("" + Note2.combo[5], 550, 438);
		g.drawString("" + Game2.score, 450, 200);
		g.drawString("" + Note2.MaxCombo, 450, 515);

		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString(TitleNAME, 20, 90);
		g.drawString(game.difficulty, 1150, 90);
		if (game.score2 < game.score) {
			g.setFont(new Font("궁서체", Font.PLAIN, 70));
			g.setColor(Color.BLUE);
			g.drawString("WIN", 1000, 300);
		} else if (game.score2 > game.score) {
			g.setFont(new Font("궁서체", Font.PLAIN, 70));
			g.setColor(Color.RED);
			g.drawString("LOSE", 1000, 300);
		}

		if (database.xox == 0)
			database.insertScore(titleName.getText(), game.difficulty, Game2.score, Note2.MaxCombo, Note2.combo[0],
					Note2.combo[1], Note2.combo[2], Note2.combo[3], Note2.combo[4], Note2.combo[5], ID);

		g.setFont(new Font("궁서체", Font.PLAIN, 80));
		g.setColor(Color.WHITE);
		g.drawString("게임 결과", 900, 200);
		gameroom.setVisible(true);
	}
}