package pe.ggarridomuni.tucuteam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactActivity extends AppCompatActivity {

    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";

    private ListView listView;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Button button = (Button) findViewById(R.id.addNew);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this, CreateEditContacts.class);
                intent.putExtra(KEY_EXTRA_CONTACT_ID, 0);
                startActivity(intent);
            }
        });

        dbHelper = new DataBaseHelper(this);

        final Cursor cursor = dbHelper.getAllContacts();
        String [] columns = new String[] {
                DataBaseHelper.TABLE_COLUMN_NAME,
                DataBaseHelper.TABLE_COLUMN_EMAIL,
        };

        int [] widgets = new int[] {
                R.id.personName,
                R.id.emailName,
        };



        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.contact_info,
                cursor, columns, widgets, 0);
        listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(cursorAdapter);
        //listView.setAdapter(new ArrayAdapter<>(this,R.layout.contact_info,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                Cursor itemCursor = (Cursor) ContactActivity.this.listView.getItemAtPosition(position);
                int personID = itemCursor.getInt(itemCursor.getColumnIndex(DataBaseHelper.TABLE_COLUMN_ID));
                Intent intent = new Intent(getApplicationContext(), CreateEditContacts.class);
                intent.putExtra(KEY_EXTRA_CONTACT_ID, personID);
                startActivity(intent);
            }
        });

    }
}
