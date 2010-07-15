package c0x3y.BrickBr3ak3r.BusinessObjects;

import android.content.Context;
import c0x3y.BrickBr3ak3r.R.drawable;
import c0x3y.Utils.SoundHandler;
import c0x3y.Utils.SoundHandler.Sounds;

public class Ball extends CollidableSprite{

	public enum BallStates
	{
		Dead,
		Bouncing,
		Initialised
	}
	
	public VerticalDirections getVerticalDirection() {
		return verticalDirection;
	}
	public void setVerticalDirection(VerticalDirections verticalDirection) {
		this.verticalDirection = verticalDirection;
	}
	public HorizontalDirections getHorizontalDirection() {
		return horizontalDirection;
	}
	public void setHorizontalDirection(HorizontalDirections horizontalDirection) {
		this.horizontalDirection = horizontalDirection;
	}
	
	private BallStates state;
	private SoundHandler soundHandler;
	public Ball(Context context, float parentHeight, float parentWidth) {
		super(drawable.ball, context, parentHeight, parentWidth);
		state = BallStates.Initialised;
		verticalDirection = VerticalDirections.Up;
		horizontalDirection = HorizontalDirections.Left;
		soundHandler = new SoundHandler(context);
	}	
	
	enum VerticalDirections
	{
		Up,
		Down
	}
	enum HorizontalDirections
	{
		Left,
		Right
	}
	private boolean Jumping;
	public boolean isJumping() {
		return Jumping;
	}
	public void setIsJumping(boolean isJumping) {
		Jumping = isJumping;
	}
	@Override
	void Animate() {
		if (state == BallStates.Bouncing)
		{
			if (getxPos() == 0)		
				horizontalDirection = HorizontalDirections.Right;
			else if(getxPos() + getWidth() >= parentWidth)
				horizontalDirection = HorizontalDirections.Left;			
			if (getyPos() == 0)
				verticalDirection = VerticalDirections.Down;
			else if (getyPos() + getHeight() >= parentHeight)
			{			
				//verticalDirection = VerticalDirections.Up;
				state = BallStates.Dead;
				return;
			}						
			
			if (verticalDirection == VerticalDirections.Down)
				updateYPos(10);
			else
				updateYPos(-10);
			if (horizontalDirection == HorizontalDirections.Left)
				updateXPos(-10);
			else
				updateXPos(10);
		}
		
	}
	
	public BallStates getState() {
		return state;
	}
	public void setState(BallStates state) {
		if (state == BallStates.Bouncing)
			updateYPos(-1);
		this.state = state;
	}
	@Override
	public boolean CheckCollided(Sprite collidedSprite) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean HandleCollision(Sprite collisionSprite) {
		soundHandler.PlaySound(Sounds.BOUNCE);
		return false;
	}
}
