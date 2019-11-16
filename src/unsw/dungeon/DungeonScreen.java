package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DungeonScreen {
    private Stage stage;
    private String title;
    private DungeonController controller;
    private Scene scene;
    private String filename;
    
    
    //remember to add filename to dungeon screen
    public DungeonScreen(Stage stage, String filename) throws IOException {
        this.stage = stage;
        this.title = "Dungeon Screen";
        //TODO not load the same
        this.filename = filename;
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(filename);
		
		controller = dungeonLoader.loadController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
        
    }
    
    
    public void start() {
//    	MediaPlayer mediaplayer;
//    	Media musicFile = new Media ("https://www.youtube.com/watch?v=06XG30BRMuY");
//    	mediaplayer = new MediaPlayer(musicFile);
//    	mediaplayer.setAutoPlay(true);
//    	
    	
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }
    
    public DungeonController getController() {
        return controller;
    }


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Scene getScene() {
		return scene;
	}


	public void setScene(Scene scene) {
		this.scene = scene;
	}


	public void setController(DungeonController controller) {
		this.controller = controller;
	}
    
}
