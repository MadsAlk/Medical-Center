package People;

import java.sql.Date;
import java.time.LocalDate;

public class Nurse extends Employee{
	private int managedBy;
	
	public Nurse(int employeeId, String password, String eFirstName, String eLastName, String gender, long phoneNumber,
			int salary, Date dateOfBirth, Date dateOfEmployment, int roleNumber, int managedBy) {
		super(employeeId, password, eFirstName, eLastName, gender, phoneNumber, salary, dateOfBirth, dateOfEmployment,
				roleNumber);
		// TODO Auto-generated constructor stub
		this.managedBy = managedBy;
	}

	public int getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(int managedBy) {
		this.managedBy = managedBy;
	}

}
