package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import People.Appointment;
import People.Procedure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import application.*;


public class TreatController {


	
	/*FXML ATTRIBUTES*/
	
    @FXML
    private TableView<Procedure> TV_Procedures;

    @FXML
    private TableColumn<Procedure, String> TC_PName;

    @FXML
    private TableColumn<Procedure, Integer> TC_PPrice;

    @FXML
    private JFXButton B_save;
    
    @FXML
    private JFXButton B_remove;
    
    @FXML
    private TextField TF_price;
    
    @FXML
    private ComboBox CB_procedures;

   
    /*_______________________________-NONFXML ATTRIBUTES-___________________________________________*/
    
    public static ObservableList<Procedure> procTV = FXCollections.observableArrayList();
    
    
    /*________________________________-FXML METHODS-_________________________________________________*/
    @FXML
    void removeProcedure(ActionEvent event) {
    	TV_Procedures.getItems().removeAll(TV_Procedures.getSelectionModel().getSelectedItems());
    	countPrice();
    }
    

    @FXML
    void chooseProcedure(ActionEvent event) {
    	procTV.add((Procedure) CB_procedures.getValue()); 
    	countPrice();
    }

    
    /*______________________________-NONFXML METHODS-________________________________________________*/
    
    public void initialize() throws NumberFormatException, ClassNotFoundException, SQLException {
        
       	TC_PName.setCellValueFactory(new PropertyValueFactory<Procedure, String>("procedureName"));
       	TC_PPrice.setCellValueFactory(new PropertyValueFactory<Procedure, Integer>("price"));


       	
       	//combobox of procedures
    	CB_procedures.setItems(getProcedures());
        CB_procedures.getSelectionModel().selectFirst(); //select the first element
            
            CB_procedures.setCellFactory(new Callback<ListView<Procedure>,ListCell<Procedure>>(){

                @Override
                public ListCell<Procedure> call(ListView<Procedure> p) {
                    
                    final ListCell<Procedure> cell = new ListCell<Procedure>(){

                        @Override
                        protected void updateItem(Procedure t, boolean bln) {
                            super.updateItem(t, bln);
                            
                            if(t != null){
                                setText(t.toString());
                            }else{
                                setText(null);
                            }
                        }
     
                    };
                    
                    return cell;
                }
            });

    	
       	TV_Procedures.setItems(procTV);
       	
        }
    
    
    public void countPrice() {
    	float p=0;
    	for (int i = 0; i < procTV.size(); i++) {
			p+= procTV.get(i).getPrice();
		}
    	TF_price.setText(String.valueOf(p));
    }

   
    
    public static ObservableList<Procedure> getProcedures() throws NumberFormatException, SQLException, ClassNotFoundException{
    	ObservableList<Procedure> procedures = FXCollections.observableArrayList();
    	
    	String SQL;
    	
    	DBConnect dbc = new DBConnect();
    	dbc.connectDB();
    	
    	System.out.println("Connection established");

    	SQL = "select * from `Procedure`";
    	Statement stmt = dbc.getCon().createStatement();
    	ResultSet rs = stmt.executeQuery(SQL);


    	while (rs.next()) 
    		procedures.add(new Procedure(
    				Integer.parseInt(rs.getString(1)),	 //proc id
    				rs.getString(2),                  	 //name
    				Float.parseFloat(rs.getString(3)), 	 //price float
    				Integer.parseInt(rs.getString(4)), 	 //insurance int
    				Integer.parseInt(rs.getString(5)))); //performed in int
    																									
    	rs.close();
    	stmt.close();

    	dbc.getCon().close();

    	return procedures;
    }
    
    @FXML
    void Save(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	ObservableList<Procedure> procedures = procTV;//TV_Procedures.getItems();
    	try {
        	String SQL;
        	
        	DBConnect dbc = new DBConnect();
        	dbc.connectDB();
        	System.out.println("Connection established"); 
        	
        	//get the insurance type of this patient
        	SQL = "select insuranceType from patient where patientId = "+ SampleController.currentAppointment.getPatientId();
        	Statement stmt = dbc.getCon().createStatement(); 
	    	ResultSet rs = stmt.executeQuery(SQL);
        	rs.next();
	    	String insurance = rs.getString(1); 
	
        	
        	float price =0;
        	for (int i = 0; i < procedures.size(); i++) {
        		//insurance discount
        		price = procedures.get(i).getPrice();
        		if(insurance.equalsIgnoreCase("birzeitstaff"))price = 0;
    			else if(insurance.equalsIgnoreCase("nathealth"))price -= 15;
    			else price *= 1-((procedures.get(i).getTamkeenInsurancePercentage()/100.0));
        		
        	// make treats in SQL
        	SQL = "insert into Treat (procedureId, doctorId, appointmentId, finalPrice) values (" + procedures.get(i).getProcedureId() +", "+
        		((People.Doctor)SignIn.loggedIn).getEmployeeId() +", "+ SampleController.currentAppointment.getAppointmentId()
        		+", " + price +")";        	
        	stmt.execute(SQL);
        	}
        	stmt.close();
        	dbc.getCon().close();
        	
        	//switch to Receipt window
        	Parent RParent = FXMLLoader.load(getClass().getResource("Receipt.fxml"));
        	Scene RScene = new Scene(RParent);
        	
        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	
        	window.setScene(RScene);
        	window.show();
        	
        	
    	
    }catch(ArithmeticException  ex) {
    	System.out.println("exception");
    }
    }
    
 
 
    
   
}
 