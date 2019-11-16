package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	StartScreen startScreen = new StartScreen(primaryStage);
    	DungeonScreen dungeonScreen = new DungeonScreen(primaryStage, "maze.json");
    	DungeonScreen dungeonOrigScreen = new DungeonScreen(primaryStage, "maze.json");
    	//DungeonScreen dungeon1Screen = new DungeonScreen(primaryStage, "maze.json");

    	startScreen.getController().setDungeonScreen(dungeonScreen);

    	dungeonScreen.getController().setDungeonScreen(dungeonOrigScreen);
    	//dungeonScreen.getController().setDungeon1Screen(dungeon1Screen);
    	startScreen.start();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
