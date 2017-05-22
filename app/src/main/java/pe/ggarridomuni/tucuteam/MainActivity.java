package pe.ggarridomuni.tucuteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;

import pe.ggarridomuni.tucuteam.fragment.MisPostsFragmentLista;
import pe.ggarridomuni.tucuteam.fragment.MisTopPostsFragmentLista;
import pe.ggarridomuni.tucuteam.fragment.RecentPostsFragmentLista;

public class MainActivity extends ProgressActivity {

    private static final String TAG = "MainActivity";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    private NavigationView navView;
    private DrawerLayout drawerLayout;

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.menu_reciente));

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
//        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        // nav view
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                toolbar.setTitle(menuItem.getTitle().toString());
                                fragment = new RecentPostsFragmentLista();

                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                fragment = new MisPostsFragmentLista();
                                toolbar.setTitle(menuItem.getTitle().toString());
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_3:
                                fragment = new MisTopPostsFragmentLista();
                                toolbar.setTitle(menuItem.getTitle().toString());
                                fragmentTransaction = true;
                                break;

                        }

//                        if(fragmentTransaction) {
//                            getSupportFragmentManager().beginTransaction()
//                                    .replace(R.id.container, fragment)
//                                    .commit();
//
//                            menuItem.setChecked(true);
//                            getSupportActionBar().setTitle(menuItem.getTitle());
//                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });




        // setear el adapter para el fragment
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new RecentPostsFragmentLista(),
                    new MisPostsFragmentLista(),
                    new MisTopPostsFragmentLista(),
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.heading_recent),
                    getString(R.string.heading_my_posts),
                    getString(R.string.heading_my_top_posts)
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
        // setear el viewpager
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // crear nuevo post
        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        else if(i == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}