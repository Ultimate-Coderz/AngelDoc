package UpdateServlets;

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

 
@WebServlet("/UpdatePasswordServ")
public class UpdatePasswordServ extends HttpServlet 
{
	
    private static final long serialVersionUID = 1L;
      
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        
        String old=request.getParameter("old");
        String newP = request.getParameter("newP");
        
        HttpSession session=request.getSession();
        Integer role=Integer.parseInt(request.getParameter("role"));
        UsersDAO udao=new UsersDAO();
        
        switch(role)
        {
        case 1: 
        {
            CoordinatorDAO cdao=new CoordinatorDAO();
            Coordinator c= (Coordinator) session.getAttribute("userObj"); 
            if(c.getPassword().equals(old))
            {
                c.setPassword(newP);
                cdao.update(c);
                Users u=udao.find(c.getEMail());
                u.setPassword(newP);
                udao.update(u);
                RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.jsp");
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.successAlert("Password updated successfully"));
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("UpdatePassword.jsp");
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.failureAlert("Old Password didn't match"));
            }
            
        }break;
        
    
        
        case 2:
        {
            CoordinatorDAO cdao=new CoordinatorDAO();
            Coordinator c= (Coordinator) session.getAttribute("userObj");
            
            if(c.getPassword().equals(old))
            {
                c.setPassword(newP);
                cdao.update(c);
                Users u=udao.find(c.getEMail());
                u.setPassword(newP);
                udao.update(u);
                RequestDispatcher rd = request.getRequestDispatcher("CoordinatorDashboard.jsp");
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.successAlert("Password updated successfully"));
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("UpdatePassword.jsp");
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.failureAlert("Old Password didn't match"));
            }
            
        }break;
        
        
        case 3: 
        {
            DoctorDAO cdao=new DoctorDAO();
            Doctor c= (Doctor) session.getAttribute("userObj");
            
            if(c.getPassword().equals(old))
            {
                c.setPassword(newP);
                cdao.update(c);
                Users u=udao.find(c.geteMail());
                u.setPassword(newP);
                udao.update(u);
                
                RequestDispatcher rd = request.getRequestDispatcher("DoctorDashboard.jsp");
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.successAlert("Password updated successfully"));
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("UpdatePassword.jsp");
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.failureAlert("Old Password didn't match"));
            }
            
        }break;
        
        
        case 4: {
            PatientDAO cdao=new PatientDAO();            
            Patient c= (Patient) session.getAttribute("userObj"); 
            
            if(c.getPassword().equals(old))
            {
                c.setPassword(newP);
                cdao.update(c);
                Users u=udao.find(c.getEMail());
                u.setPassword(newP);
                udao.update(u);
                RequestDispatcher rd = request.getRequestDispatcher("PatientDashboard.jsp");
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.successAlert("Password updated successfully"));
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("UpdatePassword.jsp");
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.failureAlert("Old Password didn't match"));
            }
            
        }break;
        
        }
        
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        doGet(request, response);
        
    }

 

}