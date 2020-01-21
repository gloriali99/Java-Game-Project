package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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
        
        // gets goals from JSON file
        JSONObject goals = json.getJSONObject("goal-condition");
        Goal list = getGoals(goals, null, dungeon);
        dungeon.setGoals(list);
        
        JSONArray jsonEntities = json.getJSONArray("entities");
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;

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
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "key":
        	id = json.getInt("id");
            Key key = new Key(dungeon,x,y,id);
            onLoad(key);
            entity = key;
            break;
        case "switch":
            Switch FloorSwitch = new Switch(x,y);
            onLoad(FloorSwitch);
            entity = FloorSwitch;
            break;
        case "invincibility": 
        	Potion potion = new Potion(dungeon, x,y); 
        	onLoad(potion); 
        	entity = potion; 
        	break; 
        case "sword": 
        	//int attacks = json.getInt("number attacks"); 
        	Sword sword = new Sword(dungeon,x,y); 
        	onLoad(sword); 
        	entity = sword; 
        	break;
        case "door":
        	id = json.getInt("id");
        	Door door = new Door(x, y, id);
            onLoad(door);
            entity = door;
            break;
        case "portal":
        	id = json.getInt("id");
        	Portal portal = new Portal(x,y,id); 
        	onLoad(portal); 
        	entity = portal; 
        	break;
        case "exit": 
        	Exit exit = new Exit(x,y); 
        	onLoad(exit); 
        	entity = exit; 
        	break; 
        case "treasure": 
        	Treasure treasure = new Treasure(dungeon,x,y); 
        	onLoad(treasure); 
        	entity = treasure; 
        	break; 
        case "enemy": 
        	Enemy enemy = new Enemy(dungeon,x,y); 
        	dungeon.getPlayer().initialise(enemy);
        	onLoad(enemy); 
        	entity = enemy; 
        	break; 
        case "hound": 
        	Hound hound = new Hound(dungeon,x,y); 
        	onLoad(hound); 
        	entity = hound; 
        	break; 
        }
        dungeon.addEntity(entity);
         
        
    }
    
    // recursive method that gets goals
    private Goal getGoals (JSONObject goals, Goal list, Dungeon dungeon) {
    	String goal = goals.getString("goal");
        if (goal.equals("AND")) {
            andGoal andGoals = new andGoal();
        	JSONArray sub = goals.getJSONArray("subgoals");
            for (int i = 0; i < sub.length(); i++) {
                JSONObject test = sub.getJSONObject(i);
                andGoals.addGoal(getGoals(test, andGoals, dungeon));
            }
            return andGoals;

        } else if (goal.equals("OR")) {
        	orGoal orGoals = new orGoal();
        	JSONArray sub = goals.getJSONArray("subgoals");
            for (int i = 0; i < sub.length(); i++) {
                JSONObject test = sub.getJSONObject(i);
                orGoals.addGoal(getGoals(test, orGoals, dungeon));

            }
            return orGoals;
        	
        } else if (goal.equals("treasure")) {
        	return new treasureGoal(dungeon);
        } else if (goal.equals("boulders")) {
        	return new boulderGoal(dungeon);
        } else if (goal.equals("enemies")) {
        	return new enemyGoal(dungeon);
        } else {
        	return new exitGoal(dungeon);
        }
    }
    

    public abstract void onLoad (Player player);
    public abstract void onLoad (Hound hound); 
    public abstract void onLoad(Wall wall);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Switch FloorSwitch);
    public abstract void onLoad(Potion potion);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Enemy enemy);

    

}
