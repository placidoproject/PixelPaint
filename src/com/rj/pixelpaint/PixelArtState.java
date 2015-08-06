package com.rj.pixelpaint;

import android.graphics.Color;


public class PixelArtState {
	public final static int PENCIL = 1;
	public final static int ERASER = 2;
	public final static int LINE = 3;
	public final static int RECTANGLE = 4;
	public final static int RECTANGLE_FILL = 40;
	public final static int CIRCLE = 6;
	public final static int CIRCLE_FILL = 60;
	public final static int BUCKET = 7;
	public final static int DRAW = 5;
    public final static int PICKER = 80;
    public final static int MOVE = 90;
	
	public int mode = DRAW;
	
	
	public int selectedColor = Color.CYAN;
	
	
	
}
