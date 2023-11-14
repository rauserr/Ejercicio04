package raul.serrano.ejercicio04;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.crypto.spec.DESedeKeySpec;

public class MainActivity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnDesencriptar;
    private Button btnCrearDireccion;
    //private final int DIRECCIONES = 123;
    private ActivityResultLauncher<Intent> launcherDirecciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVista();

        btnDesencriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if(password.isEmpty() || email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }else{
                    //ENVIAR INFORMACIÓN A LA SEGUNDA ACTIVIDAD
                    Intent intent = new Intent(MainActivity.this, DesencriptarActivity.class);
                    //PASAR INFORMACIÓN
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("USER", new Usuario(email, password));
                    //bundle.putString("EMAIL", email);
                    //bundle.putString("PASS", password);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        btnCrearDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearDireccionActivity.class);
                // startActivityForResult(intent,DIRECCIONES);
                launcherDirecciones.launch(intent);
            }
        });

        private void inicializarLaunches() {
            //1. preparar como lanzar la actividad hija (equivalente a start ActivityResult())
            //2. preparar que voy a hacer cuando la hija devuelva datos (equivalente al onACtivityResult())
            launcherDirecciones = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                if (result.getData() != null) {
                                    Bundle bundle = result.getData().getExtras();
                                    Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                                    Toast.makeText(MainActivity.this, direccion.toString(), Toast.LENGTH_SHORT);
                                }else{
                                    Toast.makeText(MainActivity.this, "MO HAY DATOS", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "CANCELADA", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
             );
        }


    /**
     *
     * @param requestCode -> indetifcador de la ventana que se ha cerrado
     * @param resultCode -> el modo en el que se ha cerrado
     * @param data -> datos que estan dentro del intent
     */

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == DIRECCIONES){
            if(resultCode == RESULT_OK) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                    Toast.makeText(this, direccion.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "NO HAY DATOS", Toast.LENGTH_SHORT).show();

                }
            }else{
                Toast.makeText(this,"CANCELADA", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    private void inicializarVista() {
        txtEmail = findViewById(R.id.txtEmailMain);
        txtPassword = findViewById(R.id.txtPasswordMain);
        btnDesencriptar = findViewById(R.id.btnDesencriptarMain);
        btnCrearDireccion = findViewById(R.id.btnCrearDireccionCrear);
    }
}