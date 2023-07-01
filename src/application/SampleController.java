package application;

import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;

import People.Appointment;
import People.Procedure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Date;

public class SampleController {

	
	/*FXML ATTRIBUTES*/
    @FXML
    private JFXButton btnTreat;

    @FXML
    private Label lblWelcom;
    
    @FXML
    private Label lblLogOut;
    
    @FXML
    private Label lbl_Treat;

    @FXML
    private ImageView iconLogOut; 
    
    @FXML
    private TextField TA_PID;
    
    @FXML
    private JFXButton btn_proc;

    @FXML
    private JFXButton btn_history;

    
    /*_______________________________-NONFXML ATTRIBUTES-___________________________________________*/
    
    public static Appointment currentAppointment;
    
    


	/*________________________________-FXML METHODS-_________________________________________________*/
    @FXML
    void Treat(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
   	try {
    	String SQL;
    	
    	DBConnect dbc = new DBConnect();
    	dbc.connectDB();
    	
    	System.out.println("Connection established"); 
    	// make a new appointment in SQL
    	SQL = "insert into appointment (patientId, departmentId, appointmentDate) values (" + TA_PID.getText() +", "+
    			((People.Doctor)SignIn.loggedIn).getWorksIn() +", '"+ new Date(System.currentTimeMillis()) +"')";
    	
    	Statement stmt = dbc.getCon().createStatement();
    	stmt.execute(SQL);
    	//get the appointmentId of the new appointment
    	SQL = "select max(appointmentId) from appointment";
    	ResultSet rs = stmt.executeQuery(SQL);
    	rs.next();
    	// make the appointment in Java
    	currentAppointment = new Appointment(Integer.valueOf(rs.getString(1)), Integer.valueOf(TA_PID.getText()), 
    			((People.Doctor)SignIn.loggedIn).getWorksIn(), (float)0.0, new Date(System.currentTimeMillis()));

    	
    	
    	rs.close();
    	stmt.close();
    	dbc.getCon().close();
  
    	//switch to treat window
    	Parent TreatParent = FXMLLoader.load(getClass().getResource("Treat.fxml"));
    	Scene TreatScene = new Scene(TreatParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(TreatScene);
    	window.show();
    	
 	}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex){
   		System.out.println("invalid patient ID");
   		lbl_Treat.setText("*invalid patient ID");
   	}
   	
    }
    
    
    
    @FXML
    void LogOut(MouseEvent event) {
   
			try {
				SignIn.signIn((Stage) lblLogOut.getScene().getWindow());
			}
			catch(Exception ex) {}
    }
    @FXML
    void imgMouseEntered(MouseEvent event) {
    	iconLogOut.setOpacity(0.5);
    	iconLogOut.setCursor(Cursor.HAND);
    }

    @FXML
    void imgMouseExited(MouseEvent event) {
    	iconLogOut.setOpacity(1);
    	
    }

    @FXML
    void lblMouseEntered(MouseEvent event) {
    	lblLogOut.setUnderline(true);
    	lblLogOut.setCursor(Cursor.HAND);
    }

    @FXML
    void lblMouseExited(MouseEvent event) {
    	lblLogOut.setUnderline(false);
    }
    

    @FXML
    void goToHistory(ActionEvent event) throws IOException {
    	Parent TreatParent = FXMLLoader.load(getClass().getResource("History.fxml"));
    	Scene TreatScene = new Scene(TreatParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(TreatScene);
    	window.show();
    }

    @FXML
    void goToProc(ActionEvent event) throws IOException {
    	Parent TreatParent = FXMLLoader.load(getClass().getResource("Proc.fxml"));
    	Scene TreatScene = new Scene(TreatParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(TreatScene);
    	window.show();
    	
    }
    	  
    /*______________________________-NONFXML METHODS-________________________________________________*/
    
    public void initialize() {
    	
    	//lblWelcom.setText("Welcome, Dr. " + SignIn.loggedIn.geteLastName());
    	//lblDep.setText("Department: "+((People.Doctor)SignIn.loggedIn).getWorksIn());
    	
    }
    

    
    }

