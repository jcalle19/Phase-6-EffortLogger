package stat_report_generator;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.*;

import java.io.IOException;
import java.util.ArrayList;

public class Scene_Handler {
	public ArrayList<Employee> empList = new ArrayList<Employee>();
	public ArrayList<String> empNames = new ArrayList<String>();
	public ArrayList<Integer> empTeams = new ArrayList<Integer>();
	public ComboBox selector; //Need to add functionality
	public ComboBox teamSelector;

	//This menu makes and implements all of the elements of the opening scene
	public Scene opening_Scene(Stage primaryStage){
		BorderPane layout = new BorderPane();
		HBox first = new HBox(20);
		first.setPadding(new Insets(10, 10, 10, 10));
		first.setAlignment(Pos.CENTER);
		
		//Making menu buttons
		Button reGen = new Button("To Report Generator");
		Button makeEmployee = new Button("To Employee Creator");
		
		//Handling events for the buttons
		makeEmployee.setOnAction(e -> primaryStage.setScene(employee_Creator_Scene(primaryStage)));
		reGen.setOnAction(e -> primaryStage.setScene(report_Generator_Scene(primaryStage)));
		
		//Assigning label
		Label title;
		if (empList.size() != 0) {
			title = new Label("Stat Report Generator: \nYou have " + empList.size() + " employee(s).");
		}
		else
			title = new Label("\tStat Report Generator: \nBegin by making some employees");
		
		first.getChildren().addAll(reGen, makeEmployee);
		
		//Organizing the layout of the borderpane
		layout.setBottom(first);
		layout.setCenter(title);
		Scene openScene = new Scene(layout, 300, 300);
		return openScene;
	}
	
	//This method creates all of the elements of the employee creator screen
	public Scene employee_Creator_Scene(Stage primaryStage) {
		BorderPane layout = new BorderPane();
		VBox attributes = new VBox(30);
		attributes.setPadding(new Insets(10, 10, 10, 10));
		attributes.setAlignment(Pos.CENTER);
		
		//Adding the labels for the employee creator window
		Label att1 = new Label("Input Username:");
		Label att2 = new Label("Input User Team:");
		Label blank = new Label("");
		
		//Creating the buttons for the employee creator window
		Button createEmployee = new Button("Make Employee");
		Button manageEmployeeTasks = new Button("Task Menu"); //Goes to the window that edits tasks
		Button home = new Button("Home");
		attributes.getChildren().addAll(att1,att2,blank,createEmployee);
		
		//Creating the text fields for the employee creator window
		VBox inputBoxes = new VBox(20);
		inputBoxes.setPadding(new Insets(10, 10, 10, 10));
		inputBoxes.setAlignment(Pos.CENTER);
		TextField username = new TextField();
		TextField userTeam = new TextField();
		inputBoxes.getChildren().addAll(username, userTeam, createEmployee);
		
		//Event handler for the make employee button, this one is down here to utilize the text fields and it needs more capabilities
		createEmployee.setOnAction(new EventHandler<ActionEvent>() {	 
            @Override
            public void handle(ActionEvent event) {
            	if (username.getText().length() != 0 && userTeam.getText().length() != 0) {
            		Employee newGuy = new Employee(username.getText(), Integer.parseInt(userTeam.getText()));
            		//Currently allows duplicate teams and users, can be fixed later
            		empList.add(newGuy);
            		empNames.add(empList.get(empList.size() - 1).getName());
            		empTeams.add(empList.get(empList.size() - 1).getTeam());
            		selector = new ComboBox(FXCollections.observableArrayList(empNames));
            		teamSelector = new ComboBox(FXCollections.observableArrayList(empTeams));
            	}
            }
        });
		
		//Event handler for home and manage employee buttons
		home.setOnAction(e -> primaryStage.setScene(opening_Scene(primaryStage)));
		manageEmployeeTasks.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (empList.size() != 0) {
					primaryStage.setScene(task_Manager_Scene(primaryStage));
				}
			}
		});
		//Making area for the buttons that change the window
		HBox menuButtons = new HBox(20);
		menuButtons.setAlignment(Pos.CENTER);
		menuButtons.setPadding(new Insets(10, 10, 10, 10));
		menuButtons.getChildren().addAll(home, manageEmployeeTasks);
		
		//Organizing areas of the borderpane
		layout.setLeft(attributes);
		layout.setCenter(inputBoxes);
		layout.setBottom(menuButtons);
		Scene emp_Creator_Scene = new Scene(layout, 300, 300);
		return emp_Creator_Scene;
	}
	
	//This scene will allow people to select made employees and add tasks to their task list
	public Scene task_Manager_Scene(Stage primaryStage) {
		BorderPane layout = new BorderPane();
		
		//Making the attributes for the task managing scene
		VBox attributes = new VBox(30);
		Label attribute1 = new Label("Time Ended:");
		Label attribute2 = new Label("Time Started:");
		Label attribute3 = new Label("Date Completed:");
		Label attribute4 = new Label("Task Project:");
		Label attribute5 = new Label("Effort Cagegory:");
		Label attribute6 = new Label("Task Name:");
		attributes.setAlignment(Pos.CENTER);
		attributes.setPadding(new Insets(10, 10, 10, 10));
		attributes.getChildren().addAll(attribute6, attribute5, attribute4, attribute3, attribute2, attribute1);
		
		//Making the text fields for the task managing scene
		VBox inputText = new VBox(20);
		TextField timeStarted = new TextField();
		TextField timeEnded = new TextField();
		TextField taskProject = new TextField();
		TextField dateCompleted = new TextField();
		TextField effortCategory = new TextField();
		TextField taskName = new TextField();
		inputText.setAlignment(Pos.CENTER);
		inputText.setPadding(new Insets(10, 10, 10, 10));
		inputText.getChildren().addAll(taskName, effortCategory, taskProject, dateCompleted, timeStarted, timeEnded);
		
		//Making the menu buttons for the task managing scene
		HBox menuButtons = new HBox(20);
		Button home = new Button("Home");
		Button empMenu = new Button("Employee Creator");
		Button finalizeTask = new Button("Add Task"); //Need to add functionality
		
		//Event handler for the menu buttons
		home.setOnAction(e -> primaryStage.setScene(opening_Scene(primaryStage)));
		empMenu.setOnAction(e -> primaryStage.setScene(employee_Creator_Scene(primaryStage)));
		finalizeTask.setOnAction(new EventHandler<ActionEvent>() {	 
            @Override
            public void handle(ActionEvent event) {
            	//Make new task object and parse the information from the text field
            	//Parse name from the combobox and select the appropriate employee object
            	//Once Selected add that task to the employee's list
            	if (selector.getValue() != null) {
            		String selectedName = "";
            		Task addedTask = new Task(timeStarted.getText(), taskProject.getText(), effortCategory.getText(), taskName.getText());
            		addedTask.setTimeFinished(timeEnded.getText());
            		addedTask.setDateCompleted(dateCompleted.getText());
            		selectedName += selector.getValue();
            		for(int i = 0; i < empList.size(); i++) {
            			if (empList.get(i).getName().equals(selectedName)) {
            				empList.get(i).getTasks().add(addedTask);
            				System.out.println("" + empList.get(i).getTasks().get(0).getTaskName());
            			}
            		}
            	}
            }
        });
		//Formatting the menu buttons
		menuButtons.setAlignment(Pos.CENTER);
		menuButtons.setPadding(new Insets(10, 10, 10, 10));
		menuButtons.getChildren().addAll(home, empMenu, finalizeTask);
		
		//Making the combobox for the employee selector
		HBox employeeSelector = new HBox(20);
		//ComboBox selector = new ComboBox(/*FXCollections.observableArrayList(empList)*/); //Need to add functionality
		Label selectorLabel = new Label("Select Employee Credit Task to:");
		employeeSelector.getChildren().addAll(selectorLabel, selector);
		employeeSelector.setAlignment(Pos.CENTER);
		employeeSelector.setPadding(new Insets(10,10,10,10));
				
		//Organizing layout of borderpane
		layout.setLeft(attributes);
		layout.setCenter(inputText);
		layout.setBottom(menuButtons);
		layout.setTop(employeeSelector);
		
		Scene task_Adding_Scene = new Scene(layout, 400, 400);
		return task_Adding_Scene;
	}
	
	public Scene report_Generator_Scene(Stage primaryStage){
		ArrayList<Employee> list4Report = new ArrayList<Employee>();
		Employee emp4Report;
		Report_Generator report_Generator = new Report_Generator();
		BorderPane layout = new BorderPane();
		VBox options = new VBox(30);
		options.setPadding(new Insets(10,10,10,10));
		options.setAlignment(Pos.CENTER);
		
		Label op1 = new Label("Choose User: ");
		Label op2 = new Label("Choose Team: ");
		Label op3 = new Label("Enter Date: ");	
		
		VBox inputs = new VBox(20);
		inputs.setPadding(new Insets(10,10,10,10));
		inputs.setAlignment(Pos.CENTER);
		
		//TextField userForReport = new TextField();
		TextField dateForReport = new TextField();
		
		HBox menuButtons = new HBox(20);
		menuButtons.setPadding(new Insets(10,10,10,10));
		menuButtons.setAlignment(Pos.CENTER);
		
		Button home = new Button("Home");
		Button genUserReport = new Button("User Report");
		Button genTeamReport = new Button("Team Report");
		home.setOnAction(e -> primaryStage.setScene(opening_Scene(primaryStage)));
		genUserReport.setOnAction(new EventHandler<ActionEvent>() {	 
            @Override
            public void handle(ActionEvent event) {
            	String tempUser = "" + selector.getValue();
            	for (int i = 0; i < empList.size(); i++) {
            		if (tempUser.equals(empList.get(i).getName())) {
            			try {
							report_Generator.generateUserReport(empList.get(i), dateForReport.getText());
						} catch (IOException e) {
							e.printStackTrace();
						}
            		}
            	}
            }
        });
		genTeamReport.setOnAction(new EventHandler<ActionEvent>() {	 
            @Override
            public void handle(ActionEvent event) {
            	int tempTeam = Integer.parseInt("" + teamSelector.getValue());
            	for (int i = 0; i < empList.size(); i++) {
            		if (tempTeam == empList.get(i).getTeam()) {
            			list4Report.add(empList.get(i));
            		}
            	}
            	try {
            		report_Generator.generateTeamReport(list4Report, dateForReport.getText());
            	} catch (IOException e) {
            		System.out.println("IO Exception");
            	}
            }
        });
		
		options.getChildren().addAll(op1,op2,op3);
		inputs.getChildren().addAll(selector,teamSelector,dateForReport);
		menuButtons.getChildren().addAll(home, genUserReport, genTeamReport);
		
		layout.setLeft(options);
		layout.setCenter(inputs);
		layout.setBottom(menuButtons);
		Scene rep_Creator_Scene = new Scene(layout, 300, 300);
		return rep_Creator_Scene;
	}
}
