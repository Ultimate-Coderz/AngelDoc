package UpdateServlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Hibernate.Doctor;
import dao.UpdateDAO;

@WebServlet("/UpdateDoctorDash")
public class UpdateDoctorDash extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        HttpSession session=request.getSession();
        Doctor doc=(Doctor) session.getAttribute("userObj");
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String special=request.getParameter("special");
        String email=request.getParameter("email");
        String city=request.getParameter("city");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        String contactNum=request.getParameter("contactNum");
        
        doc.setFirstName(firstName);
        doc.setLastName(lastName);
        doc.setDocSpec(special);
        doc.seteMail(email);
        doc.setCity(city);
        doc.setStartingHr(startTime);
        doc.setEndingHr(endTime);
        doc.setContactNumber(contactNum);
        
        UpdateDAO adao=new UpdateDAO();
        boolean status=adao.updateDoctor(doc, doc.getDocId());
                
        request.setAttribute("name", "Doctor");
        request.setAttribute("status", status);    
        session.removeAttribute("userObj");
        session.setAttribute("userObj",doc);
        RequestDispatcher rd=request.getRequestDispatcher("UpdateToDashboard");  //Change the link!!!!
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doPost(request,response);
    }
}