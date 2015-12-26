package com.capthed.abyss.component;

import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;

public abstract class Entity extends GameObject {

	public Entity(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public Entity(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
		
		this.pos = pos;
		this.size = size;
		this.tex = tex;
	}
	
	/** Moves the entity regardless of physics for delta. */
	public void move(Vec2 delta) {
		this.pos.add(delta);
		
		if (collider != null) {
			collider.move(delta);
		}
	}
	
	public void tryMove(Vec2 delta) {
		boolean col = false;
		
		move(delta);
		
		for (int i = 0; i < MapManager.getCurrent().getScenes().size(); i++) {
			for (int i2 = 0; i2 <  MapManager.getCurrent().getScenes().get(i).getGcs().size(); i2++) {
				GameComponent gc =  getByID(MapManager.getCurrent().getScenes().get(i).getGcs().get(i2));
				
				if (gc instanceof GameObject) {
					GameObject go = (GameObject) gc;
					
					if (go.isCollidable() && go != this) {
						if (this.collider.intersects(go.getCollider())) {
							this.collided(go);
							go.collided(this);
							col = true;
						}
					}
				}
			}
		}
		
		if (col)
			move(Vec2.mult(Vec2.REV, delta));
	}
}