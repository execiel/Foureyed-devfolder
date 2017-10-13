package com.foureyed.un.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.foureyed.un.feGame;
import com.foureyed.un.gamestates.Playstate;
import com.foureyed.un.util.Assets;
import com.foureyed.un.util.Collider;
import com.foureyed.un.util.GravCollider;
import com.foureyed.un.util.Level;

public class Player extends Entity{
	
	private GravCollider gcol;
	private Level world;
	private Playstate state;
	private float maxSpeed = 6, xvel = .5f;
	
	
	public Player(Vector2 pos, Level world, Playstate state) {
		super(pos, new Vector2(32, 32), EntityID.PLAYER, 2);
		this.state = state;
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
		
		for(Entity e : world.entities) {
			if(e.id == EntityID.SPIKE) {
				if(this.gcol.getBottomCollider().isColliding(new Collider(e.pos, e.size))) {
					state.reset();
				}
			}
			if(e.id == EntityID.GOAL) {
				if(this.gcol.getBottomCollider().isColliding(new Collider(e.pos, e.size))) {
					world.nextLevel();
				}
			}
		}
		
		
		if(!Gdx.input.isKeyPressed(Input.Keys.Q)) gcol.handleGravity(0.3f);
		gcol.handleCollisions(EntityID.TILE);
	}

	@Override
	public void destroy() {
		active = false;
	}

}
