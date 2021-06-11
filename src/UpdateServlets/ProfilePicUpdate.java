package UpdateServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Hibernate.Coordinator;
import Hibernate.Doctor;
import Hibernate.Patient;
import MigrateServlets.Alert;
import dao.CommonDAO;
import dao.UpdateDAO;

@WebServlet("/ProfilePicUpdate")
public class ProfilePicUpdate extends HttpServlet
{
	
private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        String dashName="";
        int id = 0;
        
        if(request.getSession().getAttribute("userObj")!=null)
        {

        	HttpSession s=request.getSession();
        	System.out.println(s.getAttribute("userObj"));
        	
        	if(s.getAttribute("userObj") instanceof Doctor)
        	{
        		dashName="DoctorDashboard.jsp";
        		Doctor d=(Doctor) s.getAttribute("userObj");
        		id=d.getDocId();
        		}
        	else if(s.getAttribute("userObj") instanceof Patient)
        	{
        		dashName="PatientDashboard.jsp";
        		Patient p= (Patient) s.getAttribute("userObj");
        		id=p.getpId();
        		}
        	else if(s.getAttribute("userObj") instanceof Coordinator)
        	{
        		Coordinator c=(Coordinator) s.getAttribute("userObj");
    			
        		if(c.getId()==1)
        		{
        			dashName="AdminDashboard.jsp";
        			id=1;
            	}
        		else
        		{
        			dashName="CoordinatorDashboard.jsp";
        			id=c.getId();
            	}
        	}
        }
        
        boolean flag=ServletFileUpload.isMultipartContent(request);
        
        if(flag)
        {
            try
            {
                
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item:multiparts)
                {
                    if(!item.isFormField())
                    {
                    	int temp=item.getName().indexOf(".");
                    	String fileExt=item.getName().substring(temp);
                    	String newFileName=id+fileExt;
                    	String name="C:/Users/ved.asole/Desktop/Project Workspace/AngleDoc/WebContent/Image"+ File.separator + newFileName;
                        item.write(new File(name));
                        CommonDAO cdao=new CommonDAO();
                        int role=cdao.getRole(id);                        
                        if(role==1)
                        {
                        	UpdateDAO udao=new UpdateDAO();
                        	udao.updateCoordinatorPic(id, fileExt);
                        }
                        else if(role==2)
                        {
                        	UpdateDAO udao=new UpdateDAO();
                        	udao.updateCoordinatorPic(id, fileExt);
                        }
                        else if(role==3)
                        {
                        	UpdateDAO udao=new UpdateDAO();
                        	udao.updateDoctorPic(id, fileExt);
                        }
                        else if(role==2)
                        {
                        	UpdateDAO udao=new UpdateDAO();
                        	udao.updatePatientPic(id, fileExt);
                        }
                    }	
                    
                }
                PrintWriter out=response.getWriter();
                RequestDispatcher rd=request.getRequestDispatcher(dashName);
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.successAlert("Profile Image changed successfully"));
                
            }catch(Exception e)
            {
            	e.printStackTrace();
            	PrintWriter out=response.getWriter();
            	RequestDispatcher rd=request.getRequestDispatcher(dashName);
                rd.include(request, response);
                Alert a=new Alert();
                out.println(a.failureAlert("Error uploading the image"));
            }
            
        }
        else
        {
        	PrintWriter out=response.getWriter();
        	RequestDispatcher rd=request.getRequestDispatcher(dashName);
            rd.include(request, response);
            Alert a=new Alert();
            out.println(a.failureAlert("Please provide a valid file"));
        }
    }

}
