package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image playerInvincibleImage;
    private Image playerSwordImage;
    private Image playerSwordInvincibleImage;
    private Image wallImage;
    private Image boulderImage;
    private Image switchImage;
    private Image exitImage;
    private Image potionImage;
    private Image swordImage;
    private Image enemyImage;
    private Image portalImage;
    private Image treasureImage;
    
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        playerInvincibleImage = new Image("/human_invincible.png");
        playerSwordImage = new Image("/human_carry_sword.png");
        playerSwordInvincibleImage = new Image("/human_invincible_sword.png");
        wallImage = new Image("/brick_brown_0.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        exitImage = new Image("/exit.png");
        potionImage = new Image("/brilliant_blue_new.png");
        swordImage = new Image("/greatsword_1_new.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        portalImage = new Image("/portal.png");
        treasureImage = new Image("/gold_pile.png");
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.equals(-1)) ((ImageView) node).setImage(null);
                else GridPane.setColumnIndex(node, newValue.intValue());
                
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if (newValue.equals(-1)) ((ImageView) node).setImage(null);
            	else GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }
    
    private void trackDoor(Door door, ImageView img) {
    	door.canPass.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, 
					Boolean oldValue, Boolean newValue) {
				if (newValue == true) img.setImage(new Image("/open_door_" + door.getId() + ".png"));
				else img.setImage(new Image("/closed_door_"+door.getId()+".png"));
				
			}
    	});
    }
    
    private void trackPlayerState(Player player, ImageView img) {
    	player.state.addListener(new ChangeListener<Number>() {
    		@Override
    		public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.equals(0)) img.setImage(playerImage);
                else if (newValue.equals(1)) img.setImage(playerInvincibleImage);
                else if (newValue.equals(2)) img.setImage(playerSwordImage);
                else if (newValue.equals(3)) img.setImage(playerSwordInvincibleImage);
                
            }
    		
    	});
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }
   
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }
    
    @Override
    protected void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    protected void onLoad(Switch newSwitch) {
        ImageView view = new ImageView(switchImage);
        addEntity(newSwitch, view);
    }

    @Override
    protected void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    protected void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    
    @Override
    protected void onLoad(Player player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
        trackPlayerState(player, view); 
    }

	@Override
	protected void onLoad(Potion potion) {
		ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
	}

	@Override
	protected void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
	}

	@Override
	protected void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
	}

	@Override
	protected void onLoad(Key key) {
		ImageView view = new ImageView(new Image("/key_"+key.getId()+".png"));
        addEntity(key, view);
	}

	@Override
	protected void onLoad(Portal portal) {
		ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
	}

	@Override
	protected void onLoad(Door door) {
		ImageView view = new ImageView(new Image("/closed_door_"+door.getId()+".png"));
        addEntity(door, view);
        trackDoor(door, view);
	}

	@Override
	protected void onLoad(Treassure treasure) {
		ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
	}

	

}
