package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Hibernate.*;

public class CoordinatorDashboardDAO
{

	public List<Doctor> getDoctors()
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
    	String hql="from Doctor order by Doc_Id";
    	Query query=session.createQuery(hql);
    	List<Doctor> list = query.list();
    	session.getTransaction().commit();
    	session.close();
    	return list;
    	
	}
	
	public List<Patient> getPatients()
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
    	String hql="from Patient";
    	Query query=session.createQuery(hql);
    	List<Patient> list = query.list();
    	session.getTransaction().commit();
    	session.close();
    	return list;
    	
	}
	
	public List<Appointment> getAppointments()
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
    	String hql="from Appointment where rownum<20";
    	Query query=session.createQuery(hql);
    	List<Appointment> list = query.list();
    	session.getTransaction().commit();
    	session.close();
    	return list;
    	
	}
	
	 public long getDoctorCount()
	    {
	        SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        String hql="select COUNT(DISTINCT doc_id) from Doctor";
	        Query query=session.createQuery(hql);
	        Long count = (Long)query.uniqueResult();
	        session.getTransaction().commit();
	        session.close();
	        return count;
	    }
	   
	    public long getPatientCount()
	    {
	        SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        String hql="select COUNT(DISTINCT Patient_Id) from Patient";
	        Query query=session.createQuery(hql);
	        Long count = (Long)query.uniqueResult();
	        session.getTransaction().commit();
	        session.close();
	        return count;
	    }
	   
	    public long getAppointmentCount()
	    {
	        SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        String hql="select COUNT(DISTINCT Appointment_Id) from Appointment";
	        Query query=session.createQuery(hql);
	        Long count = (Long)query.uniqueResult();
	        session.getTransaction().commit();
	        session.close();
	        return count;
	    }
	
}
