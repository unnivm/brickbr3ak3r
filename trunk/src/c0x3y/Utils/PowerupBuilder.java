package c0x3y.Utils;

import java.util.Random;

import android.content.Context;
import c0x3y.BrickBr3ak3r.BusinessObjects.Enums;
import c0x3y.BrickBr3ak3r.BusinessObjects.Powerup;
import c0x3y.BrickBr3ak3r.BusinessObjects.Enums.PowerupType;

public class PowerupBuilder {
	public static Powerup CreatePowerup(Context context)
	{
		Random random = new Random();
		int powerupID = random.nextInt(Enums.PowerupType.values().length);
		PowerupType type = Enums.PowerupType.class.getEnumConstants()[powerupID];
		Powerup result = new Powerup(type);
		return result;
	}

}
