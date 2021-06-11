package Hibernate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
public class Doctor {
	
	@Id
	@Column(name="Doc_Id")
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="doctor_seq")
    @SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_seq", allocationSize = 1)
	private int docId;
	
	@Column(name="Doc_FirstName",nullable=false,length=50)
	private String firstName;
	
	@Column(name="Doc_LastName",nullable=false,length=50)
	private String lastName;
	
	@Column(name="Specialization",nullable=false,length=70)
	private String docSpec;
	
	@Column(name="E_Mail",nullable=false,length=60)
	private String eMail;
	
	@Column(name="Password",nullable=false,length=20,unique=true)
	private String password;
	
	@Column(name="City",nullable=false,length=45)
	private String city;
	
	@Column(name="Starting_Hr")
	private String startingHr;
		
	@Column(name="Ending_Hr")
	private String endingHr;
	
	@Column(name="Pofile_Pic",columnDefinition = "varchar2(50) default 'Image/user.svg'")
	private String profile;
	
	@Column(name="Contact_Number")
	private String contactNumber;
	
	
	@Column(name="Room",columnDefinition = "varchar2(1000) default 'no link'")
	private String room;
	
	@OneToMany(mappedBy="doctor")
	private List<Appointment> appointmentList=new ArrayList<>();
	
	
	
	public Doctor() {}
	
	public Doctor(String firstName, String lastName, String docSpec, String eMail, String password, String city,
			String startingHr, String endingHr) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.docSpec = docSpec;
		this.eMail = eMail;
		this.password = password;
		this.city = city;
		this.startingHr = startingHr;
		this.endingHr = endingHr;
	}

	public Doctor(String firstName, String lastName, String docSpec, String eMail, String password, String city,
			String startingHr, String endingHr, String profile, String contactNumber,String room) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.docSpec = docSpec;
		this.eMail = eMail;
		this.password = password;
		this.city = city;
		this.startingHr = startingHr;
		this.endingHr = endingHr;
		this.profile = profile;
		this.contactNumber = contactNumber;
		this.room=room;
	}
	
	public Doctor(String firstName, String lastName, String docSpec, String eMail, String password, String city,
			String startingHr, String endingHr, String contactNumber, String room) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.docSpec = docSpec;
		this.eMail = eMail;
		this.password = password;
		this.city = city;
		this.startingHr = startingHr;
		this.endingHr = endingHr;
		this.contactNumber = contactNumber;
		this.room = room;
	}

	public Doctor(String firstName, String lastName, String docSpec, String eMail, String password, String city,
			String startingHr, String endingHr, String contactNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.docSpec = docSpec;
		this.eMail = eMail;
		this.password = password;
		this.city = city;
		this.startingHr = startingHr;
		this.endingHr = endingHr;
		this.contactNumber = contactNumber;
	}


	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
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



	public String getDocSpec() {
		return docSpec;
	}
	public void setDocSpec(String docSpec) {
		this.docSpec = docSpec;
	}



	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}



	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}



	public String getStartingHr() {
		return startingHr;
	}
	public void setStartingHr(String startingHr) {
		this.startingHr = startingHr;
	}

	
	public String getEndingHr() {
		return endingHr;
	}
	public void setEndingHr(String endingHr) {
		this.endingHr = endingHr;
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



	public String getRoom() {
		return room;
	}



	public void setRoom(String room) {
		this.room = room;
	}

	
	


}
