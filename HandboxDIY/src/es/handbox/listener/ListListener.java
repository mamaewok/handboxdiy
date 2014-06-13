package es.handbox.listener;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import es.handbox.app.DetailsActivity;
import es.handbox.data.RssHandboxItem;

/**
 * Implementa los listener de la lista de imtem rss
 * 
 * @author handbox
 * 
 */
public class ListListener implements OnItemClickListener {

	List<RssHandboxItem> listItems;
	Activity activity;

	public ListListener(List<RssHandboxItem> aListItems, Activity anActivity) {
		listItems = aListItems;
		activity = anActivity;
	}

	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent i = new Intent(activity, DetailsActivity.class);
		i.setData(Uri.parse(listItems.get(pos).getContent()));

		i.putExtra("title", listItems.get(pos).getTitle());
		i.putExtra("content", listItems.get(pos).getContent());

		activity.startActivity(i);
	}
}
