package dungeoncrawler.framework;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import dungeoncrawler.framework.gamestates.GameStateManager;
import dungeoncrawler.framework.gui.WindowManager;
import dungeoncrawler.game.states.MainMenu;

public class Engine {

	private static GameStateManager gameStateManager;
	
	private static WindowManager windowManager;
	private static Timer timer;
	
	public static void init() {
		gameStateManager = new GameStateManager();
		windowManager = new WindowManager();
		timer = new Timer(20, new MainGameLoop());
	}
	
	public static void start() {
		gameStateManager.stackState(new MainMenu(gameStateManager));
		windowManager.addPanel(new GameScreen());
		windowManager.addKeyListener(new Keyboard());
		windowManager.createWindow();
		timer.start();
	}
	
	private static class MainGameLoop implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gameStateManager.loop();
		}
		
	}
	
	private static class GameScreen extends JPanel {
		
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			gameStateManager.render(g);
			repaint();
		}
	}
	
	private static class Keyboard implements KeyListener {

		@Override
		public void keyPressed(KeyEvent key) {
			gameStateManager.keyPressed(key.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent key) {
			gameStateManager.keyReleased(key.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
	}
}
