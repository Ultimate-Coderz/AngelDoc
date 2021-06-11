package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hibernate.*;
import dao.*;

@WebServlet("/DeleteUserServ")
public class DeleteUserServ extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		if(request.getParameter("id")==null)
		{
			response.sendRedirect("Login.jsp"); 
		}
		
		int id=Integer.parseInt(request.getParameter("id"));
		UsersDAO udao= new UsersDAO();
		Users u=new Users();
		PrintWriter out=response.getWriter();
		int role=0;
		
		u=udao.getUser(id);
		
		if(u!=null)
		{
			role=udao.getRole(id);
			DeleteDAO ddao=new DeleteDAO();
			
			if(role==1)
			{
				ddao.deleteCoordinator(id);
			}
			else if(role==2)
			{
				ddao.deleteCoordinator(id);
			}
			else if(role==3)
			{			
				ddao.deleteDoctor(id);
			}
			else if(role==4)
			{
				ddao.deletePatient(id);
			}
			
			HttpSession session=request.getSession();
			if(session.getAttribute("userObj") instanceof Doctor)
			{
				request.setAttribute("dashName", "DoctorDashboard.jsp");
			}
			if(session.getAttribute("userObj") instanceof Patient)
			{
				request.setAttribute("dashName", "PatientDashboard.jsp");
			}
			if(session.getAttribute("userObj") instanceof Coordinator)
			{
				Coordinator c=(Coordinator) session.getAttribute("userObj");
				if(c.getId()==1)
				{
					request.setAttribute("dashName", "AdminDashboard.jsp");
				}
				else if(c.getId()>1 && c.getId()<=3000000)
				{
					request.setAttribute("dashName", "CoordinatorDashboard.jsp");
				}
				
			}			
			
			request.setAttribute("role", role);			
			request.setAttribute("id", id);
			RequestDispatcher rd=request.getRequestDispatcher("DeleteToDashboard");
			rd.forward(request, response);
		}
		else if(u==null)
		{
			out.println("<script>alert(\"Unable to find the user\")</script>");
			HttpSession session=request.getSession();
			if(session.getAttribute("userObj") instanceof Doctor)
			{
				System.out.println("role 4="+role);
				request.setAttribute("role", 3);
				RequestDispatcher rd=request.getRequestDispatcher("DashboardServ");
				rd.forward(request, response);
			}
			else if(session.getAttribute("userObj") instanceof Patient)
			{
				System.out.println("role 5="+role);
				request.setAttribute("role", 4);
				RequestDispatcher rd=request.getRequestDispatcher("DashboardServ");
				rd.forward(request, response);
			}
			else if(session.getAttribute("userObj") instanceof Coordinator)
			{
				Coordinator c=(Coordinator) session.getAttribute("userObj");
				if(c.getId()==1)
				{
					request.setAttribute("role", 1);
				}
				else if(c.getId()>1 && c.getId()<=3000000)
				{
					request.setAttribute("role", 2);
				}
				RequestDispatcher rd=request.getRequestDispatcher("DashboardServ");
				rd.forward(request, response);
			}
		}
		
	}

}
