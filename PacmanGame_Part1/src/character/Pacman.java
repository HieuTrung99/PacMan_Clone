package character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import game.Keyboard;

public class Pacman {

	BufferedImage packman;
	int frame;
	int column, row;
	int reqDir, curDir;
	final int STEP = 2;
	int columns, rows;
	ArrayList<String> lines = new ArrayList<String>();

	public Pacman() {
		try { 
			Scanner s = new Scanner(new File("data.txt"));
			int r = 0;

			while (s.hasNextLine()) {
				String line = s.nextLine();
				lines.add(line);
				if (line.contains("5")) {
					row = r;
					column = line.indexOf('5');
				}
				r++;
			}
			s.close();
			rows = lines.size();
			columns = lines.get(0).length();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		frame = 0;

		curDir = reqDir = KeyEvent.VK_RIGHT;

		try {
			packman = ImageIO.read(Pacman.class.getResource("/images/packman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	public void updatePlaying() {
		
		if (Keyboard.keys[KeyEvent.VK_LEFT]) {
			
			reqDir = KeyEvent.VK_LEFT;
		}
		 if (Keyboard.keys[KeyEvent.VK_RIGHT]) {
			reqDir = KeyEvent.VK_RIGHT;
	
		}
		 if (Keyboard.keys[KeyEvent.VK_DOWN]) {
			
			reqDir = KeyEvent.VK_DOWN;
		}
		 if (Keyboard.keys[KeyEvent.VK_UP]) {
			reqDir = KeyEvent.VK_UP;
		}
	}

	public void update() {
		updatePlaying();
		frame++;
		if (frame > 5) {
			frame = 0;
		}
		if (move(reqDir) == SUCCESS) {
			curDir = reqDir;
		
		} else {
			move(curDir);
		}
	}

	static final int SUCCESS = 1, FAIL = 0;

	private int move(int reqDir) {
		
		switch (reqDir) {
		case KeyEvent.VK_LEFT: // 37
			if (column > 0 && charAt(row, column-1) != '0') {
				column -= 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_UP:   // 38
			if (row > 0 && charAt(row-1, column) != '0') {
				row -= 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_RIGHT: // 39
			if (column < columns-1 && charAt(row, column+1) != '0') {
				column += 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_DOWN:  // 40
			if (row < rows-1 && charAt(row+1, column) != '0') {
				row += 1;
				return SUCCESS;
			}
			break;
		}
		return FAIL;
		
	}

	private char charAt(int row, int column) {
		return lines.get(row).charAt(column);
	}
	
	public void draw(Graphics2D g) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				if (charAt(r, c) != '0') {
					g.fillRect(c * 2 - 14, r * 2 - 14, 28, 28);
					g.setColor(Color.white);
				}
			}
		}
		g.drawImage(packman.getSubimage((frame / 2) * 30, (curDir - 37) * 30, 28, 28), column * STEP - 14, row * STEP - 14,
				null);
	}

}
