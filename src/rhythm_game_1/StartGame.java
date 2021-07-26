package rhythm_game_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JFrame;

public class StartGame extends JFrame {
	public static final int Login_WIDTH = 1280;
	public static final int Login_HEIGHT = 720;
	private static final String SERVER_IP = "119.201.29.123";
    private static final int SERVER_PORT = 8080;
    static String name = null;
    private static Socket socket;
    public void setname(String name) {
    	this.name=name;
    }
    public static void main(String[] args) {
        try {
        	socket=new Socket();
            socket.connect( new InetSocketAddress(SERVER_IP, SERVER_PORT) );
            //consoleLog("채팅방에 입장하였습니다.");
            new Login(socket);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void consoleLog(String log) {
        System.out.println(log);
    }
}
