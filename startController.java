package unsw.dungeon;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class startController {
	
	@FXML
	private Button startButton; 
	private dungeonScreen dScreen; 
	private Stage stage;
	 
	@FXML
    private StackPane Screen;

    @FXML
    private ImageView backgroundImage;


    @FXML
    private RadioButton medium;

    @FXML
    private ToggleGroup level;

    @FXML
    private RadioButton hard;

    @FXML
    private RadioButton easy;

	
	public startController(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void handleStartButton(ActionEvent event) throws IOException { 
		String dungeon = "maze.json";
		if (easy.isSelected()) {
			dungeon = "maze.json";
		} else if (medium.isSelected()) {
			dungeon = "boulders.json";
		} else if (hard.isSelected()) {
			dungeon = "test.json";
		}
		changeDungeon(dungeon);
		dScreen.start();
	}
	
	public void setDungeon(dungeonScreen endScreen) {
		this.dScreen = endScreen;
	}
	public void changeDungeon(String level) throws IOException {
		dScreen = new dungeonScreen(stage, level);
	}
}
