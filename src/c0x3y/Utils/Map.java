package c0x3y.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import c0x3y.BrickBr3ak3r.BusinessObjects.Brick;
import c0x3y.BrickBr3ak3r.BusinessObjects.Powerup;
import c0x3y.BrickBr3ak3r.R;

import android.content.Context;
import android.util.Log;

public class Map extends DefaultHandler{
	private int currentIndex;
	private boolean inRow = false;
	private boolean inColumn = false;
	private Context context;
	private int MaxColumns;
	private int MaxRows;
	public Map(Context context)
	{
		this.context = context;		
		Bricks = new ArrayList<Brick>();
	}
	public void setBricks(ArrayList<Brick> bricks) {
		Bricks = bricks;
	}
	public ArrayList<Brick> getBricks() {
		return Bricks;
	}
	private ArrayList<Brick> Bricks;
		
	@Override
	public void startElement(String namespaceURI, String localName, 
			String qName, Attributes atts) throws SAXException
	{
		Log.i("Ballz", "Loading map...");
			if (localName.equals("Brick"))
			{
				String type = atts.getValue("Type");
				int health = 0;
				int resourceImage = 0;
				
				if (type.equals("Solid"))
				{
					health = Integer.MAX_VALUE;
					resourceImage = R.drawable.bricksolid;
				}
				else if (type.equals("Blue"))
				{
					health = 200;
					resourceImage = R.drawable.brickblue;
				}
				else if (type.equals("Red"))
				{
					health = 300;
					resourceImage = R.drawable.brickred;
				}
				else if (type.equals("Green"))
				{
					health = 100;
					resourceImage = R.drawable.brickgreen;
				}
				Brick b = new Brick(context, resourceImage);
				//b.setHealth(health);
				Bricks.add(b);
				currentIndex = Bricks.size() - 1;
			}
			else if(localName.equals("Row"))
				inRow = true;
			else if (localName.equals("Column"))
				inColumn = true;
		
	}
	@Override
	public void characters(char ch[], int start, int length)
	{
		if (inRow)
		{
			if (Integer.parseInt(new String(ch, start, length)) > MaxRows)
				MaxRows = Integer.parseInt(new String(ch, start, length));
			Bricks.get(currentIndex).setRow(Integer.parseInt(new String(ch, start, length)));
		}
		else if(inColumn)
		{
			if (Integer.parseInt(new String(ch, start, length)) > MaxColumns)
				MaxColumns = Integer.parseInt(new String(ch, start, length));
			Bricks.get(currentIndex).setColumn(Integer.parseInt(new String(ch, start, length)));
		}
	}
	public int getMaxColumns() {
		return MaxColumns;
	}
	public int getMaxRows() {
		return MaxRows;
	}
	@Override
	public void endElement(String uri, String localName, String qName)
	{
		if (localName.equals("Row")) 
			inRow = false;
		else if (localName.equals("Column"))
			inColumn = false;	
		else if (localName.equals("Map")) // handle end of xml file
		{
			Random randomNumber = new Random();
			int numberOfPowerUps = randomNumber.nextInt(Bricks.size());
			int anum = new Random(200).nextInt(100);
			Collections.shuffle(Bricks);
			for(int i = 0; i < numberOfPowerUps; i++)
			{
				Powerup powerUp = PowerupBuilder.CreatePowerup(context); 
				Bricks.get(i).setPowerUp(powerUp);
			}
		}
	}

}
