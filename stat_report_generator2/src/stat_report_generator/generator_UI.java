package stat_report_generator;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.io.*;

public class generator_UI extends Application{
	Scene_Handler sHandle = new Scene_Handler();
	public static void main(String[] args) throws IOException {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Report Generator");
		primaryStage.setScene(sHandle.opening_Scene(primaryStage));
		primaryStage.show();
	}
	
}
