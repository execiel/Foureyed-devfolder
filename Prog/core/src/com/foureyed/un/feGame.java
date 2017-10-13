package com.foureyed.un;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.foureyed.un.gamestates.Gamestate;
import com.foureyed.un.gamestates.Playstate;

public class feGame extends ApplicationAdapter {
	
	//Fönstret bredd och höjd
	public static final int WIDTH = 960;
	public static final int HEIGHT = 600;
	
	
	private SpriteBatch batch;
	private static Gamestate gamestate;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setState(new Playstate());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Rita och updatera ut nuvarande gamestate om det finns
		if(gamestate != null) {
			gamestate.render(batch);
			gamestate.update();
		}
		
	}

	public static void setState(Gamestate g) {
		//Om det redan finns ett gamestate så se till att destroy kallas
		if(gamestate != null) gamestate.destroy();
		gamestate = g;
		gamestate.init(); //Kalla init
		System.out.println(gamestate.getName() + " is current gamestate!");
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
