package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Hibernate.*;

public class UpdateDAO
{
	
	public boolean updateDoctor(Doctor doc, int id)
	{
		try
		{
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			Doctor d=(Doctor) session.get(Doctor.class, id);
			d.setFirstName(doc.getFirstName());
			d.setLastName(doc.getLastName());
			d.setDocSpec(doc.getDocSpec());
			d.seteMail(doc.geteMail());
			d.setCity(doc.getCity());
			d.setStartingHr(doc.getStartingHr());
			d.setEndingHr(doc.getEndingHr());
			d.setContactNumber(doc.getContactNumber());	
			d.setRoom(doc.getRoom());
			session.update(d);
			session.getTransaction().commit();
			session.close();
			boolean done=updateUser(id,doc.geteMail());
			if(done)
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
	
	public boolean updatePatient(Patient pat, int id)
	{
		try
		{
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			Patient p=(Patient) session.get(Patient.class, id);
			p.setFirstName(pat.getFirstName());
			p.setLastName(pat.getLastName());
			p.setpAge(pat.getpAge());
			p.setCity(pat.getCity());
			p.setpBloodGrp(pat.getpBloodGrp());
			p.setContactNumber(pat.getContactNumber());
			p.setEMail(pat.getEMail());
			session.update(p);
			session.getTransaction().commit();
			session.close();
			boolean done=updateUser(id,pat.getEMail());
			if(done)
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
	
	public boolean updateCoordinator(Coordinator cor, int id)
	{
		try
		{
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			Coordinator c=(Coordinator) session.get(Coordinator.class, id);
			c.setFirstName(cor.getFirstName());
			c.setLastName(cor.getLastName());
			c.setEMail(cor.getEMail());
			c.setContactNumber(cor.getContactNumber());
			session.update(c);
			session.getTransaction().commit();
			session.close();
			boolean done=updateUser(id,cor.getEMail());
			if(done)
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
	
	public boolean updateUser(int id, String email)
	{
		try
		{
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			Users u=(Users) session.get(Users.class, id);
			u.seteMail(email);
			session.update(u);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public boolean updatePatientPic(int id, String extension)
	{
		try
		{
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			Patient p=(Patient) session.get(Patient.class, id);
			String image="Image/Profiles/"+id+extension;
			p.setProfile(image);
			session.update(p);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateDoctorPic(int id, String extension)
	{
		try
		{
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			Doctor d=(Doctor) session.get(Doctor.class, id);
			String image="Image/Profiles/"+id+extension;
			d.setProfile(image);
			session.update(d);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateCoordinatorPic(int id, String extension)
	{
		try
		{
			SessionFactory factory=HibernateUtil.getSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			Coordinator c=(Coordinator) session.get(Coordinator.class, id);
			String image="Image/"+id+extension;
			c.setProfile(image);
			session.update(c);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
