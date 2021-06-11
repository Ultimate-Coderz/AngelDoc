package AppointmentServlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Hibernate.*;
import dao.*;

@WebServlet("/BookAppointmentServ")
public class BookAppointmentServ extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	
    	
        HttpSession session = request.getSession();
        Doctor doc = (Doctor)session.getAttribute("docObj");
        Patient pat = (Patient) session.getAttribute("userObj");
        
        String startingTime = (String) request.getParameter("startingTime");
        Time startT = Time.valueOf(startingTime+":00");
       
        long t1 = startT.getTime();
        long t2 = t1+1800000;
        Time endT = new Time(t2);
        String endingTime = endT.toString().substring(0,5);
        String dateString=(String) request.getParameter("date");
        
        Date date = Date.valueOf(dateString);
        Appointment app = new Appointment(300.0 , "no" , doc , pat , startingTime , endingTime , date , "Pending");
        AppointmentDAO appointDAO = new AppointmentDAO();
        appointDAO.create(app);
        response.sendRedirect("PaySuccess.jsp");
        
    }

 

}