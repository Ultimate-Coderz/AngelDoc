package UpdateServlets;

import Hibernate.Coordinator;
import Hibernate.Doctor;
import Hibernate.Patient;
import dao.CommonDAO;
import dao.UpdateDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ProfilePicUpdate")
public class ProfilePicUpdate extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dashName = "";
        int id = 0;

        if (request.getSession().getAttribute("userObj") != null) {

            HttpSession s = request.getSession();
            System.out.println(s.getAttribute("userObj"));

            if (s.getAttribute("userObj") instanceof Doctor) {
                dashName = "DoctorDashboard.jsp";
                Doctor d = (Doctor) s.getAttribute("userObj");
                id = d.getDocId();
                System.out.println("DocId = " + id);
            } else if (s.getAttribute("userObj") instanceof Patient) {
                dashName = "PatientDashboard.jsp";
                Patient p = (Patient) s.getAttribute("userObj");
                id = p.getpId();
                System.out.println("PatId = " + id);
            } else if (s.getAttribute("userObj") instanceof Coordinator) {
                Coordinator c = (Coordinator) s.getAttribute("userObj");

                if (c.getId() == 1) {
                    dashName = "AdminDashboard.jsp";
                    id = 1;
                    System.out.println("AddId = " + id);
                } else {
                    dashName = "CoordinatorDashboard.jsp";
                    id = c.getId();
                    System.out.println("CorId = " + id);
                }
            }
        }

        boolean flag = ServletFileUpload.isMultipartContent(request);

        if (flag) {
            try {

                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multiparts) {
                    System.out.println("loop");
                    if (!item.isFormField()) {
                        int temp = item.getName().indexOf(".");
                        String fileExt = item.getName().substring(temp);
                        String newFileName = id + fileExt;
                        System.out.println(newFileName);
                        String name = "C:/Users/ved.asole/Desktop/Project Workspace/AngleDoc/WebContent/Image" + File.separator + newFileName;
                        item.write(new File(name));
                        System.out.println("-----------------------" + name + "-----------------------");
                        CommonDAO cdao = new CommonDAO();
                        int role = cdao.getRole(id);
                        if (role == 1) {
                            UpdateDAO udao = new UpdateDAO();
                            udao.updateCoordinatorPic(id, fileExt);
                        } else if (role == 2) {
                            UpdateDAO udao = new UpdateDAO();
                            udao.updateCoordinatorPic(id, fileExt);
                        } else if (role == 3) {
                            UpdateDAO udao = new UpdateDAO();
                            udao.updateDoctorPic(id, fileExt);
                        } else if (role == 2) {
                            UpdateDAO udao = new UpdateDAO();
                            udao.updatePatientPic(id, fileExt);
                        }
                    }

                }
                System.out.println("File uploaded successfully");
                PrintWriter out = response.getWriter();
                RequestDispatcher rd = request.getRequestDispatcher(dashName);
                rd.include(request, response);
                out.println("<html><body><center>");
                out.println("<script> alert('Profile Image changed successfully'); </script>");
                out.println("</center></body></html>");

            } catch (Exception e) {
                e.printStackTrace();
                PrintWriter out = response.getWriter();
                RequestDispatcher rd = request.getRequestDispatcher(dashName);
                rd.include(request, response);
                out.println("<html><body><center>");
                out.println("<script> alert('Error uploading the image'); </script>");
                out.println("</center></body></html>");
                System.out.println("Error uploading the file");
            }

        } else {
            PrintWriter out = response.getWriter();
            RequestDispatcher rd = request.getRequestDispatcher(dashName);
            rd.include(request, response);
            out.println("<html><body><center>");
            out.println("<script> alert('Please provide a valid file'); </script>");
            out.println("</center></body></html>");
            System.out.println("File failed to be uploaded");
        }
    }

}
