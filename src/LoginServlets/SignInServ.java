package LoginServlets;

 

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
import MigrateServlets.Alert;
import dao.*;

 

@WebServlet("/SignInServ")

 

public class SignInServ extends HttpServlet 
{
    
    private static final long serialVersionUID = 1L;

 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {

    	System.out.println(request.getSession().getAttribute("userObj"));
    	
	        if(request.getSession(false)==null || request.getParameter("email")==null || request.getParameter("pass")==null )
	        {
	            System.out.println("Session is null!!!");
	            response.sendRedirect("Login.jsp");
	        }
	        else if(request.getSession().getAttribute("userObj")!=null)
	        {
	        	HttpSession s=request.getSession();
	        	if(s.getAttribute("userObj") instanceof Doctor)
	        	{
	        		response.sendRedirect("DoctorDashboard.jsp");
	        	}
	        	else if(s.getAttribute("userObj") instanceof Patient)
	        	{
	        		response.sendRedirect("PatientDashboard.jsp");
	        	}
	        	else if(s.getAttribute("userObj") instanceof Coordinator)
	        	{
	        		Coordinator c=(Coordinator) s.getAttribute("userObj");
	        		if(c.getId()==1)
	        		{
	        			response.sendRedirect("AdminDashboard.jsp");
	        		}
	        		else
	        		{
	        			response.sendRedirect("CoordinatorDashboard.jsp");
	        		}
	        	}
	        }
        else 
        {
	        String email=request.getParameter("email");
	        String pass=request.getParameter("pass");
	        
	        UsersDAO udao=new UsersDAO();
	        Users u=null;
	        PrintWriter out=response.getWriter();
	        
	        if(! (email==null || pass==null || email.equals("") || pass.equals("") ) )
	        {
	            u=udao.validateUserLogin(email, pass);
	                
	            if(u!=null)
	            {
	                int id=u.getId();
	                HttpSession session=request.getSession();
	                if(id==1)
	                {
	                    Coordinator cor=new CoordinatorDAO().find(id);
	                    session.setAttribute("userObj", cor);
	                    request.setAttribute("role", 1);
	                }
	                else if(id>1 && id<=2999999)
	                {
	                    Coordinator cor=new CoordinatorDAO().find(id);
	                    session.setAttribute("userObj", cor);
	                    request.setAttribute("role", 2);
	                }
	                else if(id>2999999 && id<=4999999)
	                {
	                    Doctor doc=new DoctorDAO().find(id);
	                    session.setAttribute("userObj", doc);
	                    request.setAttribute("role", 3);
	                }
	                else if(id>4999999 && id<=9999999)
	                {
	                    Patient pat=new PatientDAO().find(id);
	                    session.setAttribute("userObj", pat);
	                    request.setAttribute("role", 4);
	                }
	                RequestDispatcher rd=request.getRequestDispatcher("DashboardServ");
	                rd.forward(request, response);
	            }
	            else
	            {
	                RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
	                rd.include(request, response);
	                System.out.println("Inside else 1 ");
	                Alert a=new Alert();
	                out.println(a.failureReport("Invalid username or password", ""));	                
	            }
	        }
	        else
	        {
	            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
	            rd.include(request, response);
	            System.out.println("Inside else 2 ");
	            Alert a=new Alert();
                out.println(a.failureReport("Enter correct and full details", ""));               
	        }
        }
    }    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	if(request.getSession(false)==null || request.getParameter("email")==null )
        {
            System.out.println("Session is null!!!");
            response.sendRedirect("Login.jsp");
        }
        else if(request.getSession().getAttribute("userObj")!=null)
        {
        	HttpSession s=request.getSession();
        	if(s.getAttribute("userObj") instanceof Doctor)
        	{
        		response.sendRedirect("DoctorDashboard.jsp");
        	}
        	else if(s.getAttribute("userObj") instanceof Patient)
        	{
        		response.sendRedirect("PatientDashboard.jsp");
        	}
        	else if(s.getAttribute("userObj") instanceof Coordinator)
        	{
        		Coordinator c=(Coordinator) s.getAttribute("userObj");
        		if(c.getId()==1)
        		{
        			response.sendRedirect("AdminDashboard.jsp");
        		}
        		else
        		{
        			response.sendRedirect("CoordinatorDashboard.jsp");
        		}
        	}
        }
    }
    
}