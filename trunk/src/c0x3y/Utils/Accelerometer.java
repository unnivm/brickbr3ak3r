package c0x3y.Utils;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import android.hardware.SensorManager;

public class Accelerometer implements SensorEventListener {
	private SensorManager manager;
	
	private float lastAccelX = 0;
	private float lastAccelY = 0;
	private float lastAccelZ = 0;
	private long lastUpdateTime = 0;
	
	
	
	private float speed = 0;
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	private float accelX = 0;
	private float accelY = 0;
	private float accelZ = 0;
	
	public float getAccelX() {
		return accelX;
	}

	public void setAccelX(float accelX) {
		this.accelX = accelX;
	}

	public float getAccelY() {
		return accelY;
	}

	public void setAccelY(float accelY) {
		this.accelY = accelY;
	}

	public float getAccelZ() {
		return accelZ;
	}

	public void setAccelZ(float accelZ) {
		this.accelZ = accelZ;
	}

	public void registerListener()
	{
		if (!registered)
		{
			for(Sensor s : manager.getSensorList(Sensor.TYPE_ACCELEROMETER))
			{
					manager.registerListener(this, s,0);
					registered = true;
			}
		}
	}
	public Accelerometer(Context context)
	{
		manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);						
	}
	private boolean registered;
	public boolean isRegistered()
	{
		return registered;
	}
	public void unregisterListener()
	{
		manager.unregisterListener(this);
		registered = false;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		long curTime = System.currentTimeMillis();
		long diffTime = curTime - lastUpdateTime;
		setAccelX(event.values[0]);
		setAccelY(event.values[1]);
		setAccelZ(event.values[2]);
		speed = Math.abs((accelX + accelY + accelZ - lastAccelX - lastAccelY - lastAccelZ) / diffTime); 
		lastUpdateTime = curTime;
		lastAccelX = accelX;
		lastAccelY = accelY;
		lastAccelZ = accelZ;			
	}
	
}
