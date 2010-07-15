package c0x3y.BrickBr3ak3r.BusinessObjects;

public interface ICollidable {

	boolean CheckCollided(Sprite collidedSprite);
	
	boolean HandleCollision(Sprite collisionSprite);

}
