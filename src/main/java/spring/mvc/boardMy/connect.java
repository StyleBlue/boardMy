package spring.mvc.boardMy;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	//JdbC : Java와 DB를 연결해서 사용하는 것
	public class connect {
		public static void main(String[] args) {
			
			Connection conn = null;
			try {
				// 0. jar 파일 가져오기
				// 프로젝트폴더 - Properties - ... 블로그 게시클 참고
				
				// 1. 자바에서 오라클 데이터베이스 작업을 하고 싶을 때 사용해야 하는 드라이버를 로드(컴퓨터가 프린터를 통해 출력하고 싶은 경우 드라이버를 설치하는데 그런 의미의 드라이버)
				//OracleDriver는 ojdbc8.jar 안에 존재하는 oracle.jdbc.driver 패키지 안에 있는 클래스임
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("드라이버 로드 성공");
				System.out.println(conn);
				
				// 2. 계정에 접속, SQL PLUS 콘솔창에서 하던 ID, PW를 입력후 로그인라는 과정과 같음(맨 뒤의 2개는 아이디, 비번임)
				// JDBC 작업을 할건데, 많고 많은 DB중 oracle을 사용하고, 내 컴퓨터에 설치된 전역데이터베이스 이름 orcl을 가진 DB에 접속을 하는데 포트번호 1521로 접속하겠다.DB에 접속 힐 때 사용할 계정의 ID/PW는 다음과 같다. 라는 의미
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "jspid", "jsppw");
				System.out.println(conn);
				System.out.println("접속 성공");
				
				/*
				SQL 작업을 할 수 있는 위치
				*/
				
				// SQL PLUS 에서 exit 명령어를 입력하여 콘솔이 종료되는 것과 같은 효과
				conn.close(); 
				System.out.println("접속 끊기 성공");
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로드 실패");
			} catch (SQLException e) {
				System.out.println("접속 실패");
			}
		
	}


	/*
	드라이버 로드 성공
	null
	oracle.jdbc.driver.T4CConnection@4ac3c60d
	접속 성공
	접속 끊기 성공
	*/

}
