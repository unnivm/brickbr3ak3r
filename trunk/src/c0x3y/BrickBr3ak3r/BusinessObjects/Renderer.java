package c0x3y.BrickBr3ak3r.BusinessObjects;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Renderer {
	private Canvas canvas;
	private int footerHeight;
	private ArrayList<Sprite> sprites;
	private ArrayList<DrawableText> strings; 
	public Sprite getBackground() {
		return background;
	}
	public ArrayList<DrawableText> getStrings() {
		return strings;
	}
	public void setStrings(ArrayList<DrawableText> strings) {
		this.strings = strings;
	}
	public void setBackground(Sprite background) {
		this.background = background;
	}
	private Sprite background;
	public Canvas getCanvas() {
		return canvas;
	}
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	public Renderer()
	{
		sprites = new ArrayList<Sprite>();
		strings = new ArrayList<DrawableText>();
	}
	public Renderer(Canvas canvas)
	{
		this();
		this.canvas = canvas;
	}
	public void Draw()
	{
		canvas.drawARGB(255, 0, 0, 0);
		int tileYCount = 1;
		int screenHeight = canvas.getWidth();
		int currentHeight = 0;
		canvas.drawBitmap(background.getImage(), 0, 0, new Paint());		
		while(currentHeight < screenHeight)
		{
			canvas.drawBitmap(background.getImage(), 0, background.getHeight() * tileYCount, new Paint());
			tileYCount++;
			currentHeight += background.getHeight();
		}		
		for(Sprite sprite : getSprites())	
		{
			if (sprite.isVisible())
			{
				sprite.Animate();
				canvas.drawBitmap(sprite.getImage(), sprite.getxPos(),sprite.getyPos(), sprite.getPaint());
			}
		}
		for(DrawableText text : strings)
		{
			canvas.drawText(text.getText(), text.getxPos(), text.getyPos(), text.getPaint());
		}
	}
	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	public void setFooterHeight(int footerHeight) {
		this.footerHeight = footerHeight;
	}
	public int getFooterHeight() {
		return footerHeight;
	}

}
