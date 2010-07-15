package c0x3y.BrickBr3ak3r;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import c0x3y.BrickBr3ak3r.BusinessObjects.*;
import c0x3y.BrickBr3ak3r.BusinessObjects.Ball.BallStates;
import c0x3y.BrickBr3ak3r.R;
import c0x3y.Utils.Accelerometer;
import c0x3y.Utils.Map;
import c0x3y.Utils.MapBuilder;
import c0x3y.Utils.PowerupListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;

public class GameThread extends Thread implements OnKeyListener, OnTouchListener{
	SurfaceHolder surfaceHolder;
	Renderer renderer;
	Boolean Running;
	Context context;
	ArrayList<Ball> balls;
	Map map;
	Paddle paddle;
	Display display;
	SpriteNotification gameOver;
	SpriteNotification levelUp;
	private int score;
	private int lives = 10;
	Paint fontPaint;
	
	private DrawableText ScoreText()
	{
		DrawableText returnText = new DrawableText();
		returnText.setText("Score : " + Integer.toString(score));
		returnText.setyPos(display.getHeight() - 30);
		returnText.setxPos(0);
		returnText.setPaint(fontPaint);
		return returnText;
	}
	
	private void UpdateScore()
	{
		for(Brick b : map.getBricks())
			score += b.getPoints();
	}
	private DrawableText LivesText()
	{
		DrawableText returnText = new DrawableText();
		returnText.setText("Lives : " + Integer.toString(lives));
		returnText.setyPos(display.getHeight() - 30);
		returnText.setxPos(display.getWidth() - 100);
		returnText.setPaint(fontPaint);
		return returnText;
	}
	
	public Boolean isRunning() {
		return Running;
	}
	public void setRunning(Boolean running) {
		Running = running;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	
	Accelerometer accelerometer;
	public void setRunning(boolean b) {
          Running = b;
          if (!Running)
        	  accelerometer.unregisterListener();
          else 
        	  accelerometer.registerListener();
    }

	public GameThread(final Context context, SurfaceHolder surfaceHolder, Handler handler, View view)
	{
		try {
			display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			PowerupListener listener = new PowerupListener() {
				
				@Override
				public void powerupActivated(Brick brick) {
					// TODO Auto-generated method stub
					
				}
			};
			/*PowerupListener listener = new PowerupListener() {
				
				@Override
				public void powerupActivated(Brick brick) {
					String ss = "some string";
					switch (brick.getPowerUp().getPowerUpType())
					{
					case BurningBall:
						ss = "burning ball";
						break;
					case DecreasePaddleSize:
						ss = "decrease paddle size";
						break;
					case GluePaddle:
						ss = "glue paddle";
						break;
					case Guns:
						ss = "guns";
						break;
					case IncreasePaddleSize:
						ss = "increase paddle size";
						break;
					case ThreeBalls:
						ss = "3 balls";
						balls.add(new Ball(context, display.getHeight() - 50, display.getWidth()));
						balls.add(new Ball(context, display.getHeight() - 50, display.getWidth()));
						break;
					case TwoBalls:
						ss = "2 balls";
						Ball b = new Ball(context, display.getHeight() - 50, display.getWidth());
						balls.add(b);
						renderer.getSprites().add(b);
						break;
						default:
							balls.add(new Ball(context, display.getHeight() - 50, display.getWidth()));
							break;
					
					}
					
				}
			};*/
			
			accelerometer = new Accelerometer(context);
			this.surfaceHolder = surfaceHolder;
			Running = false;
			this.context = context;
			renderer = new Renderer();
			balls = new ArrayList<Ball>();	
			balls.add(new Ball(context, display.getHeight() - 50, display.getWidth()));
			Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			map = MapBuilder.BuildMap(context.getAssets().open("brickLevels/Level1.xml"), context);
			gameOver = new SpriteNotification(R.drawable.scrgameover, context);
			levelUp = new SpriteNotification(R.drawable.scrlevelup, context);
			for(Brick b : map.getBricks())
			{
				b.setxPos(b.getWidth() * (b.getColumn()-1));
				b.setyPos(b.getHeight() * b.getRow());
				b.setPowerupListener(listener);
				renderer.getSprites().add(b);				
			}			
			renderer.getSprites().add(balls.get(0));
			paddle = new Paddle(context, display.getHeight() - 50, display.getWidth());			
			renderer.getSprites().add(paddle);
			Background background = new Background(R.drawable.space, context);
			renderer.setBackground(background);
			
			fontPaint = new Paint();
			fontPaint.setStyle(Style.FILL);
			fontPaint.setColor(Color.WHITE);
			fontPaint.setTextSize(15);
			for(Brick b : map.getBricks())
			{
				balls.get(0).collidables.add(b);
			}
			balls.get(0).collidables.add(paddle);
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void HandleLifeLost()
	{
		setRunning(false);					
		if (lives > 0)
			lives--;
		if (lives == 0)
		{
			renderer.getSprites().add(gameOver);
		}
	}
	
	private void HandleLevelUp()
	{
		setRunning(false);
		renderer.getSprites().add(levelUp);
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			if (Running)
			{
				Canvas c = null;
				try
				{
					if (balls.get(0).getState()!= BallStates.Dead)
					{
						float xMovement = 0 - accelerometer.getAccelX(); 
						paddle.updateXPos(xMovement * 5);
						if (balls.get(0).getState() == BallStates.Bouncing)
						{						
							UpdateScore();
							renderer.getStrings().clear();					
							renderer.getStrings().add(ScoreText());
							renderer.getStrings().add(LivesText());
						}
						else
						{
							InitialiseBall();
						}						
					}
					else				
					{
						HandleLifeLost();
					}
					c = surfaceHolder.lockCanvas();
					renderer.setCanvas(c);
					renderer.Draw();
					boolean activeBalls = false;
					for(Ball b : balls)
					{
						if (b.getState() != BallStates.Dead)
						{
							activeBalls = true;
							break;
						}
					}
					
					if (!activeBalls)
					{
						this.HandleLevelUp();
					}
					sleep(50);
				}
				catch (InterruptedException e) {
				}
				finally
				{
					if (c != null)
						surfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
		
	}
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
			paddle.updateXPos(10);
		else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT)
			paddle.updateXPos(-10);
		return false;
	}
	
	private void InitialiseBall()
	{
		balls.get(0).setxPos(paddle.getxPos() + (paddle.getWidth() / 2) - (balls.get(0).getWidth() / 2 ));
		balls.get(0).setyPos(paddle.getyPos() - balls.get(0).getHeight());						
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		balls.get(0).setState(BallStates.Bouncing);
		if (!this.isRunning())
		{
			InitialiseBall();
			setRunning(true);	
		}
		return false;
	}
	
	
}
