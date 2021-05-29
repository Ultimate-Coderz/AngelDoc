package dao;

import java.util.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Hibernate.*;

public class UsersDAO extends CommonDAO
{
	
	public Users validateUserLogin(String email, String pass)
	{
		
		try
		{
			List<Object> list=new ArrayList<>();
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			String hql="from Users where e_mail='"+email+"' and password='"+pass+"'";
			Query query=session.createQuery(hql);
			list=query.list();
			session.getTransaction().commit();
			session.close();
			if(list.size()==0)
			{
				return null;
			}
			return (Users) list.get(0);
		}
		catch(Exception e)		
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addUser(Users u)
	{
		try 
		{
			List<Object> list=new ArrayList<>();
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			session.save(u);
			session.getTransaction().commit();
			session.close();
			return true;
		}			
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validateUserSignUp(String email)
    {
        try
        {
			List<Users> list=new ArrayList<>();
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			String hql="from Users where e_mail='"+email+"'";
			Query query=session.createQuery(hql);
			list=query.list();
			session.getTransaction().commit();
			session.close();
			if(list.size()==0)
			{
				return true;
			}
			return false;
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
    }
	
	public Users getUser(int id)
	{
		
		List<Object> list=new ArrayList<>();
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		String hql="from Users where user_id="+id;
		Query query=session.createQuery(hql);		
		list=query.list();
		session.getTransaction().commit();
		session.close();
		if(list.size()==0)
		{
			return null;
		}
		return (Users) list.get(0);
		
	}

	public void delete(int id) 
	{
		Users u=getUser(id);		
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		session.delete(u);
		session.getTransaction().commit();
		session.close();
	}
	
	public Users find(String email)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql="from Users where e_mail='"+email+"'";
        Query query = session.createQuery(hql);
        Users e = (Users) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return e;
    }

	public void update(Users e) 
	{
		SessionFactory factory = HibernateUtil.getSessionFactory(); 		
		Session session = factory.openSession(); 		
		session.beginTransaction(); 		
		session.update(e); 		
		session.getTransaction().commit(); 		
		session.close(); 	
	}

}
