package application;

import application.Common;
//import data.PatientData;
//import domain.Patient;
//import interfaces.IPatientData;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SignUp {
	
	private static final String logoURL = "C:\\Users\\Levon\\Desktop\\images\\s.jpg";
	private static final String eyeURL = "C:\\\\Users\\\\Levon\\\\Desktop\\\\images\\\\s.jpg";

	public static void signUp(Stage stage) {
		AnchorPane anchor = new AnchorPane();
		anchor.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		// rectangle to act as section devider
		Rectangle sectionDivisor = new Rectangle();
		sectionDivisor.setWidth(800.0);
		sectionDivisor.setHeight(1000);
		sectionDivisor.setFill(Color.web("#e300e3"));
		anchor.getChildren().add(sectionDivisor);
		AnchorPane.setRightAnchor(sectionDivisor, 50.0);
		AnchorPane.setTopAnchor(sectionDivisor, 0.0);
		AnchorPane.setBottomAnchor(sectionDivisor, 0.0);

		// SIGN IN LABEL
		Label signUpLabel = Common.createLabel("SIGN UP", 64, 300, 30);
		signUpLabel.setAlignment(Pos.CENTER);
		signUpLabel.setFont(Font.font("Barlow Condensed ExtraBold", 64));
		anchor.getChildren().add(signUpLabel);
		AnchorPane.setTopAnchor(signUpLabel, 30.0);
		AnchorPane.setRightAnchor(signUpLabel, 300.0);

		// logo
		ImageView logoImage = Common.AddImage(logoURL, 350, 400);
		anchor.getChildren().add(logoImage);
		AnchorPane.setTopAnchor(logoImage, 170.0);
		AnchorPane.setLeftAnchor(logoImage, 150.0);

		//root for information details
		TilePane root = new TilePane();
		root.setOrientation(Orientation.HORIZONTAL);
		root.setVgap(20);
		root.setHgap(50);
		root.setPrefColumns(2);
		anchor.getChildren().add(root);
		AnchorPane.setTopAnchor(root, 150.0);
		AnchorPane.setRightAnchor(root, 120.0);
		
		// First Name
		VBox vFirstName = new VBox(5);
		TextField fieldFirstName = Common.createField("", "e.g. Osid", 16, 200, 30);
		Label labelFirstName= Common.createLabel("First Name", 16, 200, 30);
		vFirstName.getChildren().addAll(labelFirstName,fieldFirstName);
		root.getChildren().add(vFirstName);
		
		//Last Name
		VBox vLastName = new VBox(5);
		TextField fieldLastName = Common.createField("", "e.g. Abu-Alrub", 16, 200, 30);
		Label labelLastName = Common.createLabel("Last Name", 16, 200, 30);
		vLastName.getChildren().addAll(labelLastName,fieldLastName);
		root.getChildren().add(vLastName);
		
		// age
		VBox vAge = new VBox(5);
		TextField fieldAge = Common.createField("", "e.g. 12", 16, 200, 30);
		Label labelAge = Common.createLabel("Age", 16, 200, 30);
		vAge.getChildren().addAll(labelAge,fieldAge);
		root.getChildren().add(vAge);
		
		// Gender
		ToggleGroup toggleGender = new ToggleGroup();
		VBox vGender = new VBox(5);
		Label labelGender = Common.createLabel("Gender",16, 200, 30);
		RadioButton maleBtn = new RadioButton("Male");
		RadioButton femaleBtn = new RadioButton("Female");
		maleBtn.setToggleGroup(toggleGender);
		femaleBtn.setToggleGroup(toggleGender);
		HBox hGender = new HBox(10);
		hGender.getChildren().addAll(maleBtn,femaleBtn);
		vGender.getChildren().addAll(labelGender,hGender);
		root.getChildren().add(vGender);
		TilePane.setMargin(vGender, new Insets(10,0,0,0));
		
		// address
		VBox vAddress = new VBox(5);
		TextField fieldAddress = Common.createField("", "e.g. Groove Street", 16, 200, 30);
		Label labelAddress = Common.createLabel("Address", 16, 200, 30);
		vAddress.getChildren().addAll(labelAddress,fieldAddress);
		root.getChildren().add(vAddress);
		
		// phone 
		VBox vPhone = new VBox(5);
		TextField fieldPhone = Common.createField("", "e.g. 0592668994", 16, 200, 30);
		Label labelPhone = Common.createLabel("Phone Number", 16, 200, 30);
		vPhone.getChildren().addAll(labelPhone,fieldPhone);
		root.getChildren().add(vPhone);
		
		// password
		VBox vPassword = new VBox(5);
		Label labelPassword = new Label("Password");
		
		TextField fieldUnmaskedPassword = Common.createField("", "", 16, 300, 30);
		fieldUnmaskedPassword.setEditable(false);
		
		PasswordField fieldMaskedPassword = new PasswordField();
		fieldMaskedPassword.setPromptText("e.g. **********");
		fieldMaskedPassword.setPrefSize(300, 30);
		fieldMaskedPassword.setFont(Font.font(16));
		
		//stack pane to hold both fields
		StackPane stackPassword = new StackPane();
		stackPassword.getChildren().addAll(fieldUnmaskedPassword,fieldMaskedPassword);
		vPassword.getChildren().addAll(labelPassword,stackPassword);
		
		ImageView eyePassword = Common.AddImage(eyeURL, 40, 30);
		StackPane eyeStackPassword = new StackPane();
		eyeStackPassword.setOpacity(0.5);
		eyeStackPassword.setCursor(Cursor.HAND);
		eyeStackPassword.getChildren().add(eyePassword);
		anchor.getChildren().add(eyeStackPassword);
		AnchorPane.setRightAnchor(eyeStackPassword, 430.0);
		AnchorPane.setTopAnchor(eyeStackPassword, 458.0);
		Common.fancyFadeAnimation(eyeStackPassword);
		root.getChildren().add(vPassword);
		
		//password confirm
		VBox vPasswordConfirm = new VBox(5);
		Label labelPasswordConfirm = new Label("Password Confirm");
		
		TextField fieldUnmaskedPasswordConfirm = Common.createField("", "", 16, 300, 30);
		fieldUnmaskedPasswordConfirm.setEditable(false);
		
		PasswordField fieldMaskedPasswordConfirm = new PasswordField();
		fieldMaskedPasswordConfirm.setPromptText("e.g. **********");
		fieldMaskedPasswordConfirm.setPrefSize(300, 30);
		fieldMaskedPasswordConfirm.setFont(Font.font(16));
		
		//stack pane to hold both fields
		StackPane stackPasswordConfirm= new StackPane();
		stackPasswordConfirm.getChildren().addAll(fieldUnmaskedPasswordConfirm,fieldMaskedPasswordConfirm);
		vPasswordConfirm.getChildren().addAll(labelPasswordConfirm,stackPasswordConfirm);
		
		ImageView eyePasswordConfirm = Common.AddImage(eyeURL, 40, 30);
		StackPane eyeStackPasswordConfirm = new StackPane();
		eyeStackPasswordConfirm.setOpacity(0.5);
		eyeStackPasswordConfirm.setCursor(Cursor.HAND);
		eyeStackPasswordConfirm.getChildren().add(eyePasswordConfirm);
		anchor.getChildren().add(eyeStackPasswordConfirm);
		AnchorPane.setRightAnchor(eyeStackPasswordConfirm, 80.0);
		AnchorPane.setTopAnchor(eyeStackPasswordConfirm, 458.0);
		Common.fancyFadeAnimation(eyeStackPasswordConfirm);
		root.getChildren().add(vPasswordConfirm);

		// vbox for accoutn purposes
		VBox vSign = new VBox(0);
		anchor.getChildren().add(vSign);
		vSign.setAlignment(Pos.CENTER);
		AnchorPane.setBottomAnchor(vSign, 30.0);
		AnchorPane.setRightAnchor(vSign, 350.0);

		// sign up button
		Button signUpBtn = Common.createBtn("Sign Up", Color.WHITE, 20, 200, 50);
		signUpBtn.setFont(Font.font("", FontWeight.BOLD, 20));
		Common.fancyBtn(signUpBtn);
		vSign.getChildren().add(signUpBtn);

		// sign in label
		Label signInLabel = Common.createLabel("Have An Account? Sign In!", 16, 200, 50);
		signInLabel.setAlignment(Pos.CENTER);
		signInLabel.setUnderline(true);
		signInLabel.setTextFill(Color.BLUE);
		signInLabel.setCursor(Cursor.HAND);
		vSign.getChildren().add(signInLabel);

		
		Label warnLabel = new Label();
		warnLabel.setFont(Font.font("",FontWeight.BOLD,16));
		warnLabel.setTextFill(Color.RED);
		warnLabel.setPrefSize(700, 30);
		warnLabel.setVisible(false);
		anchor.getChildren().add(warnLabel);
		AnchorPane.setTopAnchor(warnLabel, 500.0);
		AnchorPane.setLeftAnchor(warnLabel, 730.0);
		
		/* FUNCTIONALITY */
		
		//password
		eyeStackPassword.setOnMousePressed(e -> {
			try {
				fieldUnmaskedPassword.setText(fieldMaskedPassword.getText());
				fieldMaskedPassword.setOpacity(0.0);
			} catch (Exception ex) {
			}
		});		
		
		eyeStackPassword.setOnMouseReleased(e ->{
			try {
				fieldMaskedPassword.setOpacity(1.0);
			} catch (Exception ex) {
			}
		});
		
		//confirming
		eyeStackPasswordConfirm.setOnMousePressed(e -> {
			try {
				fieldUnmaskedPasswordConfirm.setText(fieldMaskedPasswordConfirm.getText());
				fieldMaskedPasswordConfirm.setOpacity(0.0);
			} catch (Exception ex) {
			}
		});		
		
		eyeStackPasswordConfirm.setOnMouseReleased(e ->{
			try {
				fieldMaskedPasswordConfirm.setOpacity(1.0);
			} catch (Exception ex) {
			}
		});
		
		//go to sign in page
		signInLabel.setOnMouseClicked( e->{
			try {
				SignIn.signIn(stage);
			}
			catch(Exception ex) {}
		});
		
		//create account
		signUpBtn.setOnAction(e ->{
			try {
				if(!fieldMaskedPassword.getText().equals(fieldMaskedPasswordConfirm.getText()))
					throw new IllegalArgumentException("Passwords dont match!");
				
				String firstName = fieldFirstName.getText();
				String lastName = fieldLastName.getText();
				int age = (fieldAge.getText().isEmpty()) ? 0 :  Integer.valueOf(fieldAge.getText());
				String gender= (maleBtn.isSelected()) ? "male" : (femaleBtn.isSelected()) ? "female" : "";
				String phone = fieldPhone.getText();
				String address = fieldAddress.getText();
				String password = fieldMaskedPassword.getText();
				//IPatientData db = new PatientData();
				//Patient patient = new Patient(firstName,lastName,age,gender,phone,address,password);
				
//				//if(db.signUp(patient)) {
//					patient = db.signIn(phone, password);
//					MainWindow.mainWindow(patient);
//				}
				
				warnLabel.setVisible(false);
				stage.close();
			}
			catch(NumberFormatException ex) {
				warnLabel.setText("Age field can have numbers only!");
				warnLabel.setVisible(true);
			}
			catch(IllegalArgumentException ex) {
				warnLabel.setText("*"+ex.getMessage());
				warnLabel.setVisible(true);
			}
			catch(NullPointerException ex) {
				warnLabel.setText("*"+"Must fill all fields");
				warnLabel.setVisible(true);
			}
			catch(Exception ex) {
				warnLabel.setText("*"+"You broke the program sir!");
				warnLabel.setVisible(true);
			}
		});

		/* FUNCTIONALITY */

		Scene scene = new Scene(anchor,1500,700);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Sayrafi Medical Center - Sign Up");
		stage.show();
	}
}
