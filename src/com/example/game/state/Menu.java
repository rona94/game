package com.example.game.state;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.example.game.main.Game;
import com.example.game.main.Handler;
import com.example.game.framework.GameObject;
import com.example.game.framework.ObjectId;
import com.example.game.object.Button;

public class Menu implements StateMethods {

	Handler handler;
	private int width = 200, height = 54;
	
	public Menu(Game game) {
		handler = new Handler();
		
		handler.addObject(new Button(Game.WIDTH/2 - width/2, Game.HEIGHT/2 - height - 50, width, height, null, 0, ObjectId.MENU));
		handler.addObject(new Button(Game.WIDTH/2 - width/2, Game.HEIGHT/2 - 20, width, height, null, 1, ObjectId.MENU));
	}
	
	@Override
	public void tick() {
		handler.tick();
	}

	@Override
	public void render(Graphics g) {
		// background
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(0, 0, new Color(111,202,215), 0, Game.HEIGHT, new Color(163,232,238));
	    g2d.setPaint(gradient);
	    g2d.fill(new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT));
	    
	    handler.render(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < handler.object.size(); i++) {			
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getBounds().intersects(e.getX(), e.getY(), 1, 1)) {
				if(i == 0) { // play
					State.STATE = State.PLAYING;
				}
				else if(i == 1) { // quit
					System.exit(1);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE) {
			State.STATE = State.PLAYING;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
