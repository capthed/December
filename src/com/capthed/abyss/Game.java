package com.capthed.abyss;

public interface Game {

	/** Called from the main game loop. Used to initialize the primary components. */
	public void init();
	
	/** Called first. Used only to create the display. */
	public void initDisplay();
	
	/** Called every frame. Used to init non map-based GameComponents. */
	public void constInit();
	
	/** Called every frame. Used to update non map-based GameComponents. */
	public void constUpdate();
	
	/** Called every frame. Used to render non map-based GameComponents. */
	public void constRender();
}
