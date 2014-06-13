package es.handbox.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import es.handbox.handboxdiy.R;

/**
 * Actividad que muestra el título del rss Atom y en un web view su contenido
 * 
 * @author handbox
 * 
 */
public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		String titulo = (String) getIntent().getExtras().get("title");
		String contenido = (String) getIntent().getExtras().get("content");

		//Log.d("DEBUG", "title:\t" + titulo);
		//Log.d("DEBUG", "content:\t\t" + contenido);

		TextView title = (TextView) findViewById(R.id.detailsTextView);
		WebView webView = (WebView) findViewById(R.id.detailsWebView);

		title.setText(titulo);
		webView.loadData(contenido, "text/html", "UTF-8");
	}

}
