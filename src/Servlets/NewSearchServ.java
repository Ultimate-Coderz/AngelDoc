package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Hibernate.Doctor;
import dao.SearchDAO;

@WebServlet("/NewSearchServ")
public class NewSearchServ extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String name=request.getParameter("name");
		String spec=request.getParameter("spec");
		String city=request.getParameter("city");
		
		List<Doctor> docList=new ArrayList<>();
		
		if("City".equals(city)) 
		{
			docList=SearchDAO.listAllDoctors();
			if("Specialization".equals(spec))
			{
				if("".equals(name) || name==null)
				{
					docList=SearchDAO.listAllDoctors();
					}
				else 
				{
					docList=SearchDAO.getAllDoctorsByName(name);
				}
			}
			else
			{
				docList=SearchDAO.listAllDoctors();
				if("".equals(name))
				{
					docList=SearchDAO.getAllDoctorsBySpec(docList, spec);
				}
				else
				{
					docList=SearchDAO.getAllDoctorsBySpec(docList, spec);
					docList=SearchDAO.getAllDoctorsByName(docList,name);
				}
			}
		}
		else
		{
			docList=SearchDAO.listAllDoctors();
			docList=SearchDAO.getAllDoctorsByCity(docList, city);			
			if("Specialization".equals(spec))
			{
				if( !("".equals(name) || name==null) )
				{
					docList=SearchDAO.getAllDoctorsByName(docList, name);
				}
			}
			else
			{
				docList=SearchDAO.getAllDoctorsBySpec(docList, spec);
				if( !("".equals(name)) )
				{
					docList=SearchDAO.getAllDoctorsByName(docList, name);
				}
			}
		}
		
		request.setAttribute("list", docList);
		RequestDispatcher rd=request.getRequestDispatcher("Search.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
