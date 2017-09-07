package com.foureyed.un.util;

import com.badlogic.gdx.math.Vector2;

public class Collider {
	/*
	 * används för att kolla kollisioner
	 * */
	
	public Vector2 pos;
	public Vector2 size;
	
	public Collider(Vector2 pos, Vector2 size) {
		this.pos = pos;
		this.size = size;
	}
	
	public boolean isColliding(Collider c) {
		if(pos.x < c.pos.x + c.size.x && 
		c.pos.x < pos.x + size.x &&
		pos.y < c.pos.y + c.size.y && 
		c.pos.y < pos.y + size.y )
			return true;
		else return false;
	}
}
