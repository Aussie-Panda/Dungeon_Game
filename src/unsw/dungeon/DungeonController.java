package unsw.dungeon;

import java.io.IOException;
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
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private WinScreen winScreen;
    private LoseScreen loseScreen;
    
    private DungeonScreen dungeonScreen;

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

        resetButton.setStyle("-fx-font-weight: bold;");

        
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
            	//TODO need to reset
            	try {
					reset();
					System.out.println("RESET SUCCESSFUL");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	


    			

            }
        });
        
        squares.add(resetButton, dungeon.getWidth(), 0);
        
        
        trackWinStatus();

    }
    public void reset () throws IOException {
    	DungeonScreen newDungeon = new DungeonScreen(this.dungeonScreen.getStage(), this.dungeonScreen.getFilename());
		newDungeon.getController().setDungeonScreen(newDungeon);
		newDungeon.getController().setWinScreen(winScreen);
		newDungeon.getController().setLoseScreen(loseScreen);
		newDungeon.start();
		
    }

    
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
    
    public void setWinScreen(WinScreen winScreen) {
    	this.winScreen = winScreen;
    }
    
    public void setLoseScreen(LoseScreen loseScreen) {
    	this.loseScreen = loseScreen;
    }
    
    private void trackWinStatus() {
    	dungeon.winStatus.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if (newValue.equals(1)) winScreen.start();
            	else if (newValue.equals(2)) loseScreen.start();
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

