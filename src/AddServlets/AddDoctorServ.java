package AddServlets;


import Hibernate.Coordinator;
import Hibernate.Doctor;
import MigrateServlets.Alert;
import dao.DoctorDAO;
import dao.UsersDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddDoctorServ")
public class AddDoctorServ extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public boolean validateName(String uname) {

        for (int i = 0; i < uname.length(); i++) {
            if (Character.isDigit(uname.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String special = request.getParameter("special");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String city = request.getParameter("city");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String contactNum = request.getParameter("contactNum");

        boolean flag = true;
        boolean validate = true;
        String err = "Enter full details";

        if (firstName == null || secondName == null || special == null || city == null || startTime == null || endTime == null || email == null || password == null || contactNum == null ||
                firstName.equals("") || secondName.equals("") || special.equals("") || city.equals("") || startTime.equals("") || endTime.equals("") || email.equals("") || password.equals("") || contactNum.equals("")) {
            flag = false;
        }

        validate = validateName(firstName);
        if (validate) {
            Character c = firstName.charAt(0);
            String newName = Character.toUpperCase(c) + firstName.substring(1);
            firstName = newName;
        } else {
            err = "Please enter a valid first name";
        }

        validate = validateName(secondName);
        if (validate) {
            Character c = secondName.charAt(0);
            String newName = Character.toUpperCase(c) + secondName.substring(1);
            secondName = newName;
        } else {
            if (err.equals("Enter full details")) {
                err = "Please enter a valid last name";
            } else {
                err = "Please enter a valid first and last name";
            }
        }

        String[] first = email.split("@");
        if (first.length == 2) {
            validate = false;
            String[] second = first[1].split("[.]", 0);
            if (second.length == 2) {
                validate = false;
                if (second[1].length() >= 2 && second[1].length() <= 4) {
                    validate = true;
                }
            }
        }
        if (validate == false) {
            if (err.equals("Enter full details")) {
                err = "Please enter a valid email";
            } else if (err.equals("Please enter a valid first name") || err.equals("Please enter a valid last name")) {
                err = "Please enter a valid name and email";
            }
        }

        if (flag && validate) {
            UsersDAO udao = new UsersDAO();

            if (udao.validateUserSignUp(email)) {


                Doctor doc = new Doctor();
                doc.setFirstName(firstName);
                doc.setLastName(secondName);
                doc.setDocSpec(special);
                doc.seteMail(email);
                doc.setPassword(password);
                doc.setCity(city);
                doc.setStartingHr(startTime);
                doc.setEndingHr(endTime);
                doc.setContactNumber(contactNum);

                DoctorDAO adao = new DoctorDAO();
                adao.create(doc);

                HttpSession session = request.getSession();
                Coordinator cd = (Coordinator) session.getAttribute("userObj");

                if (cd.getId() == 1) {
                    RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.jsp");
                    rd.include(request, response);
                    Alert a = new Alert();
                    out.println(a.successAlert("Doctor Added Successfully"));
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("CoordinatorDashboard.jsp");
                    rd.include(request, response);
                    Alert a = new Alert();
                    out.println(a.successAlert("Doctor Added Successfully"));
                }

            } else {
                RequestDispatcher rd = request.getRequestDispatcher("AddDoctor.jsp");
                rd.include(request, response);
                Alert a = new Alert();
                out.println(a.failureAlert("User already exist"));
            }

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("AddDoctor.jsp");
            rd.include(request, response);
            Alert a = new Alert();
            out.println(a.failureAlert(err));
        }

    }

}