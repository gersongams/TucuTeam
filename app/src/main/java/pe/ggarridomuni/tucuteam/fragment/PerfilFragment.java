package pe.ggarridomuni.tucuteam.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pe.ggarridomuni.tucuteam.ConfigurationPerfilActivity;
import pe.ggarridomuni.tucuteam.NavigationDrawer;
import pe.ggarridomuni.tucuteam.NewPostActivity;
import pe.ggarridomuni.tucuteam.R;
import pe.ggarridomuni.tucuteam.models.Usuarios;

/**
 * Created by shinji on 5/22/17.
 */

public class PerfilFragment extends Fragment {
    FirebaseAuth auth;
    private DatabaseReference mDatabase;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        TextView profileEmail = (TextView)v.findViewById(R.id.profileEmail);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String user = auth.getCurrentUser().getEmail();
        profileEmail.setText(user);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        DatabaseReference userNameRef = mDatabase.child(auth.getCurrentUser().getUid()).child("username");
        DatabaseReference descriptionRef = mDatabase.child(auth.getCurrentUser().getUid()).child("description");

        userNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue(String.class);
                TextView profileName = (TextView) v.findViewById(R.id.profileName);
                profileName.setText(username);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        descriptionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String description = dataSnapshot.getValue(String.class);
                TextView profileDescription = (TextView)v.findViewById(R.id.profileDescription);
                profileDescription.setText(description);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button editProfile = (Button) v.findViewById(R.id.editProfile);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ConfigurationPerfilActivity.class));
            }
        });
        return v;
    }
}
