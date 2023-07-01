package application;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;

import People.HistoryEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HistoryController {
	
	/*_______________________________-FXML ATTRIBUTES-________________________________________________*/
    @FXML
    private TableView<HistoryEntry> TV_history;

    @FXML
    private TableColumn<HistoryEntry, Integer> TC_ID;

    @FXML
    private TableColumn<HistoryEntry, String> TC_first;

    @FXML
    private TableColumn<HistoryEntry, String> TC_last;

    @FXML
    private TableColumn<HistoryEntry, String> TC_gender;

    @FXML
    private TableColumn<HistoryEntry, Integer> TC_age;

    @FXML
    private TableColumn<HistoryEntry, Date> TC_date;

    @FXML
    private TableColumn<HistoryEntry, String> TC_dep;

    @FXML
    private TextField TA_search;
    
    @FXML
    private DatePicker DP_from;

    @FXML
    private DatePicker DP_to;

    @FXML
    private TextField TF_ageFrom;

    @FXML
    private TextField TF_ageTo;

    @FXML
    private RadioButton RB_male;

    @FXML
    private RadioButton RB_female;

    @FXML
    private JFXButton btn_gender;

    @FXML
    private JFXButton btn_age;

    @FXML
    private JFXButton btn_date;

    @FXML
    private ComboBox<Integer> CB_from;

    @FXML
    private ComboBox<Integer> CB_to;
    
    @FXML
    private JFXButton btn_return;
    
    @FXML
    private ToggleGroup gender;
    
    @FXML
    private JFXButton btn_reset;



 
	/*_______________________________-NONFXML ATTRIBUTES-___________________________________________*/
	 
	private final ObservableList<HistoryEntry> dataList = FXCollections.observableArrayList();
	
	SortedList<HistoryEntry> sortedData;
	 
    
    /*________________________________-FXML METHODS-_________________________________________________*/
	   @FXML
	    void filterAge(ActionEvent event) throws ClassNotFoundException, SQLException {
		   int from = CB_from.getSelectionModel().getSelectedItem();
		   int to = CB_to.getSelectionModel().getSelectedItem();
		   
		   
		   dataList.clear();
		   String SQL;
	    	
	    	DBConnect dbc = new DBConnect();
	    	dbc.connectDB();
	    	
	    	System.out.println("Connection established");

	    	SQL = "select A.appointmentId, P.pFirstName, P.pLastName, P.gender, P.age, A.appointmentDate, D.departmentName from Appointment A, Department D, Patient P where A.departmentId = D.departmentId and A.patientId = P.patientId and age > " + String.valueOf(from) + " and age < " + String.valueOf(to);
	    	Statement stmt = dbc.getCon().createStatement();
	    	ResultSet rs = stmt.executeQuery(SQL);
	    	
	    	String str = null;
	    	System.out.println("chk"); 
	    	while (rs.next()) {
	    		dataList.add(new HistoryEntry( 
	    				Integer.parseInt(rs.getString(1)),	 //appointment id
	    				rs.getString(2),                  	 //first name
	    				rs.getString(3),					 //last name
	    				rs.getString(4),					 //gender
	    				Integer.parseInt(rs.getString(5)), 	 //age
	    				new Date(rs.getDate(6).getTime()),   //date
	    				rs.getString(7)));  	 	 	 	 //department
	    	}
	    																									
	    	rs.close();
	    	stmt.close();

	    	dbc.getCon().close(); 
	    }

	    @FXML
	    void filterDate(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	Date from = Date.valueOf(DP_from.getValue()); System.out.println(from);
	    	Date to = Date.valueOf(DP_to.getValue());
	    	
	    	dataList.clear();
			   String SQL;
		    	
		    	DBConnect dbc = new DBConnect();
		    	dbc.connectDB();
		    	
		    	System.out.println("Connection established");

		    	SQL = "select A.appointmentId, P.pFirstName, P.pLastName, P.gender, P.age, A.appointmentDate, D.departmentName from Appointment A, Department D, Patient P where A.departmentId = D.departmentId and A.patientId = P.patientId and appointmentDate > '" + from + "' and appointmentDate < '"+to + "'";
		    	Statement stmt = dbc.getCon().createStatement();
		    	ResultSet rs = stmt.executeQuery(SQL);
		    	
		    	String str = null;
		    	System.out.println("chk"); 
		    	while (rs.next()) {
		    		dataList.add(new HistoryEntry( 
		    				Integer.parseInt(rs.getString(1)),	 //appointment id
		    				rs.getString(2),                  	 //first name
		    				rs.getString(3),					 //last name
		    				rs.getString(4),					 //gender
		    				Integer.parseInt(rs.getString(5)), 	 //age
		    				new Date(rs.getDate(6).getTime()),   //date
		    				rs.getString(7)));  	 	 	 	 //department
		    	}
		    																									
		    	rs.close();
		    	stmt.close();

		    	dbc.getCon().close(); 

	    }

	    @FXML
	    void filterGender(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	String gSQL="";
	    	String g = ((RadioButton)gender.getSelectedToggle()).getText(); System.out.println(g);
	    	if(g.compareToIgnoreCase("male") == 0) gSQL = "M";
	    	else gSQL = "F";
	    	
	    	
	    	dataList.clear();
			   String SQL;
		    	
		    	DBConnect dbc = new DBConnect();
		    	dbc.connectDB();
		    	
		    	System.out.println("Connection established");

		    	SQL = "select A.appointmentId, P.pFirstName, P.pLastName, P.gender, P.age, A.appointmentDate, D.departmentName from Appointment A, Department D, Patient P where A.departmentId = D.departmentId and A.patientId = P.patientId and gender = '" + gSQL + "'";
		    	Statement stmt = dbc.getCon().createStatement();
		    	ResultSet rs = stmt.executeQuery(SQL);
		    	
		    	String str = null;
		    	System.out.println("chk"); 
		    	while (rs.next()) {
		    		dataList.add(new HistoryEntry( 
		    				Integer.parseInt(rs.getString(1)),	 //appointment id
		    				rs.getString(2),                  	 //first name
		    				rs.getString(3),					 //last name
		    				rs.getString(4),					 //gender
		    				Integer.parseInt(rs.getString(5)), 	 //age
		    				new Date(rs.getDate(6).getTime()),   //date
		    				rs.getString(7)));  	 	 	 	 //department
		    	}
		    																									
		    	rs.close();
		    	stmt.close();

		    	dbc.getCon().close();
	    }
	    

	    @FXML
	    void Reset(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	 String SQL;
	     	
	     	DBConnect dbc = new DBConnect();
	     	dbc.connectDB();
	     	
	     	System.out.println("Connection established");

	     	SQL = "select A.appointmentId, P.pFirstName, P.pLastName, P.gender, P.age, A.appointmentDate, D.departmentName from Appointment A, Department D, Patient P where A.departmentId = D.departmentId and A.patientId = P.patientId";
	     	Statement stmt = dbc.getCon().createStatement();
	     	ResultSet rs = stmt.executeQuery(SQL);
	     	
	     	String str = null;
	     	System.out.println("chk"); 
	     	while (rs.next()) {System.out.println("1" + rs.getString(7));  str = rs.getString(7);
	     		dataList.add(new HistoryEntry( 
	     				Integer.parseInt(rs.getString(1)),	 //appointment id
	     				rs.getString(2),                  	 //first name
	     				rs.getString(3),					 //last name
	     				rs.getString(4),					 //gender
	     				Integer.parseInt(rs.getString(5)), 	 //age
	     				new Date(rs.getDate(6).getTime()),   //date
	     				rs.getString(7)));  	 	 	 	 //department
	     	}
	     																									
	     	rs.close();
	     	stmt.close();

	     	dbc.getCon().close();  
	    }

	    @FXML
	    void Return(ActionEvent event) throws IOException {
	    	Parent RParent = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        	Scene RScene = new Scene(RParent);
        	
        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	
        	window.setScene(RScene);
        	window.show();

	    }
	
    /*______________________________-NONFXML METHODS-________________________________________________*/
	
	//observalble list to store data
   
    
    
    public void initialize() throws ClassNotFoundException, SQLException {   
                               
        TC_ID.setCellValueFactory(new PropertyValueFactory<HistoryEntry, Integer>("appointmentId"));       
        TC_first.setCellValueFactory(new PropertyValueFactory<HistoryEntry, String>("pFirstName"));        
        TC_last.setCellValueFactory(new PropertyValueFactory<HistoryEntry, String>("pLastName"));        
        TC_gender.setCellValueFactory(new PropertyValueFactory<HistoryEntry, String>("gender"));        
        TC_age.setCellValueFactory(new PropertyValueFactory<HistoryEntry, Integer>("age"));       
        TC_date.setCellValueFactory(new PropertyValueFactory<HistoryEntry, Date>("appointmentDate"));        
        TC_dep.setCellValueFactory(new PropertyValueFactory<HistoryEntry, String>("departmentName"));    
        
    
        String SQL;
    	
    	DBConnect dbc = new DBConnect();
    	dbc.connectDB();
    	
    	System.out.println("Connection established");

    	SQL = "select A.appointmentId, P.pFirstName, P.pLastName, P.gender, P.age, A.appointmentDate, D.departmentName from Appointment A, Department D, Patient P where A.departmentId = D.departmentId and A.patientId = P.patientId";
    	Statement stmt = dbc.getCon().createStatement();
    	ResultSet rs = stmt.executeQuery(SQL);
    	
    	String str = null;
    	System.out.println("chk"); 
    	while (rs.next()) {System.out.println("1" + rs.getString(7));  str = rs.getString(7);
    		dataList.add(new HistoryEntry( 
    				Integer.parseInt(rs.getString(1)),	 //appointment id
    				rs.getString(2),                  	 //first name
    				rs.getString(3),					 //last name
    				rs.getString(4),					 //gender
    				Integer.parseInt(rs.getString(5)), 	 //age
    				new Date(rs.getDate(6).getTime()),   //date
    				rs.getString(7)));  	 	 	 	 //department
    	}
    																									
    	rs.close();
    	stmt.close();

    	dbc.getCon().close();   


        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<HistoryEntry> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		TA_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(entry -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (entry.getPFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (entry.getPLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (String.valueOf(entry.getAppointmentId()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (entry.getDepartmentName().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(TV_history.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		TV_history.setItems(sortedData);
		
               
        ObservableList<Integer> N = FXCollections.observableArrayList();
        N.addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99,100); 
		
        CB_from.setItems(N);
        CB_to.setItems(N);
      
		
    }    
    

}
