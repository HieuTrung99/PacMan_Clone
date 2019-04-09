package game;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import character.Background;
import character.Pacman;

public class Game {
	Pacman pacman;

	Background background;

	public Game() {
		pacman = new Pacman();
		background = new Background();
	}

	public void update() {
		pacman.move();
	}

	public void draw(Graphics2D g) {
		pacman.draw(g);
		background.draw(g);
	}

}
