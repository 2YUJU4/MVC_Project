package Servlet01_Student.src-main-java.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import StudentDAO.StudentDAO;
import StudentDTO.StudentDTO;

//입력한 정보를 DB에 전송 저장해주는 서블릿
@WebServlet("/input.proc")
public class Input extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//한글 인코딩 처리 (request -> 클라이언트로부터 넘겨받는 값이기 때문)
			request.setCharacterEncoding("utf-8");
			//getParameter는 String으로 받아줌
			String name = request.getParameter("name");
			int kor = Integer.parseInt(request.getParameter("kor"));
			int eng = Integer.parseInt(request.getParameter("eng"));
			int math = Integer.parseInt(request.getParameter("math"));

			
			StudentDAO dao = new StudentDAO();
			try {
				//생성자 오버라이딩 또는 0값을 넣기 dao.insert(new StudentDTO(0,name,kor,eng,math))
				int rs = dao.insert(new StudentDTO(name, kor, eng, math));
				
				if(rs > 0) {
					System.out.println("학생 정보 저장 완료");
					response.sendRedirect("/index.html");
				}else {
					System.out.println("학생 정보 저장 실패");
					response.sendRedirect("/index.html");
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
