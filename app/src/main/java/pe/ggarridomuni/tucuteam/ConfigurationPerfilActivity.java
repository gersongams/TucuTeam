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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import pe.ggarridomuni.tucuteam.Data.Data;
import pe.ggarridomuni.tucuteam.models.Usuarios;

/**
 * Created by bitzer on 17/06/17.
 */

public class ConfigurationPerfilActivity extends AppCompatActivity{

    private DatabaseReference mDatabase;
    private static final String TAG="ConfigurationPerfilActivity";
    private EditText editUserName, editEmail, editDrescription;
    private Button btnSignUp;
    private ProgressBar progressBar;

    Data dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        dm = new Data(this);


        editUserName = (EditText) findViewById(R.id.editUserNAme);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editDrescription = (EditText) findViewById(R.id.editDescription);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignUp = (Button) findViewById(R.id.sign_up);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userName = editUserName.getText().toString().trim();
                final String email = editEmail.getText().toString().trim();
                final String description = editDrescription.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(getApplicationContext(), "Enter Universidad!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter carrera!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(getApplicationContext(), "Enter interes!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                mDatabase = FirebaseDatabase.getInstance().getReference("users");
                FirebaseAuth auth = FirebaseAuth.getInstance();

                Usuarios user = new Usuarios(userName,email,description);
                DatabaseReference userNameRef = mDatabase.child(auth.getCurrentUser().getUid());

                HashMap<String,Object> update = new HashMap<>();
                update.put("username",userName);
                update.put("email",email);
                update.put("description",description);

                userNameRef.setValue(update);
                //create user
                dm.insert(userName,email,description);
                dm.showData();
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
