package c0x3y.BrickBr3ak3r.BusinessObjects;

import java.util.ArrayList;

import c0x3y.BrickBr3ak3r.BusinessObjects.Ball.HorizontalDirections;
import c0x3y.BrickBr3ak3r.BusinessObjects.Ball.VerticalDirections;

import android.content.Context;

public abstract class CollidableSprite extends Sprite implements ICollidable {
		
	public CollidableSprite(int drawableID, Context context) {
		super(drawableID, context);
	}
	
	public ArrayList<Sprite> collidables = new ArrayList<Sprite>();
	protected VerticalDirections verticalDirection;
	protected HorizontalDirections horizontalDirection;
	
	public CollidableSprite(int drawableID, Context context, float parentHeight, float parentWidth) {
		super(drawableID, context,parentHeight, parentWidth);
	}

	@Override
	public void updateXPos(float newXPos)
	{
		float testX = getxPos() + newXPos;
						
		for(Sprite s : collidables)
		{
			if (s.isVisible())
			{
				float sBottom = s.getyPos() + s.getHeight() - 5;
				float sTop = s.getyPos() + 5;
				float sLeft = s.getxPos() + 5;
				float sRight = s.getxPos() + s.getWidth() - 5;
				boolean isCovered = true;
				if (getyPos() + getHeight() < sTop) 
			    	isCovered = false;
			    if (getyPos() > sBottom)
			    	isCovered = false;
			    if (testX + getWidth() < sLeft)
			    	isCovered = false;
			    if (testX > sRight) 
			    	isCovered = false;
			    if (isCovered)
			    {
			    	HandleCollision(s);
			    	if (s instanceof ICollidable)
			    		((ICollidable)s).HandleCollision(this);			    	
			    	if (horizontalDirection == HorizontalDirections.Left)
			    		newXPos = getxPos();
			    	else
			    		newXPos = getxPos() + getWidth();
			    	
			    	horizontalDirection = horizontalDirection == HorizontalDirections.Left ? HorizontalDirections.Right : HorizontalDirections.Left;
			    	setxPos(newXPos);
			    	return;
			    }
			}
		}
		super.updateXPos(newXPos);
	}
	@Override
	public void updateYPos(float newYPos)
	{
		float testY = getyPos() + newYPos;
		for(Sprite s : collidables)
		{
			if (s.isVisible())
			{
				float sBottom = s.getyPos() + s.getHeight() - 5;
				float sTop = s.getyPos() + 5;
				float sLeft = s.getxPos() + 5;
				float sRight = s.getxPos() + s.getWidth() - 5;
				boolean isCovered = true;
				if (testY + getHeight() < sTop) 
			    	isCovered = false;
			    if (testY > sBottom)
			    	isCovered = false;
			    if (getxPos() + getWidth() < sLeft)
			    	isCovered = false;
			    if (getxPos() > sRight) 
			    	isCovered = false;
			    if (isCovered)
			    {		
			    	HandleCollision(s);
			    	if (s instanceof ICollidable)
			    		((ICollidable)s).HandleCollision(this);
			    	if (verticalDirection == VerticalDirections.Down)
			    		newYPos = getyPos();
			    	else
			    		newYPos = getyPos() + getHeight();
			    	setyPos(newYPos);			    	
			    	verticalDirection = verticalDirection == VerticalDirections.Down ? VerticalDirections.Up : VerticalDirections.Down;	    				    	
			    	return;
			    }
			}
		}
		super.updateYPos(newYPos);
	}
	

}
