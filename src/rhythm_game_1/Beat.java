package rhythm_game_1;

public class Beat {

	private double time;
	private String noteName;

	public double getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getNoteName() {
		return noteName;
	}

	public Beat(double time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

}
