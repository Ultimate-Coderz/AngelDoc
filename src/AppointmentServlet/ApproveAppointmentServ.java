package AppointmentServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MigrateServlets.Alert;
import dao.AppointmentDAO;

@WebServlet("/ApproveAppointmentServ")
public class ApproveAppointmentServ extends HttpServlet 
{
	
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {        
        int appId = Integer.parseInt(request.getParameter("appId"));
        AppointmentDAO appDAO = new AppointmentDAO();
        appDAO.approveAppointment(appId);
        RequestDispatcher rd = request.getRequestDispatcher("DoctorDashboard.jsp");
        rd.include(request, response);
        Alert a=new Alert();
        PrintWriter out=response.getWriter();
        out.println(a.successAlert("Appointment Approved Successfully"));
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        doGet(request, response);
    }

 

}
 