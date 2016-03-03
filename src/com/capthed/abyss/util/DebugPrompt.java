package com.capthed.abyss.util;

import java.util.StringTokenizer;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.GameLoop;
import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.component.gui.GUITextField;
import com.capthed.abyss.component.gui.GUITextFieldListener;
import com.capthed.abyss.font.Font;
import com.capthed.abyss.font.Text;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Controller;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

public class DebugPrompt {

	private static Texture tex = new Texture("res/debugPrompt.png");
	private static GUITextField field;
	private static DebugPrompt dp = new DebugPrompt();
	
	private DebugPrompt() {}
	
	public void init() {
		tex.load();
	}
	
	public void move(Vec2 delta) {
		field.setPos(Vec2.add(field.getPos(), delta));
	}
	
	public void initPrompt() {
		Font font = GameLoop.getDebugFont();
		Text txt = new Text(new Vec2(0, Display.getHeight() - 35), new Vec2(20, 32), ">:", font.getLex());
		txt.setColor(0.83f, 0.88f, 0.23f, 1).setLayer(RenderUtil.debugLayer());;
		field = (GUITextField) new GUITextField(txt, new Vec2(Display.getWidth(), 32), tex, new GUITextFieldListener() {
			public void onKeyEnetered(int k) {
				if (k == Keys.GLFW_KEY_ENTER) {
					if (process(field.getText().replace(">:", ""))) {
						field.setFocus(false);
						field.setEnabled(false);
						field.setText(">:");
					}
				}
			}
		}).setLayer(RenderUtil.debugLayer() - 1);
		field.setImmutable(2);
	}
	
	private boolean process(String p) {
		StringTokenizer token = new StringTokenizer(p, " ");
		
		if (p == null || p.trim().equals("")) return true;
		
		String first = token.nextToken();
		
		if (first.equals("exit")) {
			Abyss.stop();
		}
		else if(first.equals("debug")) {
			String var0 = token.nextToken();
			if (var0.equals("on"))
				Debug.setDebug(true);
			else if (var0.equals("off"))
				Debug.setDebug(false);
			
			else if (var0.equals("show"))
				GameLoop.setDebugRender(true);
			else if (var0.equals("hide"))
				GameLoop.setDebugRender(false);
		}
		
		else if (first.equals("create")) {
			String vec1 = token.nextToken();
			StringTokenizer tokenVec1 = new StringTokenizer(vec1, "x");
			float x = Float.valueOf(tokenVec1.nextToken());
			float y = Float.valueOf(tokenVec1.nextToken());
			Vec2 pos = new Vec2(x, y);
			
			String vec2 = token.nextToken();
			StringTokenizer tokenVec2 = new StringTokenizer(vec2, "x");
			float w = Float.valueOf(tokenVec2.nextToken());
			float h = Float.valueOf(tokenVec2.nextToken());
			Vec2 size = new Vec2(w, h);
			
			String kurac = token.nextToken();
			char var0 = kurac.toCharArray()[0];
			
			String tex = token.nextToken();
			int id = Integer.valueOf(tex);
			
			String l = token.nextToken();
			int layer = Integer.valueOf(l);
			
			DummyObject dob = null;
			if (var0 == 'T')
				dob = (DummyObject) new DummyObject(pos, size, Texture.getByID(id)).setLayer(layer);
			else if (var0 == 'A')
				dob = (DummyObject) new DummyObject(pos, size, Animation.getByID(id)).setLayer(layer);
			
			MapManager.getCurrent().add(dob);
		}
		
		else if (first.equals("remove")) {
			int id = Integer.valueOf(token.nextToken());
			if (id < GameComponent.getGcs().size() && id != 0)
				GameComponent.getByID(id).destroy();
		}
		
		else if (first.equals("mouse")) {
			String var0 = token.nextToken();
			if (var0.equals("on"))
				Display.setShowMouse(true);
			else if (var0.equals("off"))
				Display.setShowMouse(false);
		}
		else if (first.equals("controller")) {
			String var0 = token.nextToken();
			if (var0.equals("on")) {
				Controller.init();
			}
			else if (var0.equals("off"))
				Controller.setHas(false);
		} 
		else if (first.equals("church")) {
			GameLoop.church();
		}
		else if (first.equals("version")) {
			field.setText(">:" + Abyss.getVersion());
			return false;
		}
		else if (p.equals("help og")) {
			Util.info("");
		}
		else {
			return Abyss.getGame().process(p);
		}
		
		return true;
	}
	
	public void openAgain() {
		if (field.isNull()) {
			initPrompt();
			openPrompt();
			return;
		}
		field.setEnabled(true);
		field.setFocus(true);
	}
	
	public static DebugPrompt get() { return dp; }
	
	public void openPrompt() {
		MapManager.getCurrent().add(field);
		field.setFocus(true);
	}
	
	public static Texture getTex() {
		return tex;
	}

	public static void setTex(Texture tex) {
		DebugPrompt.tex = tex;
	}

	private class DummyObject extends GameObject {
		
		public DummyObject(Vec2 pos, Vec2 size, Texture tex) {
			super(pos, size, tex);
		}
		
		public DummyObject(Vec2 pos, Vec2 size, Animation anim) {
			super(pos, size, anim);
		}
	}
}