package dungeoncrawler.game.world;

import java.awt.Graphics;
import java.awt.Rectangle;

import dungeoncrawler.framework.resources.Resources;
import dungeoncrawler.framework.utils.FunctionalInterface;
import dungeoncrawler.framework.utils.MathHelper;

public class Feature extends Tile {

	private static final long serialVersionUID = 1L;

	private FunctionalInterface action;
	
	public Feature(byte id, FunctionalInterface action) {
		super(id, MathHelper.randomInt(2, 14), MathHelper.randomInt(2, 7), false);
		this.action = action;
	}
	
	public Feature(Feature copy) {
		this(copy.getID(), copy.action);
	}

	@Override
	public boolean intersects(Rectangle r) {
		if(super.intersects(r)) {
			this.action.action();
			return true;
		}
		return false;
	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(Resources.TEXTURES.get(super.getID()), super.x, super.y, super.width, super.height, null);
	}
}
