package com.galadar.graphicsTest;

import com.galadar.graphicsTest.MainActivity.GraphicsView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.*;
import android.widget.*;

public class Sprite{
	
	public enum Direction {Up, Down, Left, Right};
	int x, y, Xspeed, Yspeed, height, width;
	Direction dir;
	int stage;
	Bitmap sheet;
	GraphicsView gv;
	
	public Sprite(GraphicsView myView, Bitmap mip){
		sheet = mip;
		gv = myView;
		x = y = Yspeed = 0;
		Xspeed = 5;
		stage = 0;
		dir = Direction.Right;
		height = sheet.getHeight()/4;
		width = sheet.getWidth()/4;
	}
	
	private int getIndexRow(){
		switch (dir) {
			case Down:
				return 0;
			case Up:
				return 1;
			case Left:
				return 2;
			case Right:
				return 3;
			}
		return 0;
	}
	
	public void onDraw(Canvas canvas){
		update();
		int startY = getIndexRow()*height;
		stage = (stage+1)%4;
		int startX=stage*width;
		Rect src = new Rect(startX,startY,startX+width,startY+height);
		Rect dst = new Rect(x,y,x+width/10,y+height/10);
		canvas.drawBitmap(sheet, src, dst, null);
		
	}
	
	private void update(){
		if(x+Xspeed>=gv.getWidth()-width/10){
			Xspeed = 0;
			Yspeed = 5;
			dir=Direction.Down;
		} else if(y+Yspeed>=gv.getHeight()-height/10){
			Yspeed=0;
			Xspeed = -5;
			dir=Direction.Left;
		} else if(x+Xspeed<=0){
			Xspeed = 0;
			Yspeed = -5;
			dir=Direction.Up;
		} else if(y+Yspeed<=0){
			Yspeed = 0;
			Xspeed = 5;
			dir=Direction.Right;
		}
		
		x=x+Xspeed;
		y=y+Yspeed;
	}
	
}
