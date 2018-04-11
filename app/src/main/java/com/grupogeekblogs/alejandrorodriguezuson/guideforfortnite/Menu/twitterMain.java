package com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.Menu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.R;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.chat.Login;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.mapa;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.trucos;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.twitter.SectionsPageAdapter;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.twitter.Tab1Fragment;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.twitter.Tab2Fragment;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.twitter.twittermain;
import com.twitter.sdk.android.core.Twitter;

import java.util.Locale;

public class twitterMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webView;
    TextView WebViewStatusTextView;
    Button LoadURL ;
    String URL = String.valueOf(R.string.trucos_url);
    ProgressBar progressBar;

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_bueno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Twitter.initialize(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);



        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "FORTNITE TWEETS");
        adapter.addFragment(new Tab2Fragment(), "OFFICIAL ACCOUNT");
        viewPager.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){

            case R.id.nav_trucos:
                Intent h= new Intent(twitterMain.this,twitterMain.class);
                startActivity(h);
                break;
            case R.id.nav_armas:
                Intent i= new Intent(twitterMain.this,Armas.class);
                startActivity(i);
                break;
            case R.id.nav_mapa:
                Intent g= new Intent(twitterMain.this,Mapa.class);
                startActivity(g);
                break;
            case R.id.nav_noticias:
                Intent s= new Intent(twitterMain.this,Noticias.class);
                startActivity(s);
                break;
            case R.id.nav_skins:
                Intent t= new Intent(twitterMain.this,Skins.class);
                startActivity(t);
                break;
            case R.id.nav_twitter:
                Intent k= new Intent(twitterMain.this,twittermain.class);
                startActivity(k);
                break;
            case R.id.nav_construcciones:
                Intent p= new Intent(twitterMain.this,Construcciones.class);
                startActivity(p);
                break;
            case R.id.nav_chat:
                Intent c= new Intent(twitterMain.this,Login.class);
                startActivity(c);
                break;

            case R.id.nav_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, R.string.app_name);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, R.string.share_text);
                startActivity(Intent.createChooser(sharingIntent, String.valueOf(R.string.share_via)));
                break;
            case R.id.nav_send:
                String emailList[]= {"appfortniteguia@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, emailList);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                intent.putExtra(Intent.EXTRA_TEXT, "Write your feedback");
                startActivity(Intent.createChooser(intent, "Choice email app"));
                break;




            // this is done, now let us go and intialise the home page.
            // after this lets start copying the above.
            // FOLLOW MEEEEE>>>
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void WebViewLoadFunction(){

        // Add setWebViewClient on WebView.
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                // Do something here on page load start time.

            }
            @Override
            public void onPageFinished(WebView view, String url){
                // Show the toast message after finishing page loading.
                WebViewStatusTextView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        });


        // Add setWebChromeClient on WebView.
        webView.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView webView1, int newProgress){

                WebViewStatusTextView.setText(newProgress + "%");
                progressBar.setProgress(newProgress);

                if(newProgress == 100){
                    // Page loading finish

                }
            }
        });

        // Giving permissio to enable JavScript.
        webView.getSettings().setJavaScriptEnabled(true);

        // Pass the String variable which holds the website URL.
        webView.loadUrl(URL);

    }

}
