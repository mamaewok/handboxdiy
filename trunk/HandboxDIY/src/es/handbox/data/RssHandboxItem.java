package es.handbox.data;

/**
 * Representa una entidad ATOM
 * 
 * @author handbox
 * 
 */
public class RssHandboxItem {

	private String titulo;

	private String contenido;

	public String getTitle() {
		return titulo;
	}

	public void setTitle(String title) {
		this.titulo = title;
	}

	public String getContent() {
		return contenido;
	}

	public void setContent(String content) {
		this.contenido = content;
	}

	@Override
	public String toString() {
		return titulo;
	}

}
