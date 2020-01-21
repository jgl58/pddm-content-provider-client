package es.ua.eps.contentproviderclient;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.icu.util.LocaleData;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nombre, password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.etNombre);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Cursor result  = getContentResolver().query(
                            Uri.parse("content://com.example.persistenciadatos/usuarios"),
                            null,
                            null,
                            null
                    );

                    if(result.moveToFirst()){
                        do{
                            Log.d("Debug",result.getString(1));
                        }while (result.moveToNext());

                    }
                }

            }
        });
    }
}
