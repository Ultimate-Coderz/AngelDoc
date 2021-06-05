package AppointmentServlet;

import Hibernate.Coordinator;
import Hibernate.Doctor;
import Hibernate.Patient;
import MigrateServlets.Alert;
import dao.AppointmentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RejectAppointmentServ")
public class RejectAppointmentServ extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int appId = Integer.parseInt(request.getParameter("appId"));
        String x = "";
        AppointmentDAO appDAO = new AppointmentDAO();
        appDAO.deleteRejectAppointment(appId);
        HttpSession session = request.getSession();
        Object o = session.getAttribute("userObj");
        if (o instanceof Doctor) x = "DoctorDashboard.jsp";
        else if (o instanceof Patient) x = "PatientDashboard.jsp";
        else {
            Coordinator c = (Coordinator) o;
            if (c.getId() == 1) x = "AdminDashboard.jsp";
            else
                x = "CoordinatorDashboard.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(x);
        rd.include(request, response);
        Alert a = new Alert();
        PrintWriter out = response.getWriter();
        out.println(a.successAlert("Appointment Rejected Successfully"));

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }


}