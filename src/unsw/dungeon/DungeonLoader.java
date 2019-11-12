package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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
    	
    	// if goal is single 
    	if (! (g instanceof Goals)) {
    		g.setMain(); 
    		
    	//	if goal is muti-goal
    	} else {  
    		g.setMain();
    	}
    }
    
    private Goal loadGoal(Dungeon dungeon, JSONObject json) {
    	Goal g = null;
    	String goalName = json.getString("goal");
//    	System.out.println(json.toString());
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

        // TODO Handle other possible entities
        }
        if (entity != null) dungeon.addEntity(entity);
        
    }

    //public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

	public void onLoad(Boulder boulder) {
		// TODO Auto-generated method stub
		
	}

	public void onLoad(Player player) {
		// TODO Auto-generated method stub
		
	}

	public void onLoad(Exit exit) {
		// TODO Auto-generated method stub
		
	}

	public void onLoad() {
		// TODO Auto-generated method stub
		
	}

	public void onLoad(Switch newSwitch) {
		// TODO Auto-generated method stub
		
	}

    // TODO Create additional abstract methods for the other entities

}
