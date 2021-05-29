	package AddServlets;
	
	import java.io.IOException;
	import java.io.PrintWriter;
	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;
	
	import Hibernate.Coordinator;
	import Hibernate.Patient;
	import Hibernate.Users;
import MigrateServlets.Alert;
import dao.PatientDAO;
	import dao.UsersDAO;
	
	@WebServlet("/AddPatientServ")
	public class AddPatientServ extends HttpServlet 
	{
	    private static final long serialVersionUID = 1L;
	
	    public boolean validateName(String uname)
		{
			for(int i=0 ; i<uname.length() ; i++)
			{
				if(Character.isDigit(uname.charAt(i)))
				{
					return false;
				}
			}
			return true;
		}
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
	        PrintWriter out=response.getWriter();
	        response.setContentType("text/html");
	        
	        boolean flag=true;
	        boolean validate=true;
	        String err="Enter full details";
	        
	        String firstName=request.getParameter("firstName");
	        String secondName=request.getParameter("secondName");
	        int age=Integer.parseInt(request.getParameter("age"));
	        String gender=request.getParameter("gender");
	        String city=request.getParameter("city");
	        String bloodGroup=request.getParameter("bloodGroup");
	        String contactNum=request.getParameter("contactNum");
	        String email=request.getParameter("email");
	        String password=request.getParameter("password");
	        if(firstName==null || secondName==null|| age==0||gender==null||city==null||bloodGroup==null||email==null||password==null||contactNum==null ||
	        		firstName==null || secondName==null|| age==0||gender==null||city==null||bloodGroup==null||email==null||password==null||contactNum==null	)
	        {
	            flag=false;
	        }
	        
	        validate=validateName(firstName);        
	        if(validate)
	        {
	        	Character c=firstName.charAt(0);
				String newName=Character.toUpperCase(c)+firstName.substring(1);
				firstName=newName;
	        }
	        else
	        {
	        	err="Please enter a valid first name";
	        }
	        
	        validate=validateName(secondName);
	        if(validate)
	        {
	        	Character c=secondName.charAt(0);
				String newName=Character.toUpperCase(c)+secondName.substring(1);
				secondName=newName;
	        }
	        else
	        {
	        	if(err.equals("Enter full details"))
	        	{
	        		err="Please enter a valid last name";
	        	}
	        	else
	        	{
	        		err="Please enter a valid first and last name";
	        	}
	        }
	        
	        String[] first = email.split("@");
			if(first.length==2)
			{
				validate=false;
				String[] second=first[1].split("[.]",0);
				if(second.length==2)
				{
					validate=false;
					if(second[1].length()>=2 && second[1].length()<=4)
					{
						validate=true;
					}
				}
			}
			if(validate==false)
	        {			
	        	if(err.equals("Enter full details"))
	        	{
	        		err="Please enter a valid email";
	        	}
	        	else if(err.equals("Please enter a valid first name") || err.equals("Please enter a valid last name") )
	        	{
	        		err="Please enter a valid name and email";
	        	}
	        }
	        
	            if(flag && validate)
	            {        
	                UsersDAO udao=new UsersDAO();
	            
	                if(udao.validateUserSignUp(email))
	                {
	                    Patient pat=new Patient();
	                    pat.setFirstName(firstName);
	                    pat.setLastName(secondName);
	                    pat.setpAge(age);
	                    pat.setpGender(gender);
	                    pat.setCity(city);
	                    pat.setpBloodGrp(bloodGroup);
	                    pat.setContactNumber(contactNum);
	                    pat.setEMail(email);
	                    pat.setPassword(password);
	                    
	                    PatientDAO pdao=new PatientDAO();
	                    pdao.create(pat);
	                    
	                    HttpSession session =request.getSession();
	                    Coordinator cd=(Coordinator) session.getAttribute("userObj");
	                    
	                    if(cd.getId()==1)
	                    {
	                    	RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.jsp");
	                        rd.include(request, response);
	                        Alert a=new Alert();
	    	                out.println(a.successAlert("Patient Added Successfully"));
	                    }
	                    else
	                    {
	                    	RequestDispatcher rd = request.getRequestDispatcher("CoordinatorDashboard.jsp");
	                        rd.include(request, response);
	                        Alert a=new Alert();
	    	                out.println(a.successAlert("Patient Added Successfully"));
	                    }                   
	            }
	            else
	            {
	                RequestDispatcher rd = request.getRequestDispatcher("AddPatient.jsp");
	                rd.include(request, response);
	                Alert a=new Alert();
	                out.println(a.failureAlert("User already exist"));
	            }
	        
	        }else
	        {
	        
	        	RequestDispatcher rd = request.getRequestDispatcher("AddPatient.jsp");
	            rd.include(request, response);
	            Alert a=new Alert();
                out.println(a.failureAlert(err));
	        }
	
	 
	
	    }
	}