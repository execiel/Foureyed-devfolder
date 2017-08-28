package com.spp.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Entity{

	
	float y;
	
	public Player(World world) {
		super(BodyType.KinematicBody, world, new Vector2(100, 100), new Vector2(30, 30));
	}

	@Override
	public void render(SpriteBatch b) {
	}

	@Override
	public void update() {
		y++;
		bdef.position.set(30, y);
	}

}
