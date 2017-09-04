package com.foureyed.un.gamestates;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.foureyed.un.feGame;
import com.foureyed.un.util.Assets;
import com.foureyed.un.util.World;

public class Playstate extends Gamestate {
	
	/* Gamestatet som används när själva spelet börjar*/
	
	private Camera cam; /* Libgdxkamera som används för att följa efter spelaren */
	private World world; /* Världen/banan */

	@Override
	public void init() {
		cam = new OrthographicCamera(feGame.WIDTH, feGame.HEIGHT); /* sätter kamerans viewport till fönstrets bredd och höjd */
		Assets.load(this); /*Laddar in alla sprites för playstate*/
		world = new World(6f, cam); /* Ge världen/banan en gravitation och kamera*/
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined); /* Gör spritebatchen "medveten" om kameran*/
		world.render(sb); /* Ritar banan */
	}

	@Override
	public void update() {
		world.update(); /* Updatera banan */
	}
	
	@Override
	public void destroy() {
		Assets.dispose(this); /* Ta bort alla sprites och texturer när gamestatet byts */
	}
	
	@Override
	public String getName() {
		return "playstate";
	}

}
