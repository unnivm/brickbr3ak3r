package c0x3y.BrickBr3ak3r.BusinessObjects;

import c0x3y.BrickBr3ak3r.BusinessObjects.Enums.*;
import android.content.Context;

public class Powerup implements ICollidable {
	private Enums.PowerupType type;
	private Enums.PowerupStates state;
	public Enums.PowerupType getPowerUpType()
	{
		return type;
	}
	public Enums.PowerupStates getState() {
		return state;
	}
	public void setState(Enums.PowerupStates state) {
		this.state = state;
	}
	public Powerup(Enums.PowerupType type)
	{
		this.state = PowerupStates.Initialised;
		this.type = type;
	}
	@Override
	public boolean CheckCollided(Sprite collidedSprite) {
		return false;
	}

	@Override
	public boolean HandleCollision(Sprite collisionSprite) {
		return false;
	}	
}
