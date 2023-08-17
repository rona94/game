package com.example.game.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.example.game.main.BufferedImageLoader;
import com.example.game.main.Handler;
import com.example.game.framework.GameObject;
import com.example.game.framework.ObjectId;

public class Button extends GameObject {

	private BufferedImage[] buttons;
	private BufferedImageLoader loadSave;
	private float width, height;
//	private int index, start;
	private int index;
//	private Handler handler;
	
	public Button(float x, float y, float width, float height, Handler handler, int index, ObjectId id) {
		super(x, y, null);
		
		this.width = width;
		this.height = height;
		this.index = index;
//		this.handler = handler;
		
		loadSave = new BufferedImageLoader();
		buttons = new BufferedImage[3];
		buttons[0] = loadSave.loadImage(loadSave.MENU_PLAY);
		buttons[1] = loadSave.loadImage(loadSave.MENU_QUIT);
		buttons[2] = loadSave.loadImage(loadSave.MENU_BUTTON);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// temporary: need to fix mouse click
//		if(handler != null) {
//			start = handler.object.get(0).getStartGame();
//		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(buttons[index], (int)x, (int) y, (int) width, (int) height, null);
		
		// temporary: need to fix mouse click
//		if(State.STATE == State.MENU || (State.STATE == State.PLAYING && start == -1)) {
//			g.drawImage(buttons[index], (int)x, (int) y, (int) width, (int) height, null);
//			
//			if(State.STATE == State.PLAYING) {
//				g.setColor(Color.white);
//				g.setFont(new Font("Calibri", Font.PLAIN, 25)); 
//				g.drawString("Play Again", (int) (Game.WIDTH/2) - 50, (int) y + (int) (height/2) + 10);
//			}
//		}
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, (int) width, (int) height);
	}


}
