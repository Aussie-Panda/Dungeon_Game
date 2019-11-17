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
    
    /**
     * start the screen
     */
    public void start() {

    	
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }
    /**
     * get the controller of the dungeon
     * @return the controller of the dungeon
     */
    public DungeonController getController() {
        return controller;
    }

    /**
     * get the filename that has been loaded into the dungeon
     * @return the filename
     */
	public String getFilename() {
		return filename;
	}

	/**
	 * set the filename you wish to load into the dungeon
	 * @param filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * get the stage of the dungeon
	 * @return the stage of the dungeon
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * set the stage of the dungeon
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * get the title of the dungeon
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * set the title of the dungeon
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * get the scene of the dungeon
	 * @return the scene of the dungeon
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * set the scene of the dungeon
	 * @param scene the scene of the dungeon
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	/**
	 * set the controller for the dungeon
	 * @param controller the controller for the dungeon
	 */
	public void setController(DungeonController controller) {
		this.controller = controller;
	}
    
}
