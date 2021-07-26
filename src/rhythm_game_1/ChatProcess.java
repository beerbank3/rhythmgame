package rhythm_game_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class ChatProcess extends Thread {
	Room myRoom;
	Room room;

	BufferedReader in;
	BufferedWriter out;
	Vector<ChatProcess> allV;// 모든 사용자(대기실사용자 + 대화방사용자)
	Vector<ChatProcess> waitV;// 대기실 사용자
	Vector<Room> roomV;// 개설된 대화방 Room-vs(Vector) : 대화방사용자
	Vector<ChatProcess> waituser;
	Socket s;
	String nickName;
	Vector<Room> Roomuser;

	public ChatProcess(Socket socket, StartServer server) {
		allV = server.allV;
		waitV = server.waitV;
		roomV = server.roomV;
		this.s = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				String msg = in.readLine();// 클라이언트의 모든 메시지를 받기
				if (msg == null)
					return; // 비정상적인 종료
				if (msg.trim().length() > 0) {
					System.out.println("from Client: " + msg + ":" + s.getInetAddress().getHostAddress());
					// 서버에서 상황을 모니터!!
					String msgs[] = msg.split("\\|");
					String protocol = msgs[0];
					switch (protocol) {
					case "100": // 대기실 접속
						allV.add(this);// 전체사용자에 등록
						waitV.add(this);// 대기실사용자에 등록
						messageWait("111|" + roomV.size());
						break;
					case "150": // 대화명 입력
						nickName = msgs[1];
						// 최초 대화명 입력했을때 대기실의 정보를 출력
						messageWait("160|" + getRoomInfo());
						messageWait("180|" + getWaitInwon());
						break;
					case "160": // 방만들기 (대화방 입장)
						myRoom = new Room();
						myRoom.title = msgs[1];// 방제목
						myRoom.count = 1;
						myRoom.boss = nickName;
						roomV.add(myRoom);
						// 대기실----> 대화방 이동!!
						waitV.remove(this);
						myRoom.userV.add(this);
						messageRoom("200|" + nickName);// 방인원에게 입장 알림
						messageRoom("120|" + nickName);
						// 대기실 사용자들에게 방정보를 출력
						// 예) 대화방명:JavaLove
						// -----> roomInfo(JList) : JavaLove--1
						messageWait("160|" + getRoomInfo());
						messageWait("180|" + getWaitInwon());
						messageWait("111|" + roomV.size());
						break;
					case "170": // (대기실에서) 대화방 인원정보
						if (roomV.size() != 0)
							messageTo("170|" + getRoomInwon(msgs[1]));
						break;
					case "175": // (대화방에서) 대화방 인원정보
						messageRoom("175|" + getRoomInwon());
						break;
					case "200": // 방들어가기 (대화방 입장) ----> msgs[] = {"200","자바방"}
						for (int i = 0; i < roomV.size(); i++) {// 방이름 찾기!!
							Room r = roomV.get(i);
							if (r.title.equals(msgs[1])) {// 일치하는 방 찾음!!
								myRoom = r;
								myRoom.count++;// 인원수 1증가
								break;
							}
						} // for
							// 대기실----> 대화방 이동!!
						waitV.remove(this);
						myRoom.userV.add(this);
						messageRoom("200|" + nickName);// 방인원에게 입장 알림
						messageRoom("120|" + myRoom.boss);
						messageRoom("121|" + nickName);
						// 들어갈 방의 title전달
						messageTo("202|" + myRoom.title);
						messageWait("160|" + getRoomInfo());
						messageWait("180|" + getWaitInwon());
						break;
					case "201":// gameroom
						messageRoom("201|");
						break;
					case "300": // 메시지
						messageRoom("300|[" + nickName + "]▶ " + msgs[1]);
						// 클라이언트에게 메시지 보내기
						break;
					case "400": // 대화방 퇴장
						myRoom.count--;// 인원수 감소
						messageRoom("400|" + nickName);// 방인원들에게 퇴장 알림!!
						// 대화방----> 대기실 이동!!
						myRoom.userV.remove(this);
						waitV.add(this);
						if (myRoom.count != 0)
							messageRoom("175|" + getRoomInwon()); // 대화방 퇴장후 방인원 다시출력
							messageRoom("602|");
							messageWait("602|");
						// 대기실에 방정보 다시출력
						if (myRoom.count == 0)
							roomV.remove(myRoom);

						messageWait("160|" + getRoomInfo());
						messageWait("180|" + getWaitInwon());
						messageWait("111|" + roomV.size());
						// if(myRoom.userV.isEmpty())
						break;
					case "501":// 왼쪽으로 이동
						messageRoom("501|");
						break;
					case "502":// 오른쪽으로 이동
						messageRoom("502|");
						break;
					case "510":// 이지모드
						messageRoom("510|");
						break;
					case "511":// 노멀모드
						messageRoom("511|");
						break;
					case "512":// 하드모드
						messageRoom("512|");
						break;
					case "600":// 게임시작
						messageRoom("600|"+msgs[1]);
						messageRoom("602|");
						break;
					case "601":// 게임준비
						messageRoom("601|");
						break;
					case "801":// 키보드 s
						messageRoom("801|"+msgs[1]);
						break;
					case "802":// 키보드 d
						messageRoom("802|"+msgs[1]);
						break;
					case "803":// 키보드 f
						messageRoom("803|"+msgs[1]);
						break;
					case "804":// 키보드 bar
						messageRoom("804|"+msgs[1]);
						break;
					case "805":// 키보드 j
						messageRoom("805|"+msgs[1]);
						break;
					case "806":// 키보드 k
						messageRoom("806|"+msgs[1]);
						break;
					case "807":// 키보드 l
						messageRoom("807|"+msgs[1]);
						break;
					case "811":// 키보드 s
						messageRoom("811|"+msgs[1]);
						break;
					case "812":// 키보드 d
						messageRoom("812|"+msgs[1]);
						break;
					case "813":// 키보드 f
						messageRoom("813|"+msgs[1]);
						break;
					case "814":// 키보드 bar
						messageRoom("814|"+msgs[1]);
						break;
					case "815":// 키보드 j
						messageRoom("815|"+msgs[1]);
						break;
					case "816":// 키보드 k
						messageRoom("816|"+msgs[1]);
						break;
					case "817":// 키보드 l
						messageRoom("817|"+msgs[1]);
						break;
					case "888":// 점수 실시간 공유
						messageRoom("888|"+msgs[1]);
						break;
					case "900":// 키보드  U speedevent
						messageRoom("900|"+msgs[1]);
						break;
					case "901":// 키보드  I imageevent
						messageRoom("901|"+msgs[1]);
						break;
					case "902":// 키보드  O scoreevent
						messageRoom("902|"+msgs[1]);
						break;
					case "903":// 키보드  O scoreevent
						messageRoom("903|"+msgs[1]);
						break;
					case "1000": //게임결과 화면창
						messageRoom("1000|");
						break;
					}// 서버 switch

				} // if

			} // while

		} catch (IOException e) {
			System.out.println("★");
			e.printStackTrace();
		}
	}// run

	public String getRoomInfo() {
		String str = "";
		for (int i = 0; i < roomV.size(); i++) {
			// "자바방--1,오라클방--1,JDBC방--1"
			Room r = roomV.get(i);
			str += r.title + "--" + r.count;
			if (i < roomV.size() - 1)
				str += ",";
		}
		return str;
	}// getRoomInfo

	public String getRoomInwon() {// 같은방의 인원정보

		String str = "";

		for (int i = 0; i < myRoom.userV.size(); i++) {

			// "길동,라임,주원"

			ChatProcess ser = myRoom.userV.get(i);

			str += ser.nickName;

			if (i < myRoom.userV.size() - 1)
				str += ",";

		}

		return str;

	}// getRoomInwon

	public String getRoomInwon(String title) {// 방제목 클릭시 방의 인원정보

		String str = "";

		for (int i = 0; i < roomV.size(); i++) {

			// "길동,라임,주원"

			Room room = roomV.get(i);

			if (room.title.equals(title)) {

				for (int j = 0; j < room.userV.size(); j++) {

					ChatProcess ser = room.userV.get(j);

					str += ser.nickName;

					if (j < room.userV.size() - 1)
						str += ",";

				}

				break;

			}

		}

		return str;

	}// getRoomInwon

	public String getWaitInwon() {

		String str = "";

		for (int i = 0; i < waitV.size(); i++) {

			// "길동,라임,주원"

			ChatProcess ser = waitV.get(i);

			str += ser.nickName;

			if (i < waitV.size() - 1)
				str += ",";

		}

		return str;

	}// getWaitInwon

	public void messageAll(String msg) {// 전체사용자

		// 접속된 모든 클라이언트(대기실+대화방)에게 메시지 전달

		for (int i = 0; i < allV.size(); i++) {// 벡터 인덱스

			ChatProcess service = allV.get(i); // 각각의 클라이언트 얻어오기

			try {

				service.messageTo(msg);

			} catch (IOException e) {

				// 에러발생 ---> 클라이언트 접속 끊음!!

				allV.remove(i--); // 접속 끊긴 클라이언트를 벡터에서 삭제!!

				System.out.println("클라이언트 접속 끊음!!");

			}

		}

	}// messageAll

	public void messageWait(String msg) {// 대기실 사용자

		for (int i = 0; i < waitV.size(); i++) {// 벡터 인덱스

			ChatProcess service = waitV.get(i); // 각각의 클라이언트 얻어오기

			try {

				service.messageTo(msg);

			} catch (IOException e) {

				// 에러발생 ---> 클라이언트 접속 끊음!!

				waitV.remove(i--); // 접속 끊긴 클라이언트를 벡터에서 삭제!!

				System.out.println("클라이언트 접속 끊음!!");

			}

		}

	}// messageWait

	public void messageRoom(String msg) {// 대화방사용자

		for (int i = 0; i < myRoom.userV.size(); i++) {// 벡터 인덱스

			ChatProcess service = myRoom.userV.get(i); // 각각의 클라이언트 얻어오기

			try {

				service.messageTo(msg);

			} catch (IOException e) {

				// 에러발생 ---> 클라이언트 접속 끊음!!

				myRoom.userV.remove(i--); // 접속 끊긴 클라이언트를 벡터에서 삭제!!

				System.out.println("클라이언트 접속 끊음!!");

			}

		}

	}// messageAll

	public void messageTo(String msg) throws IOException {

		// 특정 클라이언트에게 메시지 전달 (실제 서버--->클라이언트 메시지 전달)

		out.write((msg + "\n"));
		out.newLine();
		out.flush();
	}

}
