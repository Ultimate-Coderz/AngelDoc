package Hibernate;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@DynamicInsert
@Entity
public class Patient {

    @Id
    @Column(name = "Patient_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @SequenceGenerator(name = "patient_seq", sequenceName = "patient_seq", allocationSize = 1)
    private int pId;

    @Column(name = "Patient_FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "Patient_LastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "Patient_Age", nullable = false, length = 10)
    private int pAge;

    @Column(name = "Gender", nullable = false, length = 6)
    private String pGender;

    @Column(name = "City", nullable = false, length = 45)
    private String city;

    @Column(name = "Blood_Group", nullable = false, length = 5)
    private String pBloodGrp;

    @Column(name = "E_Mail", nullable = false, length = 60)
    private String eMail;

    @Column(name = "Password", nullable = false, length = 20, unique = true)
    private String password;


    @Column(name = "Profile_Pic", columnDefinition = "varchar2(50) default 'Image/user.svg'")
    private String profile;


    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentList = new ArrayList<>();


    @Column(name = "Contact_Number")
    private String contactNumber;


    public Patient() {

        super();
        // TODO Auto-generated constructor stub
    }

    public Patient(String firstName, String lastName, int pAge, String pGender, String city, String pBloodGrp,
                   String eMail, String password) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.pAge = pAge;
        this.pGender = pGender;
        this.city = city;
        this.pBloodGrp = pBloodGrp;
        this.eMail = eMail;
        this.password = password;
    }

    public Patient(String firstName, String lastName, int pAge, String pGender, String city, String pBloodGrp,
                   String eMail, String password, String profile, String contactNumber) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.pAge = pAge;
        this.pGender = pGender;
        this.city = city;
        this.pBloodGrp = pBloodGrp;
        this.eMail = eMail;
        this.password = password;
        this.profile = profile;
        this.contactNumber = contactNumber;
    }

    public Patient(String firstName, String lastName, int pAge, String pGender, String city, String pBloodGrp,
                   String eMail, String password, String contactNumber) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.pAge = pAge;
        this.pGender = pGender;
        this.city = city;
        this.pBloodGrp = pBloodGrp;
        this.eMail = eMail;
        this.password = password;

        this.contactNumber = contactNumber;
    }


    public int getpId() {

        return pId;

    }

    public void setpId(int pId) {

        this.pId = pId;
    }


    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }


    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }


    public int getpAge() {

        return pAge;
    }

    public void setpAge(int pAge) {

        this.pAge = pAge;
    }


    public String getpGender() {

        return pGender;
    }

    public void setpGender(String pGender) {

        this.pGender = pGender;
    }


    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }


    public String getpBloodGrp() {

        return pBloodGrp;
    }

    public void setpBloodGrp(String pBloodGrp) {

        this.pBloodGrp = pBloodGrp;
    }


    public String getEMail() {

        return eMail;
    }

    public void setEMail(String eMail) {

        this.eMail = eMail;
    }


    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }


    public String getProfile() {

        return profile;
    }

    public void setProfile(String profile) {

        this.profile = profile;
    }


    public List<Appointment> getAppointmentList() {

        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {

        this.appointmentList = appointmentList;
    }

    public String getContactNumber() {

        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {

        this.contactNumber = contactNumber;
    }


}
