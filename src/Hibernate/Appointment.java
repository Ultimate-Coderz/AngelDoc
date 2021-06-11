package Hibernate;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
public class Appointment
{
	
	@Id
	@Column(name="Appointment_Id")
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="appointment_seq")
    @SequenceGenerator(name = "appointment_seq", sequenceName = "appointment_seq", allocationSize = 1)
	private int appId;

	
	@Column(name="Charges")
	private double charges;
	
	@Lob
	@Column(name="Prescription")
	private String prescription;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Doc_Id")
	private Doctor doctor;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Patient_Id")
	private Patient patient;
	
	@Column(name="Starting_Time")
	private String startingTime;
	
	@Column(name="Ending_Time")
	private String endingTime;
	
	@Column(name="Appointment_Date")
	 private java.sql.Date sqlDate;
	
	
	@Column(name="Status",columnDefinition = "varchar2(30) default 'Requested'")
	private String status;
	
	
	public Appointment() {}

	
	public Appointment(double charges, String prescription, Doctor doctor, Patient patient, String startingTime,
			String endingTime, Date sqlDate, String status) 
	{
		super();
		this.charges = charges;
		this.prescription = prescription;
		this.doctor = doctor;
		this.patient = patient;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.sqlDate = sqlDate;
		this.status=status;
	}

	public Appointment(double charges, String prescription, Doctor doctor, Patient patient, String startingTime,
			String endingTime, Date sqlDate) {
		super();
		this.charges = charges;
		this.prescription = prescription;
		this.doctor = doctor;
		this.patient = patient;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.sqlDate = sqlDate;
	}


	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}



	public double getCharges() {
		return charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}



	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}



	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}



	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}



	public String getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}



	public String getEndingTime() {
		return endingTime;
	}
	public void setEndingTime(String endingTime) {
		this.endingTime = endingTime;
	}

	
	public java.sql.Date getSqlDate() {
		return sqlDate;
	}
	public void setSqlDate(java.sql.Date sqlDate) {
		this.sqlDate = sqlDate;
	}



	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
	

}
