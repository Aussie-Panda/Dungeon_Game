package unsw.dungeon;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WinController {

    @FXML
    private Button mainMenu;

    private StartScreen startScreen;
    
    
    @FXML
    public void handleMainMenu(ActionEvent event) {
        startScreen.start();

    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

}
