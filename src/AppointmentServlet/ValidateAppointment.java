package AppointmentServlet;

import Hibernate.Appointment;
import Hibernate.Doctor;
import MigrateServlets.Alert;
import dao.DoctorDashboardDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ValidateAppointment")
public class ValidateAppointment extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Doctor doc = (Doctor) session.getAttribute("docObj");
        String appointmentStartingTime = (String) request.getParameter("startingTime");
        Time appStartTime = Time.valueOf(appointmentStartingTime + ":00");
        String dateString = request.getParameter("date");
        Date date = Date.valueOf(dateString);
        long start = appStartTime.getTime();
        long end = appStartTime.getTime() + 30 * 60 * 1000;
        Time appEndTime = new Time(end);
        String appointmentEndingTime = appEndTime.toString().substring(0, 5);

        //sysout start
        System.out.println("String end time = " + appointmentEndingTime);
        System.out.println("end time = " + appEndTime);

        //sysout end
        int i = 1;
        boolean flag = true;

        System.out.println("appStartTime.compareTo(appEndTime)" + (end - start));
        if ((end - start) >= 900000) {

            DoctorDashboardDAO docDashDAO = new DoctorDashboardDAO();
            List<Appointment> docUpcomingList = docDashDAO.getUpcomingAppointments(doc.getDocId());
            List<Appointment> docUpcomingDateList = new ArrayList<Appointment>();
            System.out.println("Upcoming size = " + docUpcomingList.size());
            if (docUpcomingList.size() > 0) {

                for (Appointment a : docUpcomingList) {
                    if (a.getSqlDate().equals(date))
                        docUpcomingDateList.add(a);
                }
                System.out.println("Date upcoming size = " + docUpcomingDateList.size());

                for (Appointment a : docUpcomingDateList) {
                    System.out.println("No of times ");

                    Time x1 = Time.valueOf(a.getStartingTime() + ":00");
                    Time x2 = Time.valueOf(a.getEndingTime() + ":00");
                    if (appStartTime.compareTo(x1) == 0 || appEndTime.compareTo(x2) == 0) {
                        System.out.println("In == wala validation");
                        flag = false;
                        break;
                    } else {
                        if (appStartTime.compareTo(x1) < 0) {
                            System.out.println("when given time is before start time");
                            if (appEndTime.compareTo(x1) <= 0)
                                System.out.println("when end time is before start time of already");
                            else {
                                System.out.println("When false: - " + a.getAppId());
                                flag = false;
                                break;
                            }
                        } else {

                            if (appStartTime.compareTo(x2) >= 0) {
                                System.out.println("when given time is after end time");
                                flag = true;
                            } else {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            } else {
                System.out.println("in upcoming minus hello");
                flag = true;
            }

            if (flag) {
                //forward to payment.jsp
                RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
                request.setAttribute("startingTime", appointmentStartingTime);
                request.setAttribute("date", dateString);
                rd.forward(request, response);

            } else {
                if (i == 1) {
                    PrintWriter out = response.getWriter();
                    RequestDispatcher rd = request.getRequestDispatcher("Appoint.jsp?id=" + doc.getDocId());
                    rd.include(request, response);
                    Alert a = new Alert();
                    out.println(a.failureReport("Can't book Appointment", "Doctor is not available in this time-slot"));
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("Appoint.jsp");
                    request.setAttribute("Doctor", doc);
                    rd.forward(request, response);
                }
            }

        } else {
            i = 0;
            flag = false;
            PrintWriter out = response.getWriter();
            RequestDispatcher rd = request.getRequestDispatcher("Appoint.jsp");
            rd.include(request, response);
            request.setAttribute("Doctor", doc);
            Alert a = new Alert();
            out.println(a.failureReport("Can't book Appointment", "Starting time must be atleast 10 minutes less than the ending time"));
        }


    }

}