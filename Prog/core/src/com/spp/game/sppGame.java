package com.spp.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spp.game.gamestates.Gamestate;
import com.spp.game.gamestates.Playstate;

public class sppGame extends ApplicationAdapter {
	
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	private static Gamestate currentGamestate;
	
	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setGamestate(new Playstate());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		
		if(currentGamestate != null) {
			currentGamestate.render(batch);
			currentGamestate.update();
		} else System.out.println("gamestate e null");
	}
	
	
	public static void setGamestate(Gamestate g)  {
		if(currentGamestate != null) currentGamestate.destroy();
		System.out.println(g.getName() + " has been set as gamestate");
		currentGamestate = g;
		currentGamestate.init();
	} 
	
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
