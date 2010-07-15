package c0x3y.BrickBr3ak3r.BusinessObjects;

import android.content.Context;

public class SpriteNotification extends Sprite {

	public SpriteNotification(int drawableID, Context context) {
		super(drawableID, context);
		setxPos((parentWidth / 2) - (getWidth() / 2));
		setyPos((parentHeight / 2 ) - (getHeight() / 2));
	}

	@Override
	void Animate() {

	}

}
