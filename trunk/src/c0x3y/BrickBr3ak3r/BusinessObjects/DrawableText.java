package c0x3y.BrickBr3ak3r.BusinessObjects;

import android.graphics.Paint;

public class DrawableText {
	private String text;
	private float xPos;
	private float yPos;
	private Paint paint;
	
	public Paint getPaint() {
		return paint;
	}
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public float getxPos() {
		return xPos;
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
	
	

}
