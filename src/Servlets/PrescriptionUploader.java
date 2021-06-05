package Servlets;

import MigrateServlets.Alert;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/PrescriptionUploader")
public class PrescriptionUploader extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String appId = request.getParameter("appId");
        System.out.println(appId);
        boolean flag = ServletFileUpload.isMultipartContent(request);
        Alert a = new Alert();
        if (flag) {
            try {

                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multiparts) {
                    System.out.println("loop");
                    if (!item.isFormField()) {
                        System.out.println("If in");
                        String name = "C:/Users/ved.asole/Desktop/Project Workspace/AngelDoc-Latest/WebContent/Prescriptions" + File.separator + appId + ".pdf";
                        item.write(new File(name));
                        System.out.println("-----------------------" + name + "-----------------------");
                    }

                }
                System.out.println("File uploaded successfully");
                PrintWriter out = response.getWriter();
                RequestDispatcher rd = request.getRequestDispatcher("DoctorDashboard.jsp");
                rd.include(request, response);
                out.println(a.successAlert("File uploaded successfully"));

            } catch (Exception e) {
                e.printStackTrace();
                PrintWriter out = response.getWriter();
                RequestDispatcher rd = request.getRequestDispatcher("Prescription-Uploader.jsp?aid=" + appId);
                rd.include(request, response);
                out.println(a.failureAlert("Error uploading the file"));
                System.out.println("Error uploading the file");
            }

        } else {
            PrintWriter out = response.getWriter();
            RequestDispatcher rd = request.getRequestDispatcher("Prescription-Uploader.jsp?aid=" + appId);
            rd.include(request, response);
            out.println(a.failureAlert("Failed to upload file"));
            System.out.println("Failed to upload file");
        }
    }

}