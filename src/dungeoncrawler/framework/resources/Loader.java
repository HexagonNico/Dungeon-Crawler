package dungeoncrawler.framework.resources;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static void load() {
		try {
			File texturesFolder = new File("res/rooms");
			for(File imgFile : texturesFolder.listFiles()) {
					Resources.ROOMS.put(imgFile.getName(), ImageIO.read(imgFile));
			}
			Resources.TEXTURES.add(Resources.FLOOR, ImageIO.read(new File("res/textures/floor.png")));
			Resources.TEXTURES.add(Resources.STONE, ImageIO.read(new File("res/textures/stone.png")));
			Resources.TEXTURES.add(Resources.WALL, ImageIO.read(new File("res/textures/wall.png")));
			Resources.TEXTURES.add(Resources.TILE, ImageIO.read(new File("res/textures/tile.png")));
			Resources.TEXTURES.add(Resources.GRASS, ImageIO.read(new File("res/textures/grass.png")));
			Resources.TEXTURES.add(Resources.PLAYER, ImageIO.read(new File("res/textures/player.png")));
			Resources.TEXTURES.add(Resources.PLAYER_2, ImageIO.read(new File("res/textures/player_2.png")));
			Resources.TEXTURES.add(Resources.PLAYER_LEFT, ImageIO.read(new File("res/textures/player_left.png")));
			Resources.TEXTURES.add(Resources.PLAYER_LEFT_2, ImageIO.read(new File("res/textures/player_left_2.png")));
			Resources.TEXTURES.add(Resources.PLAYER_RIGHT, ImageIO.read(new File("res/textures/player_right.png")));
			Resources.TEXTURES.add(Resources.PLAYER_RIGHT_2, ImageIO.read(new File("res/textures/player_right_2.png")));
			Resources.TEXTURES.add(Resources.PLAYER_BACK, ImageIO.read(new File("res/textures/player_back.png")));
			Resources.TEXTURES.add(Resources.PLAYER_BACK_2, ImageIO.read(new File("res/textures/player_back_2.png")));
			Resources.TEXTURES.add(Resources.STAIRS, ImageIO.read(new File("res/textures/stairs.png")));
			Resources.TEXTURES.add(Resources.CHEST, ImageIO.read(new File("res/textures/chest.png")));
			Resources.TEXTURES.add(Resources.ENEMY, ImageIO.read(new File("res/textures/enemy.png")));
			Resources.TEXTURES.add(Resources.ENEMY_2, ImageIO.read(new File("res/textures/enemy_2.png")));
			Resources.TEXTURES.add(Resources.ATTACK, ImageIO.read(new File("res/textures/attack.png")));
			Resources.TEXTURES.add(Resources.HEART, ImageIO.read(new File("res/textures/heart.png")));
			Resources.TEXTURES.add(Resources.ARMOR, ImageIO.read(new File("res/textures/armor.png")));
			Resources.TEXTURES.add(Resources.GOLD, ImageIO.read(new File("res/textures/gold.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	//NEED TO FIND BETTER SOLUTION
//	public static BufferedImage getRoom(HashSet<MathHelper.Direction> dirs) {
//		if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST) && dirs.contains(MathHelper.Direction.NORTH))
//			return ROOMS.get("nswe.png");
//		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
//			return ROOMS.get("nes.png");
//		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
//			return ROOMS.get("sew.png");
//		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.NORTH))
//			return ROOMS.get("nsw.png");
//		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.EAST))
//			return ROOMS.get("new.png");
//		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.SOUTH))
//			return ROOMS.get("ns.png");
//		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.EAST))
//			return ROOMS.get("we.png");
//		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.EAST))
//			return ROOMS.get("ne.png");
//		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.WEST))
//			return ROOMS.get("nw.png");
//		else if(dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
//			return ROOMS.get("se.png");
//		else if(dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.WEST))
//			return ROOMS.get("sw.png");
//		else if(dirs.contains(MathHelper.Direction.NORTH))
//			return ROOMS.get("n.png");
//		else if(dirs.contains(MathHelper.Direction.SOUTH))
//			return ROOMS.get("s.png");
//		else if(dirs.contains(MathHelper.Direction.WEST))
//			return ROOMS.get("w.png");
//		else if(dirs.contains(MathHelper.Direction.EAST))
//			return ROOMS.get("e.png");
//		else
//			return ROOMS.get("deh.png");
//	}
}
