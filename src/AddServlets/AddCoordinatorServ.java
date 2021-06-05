package AddServlets;

import Hibernate.Coordinator;
import MigrateServlets.Alert;
import dao.CoordinatorDAO;
import dao.UsersDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddCoordinatorServ")
public class AddCoordinatorServ extends HttpServlet {

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

        boolean flag = true;
        boolean validate = true;
        String err = "Enter full details";

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String contactNum = request.getParameter("contactNum");
        String designation = request.getParameter("designation");

        if (firstName == null || lastName == null || contactNum == null || email == null || password == null ||
                firstName.equals("") || lastName.equals("") || contactNum.equals("") || email.equals("") || password.equals("")) {
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

        validate = validateName(lastName);
        if (validate) {
            Character c = lastName.charAt(0);
            String newName = Character.toUpperCase(c) + lastName.substring(1);
            lastName = newName;
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
                Coordinator cor = new Coordinator();
                cor.setFirstName(firstName);
                cor.setLastName(lastName);
                cor.setEMail(email);
                cor.setPassword(password);
                cor.setContactNumber(contactNum);
                cor.setDesignation(designation);

                CoordinatorDAO adao = new CoordinatorDAO();
                adao.create(cor);

                RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.jsp");
                rd.include(request, response);
                Alert a = new Alert();
                out.println(a.successAlert("Coordinator Added Successfully"));

            } else {
                RequestDispatcher rd = request.getRequestDispatcher("AddCoordinator.jsp");
                rd.include(request, response);
                Alert a = new Alert();
                out.println(a.failureAlert("User already exist"));
            }

        } else {

            RequestDispatcher rd = request.getRequestDispatcher("AddCoordinator.jsp");
            rd.include(request, response);
            Alert a = new Alert();
            out.println(a.failureAlert(err));
        }
    }

}