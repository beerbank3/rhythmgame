package rhythm_game_1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

public class KeyListener2 extends KeyAdapter {
	Socket socket;
	String ID;
	BufferedReader in;
	BufferedWriter out;
	Game2 game;
	int i=0;
	public KeyListener2(Socket socket,String ID) {
		this.socket=socket;
		this.ID = ID;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			// in: 서버메시지 읽기객체 서버-----msg------>클라이언트
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendMsg(String msg) {// 서버에게 메시지 보내기
		try {
			out.write((msg + "\n"));
			out.newLine();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// sendMsg
	public void comboevent() {
		sendMsg("903|"+ID);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (ChatClient.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			ChatClient.game.PressS();
			sendMsg("801|"+ID);
			sendMsg("888|"+ID+"#"+game.score+"#"+game.Combo);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			ChatClient.game.PressD();
			sendMsg("802|"+ID);
			sendMsg("888|"+ID+"#"+game.score+"#"+game.Combo);
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			ChatClient.game.PressF();
			sendMsg("803|"+ID);
			sendMsg("888|"+ID+"#"+game.score+"#"+game.Combo);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			ChatClient.game.PressSpace();
			sendMsg("804|"+ID);
			sendMsg("888|"+ID+"#"+game.score+"#"+game.Combo);
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			ChatClient.game.PressJ();
			sendMsg("805|"+ID);
			sendMsg("888|"+ID+"#"+game.score+"#"+game.Combo);
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			ChatClient.game.PressK();
			sendMsg("806|"+ID);
			sendMsg("888|"+ID+"#"+game.score+"#"+game.Combo);
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			ChatClient.game.PressL();
			sendMsg("807|"+ID);
			sendMsg("888|"+ID+"#"+game.score+"#"+game.Combo);
		}else if(e.getKeyCode() == KeyEvent.VK_U && game.comboevent>0) {
			sendMsg("900|"+ID);
		}else if(e.getKeyCode() == KeyEvent.VK_I && game.imageevent>0) {
			sendMsg("901|"+ID);
		}else if(e.getKeyCode() == KeyEvent.VK_O && game.scoreevent>0) {
			sendMsg("902|"+ID);
		}
		if(i==0) {
			i++;
		Timer Timer = new Timer();
		TimerTask Task = new TimerTask(){
			@Override
			public void run() {
				sendMsg("888|"+ID+"#"+game.score+"#"+game.Combo);
			}
		};
		Timer.schedule(Task, 3000,2000);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (ChatClient.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			ChatClient.game.releaseS();
			sendMsg("811|"+ID);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			ChatClient.game.releaseD();
			sendMsg("812|"+ID);
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			ChatClient.game.releaseF();
			sendMsg("813|"+ID);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			ChatClient.game.releaseSpace();
			sendMsg("814|"+ID);
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			ChatClient.game.releaseJ();
			sendMsg("815|"+ID);
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			ChatClient.game.releaseK();
			sendMsg("816|"+ID);
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			ChatClient.game.releaseL();
			sendMsg("817|"+ID);
		}
	}
	

}
