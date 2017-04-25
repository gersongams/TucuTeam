package pe.ggarridomuni.tucuteam.fragment;


import android.support.v4.app.Fragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyTopPostsFragment extends PostListFragment {


    public MyTopPostsFragment() {
        // Required empty public constructor
    }


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START my_top_posts_query]
        // My top posts by number of stars
        String myUserId = getUid();
        Query myTopPostsQuery = databaseReference.child("user-posts").child(myUserId)
                .orderByChild("starCount");
        // [END my_top_posts_query]

        return myTopPostsQuery;
    }

}
