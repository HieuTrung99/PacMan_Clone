package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Util {

	public static HashMap<String, BufferedImage> cache = new HashMap<String, BufferedImage>();

	public static void loadAllIamge() {
		String pathname = "C:\\Users\\Admin\\eclipse-workspace\\PacmanGame\\src\\images\\";
		File file = new File(pathname);

		for (String child : file.list()) {
			loadImage(pathname + child);
		}
	}

	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		if (cache.containsKey(path)) {
			return cache.get(path);
		}
		try {
			image = ImageIO.read(new File(path));
			if (!cache.containsKey(path)) {
				cache.put(path, image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

}
