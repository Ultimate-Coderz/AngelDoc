package LoginServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MigrateServlets.Alert;
import dao.UsersDAO;

@WebServlet("/SignUpServ")

public class SignUpServ extends HttpServlet 
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
		
		String uname = request.getParameter("uname");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		UsersDAO udao=new UsersDAO();
		String err="";
		boolean flag = true;
		Alert a=new Alert();
	
		if(uname==null ||  email==null || pass==null)
		{
			flag=false;
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.include(request, response);
			
			if(uname==null || uname.equals("") )
			{
				out.println(a.failureAlert("Please enter a name"));
			}
			if(email==null || email.equals("") )
			{
				out.println(a.failureAlert("Please enter a email"));
			}
			if(pass==null || pass.equals("") )
			{
				out.println(a.failureAlert("Please enter a password"));
			}
		}
		else
		{
			String[] first = email.split("@");
			if(first.length==2)
			{
				String[] second=first[1].split("[.]",0);
				if(second.length==2)
				{
					if(second[1].length()>=2 && second[1].length()<=4)
                        flag=validateName(uname);
                        else
                            flag=false;
					if(flag==true)
					{
						Character c=uname.charAt(0);
						String newName=Character.toUpperCase(c)+uname.substring(1);
						uname=newName;
					}
					else
					{
						err="Name is not valid";
					}
				}
				else
				{
					flag=false;
					if(err.equals(""))
					{
						err="Email is not valid";
					}
					else
					{
						err="Name and email are not valid";
					}
				}
			}
			else
			{
				flag=false;
				if(err.equals(""))
				{
					err="Name and email are not valid";
				}
				else
				{
					err="Email is not valid";
				}
			}
		}
		
		if(flag)
		{
			if(udao.validateUserSignUp(email))
			{
				request.setAttribute("uname", uname);
				request.setAttribute("email", email);
				request.setAttribute("pass", pass);
				RequestDispatcher rd = request.getRequestDispatcher("Details.jsp");
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.include(request, response);
				out.println(a.failureAlert("User already exists"));
			}
			out.close();
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.include(request, response);
			out.println(a.failureAlert(err));
		}
		
	}

}
