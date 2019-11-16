package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    
    @FXML
    Button level1Button = new Button("lv.1");
    
    @FXML
    Button level2Button = new Button("lv.2");
    
    @FXML
    Button level3Button = new Button("lv.3");
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
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
        double height = 11.0;
        resetButton.setMinWidth(width);
        resetButton.setMaxHeight(height);

        level1Button.setMaxWidth(width);
        level1Button.setMaxHeight(height);
        
        level2Button.setMaxWidth(width);
        level2Button.setMaxHeight(height);
        
        level3Button.setMaxWidth(width);
        level3Button.setMaxHeight(height);
        
        resetButton.setStyle("-fx-font-weight: bold;");
        level1Button.setStyle("-fx-font-weight: bold;");
        level2Button.setStyle("-fx-font-weight: bold;");
        level3Button.setStyle("-fx-font-weight: bold;");
        
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
        
        level1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
            	//TODO need to reset
            	try {
					level("maze.json");
					System.out.println("LOAD level SUCCESSFUL");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }
        });
        
        level2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
            	//TODO need to reset
            	try {
					level("boulders.json");
					System.out.println("LOAD level SUCCESSFUL");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }
        });
        
        level3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
            	//TODO need to reset
            	try {
					level("advanced.json");
					System.out.println("LOAD level SUCCESSFUL");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }
        });
        
        
        //TODO
        squares.add(resetButton, dungeon.getWidth(), 0);
        squares.add(level1Button, dungeon.getWidth(), 1);
        squares.add(level2Button, dungeon.getWidth(), 2);
        squares.add(level3Button, dungeon.getWidth(), 3);
        

    }
    
    public void reset () throws IOException {
    	DungeonScreen newDungeon = new DungeonScreen(this.dungeonScreen.getStage(), this.dungeonScreen.getFilename());
		newDungeon.getController().setDungeonScreen(newDungeon);
		newDungeon.start();
    	
    }

    public void level (String fileName) throws IOException {
    	DungeonScreen newDungeon = new DungeonScreen(this.dungeonScreen.getStage(), fileName);
		newDungeon.getController().setDungeon1Screen(newDungeon);
		newDungeon.start();
    	
    }
    public void setDungeon1Screen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
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

