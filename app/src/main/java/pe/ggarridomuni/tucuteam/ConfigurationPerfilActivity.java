package pe.ggarridomuni.tucuteam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import pe.ggarridomuni.tucuteam.Data.Data;

/**
 * Created by bitzer on 17/06/17.
 */

public class ConfigurationPerfilActivity extends AppCompatActivity{

    private static final String TAG="ConfigurationPerfilActivity";
    private EditText inputUniversidad, inputCarrera, inputInteres;
    private Button btnSignUp;
    private ProgressBar progressBar;

    Data dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        dm = new Data(this);
        inputUniversidad = (EditText) findViewById(R.id.universidad);
        inputCarrera = (EditText) findViewById(R.id.carrera);
        inputInteres = (EditText) findViewById(R.id.interes);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignUp = (Button) findViewById(R.id.sign_up);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String universidad = inputUniversidad.getText().toString().trim();
                String carrera = inputCarrera.getText().toString().trim();
                String interes = inputInteres.getText().toString().trim();
                if (TextUtils.isEmpty(universidad)) {
                    Toast.makeText(getApplicationContext(), "Enter Universidad!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(carrera)) {
                    Toast.makeText(getApplicationContext(), "Enter carrera!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(interes)) {
                    Toast.makeText(getApplicationContext(), "Enter interes!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                //create user
                dm.insert(universidad,carrera,interes);
                startActivity(new Intent(ConfigurationPerfilActivity.this,NavigationDrawer.class));
                finish();
            }
        });

    }
    public void showData(Cursor c){
        while (c.moveToNext()){
            Log.i(c.getString(1), c.getString(2));
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
