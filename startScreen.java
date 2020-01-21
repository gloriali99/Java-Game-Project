package unsw.dungeon;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class startScreen {
	
	private Stage stage; 
	private String title; 
	private startController controller; 
	private Scene scene; 
	
	public startScreen(Stage stage) throws IOException{
		this.stage = stage; 
		title = "Dungeon"; 
		
		controller = new startController(stage); 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml")); 
		loader.setController(controller);
		
		//load into a parent node called root
		Parent root = loader.load(); 
		scene = new Scene(root);
		
		
	}

	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	public startController getController() {
		return controller; 
	}
}
