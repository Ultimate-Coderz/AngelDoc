package Hibernate;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;


@SuppressWarnings("deprecation")
public class HibernateUtil 
{
	
	static SessionFactory factory = null;

	static
	{
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Doctor.class)
				.addAnnotatedClass(Patient.class).addAnnotatedClass(Coordinator.class)
				.addAnnotatedClass(Appointment.class).addAnnotatedClass(Users.class).buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() 
	{

		return factory;

	}

}
