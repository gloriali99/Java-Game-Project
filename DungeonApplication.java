package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
	
	

    @Override
    public void start(Stage primaryStage) throws IOException {
   
    	startScreen startScreen = new startScreen(primaryStage); 
//    	dungeonScreen dScreen = new dungeonScreen(primaryStage,null);
//    	endScreen endScreen = new endScreen(primaryStage);
//    	
//    	startScreen.getController().setDungeon(dScreen);
//    	endScreen.getController().setStartScreen(startScreen);
//    	dScreen.getController().setEndScreen(endScreen);
//    	
    	startScreen.start();
    	
    	
    }
    
   public static void main(String[] args) {
        launch(args);
    }
	
}
