package dao;

import Hibernate.Appointment;
import Hibernate.Coordinator;
import Hibernate.Doctor;
import Hibernate.Patient;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ResourceBundle;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("oracle");
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            cfg.setProperty("hibernate.connection.url", rb.getString("db.url"));
            cfg.setProperty("hibernate.connection.username", rb.getString("db.username"));
            cfg.setProperty("hibernate.connection.password", rb.getString("db.password"));

            sessionFactory = cfg.addAnnotatedClass(Coordinator.class).addAnnotatedClass(Doctor.class).addAnnotatedClass(Patient.class).addAnnotatedClass(Appointment.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory initial creation error." + ex);
        }
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }

}