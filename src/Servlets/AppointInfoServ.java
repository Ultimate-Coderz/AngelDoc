package Servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Hibernate.*;
import dao.AppointmentDAO;

@WebServlet("/AppointInfoServ")
public class AppointInfoServ extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		

		HttpSession session=request.getSession();
		if(session.getAttribute("userObj")==null)
		{
			response.sendRedirect("index.jsp");
		}
		if(request.getParameter("id")==null)
		{
			response.sendRedirect("index.jsp");
		}
	
		int id=Integer.parseInt(request.getParameter("id"));
		AppointmentDAO adao=new AppointmentDAO();
		Appointment app=adao.getAppointment(id);
		
		if( session.getAttribute("userObj") instanceof Doctor )
		{
			
			if(app==null)
			{
				response.sendRedirect("DoctorDashboard.jsp");
			}
			else
			{				
				request.setAttribute("App", app);				
				RequestDispatcher rd=request.getRequestDispatcher("Appointment-doc.jsp");
				rd.forward(request, response);
			}
			
		}
		else if( session.getAttribute("userObj") instanceof Patient )
		{
			
			if(app==null)
			{
				response.sendRedirect("PatientDashboard.jsp");
			}
			else
			{
				request.setAttribute("App", app);
				RequestDispatcher rd=request.getRequestDispatcher("Appointment-pat.jsp");
				rd.forward(request, response);
			}
			
		}
		else if( session.getAttribute("userObj") instanceof Coordinator )
		{
			Coordinator c=(Coordinator) session.getAttribute("userObj");
			if(c.getId()==1)
			{
				if(app==null)
				{
					response.sendRedirect("AdminDashboard.jsp");
				}
				else
				{
					request.setAttribute("App", app);
					RequestDispatcher rd=request.getRequestDispatcher("Appointment-cor.jsp");
					rd.forward(request, response);
				}
			}
			else
			{
				if(app==null)
				{
					response.sendRedirect("CoordinatorDashboard.jsp");
				}
				else
				{
					request.setAttribute("App", app);
					RequestDispatcher rd=request.getRequestDispatcher("Appointment-cor.jsp");
					rd.forward(request, response);
				}
			}
			
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		doGet(request, response);
	}

}
