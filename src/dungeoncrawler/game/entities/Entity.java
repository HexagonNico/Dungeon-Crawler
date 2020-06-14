package dungeoncrawler.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dungeoncrawler.framework.resources.Resources;
import dungeoncrawler.framework.utils.MathHelper;
import dungeoncrawler.game.world.Tile;

public class Entity extends Rectangle {

	private static final long serialVersionUID = 1L;

	protected byte entityID;
	
	protected boolean up;
	protected boolean down;
	protected boolean left;
	protected boolean right;
	
	protected int speed;
	
	protected MathHelper.Direction facing;
	
	protected byte animationFrame;
	protected byte animationDelay;
	
	public Entity(byte id, int posXinRoom, int posYinRoom) {
		super(posXinRoom*Tile.SIZE, posYinRoom*Tile.SIZE, Tile.SIZE, Tile.SIZE);
		this.entityID = id;
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
		this.speed = 5;
		this.facing = MathHelper.Direction.SOUTH;
		this.animationFrame = 0;
	}
	
	public byte getID() {
		return entityID;
	}
	
	public void move() {
		if(up) {
			super.y-=this.speed;
			this.facing = MathHelper.Direction.NORTH;
		}
		if(down) {
			super.y+=this.speed;
			this.facing = MathHelper.Direction.SOUTH;
		}
		if(left) {
			super.x-=this.speed;
			this.facing = MathHelper.Direction.WEST;
		}
		if(right) {
			super.x+=this.speed;
			this.facing = MathHelper.Direction.EAST;
		}
	}
	
	public void setMovingUp(boolean up) {
		this.up = up;
	}
	
	public void setMovingDown(boolean down) {
		this.down = down;
	}
	
	public void setMovingLeft(boolean left) {
		this.left = left;
	}
	
	public void setMovingRight(boolean right) {
		this.right = right;
	}
	
	public void setCenterX(int x) {
		super.x = x - super.width/2;
	}

	public void setCenterY(int y) {
		super.y = y - super.height/2;
	}
	
	public void render(Graphics graphics) {
		if(up || down || left || right) {
			this.animationDelay++;
			if(this.animationDelay == 70) {
				this.animationDelay = 0;
				this.animationFrame = (byte) (1 - this.animationFrame);
			}
		}
		graphics.drawImage(Resources.TEXTURES.get(entityID + animationFrame), super.x, super.y, super.width, super.height, null);
	}
	
	public void handleCollisionWith(Tile tile) {
		Rectangle intersection = this.intersection(tile);
		if(intersection.isEmpty() || !tile.isWall())
			return;
			
		if(intersection.width > intersection.height) {
			if(this.y < tile.y)
				this.y = tile.y - this.height;
			else
				this.y = tile.y + this.height;
		}
		else {
			if(this.x < tile.x)
				this.x = tile.x - this.width;
			else
				this.x = tile.x + this.width;
		}
	}
	
	public MathHelper.Direction getFacing() {
		return facing;
	}
}
