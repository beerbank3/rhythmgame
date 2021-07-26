package rhythm_game_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	private Image screenImage;
	private Graphics screenGraphic;

	public Image background = new ImageIcon(Main.class.getResource("../images/Login.gif")).getImage();
	public Image loginBase = new ImageIcon(Main.class.getResource("../images/loginbasic.png")).getImage();
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/Button.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Button2.png"));

	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton loginButton=new JButton("LOGIN");
	private JButton signupButton=new JButton("SIGNUP");
	private JLabel user=new JLabel("ID"), Password =new JLabel("Password");
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private Socket socket;
	
	
	public Login(Socket socket) {
		this.socket=socket;
		setUndecorated(true);
		setTitle("Rhythm Game-Login");
		setSize(400, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		user.setFont(new Font("함초롱돋음",Font.BOLD,40));
		Password.setFont(new Font("함초롱돋음",Font.BOLD,25));
		user.setBounds(80, 170, 150, 40);
		Password.setBounds(80, 240, 150, 40);
		username.setBounds(230, 175, 100, 25);
		password.setBounds(230, 245, 100, 25);
		username.setOpaque(false);
		password.setOpaque(false);
		add(user);
		add(Password);
		add(username);
		add(password);
		
		exitButton.setBounds(365, 0, 30, 30);
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
		
		loginButton.setFont(new Font("함초롱돋음",Font.BOLD,70));
		loginButton.setBounds(77, 350, 255, 70);
	    loginButton.setBorderPainted(false);
	    loginButton.setContentAreaFilled(false);
	    loginButton.setFocusPainted(false);
	    loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//loginButton.setIcon(hardEnteredButton);
				loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				//loginButton.setIcon(hardbasicButton);
				loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				String ID=username.getText();
				String PW=String.valueOf(password.getPassword());
				dispose();
				new RhythmGame(ID,socket);
				/*if(ID.equals("root")&&PW.equals("root")) {
				dispose();
				new RhythmGame(ID,socket);
				}*/
			}
		});
	    add(loginButton);

	    signupButton.setFont(new Font("함초롱돋음",Font.BOLD,20));
	    signupButton.setBounds(240, 470, 120, 20);
	    signupButton.setBorderPainted(false);
	    signupButton.setContentAreaFilled(false);
	    signupButton.setFocusPainted(false);
	    signupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//loginButton.setIcon(hardEnteredButton);
				signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				//loginButton.setIcon(hardbasicButton);
				signupButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				dispose();
				new Signup(socket);
				database.xox = 0;
			}
		});
	    add(signupButton);
	}

    public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
    public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		g.drawImage(loginBase, 50, 100, null);
		paintComponents(g);
		this.repaint();
    }
}
