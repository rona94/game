package com.example.game.state;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.example.game.main.BufferedImageLoader;
import com.example.game.main.Camera;
import com.example.game.main.Game;
import com.example.game.main.Handler;
import com.example.game.framework.GameObject;
import com.example.game.framework.ObjectId;
import com.example.game.framework.PlayingTexture;

public class Playing implements StateMethods {

	private BufferedImage clouds = null;
	
	// Object
	Handler handler;
	Camera cam;
	static PlayingTexture texture;
	private static boolean firstStart = true;
	
	public Playing(Game game) {
		texture = new PlayingTexture();
		cam = new Camera(0, 0);
		handler = new Handler(cam);
		handler.switchLevel();
		
		loadImgs();
	}
	
	private void loadImgs() {
		BufferedImageLoader loader = new BufferedImageLoader();
		clouds = loader.loadImage(loader.PLAYING_CLOUDS);
	}
	
	public static PlayingTexture getInstance() {
		return texture;
	}
	
	@Override
	public void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ObjectId.PLAYER) {
				int startGame = handler.object.get(i).getStartGame();
				cam.tick(handler.object.get(i), startGame);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		GradientPaint gradient = new GradientPaint(0, 0, new Color(111,202,215), 0, Game.HEIGHT, new Color(163,232,238));
	    g2d.setPaint(gradient);

	    g2d.fill(new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT));
		
//	    g2d.translate(cam.getX(), cam.getY()); // begin of camera
	    
	    g.drawImage(clouds, 40, 150, null);
	    g.drawImage(clouds, 250, 70, null);
	    
		handler.render(g);
//		g2d.translate(-cam.getX(), -cam.getY()); // end of camera
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if(tempObj.getId() == ObjectId.PLAYER) {
				if(key == KeyEvent.VK_D) {
					tempObj.setVelX(5);
				}
				else if(key == KeyEvent.VK_A) {
					tempObj.setVelX(-5);
				}
				
				if(key == KeyEvent.VK_SPACE) {
					firstStart = false;
					
					if(tempObj.getStartGame() == -1) {
						handler.endLevel();
					}
					
					if(tempObj.getStartGame() == 1) {
						tempObj.setVelY(-10);
					}
					if(tempObj.getStartGame() != -1) {
						tempObj.setStartGame(1);
					}

					tempObj.setJumping(true);
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
//			System.exit(1);
			State.STATE = State.MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if(tempObj.getId() == ObjectId.PLAYER) {
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_A) {
					tempObj.setVelX(0);
				}
				
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		need to fix
//		for (int i = 0; i < handler.object.size(); i++) {			
//			GameObject tempObject = handler.object.get(i);
//
//			if(tempObject.getId() == ObjectId.BUTTON) {
//				if(tempObject.getBounds().intersects(e.getX(), e.getY(), 1, 1)) {
//					tempObject.setStartGame(0);
//					handler.endLevel();
//				}
//			}
//		}
	}

	public static boolean isFirstStart() {
		return firstStart;
	}
}
