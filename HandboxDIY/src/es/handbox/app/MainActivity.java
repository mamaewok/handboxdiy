package es.handbox.app;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import es.handbox.data.RssHandboxItem;
import es.handbox.handboxdiy.R;
import es.handbox.listener.ListListener;
import es.handbox.util.RssAtomReader;

/**
 * Despliega una lista de items ATOM
 * 
 * @author handbox
 * 
 */
public class MainActivity extends Activity {

	// objeto local
	private MainActivity local;

	/**
	 * Crea la vista de la aplicacion main
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// inicializa el objeto local
		local = this;

		// ejecuta descarga de rss
		GetRSSDataTask task = new GetRSSDataTask();
		task.execute("http://www.handbox.es/feed/atom/");
		//task.execute("http://www.handbox.es/feed/atom/?paged=2");
	}
	
	private class GetRSSDataTask extends
			AsyncTask<String, Void, List<RssHandboxItem>> {
		
		@Override
		protected List<RssHandboxItem> doInBackground(String... urls) {

			try {
				// Creao lector rss
				RssAtomReader lectorRss = new RssAtomReader(urls[0]);

				// parseo rss, devuelvo item
				return lectorRss.getItems();

			} catch (Exception e) {
				Log.e("fallo devolviendo rss items", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<RssHandboxItem> result) {

			// establece el listview
			ListView handboxItems = (ListView) findViewById(R.id.listMainView);

			// Crea el list adapter
			ArrayAdapter<RssHandboxItem> adapter = new ArrayAdapter<RssHandboxItem>(
					local, android.R.layout.simple_list_item_1, result);
			// Establece el listAdapter al List View
			handboxItems.setAdapter(adapter);

			// item click listener
			handboxItems.setOnItemClickListener(new ListListener(result, local));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    //Alternativa 1
	    getMenuInflater().inflate(R.menu.menu_main, menu);
	    return true;
	}
}
