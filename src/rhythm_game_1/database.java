package rhythm_game_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class database {
	public static int xox=0;
	private static Connection conn;
	private static String url = "jdbc:mysql://localhost:3306/rhythm_game?serverTimezone=UTC";
	private static String user="root";
	public static void insertScore(String name,String difficulty,int score,int combo,int perfect,int great,int good,int early,int late,int miss,String ID) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loading Ok");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rhythm_game?serverTimezone=UTC","root","root");
			System.out.println("데이터베이스 연결 성공");
			
			Statement stmt = conn.createStatement();
			String sql;
			sql="insert into score values('"+name+"','"+difficulty+"',"+score+","+combo+","+perfect+","+great+","+good+","+early+","+late+","+miss+",'"+ID+"');";
			int affectedCount = stmt.executeUpdate(sql);
			xox=affectedCount;
			System.out.println("affectedCount = "+affectedCount);
		}catch(Exception e) {
			System.out.println("오류"+e);
		}
	}
	public static void insertAccount(String ID,String PW,String Nick,String E_mail) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loading Ok");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rhythm_game?serverTimezone=UTC","root","root");
			System.out.println("데이터베이스 연결 성공");
			
			Statement stmt = conn.createStatement();
			String sql;
			sql="insert into rhythm_game.account values('"+ID+"','"+PW+"','"+Nick+"','"+E_mail+"');";
			int affectedCount = stmt.executeUpdate(sql);
			xox=affectedCount;
			System.out.println("affectedCount = "+affectedCount);
		}catch(Exception e) {
			System.out.println("오류"+e);
		}
	}
}
