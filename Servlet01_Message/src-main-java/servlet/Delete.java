package Servlet01_Message.src-main-java.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.MessageDAO;


@WebServlet("/delete.proc")
public class Delete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		String nickname = request.getParameter("nickname");
		
		MessageDAO dao = new MessageDAO();
		try {
			int rs = dao.delete(nickname);
			if(rs > 0) {
				//sendRedirect (파일경로/url값) : 인자값을 요청해라
				//클라이언트에게 응답값을 보낼 때 인자값의 url로
				//즉시 재요청하도록 만듬
				response.sendRedirect("/toOutput.proc");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
