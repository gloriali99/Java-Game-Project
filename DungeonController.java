package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    
    @FXML
    private Text action;
    
    @FXML
    private Text potion;
    
    @FXML
    private Text timer;
    
    @FXML
    private ToolBar tools;
    
    @FXML
    private Button home;
    
    @FXML
    private Button retry;
    
    @FXML
    private AnchorPane footer;
    
    static int seconds;
    
    private List<ImageView> initialEntities;
    private Player player;
    private Dungeon dungeon;
    private Stage stage;
    private String level;
    private endScreen endScreen; 
   

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, Stage stage, String level) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.stage = stage;
        this.level = level;

    }

    @FXML
    public void initialize() {
    	
    	
    	action.setFill(Color.WHITE);
    	potion.setFill(Color.DARKRED);
    	timer.setFill(Color.DARKRED);
    	tools.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN,null, null)));
    	footer.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN,null, null)));
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity); 
        }
       
        action.setText("GOALS: "+ dungeon.getGoals().goalText());
        changeText(dungeon);   
        checkPlayer(player);
        checkPotion(player);
        

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {

        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        default:
            break;
        }
    }
    
    @FXML
    public void changeText(Dungeon dungeon) {
		dungeon.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
            	if (newValue.toString().equals("DUNGEON COMPLETE")) {
            		try {
						gameOver(true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            	action.setText(newValue.toString());
            	action.setTextAlignment(TextAlignment.CENTER);
            	FadeTransition fade = new FadeTransition();  
                fade.setDuration(Duration.millis(1000));  
                fade.setFromValue(0);  
                fade.setToValue(1);  
                fade.setCycleCount(1);  
                fade.setAutoReverse(true);  
                fade.setNode(action);  
                fade.play();  

            }
		});
	}
    
    
    public void checkPlayer(Player p) {
    	 p.aliveProperty().addListener(new ChangeListener<Boolean>() {
             @Override
             public void changed(ObservableValue<? extends Boolean> observable,
                     Boolean oldValue, Boolean newValue) {
 	            if (newValue == false) {
 	            		
 	            	try {
						gameOver(false);
					} catch (IOException e) {
						e.printStackTrace();
					}
 	            }
             }
         });
    }
    
    public void checkPotion(Player p) {
    	p.potionProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
	            if (newValue == true) {
	            	potion.setText("          Potion Timer:");
	            	timer.setText("5");
	            	seconds = 4;
	            	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> timer(seconds)));
	    	    	timeline.setCycleCount(4);
	    	    	timeline.play();
	            } else {
	            	potion.setText("");
	            	timer.setText("");
	            }
            }
        });
    }
    
    public void timer(int num) {
    	timer.setText(String.valueOf(num));
    	timer.setTextAlignment(TextAlignment.CENTER);
    	FadeTransition fade = new FadeTransition();  
        fade.setDuration(Duration.millis(1000));  
        fade.setFromValue(0);  
        fade.setToValue(1);  
        fade.setAutoReverse(true);  
        fade.setNode(timer);  
        fade.play();  
        seconds--;
    }
    
    
    @FXML
	public void handleHomeButton(ActionEvent event) throws IOException { 
    	new startScreen(stage).start();
	}
    
    @FXML
	public void handleRetryButton(ActionEvent event) throws IOException {
    	new dungeonScreen(stage, level).start();
	}
    
    public void gameOver(Boolean complete) throws IOException {
    	new endScreen(stage, level, complete).start();
    }

  
    public void setEndScreen(endScreen endScreen){
    	this.endScreen = endScreen; 
    }
    
//    public void checkOver() {
//    	if(dungeon.getLifeTrigger() == 1) {
//    		endScreen.start();
//    	}
//    }
    
    
  
}

