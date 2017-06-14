package pe.ggarridomuni.tucuteam;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CreateEditContacts extends AppCompatActivity implements View.OnClickListener{

    private DataBaseHelper dbHelper ;
    EditText nameEditText;
    EditText emailEditText;

    Button saveButton;
    LinearLayout buttonLayout;
    Button editButton, deleteButton;

    int personID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        personID = getIntent().getIntExtra(ContactActivity.KEY_EXTRA_CONTACT_ID, 0);

        setContentView(R.layout.activity_create_edit_contacts);

        nameEditText = (EditText) findViewById(R.id.editTextName);
        emailEditText = (EditText) findViewById(R.id.editTextTitle);


        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
        editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

        dbHelper = new DataBaseHelper(this);

        if(personID > 0) {
            saveButton.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.VISIBLE);

            Cursor rs = dbHelper.getContact(personID);
            rs.moveToFirst();
            String personName = rs.getString(rs.getColumnIndex(DataBaseHelper.TABLE_COLUMN_NAME));
            String email = rs.getString(rs.getColumnIndex(DataBaseHelper.TABLE_COLUMN_EMAIL));

            if (!rs.isClosed()) {
                rs.close();
            }

            nameEditText.setText(personName);
            nameEditText.setFocusable(false);
            nameEditText.setClickable(false);

            emailEditText.setText(email);
            emailEditText.setFocusable(false);
            emailEditText.setClickable(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveButton:
                persistTable();
                return;
            case R.id.editButton:
                saveButton.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.GONE);
                nameEditText.setEnabled(true);
                nameEditText.setFocusableInTouchMode(true);
                nameEditText.setClickable(true);

                emailEditText.setEnabled(true);
                emailEditText.setFocusableInTouchMode(true);
                emailEditText.setClickable(true);

                return;
            case R.id.deleteButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deletePerson)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHelper.deleteContact(personID);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Delete Person?");
                d.show();

        }
    }

    public void persistTable() {
        if(personID > 0) {
            if(dbHelper.updateContact(personID, nameEditText.getText().toString(),
                    emailEditText.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Person Update Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Person Update Failed", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if(dbHelper.insertContact(nameEditText.getText().toString(),
                    emailEditText.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Person Inserted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Could not Insert person", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

}
