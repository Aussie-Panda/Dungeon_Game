package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 * @author Yanning Cao
 * @author Katrina Ding
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    
    @FXML
    Label label = new Label("Goals :");
    
    @FXML
    Button resetButton = new Button("Reset");
    
    @FXML
    Button level1Button = new Button("lv.1");
    
    @FXML
    Button level2Button = new Button("lv.2");
    
    @FXML
    Button level3Button = new Button("lv.3");
    
    @FXML
    Button level4Button = new Button("lv.4");
    
    @FXML
    Button level5Button = new Button("lv.5");
    
    @FXML
    Button DLCButton = new Button("DLC Level");
    
    private ArrayList <Label> goalLabelList = new ArrayList<Label>();
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    private DungeonScreen dungeonScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    /**
     * intilize the controller
     * add label and buttons
     */
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
        double width = 150.0;
        double height = 11.0;
        resetButton.setMinWidth(width);
        resetButton.setMaxHeight(height);

        level1Button.setMaxWidth(width);
        level1Button.setMaxHeight(height);
        
        level2Button.setMaxWidth(width);
        level2Button.setMaxHeight(height);
        
        level3Button.setMaxWidth(width);
        level3Button.setMaxHeight(height);
        
        level4Button.setMaxWidth(width);
        level4Button.setMaxHeight(height);
        
        level5Button.setMaxWidth(width);
        level5Button.setMaxHeight(height);
        
        DLCButton.setMaxWidth(width);
        DLCButton.setMaxHeight(height);
        
        label.setMaxWidth(width);
        label.setMaxHeight(height);
        
        
        resetButton.setStyle("-fx-font-weight: bold;");
        level1Button.setStyle("-fx-font-weight: bold;");
        level2Button.setStyle("-fx-font-weight: bold;");
        level3Button.setStyle("-fx-font-weight: bold;");
        level4Button.setStyle("-fx-font-weight: bold;");
        level5Button.setStyle("-fx-font-weight: bold;");
        DLCButton.setStyle("-fx-font-weight: bold;");
        label.setStyle("-fx-font-weight: bold;");
        
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
        
        level4Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {

            	try {
					level("advanced2.json");
					System.out.println("LOAD level SUCCESSFUL");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

            }
        });
        
        level5Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {

            	try {
					level("advanced3.json");
					System.out.println("LOAD level SUCCESSFUL");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

            }
        });
        DLCButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {

            	try {
					level("newMaze.json");
					System.out.println("LOAD level SUCCESSFUL");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

            }
        });
        
        
        squares.add(resetButton, dungeon.getWidth(), 0);
        squares.add(level1Button, dungeon.getWidth(), 1);
        squares.add(level2Button, dungeon.getWidth(), 2);
        squares.add(level3Button, dungeon.getWidth(), 3);
        squares.add(level4Button, dungeon.getWidth(), 4);
        squares.add(level5Button, dungeon.getWidth(), 5);
        squares.add(DLCButton, dungeon.getWidth(), 6);
        squares.add(label, dungeon.getWidth(), 7);
        
        // add goals labels

        Goal mainGoal = dungeon.getMainGoal();
        ArrayList <String> labelList = goalLabels(mainGoal);
        int i = 8;
        for (String s : labelList) {
        	Label l = new Label(s);
        	squares.add(l, dungeon.getWidth(), i);
        	goalLabelList.add(l);
        	i++;
        }
        trackGoalChangedFlag(8);
        trackWinStatus();
    }
    
    /**
     * create the label for goals
     * @param goal the goal that need to be labeld
     * @return an arraylist of the content on the label
     */
    public ArrayList<String> goalLabels(Goal goal) {
    	ArrayList <String> labelList = new ArrayList <String>();
    	if (goal != null && goal.getClass() != Goals.class) {
        	labelList.add(goal.getName() + " * " + goal.getNum());
        
        // add goals label for multi-goals
        } else if (goal != null && goal.getClass() == Goals.class){
        	Goals goals = (Goals) goal;
        	ArrayList <Goal> subgoals = goals.getSubGoals();
        	for (Goal g : subgoals) {
        		
        		labelList.addAll(goalLabels(g));
        		
        		if (subgoals.indexOf(g) != (subgoals.size() - 1)) labelList.add(goals.getType());
        	}
        	labelList.add("------------------");
        }
    	return labelList;
    }
    
    /**
     * update the goal's label
     * @param idx the goald's index
     * @param newLabelList the new lable list that is upadated
     */
    private void updateGoalLabels(int idx, ArrayList <String> newLabelList) {
    	int i = 0;
    	for (Label l : goalLabelList){
    		l.setText(newLabelList.get(i++));
    	}
    }
    
    /**
     * track if the goal has been changed
     * @param idx the index of the goal
     */
    private void trackGoalChangedFlag(int idx) {
    	dungeon.getGoalChangedFlag().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, 
					Boolean oldValue, Boolean newValue) {
				updateGoalLabels(idx, goalLabels(dungeon.getMainGoal()));
			}
    	});
    }
    
    /**
     * track if player has win the game
     */
    private void trackWinStatus() {
    	dungeon.getWinStatus().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue, Number newValue) {
				Label l = new Label();
				if (newValue.equals(1)) l.setText("WIN!!");
				else if (newValue.equals(-1)) l.setText("LOSE!!");
				l.setStyle("-fx-font-weight: bold;");
				l.setStyle("-fx-font-size: 20px;");
				squares.add(l, dungeon.getWidth(), dungeon.getHeight() - 1);
			}
    	});
    }
    
    /**
     * reset the dungeon
     * @throws IOException
     */
    public void reset () throws IOException {
    	DungeonScreen newDungeon = new DungeonScreen(this.dungeonScreen.getStage(), this.dungeonScreen.getFilename());
		newDungeon.getController().setDungeonScreen(newDungeon);
		newDungeon.start();
    	
    }

    /**
     * laod a new map in to the dungeon
     * @param fileName the fileName want to be load
     * @throws IOException
     */
    public void level (String fileName) throws IOException {
    	DungeonScreen newDungeon = new DungeonScreen(this.dungeonScreen.getStage(), fileName);
		newDungeon.getController().setDungeon1Screen(newDungeon);
		newDungeon.start();
		
    	
    }
    /**
     * set the dungeon screen
     * @param dungeonScreen
     */
    public void setDungeon1Screen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

    /**
     * handle the movement keys
     * @param event
     */
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

