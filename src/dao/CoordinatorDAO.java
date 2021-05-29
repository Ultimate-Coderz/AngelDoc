package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Hibernate.*;

public class CoordinatorDAO extends CommonDAO
{


	public void create(Coordinator c)
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		session.close();
		addUser(c);
	}
	
	public void update(Coordinator e) 
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(e);
		session.getTransaction().commit();
		session.close();
	}
	

	public Coordinator find(int id)
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		List<Coordinator> corList=new ArrayList<>();
		session.beginTransaction();
		String hql="from Coordinator where id="+id;
		Query query = session.createQuery(hql);
		corList=query.list();
		session.getTransaction().commit();
		session.close();
		if(corList.size()==0)
		{
			return null;
		}
		else
		{
			return corList.get(0);
		}
	}
	
	public Coordinator find(String email)
	{
		System.out.println("ManagementDAO");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="from Coordinator where e_mail='"+email+"'";
		Query query = session.createQuery(hql);
		Coordinator e = (Coordinator) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return e;
	}
	
	public Coordinator getCoordinator(Coordinator c)
	{
		
		System.out.println("CoordinatorDAO");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="from Coordinator where e_mail='"+c.getEMail()+"'";
		Query query = session.createQuery(hql);
		c = (Coordinator) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return c;
		
	}
	
	public void addUser(Coordinator c)
	{
		
		System.out.println("CoordinatorDAO");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="from Coordinator where e_mail='"+c.getEMail()+"'";
		Query query = session.createQuery(hql);
		c = (Coordinator) query.list().get(0);
		int id=c.getId();
		session.getTransaction().commit();
		session.close();
				
		Session session1 = factory.openSession();
		Users u=new Users(id, c.getEMail(), c.getPassword());
		session1.beginTransaction();		
		session1.save(u);
		session1.getTransaction().commit();
		session1.close();
		
	}
	
	public void delete(int id)
	{
		
		Coordinator c=find(id);
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();
		session.close();
		UsersDAO udao=new UsersDAO();
		udao.delete(id);
		
	}
	
}
