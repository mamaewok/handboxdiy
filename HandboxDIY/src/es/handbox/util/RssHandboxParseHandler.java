package es.handbox.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.handbox.data.RssHandboxItem;

/**
 * Controlador SAX
 * 
 * @author handbox
 * 
 */
public class RssHandboxParseHandler extends DefaultHandler {

	private List<RssHandboxItem> listaItems;

	// intem que parseo
	private RssHandboxItem handboxItem;

	private boolean parsingTitulo;
	private boolean parsingContenido;

	// buffers
	private StringBuffer bufferTitulo;
	private StringBuffer bufferContenido;

	public RssHandboxParseHandler() {
		listaItems = new ArrayList<RssHandboxItem>();
	}

	public List<RssHandboxItem> getItems() {
		return listaItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if ("entry".equals(qName)) {
			handboxItem = new RssHandboxItem();
		} else if ("title".equals(qName)) {
			parsingTitulo = true;
			bufferTitulo = new StringBuffer();
		} else if ("content".equals(qName)) {
			parsingContenido = true;
			bufferContenido = new StringBuffer();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("entry".equals(qName)) {
			listaItems.add(handboxItem);
			handboxItem = null;
		} else if ("title".equals(qName)) {
			parsingTitulo = false;

			if (handboxItem != null) //
				handboxItem.setTitle(bufferTitulo.toString());

		} else if ("content".equals(qName)) {
			parsingContenido = false;

			if (handboxItem != null)
				handboxItem.setContent(bufferContenido.toString());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (parsingTitulo) {
			if (handboxItem != null)
				bufferTitulo.append(new String(ch, start, length));
		} else if (parsingContenido) {
			if (handboxItem != null)
				bufferContenido.append(new String(ch, start, length));
		}
	}

}
