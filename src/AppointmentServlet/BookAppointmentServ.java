package AppointmentServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
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
    	
    	System.out.println("In book appointment");
        HttpSession session = request.getSession();
        Doctor doc = (Doctor)session.getAttribute("docObj");
        Patient pat = (Patient) session.getAttribute("userObj");
        System.out.println("Is doc null? - "+ (doc==null));
        System.out.println("Is pat null? - "+(pat==null));
       
        String startingTime = (String) request.getParameter("startingTime");
        Time startT = Time.valueOf(startingTime+":00");
       
        long t1 = startT.getTime();
        long t2 = t1+1800000;
        Time endT = new Time(t2);
        String endingTime = endT.toString().substring(0,5);
        System.out.println(startT);
        System.out.println(endT);
        System.out.println(endingTime);
//        String endingTime = (String) request.getParameter("endingTime");
        String dateString=(String) request.getParameter("date");
        System.out.println("String date: - "+dateString);
        Date date = Date.valueOf(dateString);
        Appointment app = new Appointment(300.0 , "no" , doc , pat , startingTime , endingTime , date , "Pending");
        AppointmentDAO appointDAO = new AppointmentDAO();
        appointDAO.create(app);
        response.sendRedirect("PaySuccess.jsp");
//        RequestDispatcher rd = request.getRequestDispatcher("PaySuccess.jsp");
//        rd.forward(request, response);
        
    }

 

}