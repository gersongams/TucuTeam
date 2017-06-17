package pe.ggarridomuni.tucuteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
        toolbar.setTitle("Bitzer");
        setSupportActionBar(toolbar);





        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.replace, new ContactosFragment());
        fragmentTransaction.commit();


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE);

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
        int id = item.getItemId();
        Fragment fragment = null;
        boolean fragmentTransaction = false;


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager mViewPager;
        mViewPager = (ViewPager) findViewById(R.id.container);
        FloatingActionButton floatButtom = (FloatingActionButton) findViewById(R.id.fab_new_post);

        FragmentPagerAdapter mPagerAdapter;
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new RecentPostsFragmentLista(),
                    new MisPostsFragmentLista(),
                    new MisTopPostsFragmentLista(),
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.nav_reciente),
                    getString(R.string.nav_posts),
                    getString(R.string.nav_top_posts)
            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
////     setear el viewpager
        if(mViewPager != null){
            mViewPager.setAdapter(mPagerAdapter);
            tabLayout.setVisibility(View.GONE);
            mViewPager.setVisibility(View.GONE);
            tabLayout.setupWithViewPager(mViewPager);
        }
        switch (id){

            case R.id.nav_profile:
                fragmentTransaction = true;
                fragment = new ContactosFragment();
                floatButtom.setVisibility(View.GONE);
                break;
            case R.id.nav_contactos:
                fragmentTransaction = true;
                fragment = new ContactosFragment();
                floatButtom.setVisibility(View.GONE);
                break;
            case R.id.nav_posts:
                tabLayout.setVisibility(View.VISIBLE);
                mViewPager.setVisibility(View.VISIBLE);
                fragmentTransaction = true;
                fragment = new Posts();
                floatButtom.setVisibility(View.VISIBLE);
                break;
            case R.id.nav_groups:
                fragmentTransaction = true;
                fragment = new ContactosFragment();
                floatButtom.setVisibility(View.GONE);
                break;
            case R.id.nav_manage:
                Intent i = new Intent(this, ConfigurationPerfilActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;

        }

        if(fragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.replace,fragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
