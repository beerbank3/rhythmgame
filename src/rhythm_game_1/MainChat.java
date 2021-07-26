package rhythm_game_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainChat extends JPanel implements ActionListener, Runnable {
	JList<String> roomInfo, roomInwon, waitInfo;
	JScrollPane sp_roomInfo, sp_roomInwon, sp_waitInfo;
	JButton bt_create, bt_enter, bt_exit;
	ChatClient cc;
	// 소켓 입출력객체
	BufferedReader in;
	BufferedWriter out;
	String selectedRoom;
	RhythmGame RG;
	ChatProcess CP;
	static int usercount = 0;
	static int roomcount = 0;
	int nowSelected = 0;
	String roomsize = "0";

	public MainChat(Socket socket, String ID, RhythmGame RG) {
		this.RG = RG;
		cc = new ChatClient(socket, ID);
		cc.setVisible(false);
		roomInfo = new JList<String>();
		roomInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = roomInfo.getSelectedValue(); // "자바방--1"
				if (str == null)
					return;
				if (roomsize != "0") {
					System.out.println("STR=" + str);
					selectedRoom = str.substring(0, str.indexOf("-"));
					// "자바방" <---- substring(0,3)
					// 대화방 내의 인원정보
					sendMsg("170|" + selectedRoom);
				}
			}
		});
		setLayout(null);
		roomInwon = new JList<String>();
		waitInfo = new JList<String>();
		sp_roomInfo = new JScrollPane(roomInfo);
		sp_roomInwon = new JScrollPane(roomInwon);
		sp_waitInfo = new JScrollPane(waitInfo);
		bt_create = new JButton("방만들기");
		bt_enter = new JButton("방들어가기");
		bt_exit = new JButton("나가기");

		sp_roomInfo.setBorder(null);
		sp_roomInwon.setBorder(null);// 필요없음
		sp_waitInfo.setBorder(null);
		bt_create.setBorder(null);
		bt_enter.setBorder(null);
		bt_exit.setBorder(null);

		roomInfo.setFont(new Font("Arial", Font.BOLD, 30));
		waitInfo.setFont(new Font("Arial", Font.BOLD, 17));
		sp_roomInfo.setBounds(100, 150, 850, 250);
		sp_roomInwon.setBounds(1150, 150, 250, 620);// 필요없음
		sp_waitInfo.setBounds(1050, 100, 250, 620);
		bt_create.setBounds(300, 40, 100, 50);
		bt_enter.setBounds(400, 40, 100, 50);
		bt_exit.setBounds(900, 40, 100, 50);

		roomInfo.setBackground(new Color(255, 0, 0, 0));
		roomInfo.setForeground(Color.WHITE);
		sp_roomInfo.setBackground(new Color(255, 0, 0, 0));

		roomInwon.setBackground(new Color(255, 0, 0, 0));// 인원정보
		roomInwon.setForeground(Color.WHITE);
		sp_roomInwon.setBackground(new Color(255, 0, 0, 0));

		waitInfo.setBackground(new Color(255, 0, 0, 0));// 대기실정보
		sp_waitInfo.setBackground(new Color(255, 0, 0, 0));
		waitInfo.setForeground(Color.WHITE);

		bt_create.setBackground(new Color(255, 0, 0, 0));
		bt_create.setForeground(Color.WHITE);
		bt_enter.setBackground(new Color(255, 0, 0, 0));
		bt_enter.setForeground(Color.WHITE);
		bt_exit.setBackground(new Color(255, 0, 0, 0));
		bt_exit.setForeground(Color.WHITE);

		setBackground(Color.orange);
		add(sp_roomInfo);
		// add(sp_roomInwon);
		add(sp_waitInfo);
		add(bt_create);
		add(bt_enter);
		add(bt_exit);
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 0, 1280, 720);
		setVisible(true);
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			// in: 서버메시지 읽기객체 서버-----msg------>클라이언트
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();// 서버메시지 대기
		sendMsg("100|");// (대기실)접속 알림
		sendMsg("150|" + ID);// 대화명 전달
		eventUp();
	}// 생성자

	private void eventUp() {// 이벤트소스-이벤트처리부 연결
		// 대기실(MainChat)
		bt_create.addActionListener(this);
		bt_enter.addActionListener(this);
		bt_exit.addActionListener(this);
		// 대화방(ChatClient)
		cc.sendTF.addActionListener(this);
		cc.bt_exit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == bt_create) {// 방만들기 요청
			String title = JOptionPane.showInputDialog(this, "방제목:");
			if (title == null)
				return;
			// 방제목을 서버에게 전달
			sendMsg("160|" + title);
			cc.RoomTitle.setText(title);
			sendMsg("175|");// 대화방내 인원정보 요청
			RG.setVisible(false);
			cc.setVisible(true); // 대화방이동
			cc.selectTrack(cc.nowSelected);
		} else if (ob == bt_enter) {// 방들어가기 요청
			if (selectedRoom == null) {
				JOptionPane.showMessageDialog(this, "방을 선택!!");
				return;
			}
			sendMsg("200|" + selectedRoom);
			sendMsg("175|");// 대화방내 인원정보 요청
			RG.setVisible(false);
			cc.setVisible(true);
		} else if (ob == cc.bt_exit) {// 대화방 나가기 요청
			sendMsg("400|");
			cc.setVisible(false);
			RG.setVisible(true);
			cc.selectedMusic.close();
		} else if (ob == cc.sendTF) {// (TextField입력)메시지 보내기 요청
			String msg = cc.sendTF.getText();
			if (msg.length() > 0) {
				sendMsg("300|" + msg);
				cc.sendTF.setText("");
			}
		} else if (ob == bt_exit) {// 나가기(프로그램종료) 요청
			System.exit(0);// 현재 응용프로그램 종료하기
		}
	}// actionPerformed

	public void sendMsg(String msg) {// 서버에게 메시지 보내기
		try {
			out.write((msg + "\n"));
			out.newLine();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// sendMsg

	public void run() {// 서버가 보낸 메시지 읽기
		// 왜 run메소드 사용? GUI프로그램실행에 영향 미치지않는 코드 작성.
//메소드호출은 순차적인 실행!!  스레드메소드는 동시실행(기다리지 않는 별도 실행)!!
		try {
			while (true) {
				String msg = in.readLine();// msg: 서버가 보낸 메시지
				// msg==> "300|안녕하세요" "160|자바방--1,오라클방--1,JDBC방--1"
				String msgs[] = msg.split("\\|");
				String protocol = msgs[0];
				switch (protocol) {
				case "300":
					cc.ta.append(msgs[1] + "\n");
					cc.ta.setCaretPosition(cc.ta.getText().length());
					break;
				case "160":// 방만들기
					// 방정보를 List에 뿌리
					if (msgs.length > 1) {
						// 개설된 방이 한개 이상이었을때 실행
						// 개설된 방없음 ----> msg="160|" 였을때 에러
						String[] roomNames = msgs[1].split(",");
						// "자바방--1,오라클방--1,JDBC방--1"
						roomInfo.setListData(roomNames);
					}
					if (msgs.length == 1) {
						String[] roomNames = { "No Room" };
						roomInfo.setListData(roomNames);
					}
					break;
				case "170":// (대기실에서) 대화방 인원정보
					String roomInwons[] = msgs[1].split(",");
					roomInwon.setListData(roomInwons);
					break;
				case "175":// (대화방에서) 대화방 인원정보
					String myRoomInwons[] = msgs[1].split(",");
					cc.li_inwon.setListData(myRoomInwons);
					break;
				case "180":// 대기실 인원정보
					String waitNames[] = msgs[1].split(",");
					usercount = waitNames.length;
					waitInfo.setListData(waitNames);
					break;
				case "200":// 대화방 입장
					cc.ta.append("=========[" + msgs[1] + "]님 입장=========\n");
					cc.ta.setCaretPosition(cc.ta.getText().length());
					cc.selectTrack(0);
					cc.nowSelected = 0;
					break;
				case "201":// gameroom
					cc.selectTrack(0);
					cc.nowSelected = 0;
					break;
				case "400":// 대화방 퇴장
					cc.ta.append("=========[" + msgs[1] + "]님 퇴장=========\n");
					cc.ta.setCaretPosition(cc.ta.getText().length());
					break;
				case "202":// 개설된 방의 타이틀 제목 얻기
					cc.RoomTitle.setText(msgs[1]);
					break;
				case "501":// 왼쪽으로 이동
					cc.selectLeft();
					cc.titleName.setText(cc.trackList.get(cc.nowSelected).getTitleName());
					break;
				case "502":// 오른쪽으로 이동
					cc.selectRight();
					cc.titleName.setText(cc.trackList.get(cc.nowSelected).getTitleName());
					break;
				case "510":// 이지모드
					cc.mode.setText("Easy");
					break;
				case "511":// 노멀모드
					cc.mode.setText("Normal");
					break;
				case "512":// 하드모드
					cc.mode.setText("Hard");
					break;
				case "600":// 게임시작
					if(cc.gameready2==1) {
					String game[] = msgs[1].split("#");
					cc.gameStart(game[0], game[1], game[2]);
					}
					break;
				case "601":// 게임준비
					if (cc.gameready2 == 0)
						cc.gameready2 = 1;
					else
						cc.gameready2 = 0;
					break;
				case "602":// 게임준비 초기화
					cc.gameready2 = 0;
					break;
				case "111":// 방 갯수 알려주기
					roomsize = msgs[1];
					break;
				case "120":// 방 주인 알려주기
					cc.player1 = msgs[1];
					break;
				case "121":// 플레이어 알려주기
					cc.player2 = msgs[1];
					break;
				case "801":// 키보드 s
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.PressS2();
					break;
				case "802":// 키보드 d
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.PressD2();
					break;
				case "803":// 키보드 f
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.PressF2();
					break;
				case "804":// 키보드 bar
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.PressSpace2();
					break;
				case "805":// 키보드 j
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.PressJ2();
					break;
				case "806":// 키보드 k
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.PressK2();
					break;
				case "807":// 키보드 l
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.PressL2();
					break;
				case "811":// 키보드 s
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.releaseS2();
					break;
				case "812":// 키보드 d
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.releaseD2();
					break;
				case "813":// 키보드 f
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.releaseF2();
					break;
				case "814":// 키보드 bar
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.releaseSpace2();
					break;
				case "815":// 키보드 j
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.releaseJ2();
					break;
				case "816":// 키보드 k
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.releaseK2();
					break;
				case "817":// 키보드 l
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.releaseL2();
					break;
				case "888":// 점수 실시간 공유
					String gamescore[] = msgs[1].split("#");
					if (!gamescore[0].equals(cc.ID)) {
						ChatClient.game.score2 = Integer.parseInt(gamescore[1]);
						ChatClient.game.Combo2 = Integer.parseInt(gamescore[2]);
					}
					break;
				case "900":// 키보드 U speedevent
					if (msgs[1].equals(cc.ID))
						ChatClient.game.comboevent();
					break;
				case "901":// 키보드 I imageevent
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.imageevent();
					else
						ChatClient.game.imageevent2();
					break;
				case "902":// 키보드 O scorevent
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.scoreevent();
					else
						ChatClient.game.scoreevent2();
					break;
				case "903"://comboevent
					if (!msgs[1].equals(cc.ID))
						ChatClient.game.Combo--;
					else
						ChatClient.game.Combo2--;
					break;
				case "1000": // 게임결과 화면창
					ChatClient.gameEnd();
					break;
				}// 클라이언트 switch
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// run
}
