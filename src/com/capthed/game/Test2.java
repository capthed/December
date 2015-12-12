package com.capthed.game;

import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.abyss.math.Vec2;

public class Test2 extends GameComponent{

	public Test2() {
		super();
	}
	
	public void update() {
		if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1) || Mouse.isKeyDown(Keys.GLFW_MOUSE_BUTTON_2)) {
			float x = Mouse.getX();
			float y = Mouse.getY();
			
			new Test3(new Vec2(x, y), new Vec2(8, 8));
		}
	}
}
