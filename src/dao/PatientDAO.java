package dao;

import Hibernate.Patient;
import Hibernate.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PatientDAO extends CommonDAO {

    public void create(Patient p) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
        addUser(p);
    }

    public void update(Patient e) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(e);
        session.getTransaction().commit();
        session.close();
    }

    public Patient find(int pid) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "from Patient where PATIENT_ID=" + pid;
        Query query = session.createQuery(hql);
        Patient e = (Patient) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return e;
    }

    public Patient find(String email) {

        System.out.println("PatientDAO");
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "from Patient where e_mail='" + email + "'";
        Query query = session.createQuery(hql);
        Patient e = (Patient) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return e;
    }

    public int getIdByEmail(String email) {

        System.out.println("PatientDAO");
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "from Patient where e_mail='" + email + "'";
        Query query = session.createQuery(hql);
        Patient e = (Patient) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return e.getpId();
    }

    public Patient getPatient(Patient p) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "from Patient where e_mail='" + p.getEMail() + "'";
        Query query = session.createQuery(hql);
        p = (Patient) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return p;
    }

    public void addUser(Patient p) {

        System.out.println("PatientDAO");
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "from Patient where e_mail='" + p.getEMail() + "'";
        Query query = session.createQuery(hql);
        p = (Patient) query.uniqueResult();
        int id = p.getpId();
        session.getTransaction().commit();
        session.close();

        Session session1 = factory.openSession();
        Users u = new Users(id, p.getEMail(), p.getPassword());
        session1.beginTransaction();
        session1.save(u);
        session1.getTransaction().commit();
        session1.close();

    }

    public void delete(int id) {

        Patient p = find(id);
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "delete from Appointment where Patient_Id=" + p.getpId();
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        Session session1 = factory.openSession();
        session1.beginTransaction();
        session1.delete(p);
        session1.getTransaction().commit();
        UsersDAO udao = new UsersDAO();
        udao.delete(id);

    }

}
