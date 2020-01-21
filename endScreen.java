package unsw.dungeon;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class endScreen {

	private Stage stage;  
	private endController controller; 
	private Scene scene; 
	private String level;
	
	public endScreen(Stage stage, String level, Boolean completed) throws IOException{
		this.stage = stage; 
		
		controller = new endController(stage, level); 
		FXMLLoader loader;
		if (completed == false) {
			loader = new FXMLLoader(getClass().getResource("gameover.fxml"));
		} else {
			loader = new FXMLLoader(getClass().getResource("dungeonComplete.fxml"));
		}
		
		loader.setController(controller);
		
		Parent root = loader.load();
		scene = new Scene(root); 
	}
	
	public void start() {
		stage.setScene(scene);
		stage.show();
	}
	
	public endController getController() {
		return controller;
	}
	
}
