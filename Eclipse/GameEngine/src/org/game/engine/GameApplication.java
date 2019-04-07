/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.game.engine;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 *
 * @author paul
 */
public class GameApplication {

	public static void start(final Game game) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame(game.getTitle());

				frame.setSize(game.getWidth(), game.getHeight());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				GameCanvas canvas = new GameCanvas();
				canvas.setGame(game);
//				canvas.setPreferredSize(game.dimension);
//				canvas.setMaximumSize(game.dimension);
//				canvas.setMinimumSize(game.dimension);
				frame.setLayout(new BorderLayout());
				frame.add(canvas,BorderLayout.CENTER);
				frame.setVisible(true);
				canvas.requestFocus();
				frame.setLocationRelativeTo(null);
//				frame.pack();
				new GameLoop(game, canvas).start();
			}
		});
	}
}
