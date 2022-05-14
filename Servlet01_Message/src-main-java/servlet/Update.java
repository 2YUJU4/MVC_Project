package Servlet01_Message.src-main-java.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.MessageDAO;
import com.kh.dto.MessageDTO;


@WebServlet("/update.proc")
public class Update extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String message = request.getParameter("message");
		System.out.println(nickname +" : "+message);
		
		MessageDAO dao = new MessageDAO();
		
		try {
			int rs = dao.update(new MessageDTO(nickname,message));
			if(rs > 0) {
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
