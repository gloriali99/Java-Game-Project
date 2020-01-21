package unsw.dungeon;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class endController {
	
	@FXML
	private Button restartButton; 
	
	@FXML
	private Button homeButton;
	
	private startScreen startScreen;  
	private Stage stage;
	private String level;
	
	public endController (Stage stage, String level) {
		this.stage = stage;
		this.level = level;
	}
	
	
	@FXML
	public void handleRestartButton(ActionEvent event) throws IOException  {
		new dungeonScreen(stage, level).start();
	}
	
	@FXML
	public void handleHomeButton(ActionEvent event) throws IOException  {
		new startScreen(stage).start();
		
	}
	
	public void setStartScreen(startScreen startScreen) {
		this.startScreen = startScreen;
	}
	

}
