package Hibernate;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.CoordinatorDAO;

public class Main 
{
	
	public static void main(String[] args) 
	{
		
		Logger log = Logger.getLogger("org.hibernate");
		log.setLevel(Level.OFF);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		
		SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session=factory.openSession();
		session.beginTransaction();
		CoordinatorDAO cdao=new CoordinatorDAO();
		Coordinator c=new Coordinator("Admin", "Profile","Admin","angeldocco@gmail.com","Angel@123","1234567890");
		cdao.create(c);
		session.getTransaction().commit();
		session.close();
		System.out.println("Done!!");
		
	}
	

}
