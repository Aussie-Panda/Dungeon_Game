package unsw.dungeon;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button startButton;

    private DungeonScreen dungeonScreen;
    
//    startButton.setOnAction(e -> {
//    	setUserAgentStyleSheet(STYLESHEET_MODENA);
//    });
    
    @FXML
    public void handleStart(ActionEvent event) {
        dungeonScreen.start();

    }

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

}
