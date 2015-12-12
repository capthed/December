package com.capthed.game;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.Game;
import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.math.Vec2;

public class TestRun implements Game{

	private static TestRun run;

	public static void main(String[] args) {
		run = new TestRun();
		Abyss.create("Hello World", 800, 600, run);
		Abyss.start();
	}

	@Override
	public void init() {
		new Test1(new Vec2(Display.getWidth() / 2, Display.getHeight() / 2), new Vec2(32, 32));
		new Test2();
		new Test4(new Vec2(200, 200), new Vec2(64, 64));
	}
}