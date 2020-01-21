package es.ua.eps.contentproviderclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class UsuarioActivity extends AppCompatActivity {
    TextView nombre, email, saludo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        nombre = findViewById(R.id.tvNombre);
        email = findViewById(R.id.tvEmail);
        saludo = findViewById(R.id.tvSaludo);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String id = getIntent().getStringExtra("ID");
            Cursor result = getContentResolver().query(
                    Uri.parse("content://com.example.persistenciadatos/usuarios/"+id),
                    null,
                    null,
                    null
            );


            if(result.moveToFirst()){
                do{
                    nombre.setText(result.getString(3));
                    saludo.setText("Bienvenido "+result.getString(1));
                    email.setText(result.getString(4));
                }while (result.moveToNext());
            }
        }
    }
}
