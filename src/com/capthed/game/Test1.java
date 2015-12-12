package com.capthed.game;

import com.capthed.abyss.Timer;
import com.capthed.abyss.component.Entity;
import com.capthed.abyss.math.Vec2;

public class Test1 extends Entity{
	
	public Test1(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public void update() {
		double A = Timer.getDelta() * 300;
		double A1 = Timer.getDelta() * 300;
		double val = Timer.getTimeRunning();
		
		double x = A * Math.sin(val * Math.PI);
		double y = A1 * Math.cos(val * Math.PI);
		
		/*Debug.print(x, " sin");
		Debug.print(val, " val");*/
		
		move(new Vec2((float)x, (float)y));
	}
}
