package Servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Hibernate.Doctor;
import dao.DoctorDAO;

@WebServlet("/AppointServ")
public class AppointServ extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
	
		int id=Integer.parseInt(request.getParameter("id"));		
		DoctorDAO ddao=new DoctorDAO();
		Doctor doc=ddao.getDoctor(id);
		
		request.setAttribute("Doctor", doc);
		RequestDispatcher rd=request.getRequestDispatcher("Appoint.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
		
	}

}
