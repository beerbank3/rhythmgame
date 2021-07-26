package rhythm_game_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Signup extends JFrame{

	private Image screenImage;
	private Graphics screenGraphic;
	
	public Image signupBase = new ImageIcon(Main.class.getResource("../images/signupbase.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../images/signupbasic.jpg")).getImage();
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/Button.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Button2.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);
	
	private Socket socket;
	private JLabel user=new JLabel("아이디");
	private JLabel Password =new JLabel("비밀번호");
	private JLabel Password2 =new JLabel("비밀번호 확인");
	private JLabel nickname =new JLabel("닉네임");
	private JLabel E_mail =new JLabel("이메일");
	private JTextField username = new JTextField();
	private JTextField password = new JTextField();
	private JPasswordField password1 = new JPasswordField();
	private JTextField nick = new JTextField();
	private JTextField e_mail = new JTextField();
	private JPasswordField password2 = new JPasswordField();
	
	private JButton mainButton=new JButton("메인화면");
	private JButton signupButton=new JButton("SIGNUP");

	public Signup(Socket socket) {
		this.socket=socket;
		setUndecorated(true);
		setTitle("Rhythm Game-Signup");
		setSize(400, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		//폰트수정
		user.setFont(new Font("함초롱돋음",Font.BOLD,20));
		Password.setFont(new Font("함초롱돋음",Font.BOLD,20));
		Password2.setFont(new Font("함초롱돋음",Font.BOLD,20));
		nickname.setFont(new Font("함초롱돋음",Font.BOLD,20));
		E_mail.setFont(new Font("함초롱돋음",Font.BOLD,20));
		//폰트 색상
		user.setForeground(Color.WHITE);
		Password.setForeground(Color.WHITE);
		Password2.setForeground(Color.WHITE);
		nickname.setForeground(Color.WHITE);
		E_mail.setForeground(Color.WHITE);
		//위치선정
		user.setBounds(60, 120, 150, 40);
		Password.setBounds(60, 190, 150, 40);
		Password2.setBounds(60, 260, 165, 40);
		nickname.setBounds(60, 330, 150, 40);
		E_mail.setBounds(60, 400, 150, 40);
		username.setBounds(200, 120, 150, 30);
		password.setBounds(200, 190, 150, 30);
		password1.setBounds(200, 260, 150, 30);
		nick.setBounds(200, 330, 150, 30);
		e_mail.setBounds(200, 400, 150, 30);
		//텍스트 박스배경색 제거
		username.setOpaque(false);
		password.setOpaque(false);
		password1.setOpaque(false);
		nick.setOpaque(false);
		e_mail.setOpaque(false);
		//추가
		add(user);
		add(Password);
		add(Password2);
		add(nickname);
		add(E_mail);
		add(username);
		add(password);
		add(password1);
		add(nick);
		add(e_mail);
		
		//종료
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
		
		mainButton.setFont(new Font("함초롱돋음",Font.BOLD,20));
		mainButton.setBounds(30, 510, 120, 20);
		mainButton.setBorderPainted(false);
		mainButton.setContentAreaFilled(false);
		mainButton.setFocusPainted(false);
		mainButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//loginButton.setIcon(hardEnteredButton);
				mainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				//loginButton.setIcon(hardbasicButton);
				mainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Click.mp3", false);
				buttonEnteredMusic.start();
				dispose();
				new Login(socket);
			}
		});
	    add(mainButton);
	    
	    signupButton.setFont(new Font("함초롱돋음",Font.BOLD,30));
	    signupButton.setBounds(230, 500, 150, 30);
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
				String ID=username.getText();
				String PW=password.getText();
				String PW1=String.valueOf(password1.getPassword());
				String Nick=nick.getText();
				String E_mail=e_mail.getText();
				System.out.println("실패");
				if (database.xox == 0) {
				database.insertAccount(ID,PW,Nick,E_mail);
				System.out.println("정상작동");
				dispose();
				new Login(socket);
				}
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
		g.drawImage(signupBase, 25, 50, null);
		paintComponents(g);
    }
}
