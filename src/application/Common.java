package application;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Common {
	
	public static void fancyFadeAnimation(Node node) 
	{
		
		node.setOnMouseEntered(e ->{
			try {
				final FadeTransition fadeIn = new FadeTransition(Duration.millis(100));
		        fadeIn.setNode(node);
		        fadeIn.setToValue(1);
		        fadeIn.playFromStart();
			}
			catch(Exception ex) {}
		});
		node.setOnMouseExited(e->{
			try {
				final FadeTransition fadeOut = new FadeTransition(Duration.millis(100));
				fadeOut.setNode(node);
				fadeOut.setToValue(0.5);
				fadeOut.playFromStart();
				node.setOpacity(0.5);
			}
			catch(Exception ex) {}
		});
	}
	
	public static void fancyBtn(Button btn) 
	{
		btn.setStyle("-fx-background-color: #2e7eff;");

		btn.setOnMouseEntered(e -> {
			btn.setStyle("-fx-background-color: #5294ff;");
		});

		btn.setOnMousePressed(e -> {
			btn.setStyle("-fx-background-color: #2261c7;");
		});
		
		btn.setOnMouseReleased(e -> {
			btn.setStyle("-fx-background-color: #2e7eff;");
		});

		btn.setOnMouseExited(e -> {
			btn.setStyle("-fx-background-color: #2e7eff;");
		});
	}
	
//	Color.CORNFLOWERBLUE
//	public static void fancyBtnV2(Button btn) 
//	{
//		btn.setStyle("-fx-background-color: #a3a3a3;");
//		btn.setOpacity(0.5);
//
//		btn.setOnMouseEntered(e -> {
//			btn.setStyle("-fx-background-color: #ababab;");
//			final FadeTransition fadeIn = new FadeTransition(Duration.millis(100));
//	        fadeIn.setNode(btn);
//	        fadeIn.setToValue(1);
//	        fadeIn.playFromStart();
//		});
//
//		btn.setOnMousePressed(e -> {
//			btn.setStyle("-fx-background-color: #878787;");
//		});
//		
//		btn.setOnMouseReleased(e -> {
//			btn.setStyle("-fx-background-color: #a3a3a3;");
//		});
//
//		btn.setOnMouseExited(e -> {
//			btn.setStyle("-fx-background-color: #a3a3a3;");
//			final FadeTransition fadeOut = new FadeTransition(Duration.millis(100));
//			fadeOut.setNode(btn);
//			fadeOut.setToValue(0.5);
//			fadeOut.playFromStart();
//		});
//	}
	public static void fancyBtnV2(Button btn) 
	{
		btn.setStyle("-fx-background-color: #00EBE6;");
		btn.setOpacity(0.8);

		btn.setOnMouseEntered(e -> {
			btn.setStyle("-fx-background-color: #00EEE9;");
			final FadeTransition fadeIn = new FadeTransition(Duration.millis(200));
	        fadeIn.setNode(btn);
	        fadeIn.setToValue(1);
	        fadeIn.playFromStart();
		});

		btn.setOnMousePressed(e -> {
			btn.setStyle("-fx-background-color: #00B1AD;");
		});
		
		btn.setOnMouseReleased(e -> {
			btn.setStyle("-fx-background-color: #00EBE6;");
		});

		btn.setOnMouseExited(e -> {
			btn.setStyle("-fx-background-color: #00EBE6;");
			final FadeTransition fadeOut = new FadeTransition(Duration.millis(200));
			fadeOut.setNode(btn);
			fadeOut.setToValue(0.8);
			fadeOut.playFromStart();
		});
	}
	
	public static void fancyBtnV3(Button btn) 
	{
		btn.setStyle("-fx-background-color: #00B1AD;");
		
		btn.setOnMouseEntered(e -> {
			btn.setStyle("-fx-background-color: #00B1AD;");
		});

		btn.setOnMousePressed(e -> {
			btn.setStyle("-fx-background-color: #00B1AD;");
		});
		
		btn.setOnMouseReleased(e -> {
			btn.setStyle("-fx-background-color: #00B1AD;");
		});

		btn.setOnMouseExited(e -> {
			btn.setStyle("-fx-background-color: #00B1AD;");
		});
	}

	
//	public static void fancyBtnV3(Button btn) 
//	{
//		btn.setStyle("-fx-background-color: #878787;");
//		
//		btn.setOnMouseEntered(e -> {
//			btn.setStyle("-fx-background-color: #878787;");
//		});
//
//		btn.setOnMousePressed(e -> {
//			btn.setStyle("-fx-background-color: #878787;");
//		});
//		
//		btn.setOnMouseReleased(e -> {
//			btn.setStyle("-fx-background-color: #878787;");
//		});
//
//		btn.setOnMouseExited(e -> {
//			btn.setStyle("-fx-background-color: #878787;");
//		});
//	}
	
	public static Button createBtn(String text,Color color,double textSize,double width,double height) 
	{
		Button button = new Button(text);
		button.setPrefSize(width, height);
		button.setTextFill(color);
		button.setFont(Font.font(textSize));
		button.setCursor(Cursor.HAND);
		return button;
	}
	
	public static TextField createField(String text,String promptText,double textSize,double width,double height) 
	{
		TextField field = new TextField(text);
		field.setPromptText(promptText);
		field.setFont(Font.font(textSize));
		field.setPrefSize(width, height);
		return field;
	}
	
	public static Label createLabel(String text,double textSize,double width,double height) 
	{
		Label label = new Label(text);
		label.setFont(Font.font(textSize));
		label.setPrefSize(width, height);
		return label;
	}
	
	public static ImageView AddImage(String imageURL,double width,double height) 
	{
		try {
			ImageView image = new ImageView(new Image(new File(imageURL).toURI().toURL().toExternalForm()));
			
			image.setPreserveRatio(true);
			if(width != -1)
				image.setFitWidth(width);
			if(height != -1)
				image.setFitHeight(height);
			
			return image;

		} catch (Exception e) {
			return null;
		}
	}
	
	public static void labelMouseEntered(Label label) {
		label.setOnMouseEntered(e->{
			label.setUnderline(true);
		});
		label.setOnMouseExited(e->{
			label.setUnderline(false);
		});
	}
	
	public static void imageMouseEntered(ImageView img) {
		img.setOnMouseEntered(e->{
			img.setOpacity(0.5);
		});
		img.setOnMouseExited(e->{
			img.setOpacity(1);
		});
	}
	
	
	
}
