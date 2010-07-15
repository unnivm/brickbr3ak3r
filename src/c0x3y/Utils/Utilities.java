package c0x3y.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class Utilities {
	
	public static int GetScreenWidth(Activity activity)
	{
		Display display = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getWidth();
	}
	public static Display GetScreenDisplay(Activity activity)
	{
		return ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	}

}
