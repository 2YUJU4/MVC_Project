package Servlet01_Student.src-main-java.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import StudentDAO.StudentDAO;
import StudentDTO.StudentDTO;

@WebServlet("/search")
public class Search extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name =  request.getParameter("name");
	
		//1.dao selectByName에 name값을 넘겨 -> 데이터 조회 => 동명이인이 있을수 있으니 ArrayList로 가져와야함
		//selectById => 고유값이기때문에 StudentDTO로 받아주기 가능
		//2.ToOutput서블릿으로 갔을때와 똑같은 작업을 통해 화면을 구성
	
		StudentDAO dao = new StudentDAO();
		try {
			ArrayList<StudentDTO> list = dao.selectByName(name);
			
			
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			out.write("<html><head></head><body>");
			out.write("<table border=1 align=center>");
			out.write("<tr><th colspan=6>Student</th></tr>");
			out.write("<tr><th>ID</th><th>NAME</th><th>KOR</th><th>ENG</th><th>MATH</th><th>SUM</th></tr>");
			//실제 데이터 영역
			for(StudentDTO dto : list) {
				out.write("<tr>"
						+ "<td>"+ dto.getId() +"</td>"   //id
						+ "<td>"+ dto.getName() +"</td>" //name
						+ "<td>"+ dto.getKor() +"</td>"  //kor
						+ "<td>"+ dto.getEng() +"</td>"  //eng
						+ "<td>"+ dto.getMath() +"</td>" //math
						+ "<td>"+ (dto.getKor()+dto.getEng()+dto.getMath()) +"</td>" //sum
						+ "</tr>");	
			}
			//삭제 영역
			out.write("<tr><form action='/delete' method='post'>"
					+ "<td colspan=6>"
					+ "<input type='text' name='id' placeholder='삭제할 학생의 번호'>"
					+ "<button type='submit'>Delete</button>"
					+ "</form></td></tr>");
			
			//수정 영역
			out.write("<tr><form action='/update' method='post'>"
					+"<td colspan=6>"
					+"<input type='text' name='id' placeholder='수정할 학생의 번호'>"
					+"<button type='submit'>Update</button><br>"
					+ "<input type='text' name='name' placeholder='수정할 학생의 이름'><br>"
					+ "<input type='text' name='kor' placeholder='수정할 학생의 국어점수'><br>"
					+ "<input type='text' name='eng' placeholder='수정할 학생의 영어점수'><br>"
					+ "<input type='text' name='math' placeholder='수정할 학생의 수학점수'>"
					+ "</td></form></tr>");
			
			//검색영역
			//** form 안에서는 submit 버튼 하나만
			out.write("<tr><form = action='/search' method='post'>"
					+ "<td colspan=6>"
					+ "<input type='text' name='name' placeholder='검색학 학생의 이름'>"
					+ "<button type='submit'>Search</button>"
					+ "<button type='button' id='showAll'>showAll</button>"
					+ "</td></form></tr>");
			
			//뒤로가기 영역
			out.write("<tr><td colspan=6>"
					+ "<button type='button' id='back'>Back</button>"
					+ "</td></tr>");
			
			
			
			out.write("</table>");
			
			//스크립트
			out.write("<script>"
					+ "document.getElementById('showAll').onclick=function(){"
					+ "location.href='/toOutput.proc'};"
					+ "document.getElementById('back').onclick=function(){"
					+ "location.href='/index.html'}"
					+ "</script>");
			
			out.write("</body></html>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
	
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
