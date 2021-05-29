package dao;

 

import Hibernate.*;

 

import java.sql.Time;
import java.util.List;

 

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

 

public class IndexDAO
{
	
	    public Long getDoctors()
	    {
	        SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        String hql = "select COUNT(docId) from Doctor";
	        Query query = session.createQuery(hql);
	        Long count =  (Long) query.uniqueResult();
	        session.getTransaction().commit();
	        session.close();
	        return count;
	    }
	    
        public Long getPatients()
        {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            String hql = "select COUNT(pId) from Patient";
            Query query = session.createQuery(hql);
            Long count =  (Long) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return count;
        }
        
        public Long getDepartments()
        {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            String hql = "select COUNT(DISTINCT docSpec) from Doctor";
            Query query = session.createQuery(hql);
            Long count =  (Long) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return count;
        }
        
        public int support()
        {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            String hql = "from Appointment";
            Query query = session.createQuery(hql);
            List<Appointment> list =query.list();
            double min=0;
            for(Appointment a : list)
            {
                String endingTime = a.getEndingTime();
                String startingTime = a.getStartingTime();
                Time st = Time.valueOf(startingTime+":00");
                Time et = Time.valueOf(endingTime+":00");
                long stlong = st.getTime();
                long etlong = et.getTime();
                long diff=etlong-stlong;
                min += diff/(60*1000);
            }
            session.getTransaction().commit();
            session.close();
            int hr=(int)(min/60);
            return hr;
        }
        
        
}