package MigrateServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteToDashboard")
public class DeleteToDashboard extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		if(request.getAttribute("role")==null)
		{
			response.sendRedirect("LoginPage.jsp"); 
		}
		else if(request.getAttribute("id")==null)
		{
			response.sendRedirect("LoginPage.jsp"); 
		}
		
		int role=(int) (request.getAttribute("role"));
		System.out.println(role +" Role inside the DTD !!! ");
		String dashName=(String) request.getAttribute("dashName");
		String msg="";
		PrintWriter out=response.getWriter();
		
		RequestDispatcher rd=request.getRequestDispatcher(dashName);
		rd.include(request, response);
		Alert alert=new Alert();
		
		if(role==2)
		{
			msg="Coordinator deleted successfully";			
		}
		else if(role==3)
		{
			msg="Doctor deleted successfully";
		}
		else if(role==4)
		{		
			msg="Patient deleted successfully";
		}		
		
		out.println(alert.successAlert(msg));
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}
}
