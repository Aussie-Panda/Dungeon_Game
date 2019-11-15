package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	StartScreen startScreen = new StartScreen(primaryStage);
    	WinScreen winScreen = new WinScreen(primaryStage);
    	LoseScreen loseScreen = new LoseScreen(primaryStage);
    	DungeonScreen dungeonScreen = new DungeonScreen(primaryStage, "advanced.json");
    	DungeonScreen dungeonOrigScreen = new DungeonScreen(primaryStage, "advanced.json");

    	startScreen.getController().setDungeonScreen(dungeonScreen);
    	dungeonScreen.getController().setWinScreen(winScreen);
    	dungeonScreen.getController().setLoseScreen(loseScreen);
    	
    	winScreen.getController().setStartScreen(startScreen);
    	loseScreen.getController().setDungeonScreen(dungeonScreen);

    	dungeonScreen.getController().setDungeonScreen(dungeonOrigScreen);

    	
    	startScreen.start();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
