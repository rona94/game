package com.example.game.framework;

import java.awt.image.BufferedImage;

import com.example.game.main.BufferedImageLoader;

public class PlayingTexture {
	SpriteSheet psUp, psDown, bsUp, bsDown;
	private BufferedImage playerUpSheet = null, playerDownSheet = null;
	private BufferedImage blockUpSheet = null, blockDownSheet = null;
	
	public BufferedImage[] player = new BufferedImage[2];
	public BufferedImage[] block = new BufferedImage[2];
	
	public PlayingTexture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			playerUpSheet = loader.loadImage(loader.PLAYING_PLAYER_UP);
			playerDownSheet = loader.loadImage(loader.PLAYING_PLAYER_DOWN);
			blockUpSheet = loader.loadImage(loader.PLAYING_BLOCK_UP);
			blockDownSheet = loader.loadImage(loader.PLAYING_BLOCK_DOWN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		psUp = new SpriteSheet(playerUpSheet);
		psDown = new SpriteSheet(playerDownSheet);
		
		bsUp = new SpriteSheet(blockUpSheet);
		bsDown = new SpriteSheet(blockDownSheet);
		
		getTexture();
	}

	private void getTexture() {
		// player
		player[0] = psUp.grabImage(1, 1, 60, 60);
		player[1] = psDown.grabImage(1, 1, 60, 60);
		
		// block
		block[0] = bsUp.grabImage(1, 1, 100, 400);
		block[1] = bsDown.grabImage(1, 1, 100, 400);
	}
}
