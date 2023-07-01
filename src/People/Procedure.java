package People;

import javafx.beans.property.SimpleStringProperty;

public class Procedure {
	private int procedureId;
	private SimpleStringProperty procedureName;	
	private float price;
	private int TamkeenInsurancePercentage;
	private int performedIn;
	
	

	

	public Procedure(int procedureId, String procedureName, float price, int tamkeenInsurancePercentage,
			int performedIn) {
		super();
		this.procedureId = procedureId;
		this.procedureName = new SimpleStringProperty(procedureName);
		this.price = price;
		TamkeenInsurancePercentage = tamkeenInsurancePercentage;
		this.performedIn = performedIn;
	}


	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getProcedureId() {
		return procedureId;
	}
	
	public void setProcedureId(int procedureId) {
		this.procedureId = procedureId;
	}
	public String getProcedureName() {
		return procedureName.get();
	}
	public void setProcedureName(String procedureName) { 
		this.procedureName =new SimpleStringProperty(procedureName);
	}
	
	

	public int getTamkeenInsurancePercentage() {
		return TamkeenInsurancePercentage;
	}
	public void setTamkeenInsurancePercentage(int tamkeenInsurancePercentage) {
		TamkeenInsurancePercentage = tamkeenInsurancePercentage;
	}
	public int getPerformedIn() {
		return performedIn;
	}
	public void setPerformedIn(int performedIn) {
		this.performedIn = performedIn;
	}
	
	@Override
	public String toString() {
		return procedureName.get();
	}

}
