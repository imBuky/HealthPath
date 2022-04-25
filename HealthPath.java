//Animesh Alang and Abhinav Kolli
//Date: 4/24/2022
//HealthPath is a health app that aids in providing daily diet and consumption requirements based on user input
//Note: This app is built on javafx, so make sure to have that set up before running this file.

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HealthPath extends Application {
	TextField muscleField;
	TextField genderField;
	TextField weightField;
	TextField heightField;
	TextField ageField;
	TextField desiredWeightField;
	TextField durationField;
	TextField activityLevelField;
	
	String muscleStatus;
	String activityLevel;
	boolean isFemale;
	double weight;
	double height;
	int age;
	double desiredWeight;
	int duration;
	double BMI;
	double muscleMass;
	
	Label label1;
	TextField userField1;
	Button button1;
	Button button2;
	Stage stage;
	VBox root; // spacing = 8
	HBox adder = new HBox();
	TextField answer;
	Slider slider2;
	Slider slider1;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		stage.setTitle("HealthPath");
		//Group g = new Group();
		
		root = new VBox(8); // spacing = 8
		root.setSpacing(10);
		root.setPadding(new Insets(10));
		
		HBox muscleHBox = new HBox(10); // spacing = 8
		Label optionLabel = new Label("Gain / Maintain / Slim Down        ");  
		optionLabel.setMinWidth(130);
		muscleField = new TextField();
		muscleField.setText("");
		
		muscleHBox.getChildren().addAll(optionLabel, muscleField);
		
		HBox genderHBox = new HBox(10); // spacing = 8
		Label genderLabel = new Label("Gender(M/F)                                 ");
		genderLabel.setMinWidth(130);
		genderField = new TextField();
		genderField.setText("");
		
		genderHBox.getChildren().addAll(genderLabel, genderField);
		
		HBox weightHBox = new HBox(10); // spacing = 8
		Label weightLabel = new Label("What is your weight(lbs):              ");
		weightLabel.setMinWidth(130);
		weightField = new TextField();
		weightField.setText("");
		
		weightHBox.getChildren().addAll(weightLabel, weightField);
		
		HBox heightHBox = new HBox(10); // spacing = 8
		Label heightLabel = new Label("What is your height(inches):         ");
		heightLabel.setMinWidth(130);
		heightField = new TextField();
		heightField.setText("");
		
		heightHBox.getChildren().addAll(heightLabel, heightField);
		
		HBox ageHBox = new HBox(10); // spacing = 8
		Label ageLabel = new Label("What is your age(years):               ");
		ageLabel.setMinWidth(130);
		ageField = new TextField();
		ageField.setText("");
		
		ageHBox.getChildren().addAll(ageLabel, ageField);
		
		HBox activityLevelHBox = new HBox(10); // spacing = 8
		Label activityLevelLabel = new Label("How often do you work out?        \nAnwers:\nSedentary\nLight Activity\nModerate Activity\nVery Active\n\n");
		activityLevelLabel.setMinWidth(130);
		activityLevelField = new TextField();
		activityLevelField.setText("");
		
		activityLevelHBox.getChildren().addAll(activityLevelLabel, activityLevelField);
		
		
		HBox hbox2 = new HBox(10); // spacing = 8
		button1 = new Button("Confirm");
		button1.setOnMouseClicked(new MyMouseEventHandler());
		button1.setMinWidth(100);
		button2 = new Button("Clear");
		button2.setOnMouseClicked(new MyMouseEventHandler());
		button2.setMinWidth(50);
		
		
		hbox2.getChildren().addAll(button1, button2);
		
		root.getChildren().addAll(muscleHBox, genderHBox, weightHBox, heightHBox, ageHBox, activityLevelHBox, hbox2);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(true);
		stage.sizeToScene();
		stage.show();
	}
	
	public double getBMI(double weight, double height) {
		return (((weight)/(height*height))*703);
	}
	
	public boolean getGender() {
		return !isFemale;
	}
	
	public int getAge() {
		return age;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getHeight() {
		return height;
	}
	
	public String getGoal() {
		return muscleStatus;
	}
	
	public String getActivityLevel() {
		return activityLevel;
	}
	
	private class MyMouseEventHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event){
			if(event.getSource() == button1) {
				try {
					muscleStatus = muscleField.getText();
					isFemale = genderField.getText().toLowerCase().equals("f") || genderField.getText().toLowerCase().equals("female");
				
					weight = Double.parseDouble(weightField.getText());
					height = Double.parseDouble(heightField.getText());
					age = Integer.parseInt(ageField.getText());

					activityLevel = activityLevelField.getText();
					BMI = getBMI(weight, height);
					
					
					double bmr;//Basal Metabolic Rate
				    double bmi;//Body Mass Index
				    double ree;
				    boolean gender = getGender();//getGender(); true = boy, false = girl
				    int age = 16;//getAge();
				    double height2 = height / 0.393701;// in centimeters
				    double weight2 = weight/2.20462;// in kgs
				    String goal = getGoal();//getGoal();
				    double al = 1.375;//getActivityLevel(); Sedentary, Light Activity, Moderate Activity, Very Active
					//1.2, 1.375, 1.55, 1.725
				    if(activityLevel.toLowerCase().equals("Sedentary")){
				    	al = 1.2;
				    }
				    else if(activityLevel.toLowerCase().equals("light activity")) {
				    	al = 1.375;
				    }
				    else if(activityLevel.toLowerCase().equals("aoderate activity")) {
				    	al  = 1.55;
				    }
				    else if(activityLevel.toLowerCase().equals("very active")) {
				    	al = 1.725;
				    }
				    
				    if(getGender()) {
				    	 bmr = 88.362 + (13.397 * weight2) + (4.799 * height2) - (5.677 * age);
				    }
				    else {
				    	bmr = 447.593 + (9.247 * weight2) + (3.098 * height2) - (4.330 * age);
				    }
				    
				    if (gender) {
				        bmr = 66 + (6.23 * weight) + (12.7 * height) - (4.7 * age);
				        ree = 10 * weight2 + 6.25 * height2 - 5 * age + 5;
				    } else {
				        bmr = 65 + (4.35 * weight) + (4.7 * height) - (4.7 * age);
				        ree = 10 * weight2 + 6.25 * height2 - 5 * age - 161;
				    }
				    bmi = (weight / (height * height)) * 703;
				    ree = (int)(ree *al);
				    
				    String calorieIntake = "";
				    String diet = "";
				    if (goal.toLowerCase().equals("slim down")) {
				        calorieIntake = "eat less than " + ree + " calories per day";
				        diet = "20-35% fat, 25-45% carbs, 35-50% protien";
				    } else if (goal.toLowerCase().equals("maintain")) {
				        System.out.println("eat " + ree + " calories per day");
				        calorieIntake = "eat " + ree + " calories per day";
				        diet = "25-40% fat, 25-40% carbs, 35-55% protien";
				    } else if (goal.toLowerCase().equals("gain")) {
				        calorieIntake = "eat more than " + ree + " calories per day";
				        diet = "25-40% fat, 25-40% carbs, 35-55% protien";
				    }
				    
				    int waterIntake = (int) (weight * 2/3);
				    String result = "According to the information you entered, your BMI is " + (int)(BMI) + " kg/m². Your BMR is " + (int)(bmr) + " calories, meaning burn " + (int)(bmr) + " calories per day with minimal excercise. In order to " + goal.toLowerCase() + " on your weight, you must drink " + waterIntake + " ounces of water per day. You must also " + calorieIntake + ". Your diet should consist of " + diet + ". \n\nFoods that are rich in fat are avocados, cheese, dark chocolate, whole eggs, fish, nuts, seeds, etc. \n\nFoods that are rich in carbohydrates are bread, pasta, rice, apples, bananas, dairy products and legumes. \n\nFoods that are rich in protein are beef, lamb, pork, chicken, turkey, fish, other seafood, and dairy products.";
				    
				    Alert a = new Alert(AlertType.NONE, result, ButtonType.OK);
					a.showAndWait();
				} catch(Exception e) {
					Alert a = new Alert(AlertType.NONE, "The information you entered is incomplete. Please try again.", ButtonType.OK);
					a.showAndWait();
				}
			}
			else if(event.getSource() == button2) {
				muscleField.setText("");
				genderField.setText("");
				weightField.setText("");
				heightField.setText("");
				ageField.setText("");
				desiredWeightField.setText("");
				activityLevelField.setText("");
				durationField.setText("");
			}
			
		}
		
	}

}
