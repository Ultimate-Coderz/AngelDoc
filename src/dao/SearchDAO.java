package dao;

import Hibernate.Doctor;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchDAO {

    public static List<Doctor> listAllDoctors() {

        Logger log = Logger.getLogger("org.hibernate");
        log.setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        List<Doctor> docList = null;

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "from Doctor";
        Query query = session.createQuery(hql);
        session.getTransaction().commit();
        docList = query.list();
        session.close();
        return docList;

    }

    public static List<Doctor> getAllDoctorsByName(String name) {

        Logger log = Logger.getLogger("org.hibernate");
        log.setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        if (name == null) {
            return listAllDoctors();
        }

        List<Doctor> docList = null;

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "from Doctor where Doc_FirstName like '%" + name + "%'";
        Query query = session.createQuery(hql);
        session.getTransaction().commit();
        docList = query.list();
        if (docList.size() == 0) {
            session.beginTransaction();
            Character upChar = Character.toUpperCase(name.charAt(0));
            String firstLetter = Character.toString(upChar);
            String newName = firstLetter + name.substring(1);
            hql = "from Doctor where Doc_FirstName like '%" + newName + "%'";
            query = session.createQuery(hql);
            docList = query.list();
            session.getTransaction().commit();
        } else {
            session.close();
            return docList;
        }
        if (docList.size() == 0) {
            session.beginTransaction();
            String newName = name.toUpperCase();
            hql = "from Doctor where Doc_FirstName like '%" + newName + "%'";
            query = session.createQuery(hql);
            docList = query.list();
            session.getTransaction().commit();

        } else {
            session.close();
            return docList;
        }
        if (docList.size() == 0) {
            session.beginTransaction();
            String newName = name.toLowerCase();
            hql = "from Doctor where Doc_FirstName like '%" + newName + "%'";
            query = session.createQuery(hql);
            docList = query.list();
            session.getTransaction().commit();
        }

        session.close();
        return docList;

    }

    public static List<Doctor> getAllDoctorsBySpec(List<Doctor> list, String spec) {

        List<Doctor> docList = new ArrayList<>();

        for (Doctor d : list) {
            if (d.getDocSpec().equals(spec)) {
                docList.add(d);
            }
        }
        return docList;
    }

    public static List<Doctor> getAllDoctorsByCity(List<Doctor> list, String city) {

        List<Doctor> docList = new ArrayList<>();

        for (Doctor d : list) {
            if (d.getCity().equals(city)) {
                docList.add(d);
            }
        }
        return docList;
    }


    public static List<Doctor> getAllDoctorsByName(List<Doctor> list, String name) {

        Logger log = Logger.getLogger("org.hibernate");
        log.setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");


        List<Doctor> docList = new ArrayList<>();

        if (name == null) {
            return docList;
        }

        String namePart[] = name.split(" ");

        for (String n : namePart) {
            System.out.println(n);
        }

        if (namePart.length == 1) {
            docList = sortByFirstName(list, namePart[0]);

            System.out.println("starting 1");
            for (Doctor d : docList) {
                System.out.println(d);
            }
            System.out.println("ending 1");
        } else if (namePart.length == 2) {
            String firstName = namePart[0];
            String lastName = namePart[1];

            docList = sortByFirstName(list, firstName);
            System.out.println("starting 2");
            for (Doctor d : docList) {
                System.out.println(d);
            }
            System.out.println("ending 2");
            if (!(docList.size() == 0)) {
                docList = sortByLastName(docList, lastName);
                System.out.println("starting 3");
                for (Doctor d : docList) {
                    System.out.println(d);
                }
                System.out.println("ending 3");
            }
            System.out.println("Name part is of length 2");

        }


        return docList;
    }

    public static List<Doctor> sortByFirstName(List<Doctor> list, String name) {

        List<Doctor> docList = new ArrayList<>();
        String newName = "";

        Character upChar = Character.toUpperCase(name.charAt(0));
        String firstLetter = Character.toString(upChar);
        newName = firstLetter + name.substring(1);

        for (Doctor d : list) {
            if (d.getFirstName().equals(name)) {
                docList.add(d);
            } else if (d.getFirstName().contains(name.toLowerCase())) {
                docList.add(d);
            } else if (d.getFirstName().contains(name.toUpperCase())) {
                docList.add(d);
            } else if (d.getFirstName().contains(newName)) {
                docList.add(d);
            }
        }
        return docList;
    }

    public static List<Doctor> sortByLastName(List<Doctor> list, String name) {

        List<Doctor> docList = new ArrayList<>();
        String newName = "";

        Character upChar = Character.toUpperCase(name.charAt(0));
        String firstLetter = Character.toString(upChar);
        newName = firstLetter + name.substring(1);

        for (Doctor d : list) {
            if (d.getLastName().equals(name)) {
                docList.add(d);
            } else if (d.getLastName().contains(name.toLowerCase())) {
                docList.add(d);
            } else if (d.getLastName().contains(name.toUpperCase())) {
                docList.add(d);
            } else if (d.getLastName().contains(newName)) {
                docList.add(d);
            }
        }
        return docList;
    }

    public List<String> getDepartments() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "select DISTINCT docSpec from Doctor";
        Query query = session.createQuery(hql);
        List<String> list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }


    public List<String> getCities() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "select DISTINCT city from Doctor";
        Query query = session.createQuery(hql);
        List<String> list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

}