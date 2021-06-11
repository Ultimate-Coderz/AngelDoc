	package Hibernate;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
@Table(name="Coordinator")
public class Coordinator
{
	
	@Id
	@Column(name="Id")
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="coordinator_seq")
    @SequenceGenerator(name = "coordinator_seq", sequenceName = "coordinator_seq", allocationSize = 1)
	private int id;
	
	@Column(name="First_Name",nullable=false,length=50)
	private String firstName;
	
	@Column(name="Last_Name",nullable=false,length=50)
	private String lastName;
	
	@Column(name="Designation",nullable=false,length=50)
	private String designation;
	
	@Column(name="E_Mail",nullable=false,length=60)
	private String eMail;
	
	@Column(name="Password",nullable=false,length=50)
	private String password;
	
	@Column(name="Profile_Pic",columnDefinition = "varchar(50) default 'Image/user.svg'")
	private String profile;

	@Column(name="Contact_Number")
	private String contactNumber;
	
	

	public Coordinator() 
	{
		super();
	}


	public Coordinator(String firstName, String lastName, String designation, String eMail, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.designation = designation;
		this.eMail = eMail;
		this.password = password;
	}
	

	public String getFirstName() {
		return firstName;
	}
	
	
	
	public Coordinator(String firstName, String lastName, String designation, String eMail, String password,
			String contactNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.designation = designation;
		this.eMail = eMail;
		this.password = password;
		this.contactNumber = contactNumber;
	}


	public Coordinator(String firstName, String lastName, String designation, String eMail, String password,
			String profile, String contactNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.designation = designation;
		this.eMail = eMail;
		this.password = password;
		this.profile = profile;
		this.contactNumber = contactNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
	
}