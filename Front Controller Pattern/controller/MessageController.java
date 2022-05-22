import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intro.dao.MessageDAO;
import com.intro.dto.MessageDTO;


@WebServlet("*.msg") //별표앞 슬래쉬X
public class MessageController extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println("uri" + uri);
		
		if(uri.equals("/toInput.msg")) { //input 페이지 요청
			response.sendRedirect("/input.jsp");
		}else if(uri.equals("/insert.msg")) {//메세지 등록

			request.setCharacterEncoding("utf-8");
			String nickname = request.getParameter("nickname");
			String message = request.getParameter("message");
			
			MessageDAO dao = new MessageDAO();
			
			try {
				int rs =  dao.insert(new MessageDTO(0,nickname,message));
				System.out.println("저장완료");
				if(rs>0) {
				
					response.sendRedirect("/home.jsp");  

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/toOutput.msg")){
			MessageDAO dao = new MessageDAO();

			try {
				ArrayList<MessageDTO> list = dao.selectAll();
				request.setAttribute("list", list);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/output.jsp").forward(request, response);
			
			
		}else if(uri.equals("/delete.msg")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			System.out.println("seq : "+ seq);
			
			MessageDAO dao = new MessageDAO();
			
			try {
				int rs = dao.delete(seq);
				if(rs > 0 ) {
					response.sendRedirect("/toOutput.msg");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/modify.msg")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			System.out.println(seq);
			
			
			MessageDAO dao = new MessageDAO();
			try {
				MessageDTO dto = dao.selectBySeq(seq);
				
				if(dto != null) { 
					request.setAttribute("dto", dto);
					request.getRequestDispatcher("/modify.jsp").forward(request, response);
				}else {
					response.sendRedirect("/toOutput.msg");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}else if(uri.equals("/modifyProc.msg")) {
			request.setCharacterEncoding("utf-8");
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			String nickname = request.getParameter("nickname");
			String message =request.getParameter("message");
			
			System.out.println(seq +" : "+nickname+" : "+message);
			
			MessageDAO dao =  new MessageDAO();
			try {
				int rs = dao.modifyBySeq(new MessageDTO(seq,nickname,message));
				
				if(rs > 0) {
					response.sendRedirect("/toOutput.msg");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
