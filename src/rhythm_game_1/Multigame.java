package rhythm_game_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JList;

public class Multigame {
	private Image screenImage;
	private Graphics screenGraphic;

	private static Image userinfobase = new ImageIcon(Main.class.getResource("../images/userinfo.png")).getImage();
	private static Image userlist = new ImageIcon(Main.class.getResource("../images/userlist.png")).getImage();
	private static Image gamemenu = new ImageIcon(Main.class.getResource("../images/gamemenu.png")).getImage();
	private static Image multiname = new ImageIcon(Main.class.getResource("../images/multiname.png")).getImage();
	private static Image userOn = new ImageIcon(Main.class.getResource("../images/userOn.png")).getImage();
	private static Image roomlistbase = new ImageIcon(Main.class.getResource("../images/roomlistbase.png")).getImage();
	private static Image roombase = new ImageIcon(Main.class.getResource("../images/roombase.png")).getImage();
	private Socket socket;
	static String name;
	JList<String> waituserlist;
	Multigame(String name, Socket socket) {
		this.socket = socket;
		this.name = name;
	}

	public static void screenDraw(Graphics2D g) {
		int usercount =MainChat.usercount;
		int roomcount =MainChat.roomcount;
		g.drawImage(userinfobase, 1030, 0, null);
		g.drawImage(userlist, 1030, 0, null);
		g.drawImage(gamemenu, 0, 0, null);
		g.drawImage(multiname, 20, 35, null);
		g.drawImage(roomlistbase, 100, 150, null);
		
		for(int i=0;i<usercount;i++)
			g.drawImage(userOn, 1038,110+(i*23), null);

		for(int i=0;i<roomcount;i++)
			g.drawImage(roombase, 100, 150+(i*38), null);
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(name, 1100, 80);

		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Arial", Font.BOLD, 15));
	}

	public static void setUser(ArrayList<String> user) {
		user = user;
	}
}
