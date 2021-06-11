package AppointmentServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Hibernate.*;
import MigrateServlets.Alert;
import dao.DoctorDashboardDAO;

@WebServlet("/ValidateAppointment")
public class ValidateAppointment extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        HttpSession session = request.getSession();
        Doctor doc = (Doctor) session.getAttribute("docObj");
        String appointmentStartingTime = (String) request.getParameter("startingTime");
        Time appStartTime = Time.valueOf(appointmentStartingTime+":00");
        String dateString=request.getParameter("date");
        Date date = Date.valueOf(dateString);
        long start=appStartTime.getTime();
        long end = appStartTime.getTime()+30*60*1000;
        Time appEndTime = new Time(end);
        
        int i=1;
        boolean flag = true;
        
        if((end-start)>=900000)
        {
        
        DoctorDashboardDAO docDashDAO=new DoctorDashboardDAO();
        List<Appointment> docUpcomingList = docDashDAO.getUpcomingAppointments(doc.getDocId());
        List<Appointment> docUpcomingDateList = new ArrayList<Appointment>();
        
        if(docUpcomingList.size()>0){
        
        for(Appointment a: docUpcomingList)
        {
            if(a.getSqlDate().equals(date))
            docUpcomingDateList.add(a);            
        }
         
        for(Appointment a : docUpcomingDateList)
        {
            Time x1 = Time.valueOf(a.getStartingTime()+":00");
            Time x2 = Time.valueOf(a.getEndingTime()+":00");
            if(appStartTime.compareTo(x1)==0 || appEndTime.compareTo(x2)==0)
            {
                flag=false;
                break;
            }
            else
            {
                if(appStartTime.compareTo(x1)<0)
                {
                    
                    if(appEndTime.compareTo(x1)>0)  
                    {                        
                        flag = false;
                        break;
                    }
                }
                else
                {
                    
                    if(appStartTime.compareTo(x2)>=0)
                    {
                       flag=true;
                    }
                    else
                    {
                        flag = false;
                    break;   
                    }
                }
            }
        }
        }
        else
        {
            flag=true;
        }
        
        if(flag)
        {
            RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
            request.setAttribute("startingTime", appointmentStartingTime);
            request.setAttribute("date", dateString);
            rd.forward(request, response);
            
        }
        else
        {
            if(i==1)
            {
	            PrintWriter out = response.getWriter();
	            RequestDispatcher rd = request.getRequestDispatcher("Appoint.jsp?id="+doc.getDocId());
                rd.include(request, response);
                Alert a =new Alert();
                out.println(a.failureReport("Can't book Appointment", "Doctor is not available in this time-slot"));	            
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("Appoint.jsp");
                request.setAttribute("Doctor", doc);
                rd.forward(request, response);
            }
        }
        
        }else
            {    
            i=0;
            flag=false;
                PrintWriter out = response.getWriter();
                RequestDispatcher rd = request.getRequestDispatcher("Appoint.jsp");
                rd.include(request, response);
                request.setAttribute("Doctor", doc);
                Alert a =new Alert();
                out.println(a.failureReport("Can't book Appointment", "Starting time must be atleast 10 minutes less than the ending time"));                
            }
        
        
        
        
    }
    
}