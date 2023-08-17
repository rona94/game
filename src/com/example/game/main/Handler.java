package com.example.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import com.example.game.framework.GameObject;
import com.example.game.framework.ObjectId;
import com.example.game.object.Block;
import com.example.game.object.Player;
import com.example.game.object.Score;

public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject tempObject;
//	private Camera cam;
	private BufferedImage stage;
	
	Random rand = new Random();
	
	public Handler() {
		
	}
	
	public Handler(Camera cam) {
//		this.cam = cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();		
		stage = loader.loadImage(loader.LEVEL);
	}
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void switchLevel() {
		clearLevel();
		LoadImageLevel(stage);
//		cam.setX(0);
		
//		if(Game.LEVEL <= level.length) {
//			LoadImageLevel(level[Game.LEVEL-1]);
//			Game.LEVEL++;
//		}
//		else {
//			Game.LEVEL = 1;
//			State.STATE = State.MENU;
//		}
	}
	
	public void endLevel() {
		clearLevel();
//		cam.setX(0);
//		Game.LEVEL = 1;
		LoadImageLevel(stage);
	}
	
	public void clearLevel() {
		object.clear();
	}
	
	public void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		int scoreRow = 0, scoreCol = 0;
		
		// player
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Color pixel = new Color(image.getRGB(j, i));
				int red = pixel.getRed();
				int green = pixel.getGreen();
				int blue = pixel.getBlue();
				
				if(red == 0 && green == 0 && blue == 0) { // PLAYER - black
					addObject(new Player(j*32, i*32, this, ObjectId.PLAYER));
				}
				else if(red == 127 && green == 127 && blue == 127) { // SCORE - gray
					scoreRow = j*32;
					scoreCol = i*32;
				}
			}
		}
		
		// block
		double blocks = 400;
		int blockW = 80;
		
		for (int i = 1; i < blocks; i++) {
			// need to fix
//			double percent = rand.nextDouble(0.02);
			int blockX = (int) (Game.WIDTH*0.7);
			
			double percentH = (i / 1000 * 0.1) + 0.1;
			int blockH1 = (int) (Game.HEIGHT*percentH) + rand.nextInt((int) (Game.HEIGHT*0.30));
			int blockH2 = (int) (Game.HEIGHT*percentH) + rand.nextInt((int) (Game.HEIGHT*0.30));
			
			addObject(new Block(blockX * i, 0, blockW, blockH1, this, 0, ObjectId.BLOCK));
			addObject(new Block(blockX * i, Game.HEIGHT - blockH2, blockW, blockH2, this, 1, ObjectId.BLOCK));
		}
		
		// score
		addObject(new Score(scoreRow, scoreCol, this, ObjectId.SCORE));
//		addObject(new Button((int) (Game.WIDTH/2) - 100, 355, 200, 54, this, 2, ObjectId.BUTTON)); // need to fix
	}
}
