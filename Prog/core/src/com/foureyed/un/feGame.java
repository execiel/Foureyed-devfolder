package com.foureyed.un;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.foureyed.un.gamestates.Gamestate;
import com.foureyed.un.gamestates.Playstate;

public class feGame extends ApplicationAdapter {
	
	public static final int WIDTH = 720;
	public static final int HEIGHT = 480;
	
	private SpriteBatch batch;
	private static Gamestate gamestate;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setState(new Playstate());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(gamestate != null) {
			gamestate.render(batch);
			gamestate.update();
		}
		
	}
	
	
	public static void setState(Gamestate g) {
		if(gamestate != null) gamestate.destroy();
		gamestate = g;
		gamestate.init();	
		System.out.println(gamestate.getName() + " is current gamestate!");
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
