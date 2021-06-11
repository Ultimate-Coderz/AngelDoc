package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hibernate.*;
import MigrateServlets.Alert;
import dao.*;

@WebServlet("/DetailsServ")

public class DetailsServ extends HttpServlet
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
		
		String firstName = request.getParameter("First");
		String secondName = request.getParameter("Last");
		String email = request.getParameter("Email");
		String pass = request.getParameter("Password");
		String contactNum=request.getParameter("contactNum");
		
		int option = Integer.parseInt(request.getParameter("role"));
		
		UsersDAO udao=new UsersDAO();
		boolean flag = true;
		boolean validate=true;
		String err="Enter correct and full details";
	
		if(firstName==null ||  email==null || pass==null || secondName==null)
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
	        if(err.equals("Enter correct and full details"))
	        {
	        	err="Please enter a valid last name";
	        }
	        else
	        {
	        	err="Please enter a valid first and last name";
	        }
	    }
		
		if(validate)
		{
			String[] first = email.split("@");
			if(first.length==2)
			{
				if(!flag)
				{
					err="Enter correct email";
				}
				String[] second=first[1].split("[.]",0);
				if(second.length==2)
				{
					if(!flag)
					{
						err="Enter correct email";
					}
					flag=validateName(firstName);
					if(!flag)
					{
						if(err.equals("Enter correct and full details"))
						{
							err="Enter correct name";
						}
						else
						{
							err="Enter correct name and email";
						}
					}
				}
				else
				{
					flag=false;
				}
			}
			else
			{
				flag=false;
			}
		}
		
		if(flag)
		{
			
			if(udao.validateUserSignUp(email))
			{
				
				if(option==3)
				{
					String docSpec = request.getParameter("Specialization");
					String city = request.getParameter("City");
					String startingHr = request.getParameter("Starting_Hour");
					String endingHr = request.getParameter("Ending_Hour");
					Doctor d=new Doctor(firstName , secondName , docSpec  , email ,  pass , city ,startingHr , endingHr, contactNum);
					DoctorDAO ddao=new DoctorDAO();
					ddao.create(d);	
					d=ddao.getDoctor(d);
					HttpSession session=request.getSession();
					session.setAttribute("userObj", d);
					request.setAttribute("role", 3);
					request.setAttribute("new", true);
					RequestDispatcher rd = request.getRequestDispatcher("DashboardServ");
					rd.forward(request, response);
				}
				else if(option==4)
				{
					Integer pAge = Integer.parseInt(request.getParameter("Age"));
					String gender = request.getParameter("Gender");
					String city = request.getParameter("City");
					
					String bloodGroup = request.getParameter("BloodGroup");
					Patient p= new Patient(firstName , secondName  , pAge ,gender, city , bloodGroup , email , pass, contactNum);
					PatientDAO pdao=new PatientDAO();
					pdao.create(p);
					p=pdao.getPatient(p);
					HttpSession session=request.getSession();
					session.setAttribute("userObj", p);
					request.setAttribute("role", 4);
					request.setAttribute("new", true);
					RequestDispatcher rd = request.getRequestDispatcher("DashboardServ");
					rd.forward(request, response);
				}
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.include(request, response);
				Alert a=new Alert();
				out.println(a.failureAlert("User already exists"));
			}
			out.close();
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.include(request, response);
			Alert a=new Alert();
			out.println(a.failureAlert(err));
		}
		
		
	}
	
}
