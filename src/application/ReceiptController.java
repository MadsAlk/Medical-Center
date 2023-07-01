package application;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ReceiptController {
	
	/*FXML ATTRIBUTES*/
	
 
	   @FXML
	    private ScrollPane scroll;

	    @FXML
	    private GridPane grid;

	    @FXML
	    private TextField TF_before;

	    @FXML
	    private TextField TF_after;

	    @FXML
	    private Label lbl_DName;

	    @FXML
	    private Label lbl_Dep;

	    @FXML
	    private Label lbl_PID;

	    @FXML
	    private Label lbl_PName;

	    @FXML
	    private Label lbl_age;

	    @FXML
	    private Label lbl_gender;

	    @FXML
	    private Label lbl_blood;
	    
	    @FXML
	    private Label lbl_phone;

	    @FXML
	    private Label lbl_add;

	    @FXML
	    private Label lbl_chronic;

	    @FXML
	    private Label lbl_allergy;

	    @FXML
	    private Label lbl_insurance;
	    
	    @FXML
	    private Label lbl_appID;

	    @FXML
	    private Label lbl_appdate;
	    
	    @FXML
	    private Label lbl_price;

	    @FXML
	    private Label lbl_finalprice;

	    @FXML
	    private JFXButton btn_return;

	    /*_______________________________-NONFXML ATTRIBUTES-___________________________________________*/
	    
	    /*________________________________-FXML METHODS-_________________________________________________*/
	    
	    @FXML
	    void Return(ActionEvent event) throws IOException {
	    	Parent RParent = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        	Scene RScene = new Scene(RParent);
        	
        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	
        	window.setScene(RScene);
        	window.show();
	    }
	    
	    /*______________________________-NONFXML METHODS-________________________________________________*/
	    
	    public void initialize() throws ClassNotFoundException, SQLException {
	    	DBConnect dbc = new DBConnect();
	    	dbc.connectDB();
	    	
	    	System.out.println("Connection established");

	    	String SQL = "select departmentName from department where departmentId = " + ((People.Doctor)SignIn.loggedIn).getWorksIn();
	    	Statement stmt = dbc.getCon().createStatement();
	    	ResultSet rs = stmt.executeQuery(SQL);
	    	rs.next();
	    	String departmentId = rs.getString(1);
	    	
	    	SQL = "select * from patient where PatientId = " + SampleController.currentAppointment.getPatientId();
	    	stmt = dbc.getCon().createStatement();
	    	rs = stmt.executeQuery(SQL);
	    	rs.next();
	    	
	    	String patientId = rs.getString(1);
	    	String pFirstName = rs.getString(2);
	    	String pLastName = rs.getString(3);
	    	String age = rs.getString(4);
	    	String gender = rs.getString(5);
	    	String phone = rs.getString(6);
	    	String address = rs.getString(7);
	    	String bloodType = rs.getString(8);
	    	String chronicDiseases = rs.getString(9);
	    	String allergies = rs.getString(10);
	    	String medicalHistory = rs.getString(11);
	    	String insuranceType = rs.getString(12);
	    		

	    	lbl_DName.setText(SignIn.loggedIn.geteFirstName() + " " + SignIn.loggedIn.geteLastName());
	    	lbl_Dep.setText(departmentId);
	    	lbl_PID.setText(patientId);
	    	lbl_PName.setText(pFirstName +" "+ pLastName);
	    	lbl_age.setText(age);
	    	lbl_gender.setText(gender);
	    	lbl_blood.setText(bloodType); System.out.println(phone);
	    	lbl_phone.setText(phone);
	    	lbl_add.setText(address);
	    	lbl_chronic.setText(chronicDiseases);
	    	lbl_allergy.setText(allergies);
	    	lbl_insurance.setText(insuranceType);
	    	
	    	lbl_appdate.setText(SampleController.currentAppointment.getAppointmentDate().toString());	  
	    	lbl_appID.setText(String.valueOf(SampleController.currentAppointment.getAppointmentId()));
	    	
	    	
	    	SQL = "select procedurename, price, finalprice from treat T, `procedure` P where T.procedureID = P.procedureID "
	    			+ "and T.appointmentId = " + SampleController.currentAppointment.getAppointmentId();
	    	
	    	stmt = dbc.getCon().createStatement();
	    	rs = stmt.executeQuery(SQL);
	    	int j=1/*row*/;
	    	float price=0,finalprice=0;
	    	
	    	while(rs.next()) {
	    		System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +rs.getString(3));
	    		//create labels
	    		Label labelNum = new Label();
	    		Label labelName = new Label();
	    		Label labelPrice = new Label();
	    		Label labelFinalPrice = new Label();
	    		
	    		//set labels
	    		labelNum.setText(String.valueOf(j));
	    		labelName.setText(rs.getString(1));
	    		labelPrice.setText(rs.getString(2));
	    		labelFinalPrice.setText(rs.getString(3));
	    		
	    		//add labels to grid
	    		grid.add(labelNum, 0,j);
	    		grid.add(labelName, 1, j);
	    		grid.add(labelPrice, 2, j);
	    		grid.add(labelFinalPrice, 3, j);
	    		
	    		j++;
	    		
	    		price +=Float.valueOf(rs.getString(2));
	    		finalprice += Float.valueOf(rs.getString(3));

	    	}
	    	
	    	lbl_price.setText(String.valueOf(price));
	    	lbl_finalprice.setText(String.valueOf(finalprice));
	    	
	    	rs.close();
	    	stmt.close();
	    	dbc.getCon().close();
	    }
}




