package com.example.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.example.game.main.Game;
import com.example.game.state.State;

public class MouseInput implements MouseListener {
	private Game game;
	
	public MouseInput(Game game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(State.STATE) {
			case MENU:
				game.menu().mouseClicked(e);
				break;
			case PLAYING:
				game.playing().mouseClicked(e);
				break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
