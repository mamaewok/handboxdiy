package es.handbox.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import es.handbox.handboxdiy.R;

/**
 * Clase que muestra la portada de nuestra app
 * 
 * @author Handbox
 * 
 */
public class FrontActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_front);

		Thread th = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					onPause();
					startActivity(new Intent("es.handbox.app.EnterActivity"));
//					startActivity(new Intent("es.handbox.app.MainActivity"));
				}
			}
		};
		th.start();
	}

	@Override
	public void onPause() {
		super.onPause();
		finish();
	}
}
