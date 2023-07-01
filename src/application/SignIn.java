package application;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import People.Doctor;
import People.Employee;
import People.Procedure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
//import data.PatientData;
//import domain.Patient;
//import interfaces.IPatientData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SignIn {

	public static Employee loggedIn;
	private final static String logoURL = "Pictures\\logo.png";
	private final static String eyeURL = "Pictures\\icons8-eye-24.png";
	
	public static void signIn(Stage stage) 
	{
		AnchorPane anchor = new AnchorPane();
		anchor.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	
		
		
		//rectangle to act as section divider
		Rectangle sectionDivisor = new Rectangle();
		sectionDivisor.setWidth(450.0);
		sectionDivisor.setHeight(1000);
		sectionDivisor.setFill(Color.web("#e3e3e3"));
		anchor.getChildren().add(sectionDivisor);
		AnchorPane.setRightAnchor(sectionDivisor, 50.0);
		AnchorPane.setTopAnchor(sectionDivisor, 0.0);
		AnchorPane.setBottomAnchor(sectionDivisor, 0.0);
		
		//SIGN IN LABEL
		Label signInLabel = Common.createLabel("SIGN IN", 64, 300, 30);
		signInLabel.setAlignment(Pos.CENTER);
		signInLabel.setFont(Font.font("Barlow Condensed ExtraBold",64));
		anchor.getChildren().add(signInLabel);
		AnchorPane.setTopAnchor(signInLabel, 70.0);
		AnchorPane.setRightAnchor(signInLabel, 150.0);
		
		// logo
		ImageView logoImage = Common.AddImage(logoURL, 350, 400);
		anchor.getChildren().add(logoImage);
		AnchorPane.setTopAnchor(logoImage, 170.0);
		AnchorPane.setLeftAnchor(logoImage, 150.0);
		
		//phone number section
		VBox vUsername = new VBox(5);
		anchor.getChildren().add(vUsername);
		Label usernameLabel = new Label("Phone Number");
		TextField usernameField = Common.createField("", "e.g. 0592668994", 16, 300, 30);
		vUsername.getChildren().addAll(usernameLabel,usernameField);
		AnchorPane.setRightAnchor(vUsername, 150.0);
		AnchorPane.setTopAnchor(vUsername, 200.0);
		
		//password section
		VBox vPassword = new VBox(5);
		Label passwordLabel = new Label("Password");
		anchor.getChildren().add(vPassword);
		
		//unmasked textfield
		TextField unmaskedPasswordField = Common.createField("", "", 16, 300, 30);
		unmaskedPasswordField.setEditable(false);
		
		//masked textfield
		PasswordField maskedPasswordField = new PasswordField();
		maskedPasswordField.setPromptText("e.g. **********");
		maskedPasswordField.setPrefSize(300, 30);
		maskedPasswordField.setFont(Font.font(16));
		
		//stack pane to hold both fields
		StackPane passwordFieldStack = new StackPane();
		passwordFieldStack.getChildren().addAll(unmaskedPasswordField,maskedPasswordField);
		
		vPassword.getChildren().addAll(passwordLabel,passwordFieldStack);
		AnchorPane.setRightAnchor(vPassword, 150.0);
		AnchorPane.setTopAnchor(vPassword, 350.0);
		
		//warning label
		Label warnLabel = new Label();
		warnLabel.setTextFill(Color.RED);
		warnLabel.setAlignment(Pos.CENTER);
		vPassword.getChildren().add(warnLabel);
		warnLabel.setVisible(false);
		
		//eye for unmasking password
		ImageView eyeImage = Common.AddImage(eyeURL, 40, 30);
		StackPane eyeStack = new StackPane();
		eyeStack.setOpacity(0.5);
		eyeStack.setCursor(Cursor.HAND);
		eyeStack.getChildren().add(eyeImage);
		anchor.getChildren().add(eyeStack);
		AnchorPane.setRightAnchor(eyeStack, 110.0);
		AnchorPane.setTopAnchor(eyeStack, 380.0);
		Common.fancyFadeAnimation(eyeStack);
		
		//vbox for accoutn purposes
		VBox vSign = new VBox(0);
		anchor.getChildren().add(vSign);
		vSign.setAlignment(Pos.CENTER);
		AnchorPane.setBottomAnchor(vSign, 80.0);
		AnchorPane.setRightAnchor(vSign, 195.0);
		
		//sign in button
		Button signInBtn = Common.createBtn("Sign In", Color.WHITE, 20, 200, 50);
		signInBtn.setFont(Font.font("",FontWeight.BOLD,20));
		Common.fancyBtn(signInBtn);
		vSign.getChildren().add(signInBtn);
		
		//register label
		Label registerLabel = Common.createLabel("No Account? Register Now!", 16, 200, 50);
		registerLabel.setAlignment(Pos.CENTER);
		registerLabel.setUnderline(true);
		registerLabel.setTextFill(Color.BLUE);
		registerLabel.setCursor(Cursor.HAND);
		vSign.getChildren().add(registerLabel);
		
		
		/* FUNCTIONALITY */
		
		maskedPasswordField.setOnKeyTyped(e ->{
			try {
			unmaskedPasswordField.setText(maskedPasswordField.getText());
			}
			catch(Exception ex) {}
		});
		
		eyeStack.setOnMousePressed(e ->{
			try {
				unmaskedPasswordField.setText(maskedPasswordField.getText());
				maskedPasswordField.setOpacity(0.0);
			}
			catch(Exception ex) {}
		});
		
		eyeStack.setOnMouseReleased(e ->{
			try {
				maskedPasswordField.setOpacity(1.0);
			}
			catch(Exception ex) {}
		});
		
		registerLabel.setOnMouseClicked(e ->{
			try {
				SignUp.signUp(stage);
			}
			catch(Exception ex) {}
		});
		
		signInBtn.setOnAction( e->{
			try {
		    	String SQL;
		    	
		    	DBConnect dbc = new DBConnect();
		    	dbc.connectDB();
		    	
		    	System.out.println("Connection established");

		    	SQL = "select * from Employee where employeeId = " + usernameField.getText() + " and password = '" + maskedPasswordField.getText() +"'" ;
		    	Statement stmt = dbc.getCon().createStatement(); 
		    	ResultSet rs = stmt.executeQuery(SQL);

		    	
		    	if(rs.next()==true) { System.out.println("true" + rs.getString(1));
		    	//if Doctor
		    	if(Integer.valueOf(rs.getString(10))==1) { 
		    		SQL = "select specialization, worksIn from Doctor where DoctorId = " + usernameField.getText() ;
			    	
			    	Statement stmt2 = dbc.getCon().createStatement(); 
			    	ResultSet rs2 = stmt2.executeQuery(SQL);
			    	rs2.next(); 
			  
			    	loggedIn = new Doctor(
			    			Integer.valueOf(rs.getString(1)),
			    			rs.getString(2),
			    			rs.getString(3),
			    			rs.getString(4),
			    			rs.getString(5),
			    			Long.valueOf(rs.getString(6)),
			    			Integer.valueOf(rs.getString(7)),
			    			rs.getObject("dateOfBirth", Date.class),
			    			rs.getObject("dateOfEmployment", Date.class),
			    			Integer.valueOf(rs.getString(10)),
			    			rs2.getString(1),
			    			Integer.valueOf(rs2.getString(2)));
			    	
			    	Parent TreatParent = FXMLLoader.load(SignIn.class.getResource("Sample.fxml"));
			    	Scene TreatScene = new Scene(TreatParent);	    
			    	
			    	stage.setScene(TreatScene);
			    	stage.show();
		    	}
		    	else {
		    		System.out.println("not a doctor");
		    	}
		    	}
		    	else {
		    		warnLabel.setText("this isn't a valid account");
					warnLabel.setVisible(true);
		    	}
		    	
		    	
		    	rs.close();
		    	stmt.close();
		    	dbc.getCon().close();
		    	
		    	
		    

			}
			catch(IllegalArgumentException ex) {
				warnLabel.setText("*"+ex.getMessage());
				warnLabel.setVisible(true);
			}
			catch(NullPointerException ex) {
				warnLabel.setText("*"+"Textfields can't be empty");
				warnLabel.setVisible(true);
			}
			catch(MySQLSyntaxErrorException ex) {
				warnLabel.setText("*"+"invalid id");
				warnLabel.setVisible(true);
			}
			catch(Exception ex) {ex.printStackTrace();
				warnLabel.setText("*"+"Something went horribly wrong!");
				warnLabel.setVisible(true);
			}
		});
		/* FUNCTIONALITY */
		
		Scene scene = new Scene(anchor,1200,700);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Sayrafi Medical Center - Sign In");
		stage.show();
		
	}
}
