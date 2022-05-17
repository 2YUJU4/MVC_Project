package Servlet01_Student.src-main-java.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import StudentDAO.StudentDAO;


@WebServlet("/delete")
public class Delete extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
	
		StudentDAO dao = new StudentDAO();
		try {
			int rs = dao.delete(id);
			response.sendRedirect("toOutput.proc");
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

