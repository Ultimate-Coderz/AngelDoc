package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Hibernate.*;
import dao.HibernateUtil;

public class DoctorDAO extends CommonDAO
{
	
	public Doctor getDoctor(int id)
	{
		
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		Doctor doc=(Doctor) session.get(Doctor.class, id);
		session.getTransaction().commit();
		session.close();
		return doc;		
		
	}
	
	public void create(Doctor d) 
	{
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(d);
		session.getTransaction().commit(); 	
		session.close();
		addUser(d);
	}
	
	public void update(Doctor e) 
	{
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(e);
		session.getTransaction().commit();
		session.close();
	}
	
	public Doctor find(int did)
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="from Doctor where doc_id="+did;
		Query query = session.createQuery(hql);
		Doctor e = (Doctor) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return e;
	}
	
	public Doctor find(String email)
	{
		System.out.println("DoctorDAO");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="from Doctor where e_mail='"+email+"'";
		Query query = session.createQuery(hql);
		Doctor e = (Doctor) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return e;
	}
	
	public Doctor getDoctor(Doctor d)
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="from Doctor where e_mail='"+d.geteMail()+"'";
		Query query = session.createQuery(hql);
		d = (Doctor) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return d;
	}
	
	public void addUser(Doctor d)
	{
		
		System.out.println("DoctorDAO");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="from Doctor where id="+d.getDocId();
		Query query = session.createQuery(hql);
		d = (Doctor) query.uniqueResult();		
		int id=d.getDocId();
		session.getTransaction().commit();
		session.close();
		
		Session session1 = factory.openSession();
		session1.beginTransaction();
		Users u=new Users(id, d.geteMail(), d.getPassword());
		session1.save(u);
		session1.getTransaction().commit();
		session1.close();
		
	}

	public void delete(int id)
	{
		
		Doctor d=find(id);
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="delete from Appointment where Doc_Id="+d.getDocId();
		Query query=session.createQuery(hql);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		Session session1 = factory.openSession();
		session1.beginTransaction();
		session1.delete(d);
		session1.getTransaction().commit();
		session1.close();
		UsersDAO udao=new UsersDAO();
		udao.delete(id);
		
	}

}
