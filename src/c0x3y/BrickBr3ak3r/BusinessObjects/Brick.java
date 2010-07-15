package c0x3y.BrickBr3ak3r.BusinessObjects;

import c0x3y.Utils.PowerupListener;
import android.content.Context;

public class Brick extends Sprite implements ICollidable {

	private int Row;
	private int Column;
	private Powerup powerUp;
	private PowerupListener powerupListener;
	public PowerupListener getPowerupListener() {
		return powerupListener;
	}

	public void setPowerupListener(PowerupListener powerupListener) {
		this.powerupListener = powerupListener;
	}

	private int points; 
	private boolean pointsRecorded = false;
	public int getRow() {
		return Row;
	}

	public int getPoints() {
		if (pointsRecorded || isVisible())
			return 0;
		else
		{
			pointsRecorded = true;
			return points;
		}
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setRow(int row) {
		Row = row;
	}

	public int getColumn() {
		return Column;
	}

	public void setColumn(int column) {
		Column = column;
	}

	public Brick(Context context, int brickDrawable) {
		super(brickDrawable, context);
		points = 50;
	}

	private int health;
	private boolean startHiding;
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isStartHiding() {
		return startHiding;
	}

	public void setStartHiding(boolean startHiding) {
		this.startHiding = startHiding;
	}

	@Override
	void Animate() {
		if (startHiding)
		{
			setOpacity(0);
			/*if (getOpacity() > 0)
				if (getOpacity() - 10 >= 0)
					setOpacity(getOpacity() - 10);
				else
					setOpacity(0);*/
		}
	}

	@Override
	public boolean CheckCollided(Sprite collidedSprite) {
		
		return false;
	}

	@Override
	public boolean HandleCollision(Sprite collisionSprite) {	
		health -= 50;
		if (health <= 0)
		{
			startHiding = true;
			setVisible(false);
			// if this brick has a powerup handle it
			if (powerUp != null)				
				powerupListener.powerupActivated(this);
		}
		return false;
	}

	public void setPowerUp(Powerup powerUp) {
		this.powerUp = powerUp;
	}

	public Powerup getPowerUp() {
		return powerUp;
	}

}
