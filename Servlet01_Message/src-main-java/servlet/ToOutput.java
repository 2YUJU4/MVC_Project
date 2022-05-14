package Servlet01_Message.src-main-java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.MessageDAO;
import com.kh.dto.MessageDTO;


@WebServlet("/toOutput.proc")
public class ToOutput extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DAO를 통해 DB에 접속해 tbl_msg 테이블의 모든행을 조회
		MessageDAO dao = new MessageDAO();
		
		try {
			ArrayList<MessageDTO> list = dao.selectAll();
			//출력 하기
			for(MessageDTO dto : list) {
				System.out.println(dto.toString());
			}
			
			//문서의 타입 명시, 인코딩 타입 (한글 설정)
			response.setContentType("text/html; charset=utf-8");
			
		//html table로 데이터를 뿌려주는 작업
		PrintWriter out = response.getWriter();
		out.write("<html>");
		out.write("<head>");
		out.write("<style>");
		
		out.write("*{border : 1px solid black}");
		out.write("table{width:400px; height:300px; text-align:center}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		//table 태그를 꾸며서 write
		out.write("<table>");
		out.write("<tr>"
				+ "<th>Nickname</th>"
				+ "<th>Message</th>"
				+ "</tr>");
		
		//반복문
		for(MessageDTO dto : list) {
			out.write("<tr>"
					+ "<td>"+ dto.getNickname() +"</td>"
					+ "<td>"+ dto.getMassage() +"</td>"
					+ "</tr>");	
		}
		out.write("<tr>"
				+ "<form action='/delete.proc' method='post'>"
				+ "<td colspan='2'>"
				+ "<input type='text' name='nickname' placeholder='삭제할 닉네임을 입력하세요'>"
				+ "<button type='submit'>삭제</button>"
				+ "</td>"
				+ "</form>"
				+ "</tr>");
		
		out.write("<tr>"
				+ "<form action='/update.proc' method='post'>"
				+ "<td colspan='2'>"
				+ "<input type='text' name='nickname' placeholder='수정할 닉네임 입력'><br>"
				+ "<textarea name='message' placeholder='수정할 메세지 입력'></textarea><br>"
				+ "<button type='submit'>수정</button>"
				+ "</td>"
				+ "</form>"
				+ "</tr>");
		
		out.write("<tr><td colspan='2'>"
				+ "<button id='btnBack' type='button'>back</button>"
				+ "</td>"
				+ "</tr>");
		
		out.write("</table>");
		out.write("<script>");
		
		
		out.write("document.getElementById('btnBack').onclick = function(){"
				+ "location.href='/index.html'};");
		out.write("</script>");
		out.write("</body>");
		out.write("</html>");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
