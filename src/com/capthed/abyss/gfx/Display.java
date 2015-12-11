package com.capthed.abyss.gfx;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.capthed.abyss.input.Keyboard;
import com.capthed.util.Debug;

public abstract class Display {

	private static long display;
	private static boolean showMouse = true;
	
	/** Creates the display with the position, title and use of borders and decorations. */
	public static void create(int w, int h, String title, boolean decorated) {
		glfwInit();
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		glfwWindowHint(GLFW_DECORATED, decorated ? GLFW_TRUE : GLFW_FALSE);
		
		display = glfwCreateWindow(w, h, title, NULL, NULL);
		if (display == NULL)
			Debug.print("Display not created", "");
		
		
		// TODO: edit when input is considered
		glfwSetKeyCallback(display, new Keyboard());
		
		glfwSetWindowPos(display, 200, 200);
		
		glfwMakeContextCurrent(display);
		
		glfwSetInputMode(display, GLFW_CURSOR, showMouse ? GLFW_CURSOR_NORMAL: GLFW_CURSOR_HIDDEN);
		
		glfwShowWindow(display);
	}
	
	/** Swaps the display buffer. Called once in the game loop. */
	public static void swap() {
		 glfwSwapBuffers(display);
	}
	
	/** @return True if the display is closing */
	public static boolean isCloseRequested() {
		return glfwWindowShouldClose(display) == GLFW_TRUE ? true : false;
	}

	/** @return True if the mouse is visible */
	public static boolean isShowMouse() {
		return showMouse;
	}

	public static void setShowMouse(boolean showMouse) {
		Display.showMouse = showMouse;
	}

	/** @return The GLFW Window long. */
	public static long getDisplay() {
		return display;
	}
}
