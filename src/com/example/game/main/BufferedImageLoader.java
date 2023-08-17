package com.example.game.main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage image;
	
	public final String LEVEL = "/stage.png";
	
	public final String PLAYING_PLAYER_UP = "/sprite_0.png";
	public final String PLAYING_PLAYER_DOWN = "/sprite_1.png";
	public final String PLAYING_BLOCK_UP = "/pipe2.png";
	public final String PLAYING_BLOCK_DOWN = "/pipe.png";
	public final String PLAYING_CLOUDS = "/cloud2.png";
	
	public final String MENU_PLAY = "/play_button.png"; 
	public final String MENU_QUIT = "/quit_button.png"; 
	public final String MENU_BUTTON = "/button.png"; 
	
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
}
