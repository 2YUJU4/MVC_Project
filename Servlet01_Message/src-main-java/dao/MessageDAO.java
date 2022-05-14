import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class MessageDAO {
	private BasicDataSource bds = new BasicDataSource();
	
	public MessageDAO() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		
		bds.setUrl(url);
		bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		bds.setUsername(username);
		bds.setPassword(password);
		bds.setInitialSize(30);
	}
	
	public Connection getConnection() throws Exception{
		return bds.getConnection();
	}
	
	//닉네임을 이용한 데이터 수정
	public int update(MessageDTO dto) throws Exception{
		String sql = "update tbl_msg set message = ? where nickname = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, dto.getMassage());
			pstmt.setString(2, dto.getNickname());
			
			int rs = pstmt.executeUpdate();
			return rs;
			
		}
	}
	
	
	
	//닉네임을 이용한 데이터 삭제
	public int delete(String nickname) throws Exception{
		String sql = "delete from tbl_msg where nickname = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
				
			pstmt.setString(1, nickname);
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	//모든 메세지 조회
	public ArrayList<MessageDTO> selectAll() throws Exception{
		String sql = "select * from tbl_msg";
		
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<MessageDTO> list = new ArrayList<>();
			while(rs.next()) {
				String nickname = rs.getString("nickname");
				String message = rs.getString("message");
				list.add(new MessageDTO(nickname, message));
			}
			return list;
		}
		
	}
	
	
	
	// nickname+message insert 
	// insert add create append..
	public int insert(String nickname, String message) throws Exception{
		String sql = "insert into tbl_msg values(?, ?)";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			// 첫번째 인자값 -> 물음표의 번호
			// 두번째 인자값 -> 그 물음표에 셋팅해줄 값
			pstmt.setString(1, nickname);
			pstmt.setString(2, message);
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
}
