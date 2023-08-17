package com.example.game.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.example.game.main.Animation;
import com.example.game.main.Game;
import com.example.game.main.Handler;
import com.example.game.framework.GameObject;
import com.example.game.framework.ObjectId;
import com.example.game.framework.PlayingTexture;
import com.example.game.state.Playing;

public class Player extends GameObject {
	
	private float width = 50, height = 50;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10, SPEED = (float) (speedGame * 400);
	private float tempX = 0;
	
	private Handler handler;
	
	PlayingTexture texture = Playing.getInstance();
	
	private Animation playerFly;

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;

		playerFly = new Animation(2, texture.player[0], texture.player[1]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if(startGame == 1) {
			x += velX;
			y += velY;
			
			if(velY > 0) {
				falling = false;
			}
			else {
				falling = true;
			}
			
			
			velY += gravity;
			if(velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		
		collision(object);
		playerFly.runAnimation();
	}
	
	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.PLAYER) {
				if(getBounds().getX() < 0) {
					x = 0;
				}
//				else if(getBounds().getX() > Game.WIDTH - width) {
//					x = Game.WIDTH - width;
//				}
				
				if(getBounds().getY() + height >= Game.HEIGHT) {
//					handler.endLevel();
					setStartGame(-1);
				}
			}
			if(tempObject.getId() == ObjectId.BLOCK) {
				if(getBounds().intersects(tempObject.getBounds())) {
//					handler.endLevel();
					setStartGame(-1);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(startGame == 1) {
			if(tempX > SPEED) {
				x++;
				tempX = 0;
			}
			else {
				tempX++;
			}
		}
		
		if(!falling) {
			g.drawImage(texture.player[0], (int) x, (int) y, (int) width, (int) height, null);
		}
		else if(falling) {
			g.drawImage(texture.player[1], (int) x, (int) y, (int) width, (int) height, null);
		}
		else {
			playerFly.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
		}
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.red);
//		g2d.draw(getBounds());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, (int) width, (int) height);
	}
}
