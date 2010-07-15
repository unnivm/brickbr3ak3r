package c0x3y.Utils;

import java.util.HashMap;

import c0x3y.BrickBr3ak3r.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundHandler {
	private static SoundPool soundPool;
	private static HashMap<Sounds, Integer> soundPoolMap;
	private Context context;
	public enum Sounds
	{
		BOUNCE,
		GAMEOVER,
		GUNFIRE,
		LEVELCOMPLETE,
		LIFELOST
	}
	
	public SoundHandler(Context context)
	{	
		if (soundPoolMap == null)
		{
			soundPoolMap = new HashMap<Sounds, Integer>();			
			soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 100);
			soundPoolMap.put(Sounds.BOUNCE, soundPool.load(context, R.raw.bounce, 1));
		}
		this.context = context;
	}
	
	public void PlaySound(Sounds sound)
	{
		AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		int streamVolume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, 0, 1f);
	}
	
	
	
	

}
