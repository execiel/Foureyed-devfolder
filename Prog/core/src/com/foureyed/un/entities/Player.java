package com.foureyed.un.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.foureyed.un.feGame;
import com.foureyed.un.gamestates.Playstate;
import com.foureyed.un.util.Assets;
import com.foureyed.un.util.GravCollider;
import com.foureyed.un.util.World;

public class Player extends Entity{
	
	//spelarens grav kollider som sköter kollisioner och gravitiation
	GravCollider gcol;
	//Världen som spelaren är i
	World world;
	//maxspeed = högsta hastigheten, xvel = nuvarande hastigheten
	private float maxSpeed = 5, xvel = .5f;
	
	
	public Player(Vector2 pos, World world) {
		super(pos, new Vector2(32, 32), EntityID.PLAYER, 2);
		
		this.world = world;
		gcol = new GravCollider(this, world, maxSpeed);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(Assets.playerspr, pos.x, pos.y, size.x, size.y);
	}

	@Override
	public void update() {
		
		//ökar xvel konstant
		xvel+=0.5f;
		if(xvel >= maxSpeed) xvel = maxSpeed;
		
		//spelarens rörelse
		if(Gdx.input.isKeyPressed(Input.Keys.A))
			pos.x-=xvel;
		else if(Gdx.input.isKeyPressed(Input.Keys.D))
			pos.x+=xvel;
		else
			//återsställer xvel när man inte rör sig
			xvel = 1f;
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) gcol.jump();
		if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			world.destroy();
			feGame.setState(new Playstate());
		}
		
		
		gcol.handleGravity(0.3f);
		gcol.handleCollisions(EntityID.TILE);
	}

	@Override
	public void destroy() {
		active = false;
	}

}
