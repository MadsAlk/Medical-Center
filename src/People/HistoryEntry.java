package People;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;

public class HistoryEntry {
	
	private int appointmentId;
	private SimpleStringProperty pFirstName; 
	private SimpleStringProperty pLastName;
	private SimpleStringProperty gender;
	private int age;
	private Date appointmentDate;
	private SimpleStringProperty departmentName;
	
	
	
	public HistoryEntry(int appointmentId, String pFirstName, String pLastName,
			String gender, int age, Date appointmentDate, String departmentName) {
		super();
		this.appointmentId = appointmentId;
		this.pFirstName = new SimpleStringProperty(pFirstName);
		this.pLastName = new SimpleStringProperty(pLastName);
		this.gender = new SimpleStringProperty(gender);
		this.age = age;
		this.appointmentDate = appointmentDate;
		this.departmentName = new SimpleStringProperty(departmentName);
	}
	
	
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getPFirstName() {
		return pFirstName.get();
	}
	public void setPFirstName(String pFirstName) {
		this.pFirstName = new SimpleStringProperty(pFirstName);
	}
	public String getPLastName() {
		return pLastName.get();
	}
	public void setPLastName(String pLastName) {
		this.pLastName = new SimpleStringProperty(pLastName);
	}
	public String getGender() {
		return gender.get();
	}
	public void setGender(String gender) {
		this.gender = new SimpleStringProperty(gender);
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getDepartmentName() {
		return departmentName.get();
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = new SimpleStringProperty(departmentName);
	}
	
	
	
}
