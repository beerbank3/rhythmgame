package rhythm_game_1;

import java.util.Vector;

public class Room {

	String title; //방제목
	int count; //인원수
	String boss; //방주인
	Vector<ChatProcess> userV; //같은방에 접속한 유저 저장
	public Room() {
		userV = new Vector<>();
	}
}