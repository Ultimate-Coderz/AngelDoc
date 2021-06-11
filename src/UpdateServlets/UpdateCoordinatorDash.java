package UpdateServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Hibernate.Coordinator;
import dao.LoggerBO;
import dao.UpdateDAO;

@WebServlet("/UpdateCoordinatorDash")
public class UpdateCoordinatorDash extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		Coordinator c=(Coordinator) session.getAttribute("userObj");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String contactNum=request.getParameter("contactNum");
		
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setEMail(email);
		c.setContactNumber(contactNum);
		
		UpdateDAO adao=new UpdateDAO();	
		boolean status=adao.updateCoordinator(c, c.getId());
		
		if(c.getId()==1)
		{
			request.setAttribute("name", "Admin");
		}
		else
		{
			request.setAttribute("name", "Coordinator");
		}
		request.setAttribute("status", status);	
		session.removeAttribute("userObj");
		session.setAttribute("userObj", c);
		RequestDispatcher rd=request.getRequestDispatcher("UpdateToDashboard");
		LoggerBO lbo=new LoggerBO();
        Logger logger=lbo.getLogger("UpdateCoordinatorDash");
        logger.info(c.getFirstName()+" "+c.getLastName()+" updated his profile");       
		rd.forward(request, response);
	}
}

