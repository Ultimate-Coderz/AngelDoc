package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Hibernate.*;

public class AddUpdateDAO
{
	
	public Object getDetails(int id, int role)
	{
		
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		Object o=null;
		
		switch(role)
		{
			
			case 1:
			{
				session.beginTransaction();
				o=session.get(Coordinator.class, id);
				session.getTransaction().commit();
				session.close();
				return o;
			}
			case 2:
			{
				session.beginTransaction();
				o=session.get(Coordinator.class, id);
				session.getTransaction().commit();
				session.close();
				return o;
			}
			case 3:
			{
				session.beginTransaction();
				o=session.get(Doctor.class, id);
				session.getTransaction().commit();
				session.close();
				return o;
			}
			case 4:
			{
				session.beginTransaction();
				o=session.get(Patient.class, id);
				session.getTransaction().commit();
				session.close();
				return o;
			}
			case 5:
			{
				session.beginTransaction();
				o=session.get(Appointment.class, id);
				session.getTransaction().commit();
				session.close();
				return o;
			}
			default :
			{
				return o;
			}
		
		}	
		
	}
	
	public void addUser(int role, Object o)
	{
		
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		
		switch(role)
		{
			
			case 1:
			{
				session.beginTransaction();
				Coordinator cd=(Coordinator) o;
				session.save(cd);
				session.getTransaction().commit();
				session.close();
				break;
			}
			case 2:
			{
				session.beginTransaction();
				Coordinator cd=(Coordinator) o;
				session.save(cd);
				session.getTransaction().commit();
				session.close();
				break;
			}
			case 3:
			{
				session.beginTransaction();
				Doctor d=(Doctor) o;
				session.save(d);
				session.getTransaction().commit();
				session.close();
				break;
			}
			case 4:
			{
				session.beginTransaction();
				Patient p=(Patient) o;
				session.save(p);
				session.getTransaction().commit();
				session.close();
				break;
			}
			case 5:
			{
				session.beginTransaction();
				Appointment a=(Appointment) o;
				session.save(a);
				session.getTransaction().commit();
				session.close();
				break;
			}
		
		}	
		
	}

}
