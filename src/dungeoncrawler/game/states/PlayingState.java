package dungeoncrawler.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dungeoncrawler.framework.gamestates.GameState;
import dungeoncrawler.framework.gamestates.GameStateManager;
import dungeoncrawler.framework.resources.Resources;
import dungeoncrawler.framework.utils.MathHelper;
import dungeoncrawler.game.entities.Enemy;
import dungeoncrawler.game.entities.Player;
import dungeoncrawler.game.world.Feature;
import dungeoncrawler.game.world.Tile;
import dungeoncrawler.game.world.World;
import dungeoncrawler.game.world.generator.LevelGenerator;
import dungeoncrawler.game.world.generator.RoomData;

public class PlayingState extends GameState {

	private LevelGenerator generator;
	private World world;
	private Player player;
	
	protected PlayingState(GameStateManager manager) {
		super(manager);
		this.generator = new LevelGenerator();
		this.player = new Player();
		this.generateLevel();
	}

	@Override
	protected void loop() {
		this.player.move();
		this.world.changeRoom(player);
		
		this.collisions();
		
		this.world.getRoom().featureInteraction(player);
		
		this.player.regenerateHealth();
		this.playerAttacks();
	}

	@Override
	protected void render(Graphics graphics) {
		this.world.getRoom().render(graphics);
		this.player.render(graphics);
		
		graphics.drawImage(Resources.TEXTURES.get(Resources.ATTACK), this.player.getAttackBox().x, this.player.getAttackBox().y, this.player.getAttackBox().width, this.player.getAttackBox().height, null);
		
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.PLAIN, 15));
		graphics.drawImage(Resources.TEXTURES.get(Resources.HEART), 0, 0, Tile.SIZE*2/3, Tile.SIZE*2/3, null);
		graphics.drawString(this.player.getHp()+"/"+this.player.getMaxHp(), Tile.SIZE*2/3+5, 20);
		graphics.drawImage(Resources.TEXTURES.get(Resources.ARMOR), 80, 0, Tile.SIZE*2/3, Tile.SIZE*2/3, null);
		graphics.drawString(this.player.getArmor()+"", Tile.SIZE*2/3+85, 20);
		graphics.drawImage(Resources.TEXTURES.get(Resources.GOLD), 160, 0, Tile.SIZE*2/3, Tile.SIZE*2/3, null);
		graphics.drawString(this.player.getGold()+"", Tile.SIZE*2/3+165, 20);
	}

	@Override
	protected void keyPressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_W:
			this.player.setMovingUp(true);
			break;
		case KeyEvent.VK_A:
			this.player.setMovingLeft(true);
			break;
		case KeyEvent.VK_S:
			this.player.setMovingDown(true);
			break;
		case KeyEvent.VK_D:
			this.player.setMovingRight(true);
			break;
		case KeyEvent.VK_Q:
			this.player.attack();
			break;
		}
	}

	@Override
	protected void keyReleased(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_W:
			this.player.setMovingUp(false);
			break;
		case KeyEvent.VK_A:
			this.player.setMovingLeft(false);
			break;
		case KeyEvent.VK_S:
			this.player.setMovingDown(false);
			break;
		case KeyEvent.VK_D:
			this.player.setMovingRight(false);
			break;
		}
	}
	
	private void generateLevel() {
		this.generator.reset();
		while(!this.generator.finished()) {
			this.generator.generate();
		}
		this.world = new World(this.generator.getRoomsData());
		
		this.world.getRoomRandom().placeFeature(new Feature(Resources.STAIRS, this::generateLevel));
		
		for(int i=0;i<12;i++) {
			this.world.getRoomRandom().placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));
		}
		
		for(int i=0;i<25;i++) {
			this.world.getRoomRandom().spawnEnemy(new Enemy(Resources.ENEMY, 5, this.player));
		}
		
		this.spawnPlayer();
	}
	
	private void spawnPlayer() {
		if(this.world.getRoom(0, 0).getData().getTileAt(player.x / Tile.SIZE, player.y / Tile.SIZE).getID() != Resources.FLOOR) {
			this.player.replaceRandomly();
			this.spawnPlayer();
		}
	}

	private void collisions() {
		RoomData roomIn = this.world.getRoom().getData();
		for(int i=0;i<roomIn.getSizeX();i++) {
			for(int j=0;j<roomIn.getSizeY();j++) {
				this.player.handleCollisionWith(roomIn.getTileAt(i, j));
				for(Enemy enemy : this.world.getRoom().getEnemies()) {
					enemy.handleCollisionWith(roomIn.getTileAt(i, j));
				}
			}
		}
	}
	
	private void givePlayerRandomLoot() {
		switch(MathHelper.randomInt(3)) {
		case 0: this.player.addArmor(MathHelper.randomInt(3, 5)); break;
		case 1: this.player.giveGold(MathHelper.randomInt(3, 7)); break;
		case 2: this.player.instantHeal(MathHelper.randomInt(2, 5)); break;
		}
	}
	
	private void playerAttacks() {
		this.player.decreaseTime();
		for(int i=0;i<this.world.getRoom().getEnemies().size();i++) {
			this.world.getRoom().getEnemies().get(i).move();
			
			if(this.world.getRoom().getEnemies().get(i).intersects(this.player)) {
				this.player.damage(5 -  5*this.player.getArmor()/100);
			}
			
			if(this.world.getRoom().getEnemies().get(i).intersects(this.player.getAttackBox())) {
				this.world.getRoom().getEnemies().get(i).damage(3, this.player.getFacing());
				if(this.world.getRoom().getEnemies().get(i).getHp() <= 0) {
					this.world.getRoom().getEnemies().remove(i);
					this.player.giveGold(MathHelper.randomInt(2, 5));
				}
			}
		}
	}
}
