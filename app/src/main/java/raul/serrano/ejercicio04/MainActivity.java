package raul.serrano.ejercicio04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.crypto.spec.DESedeKeySpec;

public class MainActivity extends AppCompatActivity {
    private EditText txtPassword;
    private Button btnDesencriptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciailzarVista();

        btnDesencriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password =txtPassword.getText().toString();

                if(password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }else{
                    //ENVIAR INFORMACIÓN A LA SEGUNDA ACTIVIDAD
                    Intent intent = new Intent(MainActivity.this, DesencriptarActivity.class);
                    //PASAR INFORMACIÓN
                    Bundle bundle = new Bundle();
                    bundle.putString("PASS", password);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    private void iniciailzarVista() {
        txtPassword = findViewById(R.id.txtPasswordMain);
        btnDesencriptar = findViewById(R.id.btnDesencriptarMain);
    }
}