package People;

public class Treat {
	private int treatmentId;
	private int procedureId;
	private int doctorId;
	private int appointmentId;
	
	
	
	
	public Treat(int treatmentId, int procedureId, int doctorId, int appointmentId) {
		super();
		this.treatmentId = treatmentId;
		this.procedureId = procedureId;
		this.doctorId = doctorId;
		this.appointmentId = appointmentId;
	}
	
	
	public int getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
	}
	public int getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(int procedureId) {
		this.procedureId = procedureId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	
	
	
}

