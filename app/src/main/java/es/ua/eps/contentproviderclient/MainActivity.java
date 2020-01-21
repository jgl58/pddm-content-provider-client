package es.ua.eps.contentproviderclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
                        Boolean login = false;
                        String id = "";
                        do{
                            if(nombre.getText().toString().equals(result.getString(1))
                            && password.getText().toString().equals(result.getString(2))){
                                id = result.getString(0);
                                login = true;
                            }
                        }while (result.moveToNext());

                        if(login){
                            Intent intent =new Intent(getApplicationContext(),UsuarioActivity.class);
                            intent.putExtra("ID",id);
                            startActivity(intent);
                        }else{

                            mostrarDialog().show();
                        }
                    }
                }

            }
        });
    }

    public Dialog mostrarDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Error. Usuario no encontrado")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
