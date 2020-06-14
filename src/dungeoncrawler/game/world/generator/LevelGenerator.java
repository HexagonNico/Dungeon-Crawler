package dungeoncrawler.game.world.generator;

import java.util.HashSet;

import dungeoncrawler.framework.utils.MathHelper;

public class LevelGenerator {

	public static final int WORLD_SIZE = 5;
	
	private int posX;
	private int posY;
	
	private HashSet<MathHelper.Direction>[][] roomsData;
	private boolean generated[][];

	@SuppressWarnings("unchecked")
	public void reset() {
		this.roomsData = new HashSet[WORLD_SIZE][WORLD_SIZE];
		this.generated = new boolean[WORLD_SIZE][WORLD_SIZE];
		for(int i=0;i<this.roomsData.length;i++) {
			for(int j=0;j<this.roomsData[i].length;j++) {
				this.roomsData[i][j] = new HashSet<>();
				this.generated[i][j] = false;
			}
		}
		this.setRandomPosition();
	}
	
	public void generate() {
		MathHelper.Direction direction = MathHelper.randomDirection();
		if(this.isValidPosition(posX + direction.dirX, posY + direction.dirY)) {
			if(!this.generated[posX + direction.dirX][posY + direction.dirY]) {
				this.roomsData[posX][posY].add(direction);
				this.roomsData[posX + direction.dirX][posY + direction.dirY].add(direction.opposite);
			}
			this.posX += direction.dirX;
			this.posY += direction.dirY;
			this.generated[posX][posY] = true;
		}
		else {
			this.generate();
		}
	}
	
	private void setRandomPosition() {
		this.posX = MathHelper.randomInt(WORLD_SIZE);
		this.posY = MathHelper.randomInt(WORLD_SIZE);
		this.generated[posX][posY] = true;
	}
	
	private boolean isValidPosition(int x, int y) {
		return x >= 0 && y >= 0 && x < WORLD_SIZE && y < WORLD_SIZE;
	}
	
	public boolean finished() {
		for(int i=0;i<this.generated.length;i++) {
			for(int j=0;j<this.generated[i].length;j++) {
				if(!this.generated[i][j]) return false;
			}
		}
		return true;
	}
	
	public HashSet<MathHelper.Direction>[][] getRoomsData() {
		return roomsData;
	}
}
