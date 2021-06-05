package Servlets;

import dao.UsersDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ForgetPassword")
public class ForgetPassword extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("email") == null) {
            System.out.println("Liza == noob");
            response.sendRedirect("ForgetPassword.jsp");
        }
        String email = request.getParameter("email");
        UsersDAO udao = new UsersDAO();
        int otp;
        while (true) {
            otp = (int) (Math.random() * 10000);
            if (otp < 1000) {
                otp = (int) (Math.random() * 10000);
            } else {
                break;
            }
        }

        boolean correct = udao.validateUserSignUp(email);
        if (!correct) {
            request.setAttribute("email", email);
            request.setAttribute("correct", false);
            request.setAttribute("otp", otp);
            RequestDispatcher rd = request.getRequestDispatcher("ForgetPasswordOtp.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("email", email);
            request.setAttribute("correct", true);
            RequestDispatcher rd = request.getRequestDispatcher("ForgetPassword.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }

}
