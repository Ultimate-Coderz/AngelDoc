package dao;

import java.sql.SQLException;
import java.text.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Hibernate.*;

public class DoctorDashboardDAO
{

	public List<Appointment> getUpcomingAppointments(int id)
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
    	String hql="from Appointment where Doc_Id="+id + " and Status='Accepted'";
    	Query query=session.createQuery(hql);
    	List<Appointment> list = query.list();
    	session.getTransaction().commit();
    	session.close();
    	return list;
    	
	}
	
	public List<Appointment> getPendingAppointments(int id)
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
    	String hql="from Appointment where Doc_Id="+id + " and Status='Pending'";
    	Query query=session.createQuery(hql);
    	List<Appointment> list = query.list();
    	session.getTransaction().commit();
    	session.close();
    	return list;
    	
	}
	
	public List<Appointment> getPastAppointments(int id)
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
    	String hql="from Appointment where Doc_Id="+id + " and Status='Closed' and rownum<8";
    	Query query=session.createQuery(hql);
    	List<Appointment> list = query.list();
    	session.getTransaction().commit();
    	session.close();
    	return list;
    	
	}
	
	public Long getPatientsMet(int docId)
    {
       
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            String hql="select COUNT(DISTINCT Patient_id) from Appointment where doc_id="+docId;
            Query query=session.createQuery(hql);
            Long count = (Long)query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return count;
    }
   
    public Long getPendingCount(int id)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql="select COUNT(DISTINCT Appointment_Id) from Appointment where Doc_Id="+id + " and Status='Pending'";
        Query query=session.createQuery(hql);
        Long count = (Long)query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }
   
    public Long getPastCount(int id)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql="select COUNT(DISTINCT Appointment_Id) from Appointment where Doc_Id="+id + " and Status='Closed'";
        Query query=session.createQuery(hql);
        Long count = (Long)query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }
    

    public void updateStatus(int id) throws SQLException, ParseException
    {       
        //Getting Current time in long:-
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date utilDate = new java.util.Date();
        long currLongTimeD = utilDate.getTime();
       
       
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "from Appointment where doc_Id="+ id ;
        Query q = session.createQuery(hql);
        List<Appointment> list = q.list();
        session.getTransaction().commit();
        session.close();
        for(Appointment a : list)
        {
            String s = a.getSqlDate()+" "+a.getStartingTime()+":00";
            java.util.Date appDate = sfd.parse(s);
            long appLongTimeD = appDate.getTime();
            if((currLongTimeD-appLongTimeD)>1800000)
            {
                updateAppointment(a);
               
            }
           
        }
       
       
    }
   
    public void updateAppointment(Appointment a)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        a.setStatus("Closed");
        session.update(a);
        session.getTransaction().commit();
        session.close();
    }
   
	
}
