package game;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameScreen extends JPanel {

	public static final int WIDTH = 450;
	public static final int HEIGHT = 550;

	private boolean running;
	private Game game;

	public GameScreen() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		Util.loadAllIamge();
		game = new Game();
		running = false;
	}

	public void start() {
		if (running) {
			return;
		}
		running = true;
		Thread thread = new Thread(new MainLoop());
		thread.start();
	}

	private void update() {
		game.update();

	}

	private void draw(Graphics2D g) {
		game.draw(g);
	}

	private class MainLoop implements Runnable {

		@Override
		public void run() {
			long desiredFrameRateTime = 1000 / 30;
			long currentTime = System.currentTimeMillis();
			long lastTime = currentTime - desiredFrameRateTime;
			long unprocessedTime = 0;
			boolean needsRender = false;
			while (running) {
				currentTime = System.currentTimeMillis();
				unprocessedTime += currentTime - lastTime;
				lastTime = currentTime;

				while (unprocessedTime >= desiredFrameRateTime) {
					unprocessedTime -= desiredFrameRateTime;
					update();
					needsRender = true;
				}

				if (needsRender) {
					Graphics2D g = (Graphics2D) getGraphics();
					draw(g);
					needsRender = false;
				} else {
					try {
						Thread.sleep(1);
					} catch (InterruptedException ex) {
					}
				}
			}
		}

	}

}
