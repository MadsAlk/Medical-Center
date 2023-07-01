package People;

import java.sql.Date;

public class Appointment {
	private int appointmentId;
	private int patientId;
	private int departmentId;
	private float payment;
	private Date appointmentDate;
	
	
	public Appointment(int appointmentId, int patientId, int departmentId, float payment, Date appointmentDate) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.departmentId = departmentId;
		this.payment = payment;
		this.appointmentDate = appointmentDate;
	}
	
	
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public float getPayment() {
		return payment;
	}
	public void setPayment(float payment) {
		this.payment = payment;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


}


