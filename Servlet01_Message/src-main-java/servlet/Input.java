package Servlet01_Message.src-main-java.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.MessageDAO;

@WebServlet("/input.proc")
public class Input extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String message = request.getParameter("message");
		
		System.out.println("nickname : " + nickname);
		System.out.println("message : " + message);
		
		MessageDAO dao = new MessageDAO();
		try {
			int rs = dao.insert(nickname, message);
			if(rs > 0) { // insert 제대로 수행된 경우
				System.out.println("메세지 저장 완료!");
				response.sendRedirect("/index.html");
			}else { // insert 제대로 수행X
				System.out.println("메세지 저장 실패!");
				response.sendRedirect("/input.html");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
