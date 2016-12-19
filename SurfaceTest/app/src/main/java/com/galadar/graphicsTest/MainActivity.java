package com.galadar.graphicsTest;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.Context;
import android.content.*;
import android.graphics.*;
import android.view.View.*;


public class MainActivity extends Activity implements OnLongClickListener, OnTouchListener{

	@Override
	public boolean onLongClick(View p1)
	{
		// TODO: Implement this method
		return false;
	}


	@Override
	public boolean onTouch(View p1, MotionEvent p2)
	{
		// TODO: Implement this method
		return false;
	}

	
	final Handler h = new Handler();
	GraphicsView myView;
	Bitmap sheet;
	float x,y;
	Sprite mySprite;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
		
		sheet = BitmapFactory.decodeResource(getResources(), R.drawable.mysheet);
		x=y=0;
		
		myView = new GraphicsView(this);
		myView.setOnTouchListener(this);
		setContentView(myView);
    }
	
	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		myView.pause();
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		myView.resume();
	}
	
	
	
	public class GraphicsView extends SurfaceView implements Runnable{
	
		SurfaceHolder holder;
		Thread t = null;
		boolean running = false;
		boolean loaded = false;
		
		public GraphicsView(Context context){
			super(context);
			holder = getHolder();
			t = new Thread(this);
			t.start();
		}
		
		public void run(){
			while(true){
				if(holder.getSurface().isValid()){
					break;
				}
			}
			if(!loaded){
				mySprite = new Sprite(GraphicsView.this, sheet);
				loaded = true;
			}
			Canvas c = holder.lockCanvas();
			onDraw(c);
			holder.unlockCanvasAndPost(c);
			if(running){
				h.postDelayed(this, 60);
			}
		}

		@Override
		protected void onDraw(Canvas canvas)
		{
			super.onDraw(canvas);
			canvas.drawARGB(255, 255, 255, 255);
			mySprite.onDraw(canvas);
		}
	
		public void pause(){
			running = false;
			while(true){
				try
				{
					t.join();
				}
				catch (InterruptedException e)
				{e.printStackTrace();}
				break;
			}
			t = null;
		}
	
		public void resume(){
			running = true;
			t= new Thread(this);
			t.start();
		}
		
	}
}
