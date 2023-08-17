package com.example.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {
	public void tick();
	public void render(Graphics g);
	
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);

	public void mouseClicked(MouseEvent e);
}
