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
import org.apache.log4j.Logger;
import Hibernate.*;
import MigrateServlets.Alert;
import dao.LoggerBO;

@WebServlet("/DashboardServ")
public class DashboardServ extends HttpServlet 
{
    
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	
    	LoggerBO lbo=new LoggerBO();
        Logger logger=lbo.getLogger("DashboardServ");
        
        if(request.getSession().getAttribute("userObj")==null)
        {
            response.sendRedirect("Login.jsp");
        }
        
        int role=(int) request.getAttribute("role");
        boolean newUser=false;
        if(request.getAttribute("new")!=null)
        {        	 
        	 newUser=(boolean) request.getAttribute("new");
        }       

    	Alert a=new Alert();
    	PrintWriter out=response.getWriter();
    	HttpSession session=request.getSession();
        
        switch(role)
        {
            case 1:
            {
                RequestDispatcher rd=request.getRequestDispatcher("AdminDashboard.jsp");                
                rd.include(request, response);
                Coordinator c=(Coordinator) session.getAttribute("userObj");
                out.println(a.UserAlert(c.getFirstName()));
                logger.info(c.getFirstName() +" "+ c.getLastName() + " logged in");
                break;
            }
            case 2:
            {
                RequestDispatcher rd=request.getRequestDispatcher("CoordinatorDashboard.jsp");
                rd.include(request, response);
                Coordinator c=(Coordinator) session.getAttribute("userObj");
                out.println(a.UserAlert(c.getFirstName()));
                logger.info(c.getFirstName() +" "+ c.getLastName() + " logged in");
                break;
            }
            case 3:
            {
                RequestDispatcher rd=request.getRequestDispatcher("DoctorDashboard.jsp");
            	Doctor d=(Doctor) session.getAttribute("userObj");
                if(newUser)
                {
                	rd.include(request, response);
                	out.println(a.newUserAlert(d.getFirstName()));
                	logger.info(d.getFirstName() +" "+ d.getLastName() + " logged in");
                }
                else
                {
                	rd.include(request, response);
                    out.println(a.UserAlert(d.getFirstName()));
                    logger.info(d.getFirstName() +" "+ d.getLastName() + " logged in");
                }
                break;
            }
            case 4:
            {
                RequestDispatcher rd=request.getRequestDispatcher("PatientDashboard.jsp");
                Patient p=(Patient) session.getAttribute("userObj");
                if(newUser)
                {                	
                	rd.include(request, response);
                    out.println(a.newUserAlert(p.getFirstName()));
                    logger.info(p.getFirstName() +" "+ p.getLastName() + " logged in");
                }
                else
                {
                	rd.include(request, response);
                    out.println(a.UserAlert(p.getFirstName()));
                    logger.info(p.getFirstName() +" "+ p.getLastName() + " logged in");
                }
                break;
            }
            default:
            {
                response.sendRedirect("Login.jsp");
            }
        }
        
    }
    
}