package character;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Pacman extends Frame implements KeyListener {

	BufferedImage packman;
	int frame;
	int column, row;
	int reqDir, curDir;
	final int STEP = 1;
	int columns, rows;

	ArrayList<String> lines = new ArrayList<String>();

	public Pacman() {
		addKeyListener(this);
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

	public void draw(Graphics2D g) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				if (charAt(r, c) != '0') {
					g.fillRect(c * 2 - 14, r * 2 - 14, 28, 28);
				}
			}
		}
		g.drawImage(packman.getSubimage((frame / 2) * 30, (curDir - 37) * 30, 28, 28), column * 2 - 14,
				row * 2 - 14, null);
	}

	public void move() {
		frame++;
		if (frame > 5) {
			frame = 0;
		}

		if (update(reqDir) == SUCCESS) {
			curDir = reqDir;
		} else {
			update(curDir);
		}
	}

	static final int SUCCESS = 1, FAIL = 0;

	private int update(int reqDir) {
		// current position of packman is (row,column)
		switch (reqDir) {
		case KeyEvent.VK_LEFT: // 37
			if (column > 0 && charAt(row, column - 1) != '0') {
				column -= 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_UP: // 38
			if (row > 0 && charAt(row - 1, column) != '0') {
				row -= 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_RIGHT: // 39
			if (column < columns - 1 && charAt(row, column + 1) != '0') {
				column += 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_DOWN: // 40
			if (row < rows - 1 && charAt(row + 1, column) != '0') {
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (37 <= key && key <= 40) {
			reqDir = key;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
