package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    
    @FXML
    Button resetButton = new Button("Reset");
    Button returnButton = new Button("Return");
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private StartScreen startScreen;
    private WinScreen winScreen;
    private LoseScreen loseScreen;
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }


        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        //gridPane.add(button1, 0, 2); 
        
        //AnchorPane anchor_pane = new AnchorPane();
        double width = 100.0;
        double height = 10.0;
        resetButton.setMaxWidth(width);
        resetButton.setMaxHeight(height);
        returnButton.setMaxWidth(width);
        returnButton.setMaxHeight(height);
        
        resetButton.setStyle("-fx-font-weight: bold;");
        returnButton.setStyle("-fx-font-weight: bold;");

        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	//TODO need to reset before goes to the start screen
            	startScreen.start();
            }
        });
        
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	//TODO need to reset

                	System.out.println("-----DUNGEON RESET SUCCESSFUL-----");

            }
        });
        
        squares.add(resetButton, dungeon.getWidth(), 0);
        squares.add(returnButton, dungeon.getWidth(), 1);
        
        
        trackWinStatus();
    }
    
    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }
    
    public void setWinScreen(WinScreen winScreen) {
    	this.winScreen = winScreen;
    }
    
    public void setLoseScreen(LoseScreen loseScreen) {
    	this.loseScreen = loseScreen;
    }
    
    private void trackWinStatus() {
    	dungeon.winStatus.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, 
					Boolean oldValue, Boolean newValue) {
				if (newValue == true) winScreen.start();
				else loseScreen.start();
				
			}
    	});
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

}

