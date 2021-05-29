package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Hibernate.Appointment;

public class AppointmentDAO 
{
	
	public Appointment getAppointment(int id)
	{
		
		Appointment app=null;
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		app=(Appointment) session.get(Appointment.class, id);
		session.getTransaction().commit();
		session.close();
		return app;		
	}

	public void create(Appointment app) 
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(app);
        session.getTransaction().commit();
        session.close();
	}
	
	public void approveAppointment(int id)
    {
        Appointment app = getAppointment(id);
        app.setStatus("Accepted");
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session=factory.openSession();
        session.beginTransaction();
        session.update(app);
        session.getTransaction().commit();
        session.close();
       
    }
   
    public void deleteRejectAppointment(int id)
    {
        Appointment app = getAppointment(id);
        app.setStatus("Rejected");
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session=factory.openSession();
        session.beginTransaction();
        session.update(app);
        session.getTransaction().commit();
        session.close();
    }

}
