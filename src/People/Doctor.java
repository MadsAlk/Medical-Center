package People;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public class Doctor extends Employee{
	private SimpleStringProperty specialization;
	private int worksIn;

	
	
	public Doctor(int employeeId, String password, String eFirstName, String eLastName, String gender, long phoneNumber,
			int salary, Date dateOfBirth, Date dateOfEmployment, int roleNumber, String specialization,int worksIn) {
		super(employeeId, password, eFirstName, eLastName, gender, phoneNumber, salary, dateOfBirth, dateOfEmployment,
				roleNumber);
		this.specialization = new SimpleStringProperty(specialization);
		this.worksIn = worksIn;
		// TODO Auto-generated constructor stub
	}

	
	
	public int getWorksIn() {
		return worksIn;
	}



	public void setWorksIn(int worksIn) {
		this.worksIn = worksIn;
	}



	public String getSpecialization() {
		return specialization.get();
	}

	public void setSpecialization(String specialization) {
		this.specialization =new SimpleStringProperty(specialization);
	}
	
	

}
