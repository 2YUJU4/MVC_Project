import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import StudentDTO.StudentDTO;

public class StudentDAO {

	private BasicDataSource bds;
	
	public StudentDAO() {
		// 서버가 실행될때 이미 생성 해놓은 Connection Pool 찾는 작업
		try {
			Context iCtx = new InitialContext(); //Connection Pool을 검색하기 위한 인스턴스 생성
			Context envCtx = (Context)iCtx.lookup("java:comp/env");// 자원이 실제 존재하는 위치까지 찾아가는 작업
			bds = (BasicDataSource)envCtx.lookup("jdbc/bds"); //자원의 name값을 이용해 이미 만들어진 bds 인스턴스 가져오기
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws Exception{
		return bds.getConnection();
	}
	//데이터 전체 조회
	public ArrayList<StudentDTO> selectAll() throws Exception{
		String sql = "select * from tbl_std";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<StudentDTO> list = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int math = rs.getInt("math");
				
				list.add(new StudentDTO(id,name,kor,eng,math));
			}
			return list;
		}
	}
	
	//이름에 따른 데이터 조회
	public ArrayList<StudentDTO> selectByName(String name) throws Exception{
		String sql = "select * from tbl_std where name = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){

			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<StudentDTO> list = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name_std = rs.getString("name");
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int math = rs.getInt("math");
				
				list.add(new StudentDTO(id,name_std,kor,eng,math));
			}
			return list;
		}
	}
	
	
	//데이터 삭제
	public int delete(int id) throws Exception{
		String sql = "delete from tbl_std where id=?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, id);
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	//데이터 수정
	public int update(StudentDTO dto) throws Exception{
		String sql = "update tbl_std set name=?,kor=?,eng=?,math=? where id=?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMath());
			pstmt.setInt(5, dto.getId());
			
			int rs = pstmt.executeUpdate();
			return rs;
			
		}	
	}
	
	
	
	
	
	//데이터 삽입
	public int insert(StudentDTO dto) throws Exception{
		String sql = "insert into tbl_std values(seq_std.nextval,?,?,?,?)";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMath());
			
			int rs = pstmt.executeUpdate();
			return rs;
			
		}
	}

	
}
