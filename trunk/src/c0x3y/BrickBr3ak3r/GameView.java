package c0x3y.BrickBr3ak3r;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class GameView  extends SurfaceView implements SurfaceHolder.Callback{

	GameThread gameThread;
	public GameThread getThread()
	{
		return gameThread;
	}
	public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // register our interest in hearing about changes to our surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        
        
        // create thread only; 
        gameThread = new GameThread(context, holder, new Handler() {
            @Override
            public void handleMessage(Message m) {
            	// Use for pushing back messages.
            }
        }, this);              
        this.setOnKeyListener(gameThread);
        this.setOnTouchListener(gameThread);
        setFocusable(true); // make sure we get key events
    }
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gameThread.setRunning(true);
		gameThread.start();
	
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		gameThread.setRunning(false);	
	}


}
