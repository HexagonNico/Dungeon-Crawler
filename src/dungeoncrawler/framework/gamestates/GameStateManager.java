package dungeoncrawler.framework.gamestates;

import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.Stack;

public class GameStateManager {

	private Stack<GameState> states;
	
	public GameStateManager() {
		this.states = new Stack<>();
	}
	
	public void stackState(GameState state) {
		this.states.add(state);
	}
	
	public void backToPreviousState() {
		this.states.pop();
	}
	
	public void clearStack() {
		this.states.clear();
	}

	public void loop() {
		try {
			this.states.peek().loop();
		} catch(EmptyStackException e) {
			System.err.println("[GameStateManager]: Error! GameState stack is empty!");
			System.exit(-1);
		}
	}

	public void render(Graphics graphics) {
		try {
			this.states.peek().render(graphics);
		} catch(EmptyStackException e) {
			System.err.println("[GameStateManager]: Error! GameState stack is empty!");
			System.exit(-1);
		}
	}
	
	public void keyPressed(int keyCode) {
		try {
			this.states.peek().keyPressed(keyCode);
		} catch(EmptyStackException e) {
			System.err.println("[GameStateManager]: Error! GameState stack is empty!");
			System.exit(-1);
		}
	}
	
	public void keyReleased(int keyCode) {
		try {
			this.states.peek().keyReleased(keyCode);
		} catch(EmptyStackException e) {
			System.err.println("[GameStateManager]: Error! GameState stack is empty!");
			System.exit(-1);
		}
	}
}
