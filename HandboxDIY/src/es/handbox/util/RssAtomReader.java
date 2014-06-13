package es.handbox.util;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import es.handbox.data.RssHandboxItem;

/**
 * Lee los datos RRS
 * 
 * @author handbox
 * 
 */
public class RssAtomReader {

	private String rssUrl;

	/**
	 * Constructor
	 * 
	 * @param rssUrl
	 */
	public RssAtomReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	/**
	 * Devuelve RSS items.
	 * 
	 * @return
	 */
	public List<RssHandboxItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();

		SAXParser saxParser = factory.newSAXParser();

		RssHandboxParseHandler handler = new RssHandboxParseHandler();

		saxParser.parse(rssUrl, handler);

		return handler.getItems();

	}

}
