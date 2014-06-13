package es.handbox.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import es.handbox.handboxdiy.R;

public class LoginActivity extends Activity {

	private Typeface fontTitulo, fontInfo;
	private TextView titulo, tvCorreo, tvContrasenia, tvRepetir, tvNick;
	
	private CheckBox condicion;
	private Button aceptar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// inicializa fuentes //buscar como hacer desde xml
		fontTitulo = Typeface.createFromAsset(getAssets(), "Intro.otf");
		fontInfo = Typeface.createFromAsset(getAssets(), "LatoRegular.ttf");

		// Inicializa todos texView
		titulo = (TextView) findViewById(R.id.tv_titulo);
		tvCorreo = (TextView) findViewById(R.id.tv_ma);
		tvContrasenia = (TextView) findViewById(R.id.tv_pw1);
		tvRepetir = (TextView) findViewById(R.id.tv_pw2);
		tvNick = (TextView) findViewById(R.id.tv_ni);

		// estabelce fuentes
		titulo.setTypeface(fontTitulo);
		tvCorreo.setTypeface(fontInfo);
		tvContrasenia.setTypeface(fontInfo);
		tvRepetir.setTypeface(fontInfo);
		tvNick.setTypeface(fontInfo);

		// Inicializo los editText

		final EditText correo = (EditText) findViewById(R.id.et_co);
		final EditText contrasenia = (EditText) findViewById(R.id.et_pw1);
		final EditText repetir = (EditText) findViewById(R.id.et_pw2);
		final EditText nick = (EditText) findViewById(R.id.et_ni);

		// Recupero los textos de los editText
//		final String textCorreo = this.correo.getText().toString();
//		final String textContrasenia = this.contrasenia.getText().toString();
//		final String textRepetir = this.repetir.getText().toString();
//		final String textNick = this.nick.getText().toString();

		// inicializa y da funcionalidad a boton de aceptar
		aceptar = (Button) findViewById(R.id.btn_login);
		aceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String textCorreo = correo.getText().toString();
				String textContrasenia = contrasenia.getText().toString();
				String textRepetir = repetir.getText().toString();
				String textNick = nick.getText().toString();
						// compruebo que el correo sea válido
						if (textCorreo.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
							//Compruebo que las contraseñas no esten vacias y sean iguales
							if (textContrasenia.isEmpty() == false){
									if(textContrasenia.equals(textRepetir)) {
										//compruebo que el nick no esté vacio
										if(!textNick.equals("")){
											mostrarToast("Datos correctos").show();
											startActivity(new Intent("es.handbox.app.MainActivity"));
										}else{
											mostrarToast("Necesitas un Nick").show();
										}
									}else{
										mostrarToast("Contraseña no coincide").show();
									}								
							}else{
								mostrarToast("Contraseña vacia").show();
							}
						} else {
							mostrarToast("Email no válido").show();
						}
					}

				
				});
			}
	
	
	public Toast mostrarToast(String text){
		Toast toast1 = Toast.makeText(
				getApplicationContext(), text,
				Toast.LENGTH_SHORT);
		return toast1;
	}

}
