package c0x3y.BrickBr3ak3r;

import c0x3y.BrickBr3ak3r.R;
import android.app.Activity;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;

public class init extends Activity {
    
	GameView gameView;
	GameThread mainThread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gameView = (GameView) findViewById(R.id.game);
    }
}