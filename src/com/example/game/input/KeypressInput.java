package com.example.game.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.example.game.main.Game;
import com.example.game.state.State;

public class KeypressInput extends KeyAdapter {
	private Game game;
	
	public KeypressInput(Game game) {
		this.game = game;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(State.STATE) {
			case MENU:
				game.menu().keyPressed(e);
				break;
			case PLAYING:
				game.playing().keyPressed(e);
				break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch(State.STATE) {
			case MENU:
				game.menu().keyReleased(e);
				break;
			case PLAYING:
				game.playing().keyReleased(e);
				break;
		}
	}
}
