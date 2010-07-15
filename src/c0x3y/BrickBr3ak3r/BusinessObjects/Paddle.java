package c0x3y.BrickBr3ak3r.BusinessObjects;

import c0x3y.BrickBr3ak3r.R.drawable;
import android.content.Context;

public class Paddle extends Sprite {

	public Paddle(Context context, float parentHeight, float parentWidth) {
		super(drawable.goldpaddle, context, parentHeight, parentWidth);
		super.setyPos(parentHeight - getHeight());
	}
	
	@Override
	void Animate() {
		// TODO Auto-generated method stub

	}
	@Override
	public void updateYPos(float newYPos)
	{
		
	}

}
