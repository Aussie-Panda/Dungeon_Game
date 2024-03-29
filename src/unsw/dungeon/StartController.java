package unsw.dungeon;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button startButton;

    private DungeonScreen dungeonScreen;
    
    /**
     * handle the start button
     * @param event
     */
    @FXML
    public void handleStart(ActionEvent event) {
        dungeonScreen.start();

    }
    /**
     * set the dungeon screen
     * @param dungeonScreen
     */
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

}
