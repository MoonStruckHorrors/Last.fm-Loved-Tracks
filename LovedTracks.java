import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class LovedTracks extends DefaultHandler {
	String tmpStr;
	String ttl;
	boolean nameDone = false;
	public static void main(String[] args) {
		LovedTracks xyz = new LovedTracks();
		xyz.go("desired_username_here", "YOUR_APIKey_here");
	}
	
	public void go(String user, String APIKey) {
		String url = "http://ws.audioscrobbler.com/2.0/?method=user.getlovedtracks&user=" + user + "&" + "api_key=" + APIKey;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			SAXParser parser = factory.newSAXParser();
			parser.parse(url, this);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equals("track")) {
			nameDone = false;
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tmpStr = new String(ch, start, length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("name")==true && nameDone==false) {
			System.out.println(tmpStr);
			nameDone = true;
		}
	}
}