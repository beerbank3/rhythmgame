package rhythm_game_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class StartServer implements Runnable {
	public static final int PORT = 8080;
	Vector<ChatProcess> allV;//모든 사용자(대기실사용자 + 대화방사용자)
	Vector<ChatProcess> waitV;//대기실 사용자	   
	Vector<Room> roomV;//개설된 대화방 Room-vs(Vector) : 대화방사용자
	Vector<ChatProcess> waituser;
	Vector<Room> Roomuser;
	public StartServer() {
		allV = new Vector<>();
		waitV = new Vector<>();
		roomV = new Vector<>();
		new Thread(this).start();
	}
	@Override
	public void run() {
		try {
			// 1. 서버 소켓 생성
			ServerSocket serverSocket = new ServerSocket();
			// 2. 바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			consoleLog("연결 기다림 - " + hostAddress + ":" + PORT);

			// 3. 요청 대기
			while (true) {
				Socket socket = serverSocket.accept();
				ChatProcess ser = new ChatProcess(socket,this);
				//new ChatServerProcessThread(socket, this).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new StartServer();
	}
	private static void consoleLog(String log) {
		System.out.println("[server " + Thread.currentThread().getId() + "] " + log);
	}
}