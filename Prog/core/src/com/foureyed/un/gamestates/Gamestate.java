package com.foureyed.un.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Gamestate {
	/* Ett gamestate är*/
	public abstract String getName();
	public abstract void init();
	public abstract void render(SpriteBatch sb);
	public abstract void update();
	public abstract void destroy();
}
