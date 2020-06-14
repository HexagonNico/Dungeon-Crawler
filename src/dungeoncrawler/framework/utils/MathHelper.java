package dungeoncrawler.framework.utils;

import java.util.Random;

public class MathHelper {
	
	private static final Random rand = new Random();

	public static int randomInt(int upperBound) {
		return rand.nextInt(upperBound);
	}

	public static int randomInt(int lowerBound, int upperBound) {
		return rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
	}
	
	public static Direction randomDirection() {
		return Direction.values()[rand.nextInt(Direction.values().length)];
	}
	
	public enum Direction {
		NORTH(0, -1),
		SOUTH(0, 1),
		WEST(-1, 0),
		EAST(1, 0);
		
		public int dirX;
		public int dirY;
		public Direction opposite;
		
		static {
			NORTH.opposite = SOUTH;
			SOUTH.opposite = NORTH;
			WEST.opposite = EAST;
			EAST.opposite = WEST;
		}
		
		private Direction(int dirX, int dirY) {
			this.dirX = dirX;
			this.dirY = dirY;
		}
	}
}
