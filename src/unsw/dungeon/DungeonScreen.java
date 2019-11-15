package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
}
