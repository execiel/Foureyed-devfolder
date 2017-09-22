package com.foureyed.un.util;

import com.badlogic.gdx.math.Vector2;
import com.foureyed.un.entities.Entity;
import com.foureyed.un.entities.EntityID;

public class GravCollider {
	
	/* Sköter spelarens fysik (för tillfället men kan sköta flera sakers) */
	
	private Entity ent;
	private Level world;
	private float maxSpeed;
	
	private float grav;
	private float yvel, startYvel;
	
	private boolean inGround = false;
	private boolean doJump;
	private float jumpCounter = 0, jumpHeight = 20;
	
	public GravCollider(Entity ent, Level world, float maxSpeed) {
		this.ent = ent;
		this.world = world;
		this.maxSpeed = maxSpeed;
		this.grav = world.gravity;
		startYvel = 2;
		yvel = startYvel;
	}
	
	public void handleCollisions(EntityID id) {
		for(Entity e : world.entities) {
			if(e.id == id) {
				
				
				if(getLeftCollider().isColliding(new Collider(e.pos, e.size))) {
					ent.pos.x = e.pos.x + e.size.x;
				}
				if(getRightCollider().isColliding(new Collider(e.pos, e.size))) {
					ent.pos.x = e.pos.x - ent.size.x;
				}
				if(getTopCollider().isColliding(new Collider(e.pos, e.size))) {
					ent.pos.y = e.pos.y - ent.size.y;
					jumpCounter = jumpHeight;
				}
				if(getBottomCollider().isColliding(new Collider(e.pos, e.size))) {
					ent.pos.y = e.pos.y + e.size.y;
					yvel = 2;
					inGround = true;
				}
				
				
			}
		
		} 
	}
	
	public void handleGravity(float gravInc) {
		
		if(doJump) {
			jumpCounter++;
		}
		
		if(jumpCounter >= jumpHeight) {
			jumpCounter = 0;
			yvel = 0;
			doJump = false;
		}
		
		if(!doJump) {
			yvel+=gravInc;
			if(yvel >= grav) yvel = grav;
		} else {
			yvel-=gravInc/2;
			if(yvel <= startYvel) yvel = startYvel;
		}
		
		if(!doJump) ent.pos.y -= yvel;
		else ent.pos.y += yvel;
		
		if(yvel > 2) inGround = false;
		
	}
	
	public void jump() {
		if(inGround) doJump = true;
		yvel = grav;
	}
	
	public Collider getRightCollider() {
		Vector2 pos = new Vector2(ent.pos.x+(ent.size.x-maxSpeed), ent.pos.y+grav);
		Vector2 size = new Vector2(maxSpeed, ent.size.y-grav*2);
		
		return new Collider(pos, size);
	}
	public Collider getLeftCollider() {
		Vector2 pos = new Vector2(ent.pos.x, ent.pos.y+grav);
		Vector2 size = new Vector2(maxSpeed, ent.size.y-grav*2);
		
		return new Collider(pos, size);
	}
	public Collider getTopCollider() {
		Vector2 pos = new Vector2(ent.pos.x+maxSpeed, ent.pos.y+ent.size.y-grav);
		Vector2 size = new Vector2(ent.size.x-maxSpeed*2, grav);
		
		return new Collider(pos, size);
		
	}
	public Collider getBottomCollider() {
		Vector2 pos = new Vector2(ent.pos.x+maxSpeed, ent.pos.y);
		Vector2 size = new Vector2(ent.size.x-maxSpeed*2, grav);
		
		return new Collider(pos, size);
	}
	
}
