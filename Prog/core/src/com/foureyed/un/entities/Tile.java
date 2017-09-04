package com.foureyed.un.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.foureyed.un.util.Assets;

public class Tile extends Entity{
	
	public Tile(Vector2 pos) {
		super(pos, new Vector2(32, 32), EntityID.TILE, 1);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(Assets.tilespr, pos.x, pos.y, size.x, size.y);
	}

	@Override
	public void update() {

	}

	@Override
	public void destroy() {
		active = false;
	}

}
