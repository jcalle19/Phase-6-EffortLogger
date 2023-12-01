// Author: Ethan Tang
// Group: Thurs12
// Class: CSE360 w/ Dr. Carter

package planningPokerLogin;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EffortLoggerV2 extends Application 
{

    public List<String> usersInLobby = new ArrayList<>(); // Stores list of users in a lobby.
    public Map <String, List<String>> activeLobbies = new HashMap<>(); // Stores list of lobbies.
    private Stage sessionStartedStage; // Reference to the pop-up window.
    private int lobbyCount = 0; // Unique lobby number counter.
    
    // Main method which launches program window.
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    // Starts the program by initializing the primaryStage.
    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("EffortLoggerV2");
        primaryStage.setScene(createMainScene(primaryStage));
        primaryStage.show();
    }
    
    // Main EffortLoggerV2 page.
    public Scene createMainScene(Stage primaryStage) 
    {
        // Create buttons for EffortLog and PlanningPoker.
        Button planningPokerButton = createStyledButton("Planning Poker");
        Button effortLogButton = createStyledButton("Effort Log");

        // Redirect for Planning Poker button.
        planningPokerButton.setOnAction(e -> showPlanningPokerMenu(primaryStage));
        
        // Added event handling for EffortLog button.
        effortLogButton.setOnAction(e -> showEffortLogMenu(primaryStage));

        // Formatting window UI.
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
        mainLayout.getChildren().addAll(effortLogButton, planningPokerButton);

        return new Scene(mainLayout, 300, 300);
    }
    
    // Effort Log main menu.
    private void showEffortLogMenu(Stage primaryStage) 
    {
        // Buttons for EffortLog menu.
        Button enterNewTaskButton = createStyledButton("Enter New Task");
        Button statSummaryButton = createStyledButton("Stat Summary");
        Button backButton = createStyledButton("Back");

        // Event handling for buttons.
        backButton.setOnAction(e -> primaryStage.setScene(createMainScene(primaryStage)));

        // Formatting program UI.
        VBox effortLogLayout = new VBox(20);
        effortLogLayout.setAlignment(Pos.CENTER);
        effortLogLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
        effortLogLayout.getChildren().addAll(enterNewTaskButton, statSummaryButton, backButton);

        // Displaying program UI.
        Scene effortLogScene = new Scene(effortLogLayout, 300, 300);
        primaryStage.setScene(effortLogScene);
        primaryStage.setTitle("Effort Log");
    }

    // Planning Poker main menu.
    private void showPlanningPokerMenu(Stage primaryStage) 
    {
    	// Buttons for PLanning Poker menu.
        Button createSessionButton = createStyledButton("Create Session");
        Button joinSessionButton = createStyledButton("Join Session");
        Button backButton = createStyledButton("Back");
        
        // Event handling for creating and joining a session.
        createSessionButton.setOnAction(e -> createNewSession(primaryStage));
        joinSessionButton.setOnAction(e -> showActiveLobbies(primaryStage));
        backButton.setOnAction(e -> primaryStage.setScene(createMainScene(primaryStage)));
        
        // Formatting program UI.
        VBox planningPokerLayout = new VBox(20); 
        planningPokerLayout.setAlignment(Pos.CENTER);
        planningPokerLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
        planningPokerLayout.getChildren().addAll(createSessionButton, joinSessionButton, backButton);
        
        // Displaying program UI.
        Scene planningPokerScene = new Scene(planningPokerLayout, 300, 300);
        primaryStage.setScene(planningPokerScene);
        primaryStage.setTitle("Planning Poker");
    }
    
    // Creates a new session of Planning Poker.
    public void createNewSession(Stage primaryStage) 
    {
        String lobbyName = "Lobby " + (++lobbyCount);
        showLobby(primaryStage, lobbyName);
    }
    
    // Displays a session of Planning Poker.
    private void showLobby(Stage primaryStage, String roomName) 
    {
        // Buttons for Planning Poker lobby.
        Button startSessionButton = createStyledButton("Start Session");
        Button backButton = createStyledButton("Back");
        
        // Event handling for buttons.
        startSessionButton.setOnAction(e -> startPlanningPokerSession(primaryStage, roomName));
        backButton.setOnAction(e -> showPlanningPokerMenu(primaryStage));
        
        // Formatting lobby UI.
        VBox lobbyLayout = new VBox(20); 
        lobbyLayout.setAlignment(Pos.CENTER);
        lobbyLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
        lobbyLayout.getChildren().addAll(startSessionButton, backButton);
        
        // Displaying UI.
        Scene lobbyScene = new Scene(lobbyLayout, 300, 300);
        primaryStage.setScene(lobbyScene);
        primaryStage.setTitle(roomName);
    }
    
    // Starts a planning poker session.
    private void startPlanningPokerSession(Stage primaryStage, String roomName) 
    {
    	// Adding users for demonstration.
        addUserToLobby("User1");
        addUserToLobby("User2");
        
        // Display message and list of users
        Label messageLabel = createStyledLabel("Planning Poker Session started with:");
        Label userListLabel = createUsersLabel();
        Button endSessionButton = createStyledButton("End Session");
        
        // Event handling for button.
        endSessionButton.setOnAction(e -> endPlanningPokerSession(primaryStage));
        
        // Formatting UI.
        VBox sessionStartedLayout = new VBox(20);  // Increased spacing between buttons
        sessionStartedLayout.setAlignment(Pos.CENTER);
        sessionStartedLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
        sessionStartedLayout.getChildren().addAll(messageLabel, userListLabel, endSessionButton);
        Scene sessionStartedScene = new Scene(sessionStartedLayout, 300, 300);

        // Create a new stage for the "Session Started" scene.
        sessionStartedStage = new Stage();
        sessionStartedStage.setScene(sessionStartedScene);
        sessionStartedStage.setTitle(roomName);
        sessionStartedStage.show();

    }
    
    // Close the pop-up window when ending a Planning Poker session.
    private void endPlanningPokerSession(Stage primaryStage) 
    {
        if (sessionStartedStage != null) {
            sessionStartedStage.close();
        }

        usersInLobby.clear(); // Clear the list of users.
        showPlanningPokerMenu(primaryStage); // Return to the main Planning Poker menu.
    }
    
    // Displays a list of active Planning Poker lobbies.
    private void showActiveLobbies(Stage primaryStage) 
    {
        VBox lobbyListLayout = new VBox(20);

        // Adding lobbies for demonstration.
        activeLobbies.put("Lobby 2", new ArrayList<>());
        activeLobbies.put("Lobby 3", new ArrayList<>());

        // Creating stages for each lobby.
        for (String lobbyName : activeLobbies.keySet()) 
        {
            Button joinButton = createStyledButton("Join");
            joinButton.setOnAction(e -> joinLobby(primaryStage, lobbyName));

            Label lobbyLabel = createStyledLabel(lobbyName);
            HBox lobbyEntry = new HBox(20, lobbyLabel, joinButton);
            lobbyEntry.setAlignment(Pos.CENTER);
            lobbyListLayout.getChildren().add(lobbyEntry);
        }
        
        // Event handling for back button.
        Button backButton = createStyledButton("Back");
        backButton.setOnAction(e -> showPlanningPokerMenu(primaryStage));
        
        // Formatting UI for lobby display.
        VBox activeLobbiesLayout = new VBox(20); 
        activeLobbiesLayout.setAlignment(Pos.CENTER);
        activeLobbiesLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
        activeLobbiesLayout.getChildren().addAll(lobbyListLayout, backButton);
        
        // Displaying UI.
        Scene activeLobbiesScene = new Scene(activeLobbiesLayout, 300, 300);
        primaryStage.setScene(activeLobbiesScene);
        primaryStage.setTitle("Active Lobbies");
    }	
    
    // For when a user joins a lobby.
    private void joinLobby(Stage primaryStage, String lobbyName) 
    {
        showLobby(primaryStage, lobbyName);
    }
    
    // Adds a user to the lobby.
    public void addUserToLobby(String username) 
    {
        usersInLobby.add(username);
    }
    
    // Used to style each button in program.
    private Button createStyledButton(String text) 
    {
        Button button = new Button(text);
        button.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20px; -fx-border-color: black; -fx-border-width: 2px;");
        return button;
    }
    
    // Used to style each label in program.
    private Label createStyledLabel(String text) 
    {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 16px;");
        return label;
    }
    
    // Creates a label which displays all users in the lobby.
    private Label createUsersLabel() 
    {
        Label userListLabel = createStyledLabel("" + String.join("\n", usersInLobby));
        return userListLabel;
    }
}
