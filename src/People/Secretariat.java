package People;

import java.sql.Date;
import java.time.LocalDate;

public class Secretariat extends Employee{

	
	public Secretariat(int employeeId, String password, String eFirstName, String eLastName, String gender,
			long phoneNumber, int salary, Date dateOfBirth, Date dateOfEmployment, int roleNumber) {
		super(employeeId, password, eFirstName, eLastName, gender, phoneNumber, salary, dateOfBirth, dateOfEmployment,
				roleNumber);
		// TODO Auto-generated constructor stub
	}

}
