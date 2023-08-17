package com.example.game.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.example.game.main.Handler;
import com.example.game.framework.GameObject;
import com.example.game.framework.ObjectId;
import com.example.game.framework.PlayingTexture;
import com.example.game.state.Playing;

public class Block extends GameObject {

	PlayingTexture texture = Playing.getInstance();
	private float width, height;
	private final float SPEED = speedGame * 1;
	private float tempX = 0;
	private Handler handler;
	private int start;
	private int type;
	
	public Block(float x, float y, float width, float height, Handler handler, int type, ObjectId id) {
		super(x, y, id);
		this.width = width;
		this.height = height;
		this.handler = handler;
		this.type = type;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		start = handler.object.get(0).getStartGame();
	}

	@Override
	public void render(Graphics g) {
		if(start == 1) {
			if(tempX > SPEED) {
				x--;
				tempX = 0;
			}
			else {
				tempX++;
			}
		}	
		
		int newY = (int) y;
		if(type == 0) {
			newY = (int) (y+height-400);
		}
		
		g.drawImage(texture.block[type], (int) x, (int) newY, (int) width, (int) 400, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, (int)width, (int) height);
	}

}
