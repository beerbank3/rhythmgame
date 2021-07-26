package rhythm_game_1;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 6;
	public static final int SLEEP_TIME = 10;
	public static final int REACH_TIME = 2;

	public static void main(String[] args) {
		String game[] = {"12314#55555#asdasd"};
		String gamescore[] = game[0].split("#");
		System.out.println(gamescore[0]);
		System.out.println(gamescore[1]);
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				System.out.println(gamescore[2]);
			}
		};
		timer.schedule(task, 2000, 2000);
	}

}