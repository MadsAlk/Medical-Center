package People;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public class Employee {
private int employeeId;
private SimpleStringProperty password;
private SimpleStringProperty eFirstName;
private SimpleStringProperty eLastName;
private SimpleStringProperty gender;
private long phoneNumber;
private int salary;
private Date dateOfBirth;
private Date dateOfEmployment;
private int roleNumber;






public Employee(int employeeId, String password, String eFirstName,
		String eLastName, String gender, long phoneNumber, int salary,
		Date dateOfBirth, Date dateOfEmployment, int roleNumber) {
	super();
	this.employeeId = employeeId;
	this.password =new SimpleStringProperty(password);
	this.eFirstName =new SimpleStringProperty(eFirstName);
	this.eLastName =new SimpleStringProperty(eLastName);
	this.gender =new SimpleStringProperty(gender);
	this.phoneNumber = phoneNumber;
	this.salary = salary;
	this.dateOfBirth = dateOfBirth;
	this.dateOfEmployment = dateOfEmployment;
	this.roleNumber = roleNumber;
}


public int getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
}
public String getPassword() {
	return password.get();
}
public void setPassword(String password) {
	this.password =new SimpleStringProperty(password);
}
public String geteFirstName() {
	return eFirstName.get();
}
public void seteFirstName(String eFirstName) {
	this.eFirstName =new SimpleStringProperty(eFirstName);
}
public String geteLastName() {
	return eLastName.get();
}
public void seteLastName(String eLastName) {
	this.eLastName =new SimpleStringProperty(eLastName);
}
public String getGender() {
	return gender.get();
}
public void setGender(String gender) {
	this.gender =new SimpleStringProperty(gender);
}
public long getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(long phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public Date getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public Date getDateOfEmployment() {
	return dateOfEmployment;
}
public void setDateOfEmployment(Date dateOfEmployment) {
	this.dateOfEmployment = dateOfEmployment;
}
public int getRoleNumber() {
	return roleNumber;
}
public void setRoleNumber(int roleNumber) {
	this.roleNumber = roleNumber;
}


}

