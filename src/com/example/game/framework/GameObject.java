package com.example.game.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.example.game.state.Playing;

public abstract class GameObject {
	protected float x, y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected float climbX = 0, climbY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected int startGame = 0;
	protected int speedGame = 1;
	protected int scoreGame = 0;
	
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
		startGame = Playing.isFirstStart() ? startGame : 1;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public int getStartGame() {
		return startGame;
	}

	public void setStartGame(int startGame) {
		this.startGame = startGame;
	}

}
