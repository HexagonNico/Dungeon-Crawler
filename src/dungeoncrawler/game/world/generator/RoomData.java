package dungeoncrawler.game.world.generator;

import java.awt.Graphics;
import java.util.HashSet;

import dungeoncrawler.framework.resources.Resources;
import dungeoncrawler.framework.utils.MathHelper;
import dungeoncrawler.game.world.Tile;

public class RoomData {

	private Tile[][] tilesData;
	
	private HashSet<MathHelper.Direction> exits;
	
	public RoomData(byte[][] tilesData, MathHelper.Direction... exits) {
		this.tilesData = new Tile[tilesData.length][tilesData[0].length];
		for(int i=0;i<this.tilesData.length;i++) {
			for(int j=0;j<this.tilesData[i].length;j++) {
				this.tilesData[i][j] = new Tile(tilesData[i][j], j, i, tilesData[i][j] == 1 || tilesData[i][j] == 2);
			}
		}
		this.exits = new HashSet<>();
		for(MathHelper.Direction direction : exits) {
			this.exits.add(direction);
		}
	}
	
	public void render(Graphics graphics) {
		for(int i=0;i<this.tilesData.length;i++) {
			for(int j=0;j<this.tilesData[i].length;j++) {
				graphics.drawImage(Resources.TEXTURES.get(this.tilesData[i][j].getID()), j*Tile.SIZE, i*Tile.SIZE, Tile.SIZE, Tile.SIZE, null);
			}
		}
	}
	
	public HashSet<MathHelper.Direction> getExits() {
		return exits;
	}
	
	public Tile getTileAt(int x, int y) {
		return tilesData[y][x];
	}
	
	public int getSizeY() {
		return tilesData.length;
	}
	
	public int getSizeX() {
		return tilesData[0].length;
	}
}
