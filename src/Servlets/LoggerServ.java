package Servlets;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@WebServlet("/LoggerServ")
public class LoggerServ extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Logger logger = Logger.getLogger(LoggerServ.class.getName());
        logger.setLevel(Level.INFO);
        InputStream input = new FileInputStream("C:\\Users\\ved.asole\\Desktop\\Project Workspace\\AngelDoc-Latest\\WebContent\\WEB-INF\\log4j.properties");
        PropertyConfigurator.configure(input);
        logger.info("This is a info message here");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

}
