package com.example.game.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.example.game.input.KeypressInput;
import com.example.game.input.MouseInput;
import com.example.game.state.Menu;
import com.example.game.state.Playing;
import com.example.game.state.State;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1158812416093996544L;

	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT; // window size
	
	public static int LEVEL = 1;
	
	private Menu menu;
	private Playing playing;
	
	public static void main(String[] args) {
		new Window(450, 600, "Fly Fly", new Game());
	}
	
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		menu = new Menu(this);
		playing = new Playing(this);
		
		this.addKeyListener(new KeypressInput(this));
		this.addMouseListener(new MouseInput(this));
	}

	public synchronized void start() {
		if(running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		init();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		
		int updates = 0;
		int frames = 0;
		
		double delta = 0;
		
		while(running){
			long now = System.nanoTime();
			
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick(); // updates
				updates++;
				delta--;
			}
			
			render(); // graphics
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		switch(State.STATE) {
			case MENU:
				menu.tick();
				break;
			case PLAYING:
				playing.tick();
				break;
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		switch(State.STATE) {
			case MENU:
				menu.render(g);
				break;
			case PLAYING:
				playing.render(g);
				break;
		}
		
		g.dispose();
		bs.show();
	}
	
	public Menu menu() {
		return menu;
	}
	
	public Playing playing() {
		return playing;
	}
}
