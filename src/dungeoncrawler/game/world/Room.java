package dungeoncrawler.game.world;

import java.awt.Graphics;
import java.util.ArrayList;

import dungeoncrawler.framework.resources.Resources;
import dungeoncrawler.game.entities.Enemy;
import dungeoncrawler.game.entities.Player;
import dungeoncrawler.game.world.generator.RoomData;

public class Room {

	private RoomData data;
	private ArrayList<Feature> features;
	private ArrayList<Enemy> enemies;
	
	public Room(RoomData data) {
		this.data = data;
		this.features = new ArrayList<>();
		this.enemies = new ArrayList<>();
	}
	
	public RoomData getData() {
		return data;
	}
	
	public void placeFeature(Feature feature) {
		if(data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.FLOOR ||
			data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.GRASS ||
			data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.TILE)
			this.features.add(feature);
		else
			this.placeFeature(new Feature(feature));
	}
	
	public void featureInteraction(Player player) {
		for(int i=0;i<this.features.size();i++) {
			if(this.features.get(i).intersects(player))
				this.features.remove(i);
		}
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public void spawnEnemy(Enemy enemy) {
		if(data.getTileAt(enemy.x / Tile.SIZE, enemy.y / Tile.SIZE).getID() == Resources.FLOOR)
			this.enemies.add(enemy);
		else
			this.spawnEnemy(new Enemy(enemy));
	}
	
	public void render(Graphics graphics) {
		this.data.render(graphics);
		for(Feature feature : features) {
			feature.render(graphics);
		}
		for(Enemy enemy : enemies) {
			enemy.render(graphics);
		}
	}
}
