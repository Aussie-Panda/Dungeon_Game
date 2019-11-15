package unsw.dungeon;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoseController {

    @FXML
    private Button mainMenu;
    
    
    @FXML
    private Button tryAgain;
    
    private DungeonScreen dungeonScreen;
    private StartScreen startScreen;
    
    @FXML
    public void handleTryAgain(ActionEvent event) {
        dungeonScreen.start();

    }
    
    @FXML
    public void handleMainMenu(ActionEvent event) {
    	startScreen.start();
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
    
    public void setStartScreen(StartScreen startScreen) {
    	this.startScreen = startScreen;
    }

}
