package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;

import People.Procedure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProcController {

	/*_______________________________-FXML ATTRIBUTES-________________________________________________*/
	
    @FXML
    private TableView<Procedure> TV_proc;

    @FXML
    private GridPane grid_dep;

    @FXML
    private TableColumn<Procedure, Integer> TC_ID;

    @FXML
    private TableColumn<Procedure, String> TC_name;

    @FXML
    private TableColumn<Procedure, Float> TC_price3;

    @FXML
    private TableColumn<Procedure, Integer> TC_tamkeen;

    @FXML
    private TableColumn<Procedure, Integer> TC_dep;
    
    @FXML
    private TextField TF_id;

    @FXML
    private TextField TF_name;

    @FXML
    private TextField TF_depID;

    @FXML
    private TextField TF_tamkeen;

    @FXML
    private TextField TF_price;

    @FXML
    private JFXButton btn_add;
    @FXML
    
    private JFXButton btn_refresh;
    
    @FXML
    private TextField TF_showDep;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_show;

    @FXML
    private JFXButton btn_return;

    @FXML
    private JFXButton btn_sort;

    @FXML
    private RadioButton RB_name;

    @FXML
    private ToggleGroup sort;

    @FXML
    private RadioButton RB_price;

    @FXML
    private RadioButton RB_discount;

    

    

   
	
	/*_______________________________-NONFXML ATTRIBUTES-___________________________________________*/
    
	
    /*________________________________-FXML METHODS-_________________________________________________*/
    
    //Add
    @FXML
    void addProc(ActionEvent event) throws ClassNotFoundException, SQLException {
    	DBConnect dbc = new DBConnect();
    	dbc.connectDB();
    	
    	System.out.println("Connection established");
       	
       	String SQL = "insert into `Procedure` (procedureName, price, TamkeenInsurancePercentage, performedIn) values "
       			+ "('" + TF_name.getText() + "', "+TF_price.getText() + ", "+ TF_tamkeen.getText() +", " + TF_depID.getText() +")";
    	
    	Statement stmt = dbc.getCon().createStatement();
    	stmt.execute(SQL);
 

    	stmt.close();
    	dbc.getCon().close();
    }
    
    
    //Refresh
    @FXML
    void refresh(ActionEvent event) throws NumberFormatException, ClassNotFoundException, SQLException {
    	TV_proc.setItems(TreatController.getProcedures());
    }
    
    
    //Delete
    @FXML
    void Delete(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	ObservableList<Procedure> procedures = TV_proc.getSelectionModel().getSelectedItems();
    	
    	DBConnect dbc = new DBConnect();
    	dbc.connectDB();
    	
    	System.out.println("Connection established");
       	
    	String SQL;
    	Statement stmt = null;
    	for (int i = 0; i < procedures.size(); i++) {
    		SQL = "DELETE FROM `procedure` WHERE ProcedureId = " + procedures.get(i).getProcedureId();
        	
        	stmt = dbc.getCon().createStatement();
        	stmt.execute(SQL);
        	
		}
    	stmt.close();
    	dbc.getCon().close();
    	
    	TV_proc.getItems().removeAll(TV_proc.getSelectionModel().getSelectedItems());
    }

    
    
    //Show
    @FXML
    void Show(ActionEvent event) throws NumberFormatException, ClassNotFoundException, SQLException {
    	int id = Integer.valueOf(TF_showDep.getText());
    	ObservableList<Procedure> procedures = TreatController.getProcedures();
    	
    	
    	for (int i = procedures.size(), j = 0; j<i; j++) {
			if(procedures.get(j).getPerformedIn() != id) {
				procedures.remove(j);
				j--;
				i--;
			}
		}
    	TV_proc.setItems(procedures);
    }

    
    //Sort
    @FXML
    void Sort(ActionEvent event) throws ClassNotFoundException, SQLException {
    	String sortBy="";
    	if(((RadioButton)sort.getSelectedToggle()).getText().equalsIgnoreCase("name"))sortBy = "procedureName";
    	else if(((RadioButton)sort.getSelectedToggle()).getText().equalsIgnoreCase("price"))sortBy = "price";
    	else sortBy = "TamkeenInsurancePercentage";
    	
ObservableList<Procedure> procedures = FXCollections.observableArrayList();
    	
    	String SQL;
    	
    	DBConnect dbc = new DBConnect();
    	dbc.connectDB();
    	
    	System.out.println("Connection established");

    	SQL = "select * from `Procedure` order by " + sortBy;
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

    	TV_proc.setItems(procedures);
    	
    	
    }
    
    
    //Return
    @FXML
    void Return(ActionEvent event) throws IOException {
    	Parent TreatParent = FXMLLoader.load(getClass().getResource("Sample.fxml"));
    	Scene TreatScene = new Scene(TreatParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(TreatScene);
    	window.show();
    }
    
	
	/*______________________________-NONFXML METHODS-________________________________________________*/
	
    public void initialize() throws NumberFormatException, ClassNotFoundException, SQLException {
    	ObservableList<Procedure> procedures = TreatController.getProcedures();
    
    	TC_ID.setCellValueFactory(new PropertyValueFactory<Procedure, Integer>("procedureId"));
    	TC_name.setCellValueFactory(new PropertyValueFactory<Procedure, String>("procedureName"));
       	TC_price3.setCellValueFactory(new PropertyValueFactory<Procedure, Float>("price"));
       	TC_tamkeen.setCellValueFactory(new PropertyValueFactory<Procedure, Integer>("TamkeenInsurancePercentage"));
       	TC_dep.setCellValueFactory(new PropertyValueFactory<Procedure, Integer>("performedIn"));

       	TV_proc.setItems(procedures);
       	
       	DBConnect dbc = new DBConnect();
    	dbc.connectDB();
    	
    	System.out.println("Connection established");
       	
       	String SQL = "select * from Department";
    	
    	Statement stmt = dbc.getCon().createStatement();
    	ResultSet rs = stmt.executeQuery(SQL);
    	int j=1/*row*/;
    	
    	while(rs.next()) {
    		System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +rs.getString(3));
    		//create labels
    		Label id = new Label();
    		Label name = new Label();
    		
    		
    		//set labels
    		id.setText(rs.getString(1));
    		name.setText(rs.getString(2));
    		
    		
    		//add labels to grid
    		grid_dep.add(id, 0,j);
    		grid_dep.add(name, 1, j);
    		
    		
    		j++;
    		

    	}
  
    	
    	rs.close();
    	stmt.close();
    	dbc.getCon().close();
       	
    }
	
	
	
	
	
	
}
