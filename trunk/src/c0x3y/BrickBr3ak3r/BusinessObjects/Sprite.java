package c0x3y.BrickBr3ak3r.BusinessObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.Display;
import android.view.WindowManager;

public abstract class Sprite {
	private float height;
	private float width;
	private float xPos;
	protected float parentWidth;
	protected float parentHeight;
	protected Paint paint;
	private boolean visible; 
	
	// Collision detection properties
	
	private float colWidth;
	private float colHeight;
	private float xOffset;
	private float yOffset;
	
	public float getHeight() {
		return height;
	}
	
	public int getOpacity()
	{
		return paint.getAlpha();
	}
	public void setOpacity(int value)
	{
		paint.setAlpha(value);
	}
	public Paint getPaint() {
		return paint;
	}

	public void setHeight(float height) {
		this.height = height;
		this.colHeight = height * 0.8f;
		this.yOffset = (height - colHeight) / 2;
	}
	public float getXOffset() {
		return xOffset;
	}

	public float getYOffset() {
		return yOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getColWidth() {
		return colWidth;
	}

	public float getColHeight() {
		return colHeight;
	}

	abstract void Animate();
	public void updateXPos(float newXPos)
	{
		xPos += newXPos; 
        // boundary checking.
        if (xPos + width >= parentWidth) 
            xPos = parentWidth - width; 
        else if (xPos < 0) 
            xPos = 0;
	}

	public void updateYPos(float newYPos)
	{
		yPos += newYPos; 
        // boundary checking. 
        if (yPos + height >= parentHeight) 
            yPos = parentHeight - height; 
        else if (yPos < 0) 
            yPos = 0 ;
	}
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		this.colWidth = width * 0.8f;
		this.xOffset = (width - colWidth) / 2;
	}

	public float getxPos() {
		return xPos;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public Bitmap getImage() {
		return image;
	}

	
	private float yPos;
	private Bitmap image;

	public Sprite(int drawableID, Context context)
	{
		image = BitmapFactory.decodeResource(context.getResources(),drawableID);
		height = image.getHeight();
		width = image.getWidth();
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		parentHeight = display.getHeight() - 20;
		parentWidth = display.getWidth();
		paint = new Paint();
		visible = true;
	}
	public Sprite(int drawableID, Context context, float parentHeight, float parentWidth)
	{
		this(drawableID, context);
		this.parentHeight = parentHeight;
		this.parentWidth = parentWidth;
	}
}
