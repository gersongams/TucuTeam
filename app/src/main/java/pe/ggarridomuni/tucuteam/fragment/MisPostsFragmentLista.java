package pe.ggarridomuni.tucuteam.fragment;


import android.support.v4.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */

public class MisPostsFragmentLista extends ListaPostFragment {


    public MisPostsFragmentLista() {
        // Required empty public constructor
    }


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("user-posts")
                .child(getUid());
    }

}
