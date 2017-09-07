package com.foureyed.un.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Gamestate {
	/* Ett gamestate är ett stadie av spelet, exempelvis när spelet är igång eller pausat, i menyn eller annat*/
	public abstract String getName(); /* ger tillbaka vilket gamestate som är aktuellt */
	public abstract void init(); /*kallas en gång när gamestate startas*/
	public abstract void render(SpriteBatch sb); /*Ritar ut allt kallas varje frame*/
	public abstract void update(); /*Sköter allt annat som behöver göras varje frame*/
	public abstract void destroy(); /*kallas sist av allt och bör användas för att ta bort saker osv*/
}
