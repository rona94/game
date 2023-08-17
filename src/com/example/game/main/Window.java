package com.example.game.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7416758496885055735L;

	public Window(int width, int height, String title, Game game) {
		// set frame size
		Dimension size = new Dimension(width, height);
		game.setPreferredSize(size);
		game.setMaximumSize(size);
		game.setMinimumSize(size);
		
		add(game);
		setTitle(title);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		game.start();
	}
}
