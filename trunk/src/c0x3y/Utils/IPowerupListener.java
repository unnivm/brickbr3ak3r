package c0x3y.Utils;

import java.util.EventListener;

import c0x3y.BrickBr3ak3r.BusinessObjects.Brick;

public interface IPowerupListener  extends EventListener{
	void powerupActivated(Brick brick);
}
