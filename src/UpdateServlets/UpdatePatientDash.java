package UpdateServlets;

import Hibernate.Patient;
import dao.UpdateDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdatePatientDash")
public class UpdatePatientDash extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Patient pat = (Patient) session.getAttribute("userObj");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        String city = request.getParameter("city");
        String bloodGroup = request.getParameter("bloodGroup");
        String contactNum = request.getParameter("contactNum");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        pat.setFirstName(firstName);
        pat.setLastName(lastName);
        pat.setpAge(age);
        pat.setCity(city);
        pat.setpBloodGrp(bloodGroup);
        pat.setContactNumber(contactNum);
        pat.setEMail(email);
        pat.setPassword(password);

        UpdateDAO adao = new UpdateDAO();
        boolean status = adao.updatePatient(pat, pat.getpId());

        request.setAttribute("name", "Patient");
        request.setAttribute("status", status);
        RequestDispatcher rd = request.getRequestDispatcher("UpdateToDashboard");  //Change the link!!!!
        rd.forward(request, response);

    }

}
