package es.handbox.app;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.OnErrorListener;

import es.handbox.handboxdiy.R;

public class EnterActivity extends Activity {

	private Button boton;
	private Typeface fontTitulo, fontSaludo, fontCraft;
	private TextView titulo, craftText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter);

		fontTitulo = Typeface.createFromAsset(getAssets(), "Intro.otf");
		fontSaludo = Typeface.createFromAsset(getAssets(), "Weston.otf");
		fontCraft = Typeface.createFromAsset(getAssets(), "HaloHandletter.ttf");
		
		final TextView txtSaludo = (TextView) findViewById(R.id.txtSaludo);
		titulo = (TextView)findViewById(R.id.txtTitulo);
		craftText = (TextView)findViewById(R.id.txtCraft);
		
		txtSaludo.setTypeface(fontSaludo);
		titulo.setTypeface(fontTitulo);
		craftText.setTypeface(fontCraft);
		
		
		// con facebook
		LoginButton logButtonFace = (LoginButton) findViewById(R.id.login_button);
		logButtonFace.setOnErrorListener(new OnErrorListener() {

			@Override
			public void onError(FacebookException error) {
			}
		});

		logButtonFace.setReadPermissions(Arrays.asList("email",
				"public_profile", "user_friends"));
		logButtonFace.setSessionStatusCallback(new Session.StatusCallback() {

			@Override
			public void call(Session session, SessionState state,
					Exception exception) {

				if (session.isOpened()) {
					Request.newMeRequest(session,
							new Request.GraphUserCallback() {
								@Override
								public void onCompleted(GraphUser user,
										Response response) {
									if (user != null) {
										txtSaludo
												.setText("!Has iniciado sesión! "
														+ user.getUsername());
										startActivity(new Intent(
												"es.handbox.app.MainActivity"));
									}
								}
							}).executeAsync();
				} else if (session.isClosed()) {
					txtSaludo.setText("!No has iniciado sesión!");
				}
			}
		});

		// con twitter (...) TO DO
		
		//login normal
		boton = (Button)findViewById(R.id.button1);
		boton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent("es.handbox.app.LoginActivity"));
			}
		});
		

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
	}
}
