package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import Hibernate.Coordinator;
import Hibernate.Doctor;
import Hibernate.Patient;
import dao.LoggerBO;
import java.io.IOException;

@WebServlet("/LogoutServ")
public class LogoutServ extends HttpServlet 
{

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        HttpSession session = request.getSession();
        LoggerBO lbo=new LoggerBO();
        Logger logger=lbo.getLogger("LogoutServ");
        
        String name="";
        
        if (session.getAttribute("userObj") instanceof Doctor) 
        {
            Doctor d=(Doctor) session.getAttribute("userObj");
            name=d.getFirstName()+" "+d.getLastName();
        } 
        else if (session.getAttribute("userObj") instanceof Patient) 
        {
        	Patient p=(Patient) session.getAttribute("userObj");
            name=p.getFirstName()+" "+p.getLastName();
        } 
        else if (session.getAttribute("userObj") instanceof Coordinator) 
        {
            Coordinator c = (Coordinator) session.getAttribute("userObj");
            if (c.getId() == 1) 
            {
            	name=c.getFirstName()+" "+c.getLastName();
            } 
            else 
            {
            	name=c.getFirstName()+" "+c.getLastName();
            }
        }
        session.removeAttribute("userObj");
        logger.info(name + " logged out");        
        session.invalidate();
        response.sendRedirect("index.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
