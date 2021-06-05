package Servlets;

import Hibernate.Doctor;
import dao.SearchDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/NewSearchServ")
public class NewSearchServ extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String spec = request.getParameter("spec");
        String city = request.getParameter("city");

        List<Doctor> docList = new ArrayList<>();

        if ("City".equals(city)) {
            docList = SearchDAO.listAllDoctors();
            if ("Specialization".equals(spec)) {
                if ("".equals(name) || name == null) {
                    docList = SearchDAO.listAllDoctors();
                    System.out.println("1 Executed");
                } else {
                    docList = SearchDAO.getAllDoctorsByName(name);

                    System.out.println("3 Executed");
                }
            } else {
                docList = SearchDAO.listAllDoctors();
                if ("".equals(name)) {
                    docList = SearchDAO.getAllDoctorsBySpec(docList, spec);
                    System.out.println("4 Executed");

                } else {
                    docList = SearchDAO.getAllDoctorsBySpec(docList, spec);
                    docList = SearchDAO.getAllDoctorsByName(docList, name);
                    System.out.println("6 Executed");
                }
            }
        } else {
            docList = SearchDAO.listAllDoctors();
            System.out.println(docList.size());
            docList = SearchDAO.getAllDoctorsByCity(docList, city);
            System.out.println(docList.size());
            for (Doctor d : docList) {
                System.out.println(d);
            }
            if ("Specialization".equals(spec)) {
                if ("".equals(name) || name == null) {

                    System.out.println("7 Executed");
                } else {
                    docList = SearchDAO.getAllDoctorsByName(docList, name);
                    System.out.println("8 Executed");
                }
            } else {
                docList = SearchDAO.getAllDoctorsBySpec(docList, spec);
                System.out.println(docList.size());
                if ("".equals(name)) {
                    System.out.println("9 Executed");
                } else {
                    docList = SearchDAO.getAllDoctorsByName(docList, name);
                    System.out.println("10 Executed");
                }
            }
        }

        request.setAttribute("list", docList);
        RequestDispatcher rd = request.getRequestDispatcher("Search.jsp");
        rd.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
