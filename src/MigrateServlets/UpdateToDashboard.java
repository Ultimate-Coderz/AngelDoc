package MigrateServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hibernate.Coordinator;
import Hibernate.Doctor;
import Hibernate.Patient;

@WebServlet("/UpdateToDashboard")
public class UpdateToDashboard extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		boolean status=(boolean) request.getAttribute("status");		
		String name=(String) request.getAttribute("name");			
		
		String dashName="";
		
		HttpSession session=request.getSession();
		if(session.getAttribute("userObj") instanceof Doctor)
		{
			dashName="DoctorDashboard.jsp";
		}
		if(session.getAttribute("userObj") instanceof Patient)
		{
			dashName="PatientDashboard.jsp";
		}
		if(session.getAttribute("userObj") instanceof Coordinator)
		{
			Coordinator c=(Coordinator) session.getAttribute("userObj");
			if(c.getId()==1)
			{
				dashName="AdminDashboard.jsp";
			}
			else if(c.getId()>1 && c.getId()<=3000000)
			{
				dashName="CoordinatorDashboard.jsp";
			}
			
		}			

		request.setAttribute("status", status);
		request.setAttribute("name", name);
		RequestDispatcher rd=request.getRequestDispatcher(dashName);
		rd.include(request, response);
		Alert a=new Alert();
		
		if(status==true)
		{
			out.println(a.successAlert("Profile updated successfully"));
		}
		else
		{
			out.println(a.successAlert("Unable to update profile"));
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		doGet(request, response);
	}

}
