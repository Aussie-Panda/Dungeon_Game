package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private int keyId = 1;
    private int doorId = 1;
    private int portalId = 1;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);
        // TODO: add player here

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        dungeon.checkSpawnOnSwitch();
        // load goals
        loadMainGoal(dungeon, json.getJSONObject("goal-condition"));
    	
        return dungeon;
    }
    
    private void loadMainGoal (Dungeon dungeon, JSONObject json) {
    	Goal g = loadGoal(dungeon, json); 
    	g.setMain();
    }
    
    private Goal loadGoal(Dungeon dungeon, JSONObject json) {
    	Goal g = null;
    	String goalName = json.getString("goal");
    	switch (goalName) {
    	case "boulders":
			g = new SwitchGoal(dungeon);  
			break;
			
    	case "enemies":
    		g = new EnemyGoal(dungeon);
    		break;
    		
    	case "treasures":
    		g = new TreassureGoal(dungeon);
    		break;
    		
    	case "exit":
    		g = new ExitGoal(dungeon);
    		break;
    	};
    	
    	// if single goal not found, it's multi-goal
    	if (g == null) {
    		g = new Goals(dungeon, goalName);
    		JSONArray jsonSubgoals = json.getJSONArray("subgoals");
    		for (int i = 0; i < jsonSubgoals.length(); i++) {
                ((Goals) g).addSubgoals(loadGoal(dungeon, jsonSubgoals.getJSONObject(i)));
            }
    	}
    	return g;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;    
        case "boulder":
            Boulder boulder = new Boulder(x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
            Switch newswitch = new Switch(x, y);
            onLoad(newswitch);
            entity = newswitch;
            break;     
    	case "treasure":
    		Treassure treasure = new Treassure(dungeon, x, y);
    		onLoad(treasure);
    		entity = treasure;
    		break;
    	case "door":
    		Door door = new Door(dungeon, doorId, x, y);
    		onLoad(door);
    		entity = door;
    		doorId++;
    		break;
    	case "key":
    		Key key = new Key(dungeon, keyId, x, y);
    		onLoad(key);
    		entity = key;
    		keyId++;
    		break;
    	case "portal":
    		int targetId;
    		boolean isA;
    		if (portalId%2 == 1) {
    			targetId = portalId;
    			isA = true;
    		} else {
    			targetId = portalId - 1;
    			isA = false;
    		}
    		Portal portal = new Portal(dungeon, targetId, isA, x, y);
    		portalId++;
    		onLoad(portal);
    		entity = portal;
    		break;
    	case "enemy":
    		Enemy enemy = new Enemy(dungeon, x, y);
    		onLoad(enemy);
    		entity = enemy;
    		break;
    	case "sword":
    		Sword sword = new Sword(x, y);
    		onLoad(sword);
    		entity = sword;
    		break;
    	case "invincibility":
    		Potion potion = new Potion(x, y);
    		onLoad(potion);
    		entity = potion;
    		break;
    	
    	//"player", "wall", "exit", "treasure", "door", "key", "boulder", "switch", "portal", "enemy", "sword", "invincibility"]

        }
        if (entity != null) dungeon.addEntity(entity);
        
    }


    protected abstract void onLoad(Potion potion);

	protected abstract void onLoad(Sword sword);

	protected abstract void onLoad(Enemy enemy);

	protected abstract void onLoad(Key key);

	protected abstract void onLoad(Portal portal);

	protected abstract void onLoad(Door door);

	protected abstract void onLoad(Treassure treasure);

	protected abstract void onLoad(Wall wall);

	protected abstract void onLoad(Boulder boulder);

	protected abstract void onLoad(Player player);

	protected abstract void onLoad(Exit exit);

	protected abstract void onLoad(Switch newSwitch);

    // TODO Create additional abstract methods for the other entities

}
