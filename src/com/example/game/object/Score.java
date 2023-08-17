package com.example.game.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.example.game.main.Game;
import com.example.game.main.Handler;
import com.example.game.framework.GameObject;
import com.example.game.framework.ObjectId;
import com.example.game.framework.PlayingTexture;
import com.example.game.state.Playing;

public class Score extends GameObject {

	PlayingTexture texture = Playing.getInstance();
	private float width, height;
	private Handler handler;
	private String score = "0";
	private int start = 0;
	
	public Score(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		start = object.get(0).getStartGame();
		if(start == 1) {
			float newX = 0;
			
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ObjectId.BLOCK) {
					newX = tempObject.getX();
					break;
				}
			}
			
			if(newX < Game.WIDTH * 0.3) {
				score = Integer.toString((int) (Math.abs(newX) / (Game.WIDTH * 0.7)) + 1);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		int w = 70;
		int h = 50;
		g.setColor(Color.gray);
		g.fillRect((int)Game.WIDTH/2 - (int)w/2, 50, w, h);
		
		g.setColor(Color.black);
		g.setFont(new Font("Calibri", Font.PLAIN, 25)); 
		g.drawString(score, (int)Game.WIDTH/2 - (int)w/2 + ((5-score.length())*7+1), 84);
		
		if(start == -1) {
			g.setColor(new Color(0,0,0, 200));
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.PLAIN, 35)); 
			g.drawString("Game Over", (int)Game.WIDTH/2 - 80, 220);
			
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.PLAIN, 70)); 
			g.drawString(score, (int)Game.WIDTH/2 - (38*score.length() / 2), 300);
			
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.PLAIN, 25)); 
			g.drawString("pts", (int)Game.WIDTH/2 - 18, 330);
			
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.ITALIC, 16)); 
			g.drawString("press            to play again", (int)Game.WIDTH/2 - 80, 370);
			
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.ITALIC | Font.BOLD, 16));
			g.drawString("space", (int)Game.WIDTH/2 - 40, 370);
		}
		else if (start == 0 && Playing.isFirstStart()) {
			g.setColor(new Color(0,0,0, 100));
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.ITALIC, 16)); 
			g.drawString("press            to play", (int)Game.WIDTH/2 - 80, Game.HEIGHT/2);
			
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.ITALIC | Font.BOLD, 16));
			g.drawString("space", (int)Game.WIDTH/2 - 40, Game.HEIGHT/2);
		}
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

}
