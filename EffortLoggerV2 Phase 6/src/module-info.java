module ASUHelloWorldJavaFX {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens planningPokerLogin to javafx.graphics, javafx.fxml;
}