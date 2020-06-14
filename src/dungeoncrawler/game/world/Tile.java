package dungeoncrawler.game.world;

import java.awt.Rectangle;

public class Tile extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public static final int SIZE = 50;
	
	private byte tileID;
	private boolean wall;
	
	public Tile(byte id, int posXinRoom, int posYinRoom, boolean isWall) {
		super(posXinRoom * SIZE, posYinRoom * SIZE, SIZE, SIZE);
		this.tileID = id;
		this.wall = isWall;
	}
	
	public byte getID() {
		return tileID;
	}
	
	public boolean isWall() {
		return wall;
	}
}
