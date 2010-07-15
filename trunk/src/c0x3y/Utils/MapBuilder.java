package c0x3y.Utils;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;

public class MapBuilder {
	public static Map BuildMap(InputStream is, Context context) throws ParserConfigurationException, SAXException, IOException
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		Map resultMap = new Map(context);
		reader.setContentHandler(resultMap);
		reader.parse(new InputSource(is));	
		return resultMap;
	}
	

}
