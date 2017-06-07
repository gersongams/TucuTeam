package pe.ggarridomuni.tucuteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import pe.ggarridomuni.tucuteam.fragment.ContactosFragment;
import pe.ggarridomuni.tucuteam.fragment.MisPostsFragmentLista;
import pe.ggarridomuni.tucuteam.fragment.MisTopPostsFragmentLista;
import pe.ggarridomuni.tucuteam.fragment.Posts;
import pe.ggarridomuni.tucuteam.fragment.RecentPostsFragmentLista;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Contactos");
        setSupportActionBar(toolbar);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content, new ContactosFragment());
        fragmentTransaction.commit();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // crear nuevo post
        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationDrawer.this, NewPostActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }
        else if(i == R.id.action_language){
            startActivity(new Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS));
            return true;
        }
        else if(i == R.id.action_setting){
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        boolean fragmentTransaction = false;
        Fragment fragment = null;
        int id = item.getItemId();

        if(id == R.id.menu_seccion_1){
            fragment = new ContactosFragment();
            fragmentTransaction = true;
        }
        else if (id == R.id.nav_camera) {
            // Handle the camera action
            fragment = new RecentPostsFragmentLista();
            fragmentTransaction = true;

        } else if (id == R.id.nav_gallery) {
            fragment = new MisPostsFragmentLista();
            fragmentTransaction = true;

        } else if (id == R.id.nav_slideshow) {
            fragment = new MisTopPostsFragmentLista();
            fragmentTransaction = true;

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

//        toolbar.setTitle(menuItem.getTitle().toString());

        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    private FragmentPagerAdapter mPagerAdapter;
//    private ViewPager mViewPager;
//
//    // setear el adapter para el fragment
//    mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
//        private final Fragment[] mFragments = new Fragment[] {
//                new RecentPostsFragmentLista(),
//                new MisPostsFragmentLista(),
//                new MisTopPostsFragmentLista(),
//        };
//        private final String[] mFragmentNames = new String[] {
//                getString(R.string.heading_recent),
//                getString(R.string.heading_my_posts),
//                getString(R.string.heading_my_top_posts)
//        };
//        @Override
//        public Fragment getItem(int position) {
//            return mFragments[position];
//        }
//        @Override
//        public int getCount() {
//            return mFragments.length;
//        }
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentNames[position];
//        }
//    };
//    // setear el viewpager
////        mViewPager = (ViewPager) findViewById(R.id.container);
////        mViewPager.setAdapter(mPagerAdapter);
//    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);
//
//    // crear nuevo post
//    findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            startActivity(new Intent(MainActivity.this, NewPostActivity.class));
//        }
//    });
}
