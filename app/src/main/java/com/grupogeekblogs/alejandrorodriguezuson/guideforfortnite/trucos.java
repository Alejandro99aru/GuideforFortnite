package com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.Menu.Armas;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.Menu.Construcciones;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.Menu.Mapa;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.Menu.Noticias;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.Menu.Skins;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.Menu.twitterMain;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.chat.Login;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.twitter.twittermain;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.wallpapers.MainActivityWallpapers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLConnection;

import java.net.URL;



import java.security.PrivateKey;
import java.util.Locale;

public class trucos extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webView;
    TextView WebViewStatusTextView, txt_view;
    Button LoadURL;
    ProgressBar progressBar;

    DrawerLayout drawer;
    NavigationView navigationView;
    android.support.v7.widget.Toolbar toolbar;
    String URL = String.valueOf(R.string.url_trucos);


    private FirebaseAnalytics mFirebaseAnalytics;
    private static final String TAG = "MainActivity";

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trucos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
        mAdView.loadAd(adRequest);


        String l7 = Locale.getDefault().toString();

        String URL1 = String.valueOf(R.string.url_trucos);

        txt_view = findViewById(R.id.textView12);

        txt_view.setText(R.string.url_trucos);

        FirebaseCrash.log("Activity created");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        webView = (WebView) findViewById(R.id.webview);
        WebViewStatusTextView = (TextView) findViewById(R.id.textView);
        LoadURL = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);


        ConnectivityManager ConnectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.conexion_internet)
                    .setNegativeButton("Okey", null);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.conexion_internet)
                    .setNegativeButton("Okey", null);


        }


        WebViewLoadFunction();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        switch (id) {

            case R.id.nav_trucos:
                Intent h = new Intent(trucos.this, trucos.class);
                startActivity(h);
                break;
            case R.id.nav_armas:
                Intent i = new Intent(trucos.this, Armas.class);
                startActivity(i);
                break;
            case R.id.nav_noticias:
                Intent s = new Intent(trucos.this, Noticias.class);
                startActivity(s);
                break;
            case R.id.nav_skins:
                Intent t = new Intent(trucos.this, Skins.class);
                startActivity(t);
                break;
            case R.id.nav_mapa:
                Intent g= new Intent(trucos.this,Mapa.class);
                startActivity(g);
                break;
            case R.id.nav_twitter:
                Intent k = new Intent(trucos.this, twitterMain.class);
                startActivity(k);
                break;
            case R.id.nav_construcciones:
                Intent p = new Intent(trucos.this, Construcciones.class);
                startActivity(p);
                break;
            case R.id.nav_chat:
                Intent c = new Intent(trucos.this, Login.class);
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
                String emailList[] = {"appfortniteguia@gmail.com"};
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

    public void WebViewLoadFunction() {

        // Add setWebViewClient on WebView.
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Do something here on page load start time.

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Show the toast message after finishing page loading.
                WebViewStatusTextView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        });


        // Add setWebChromeClient on WebView.
        webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView webView1, int newProgress) {

                WebViewStatusTextView.setText(newProgress + "%");
                progressBar.setProgress(newProgress);

                if (newProgress == 100) {
                    // Page loading finish

                }
            }
        });

        // Giving permissio to enable JavScript.
        webView.getSettings().setJavaScriptEnabled(true);

        // Pass the String variable which holds the website URL.
        webView.loadUrl(String.valueOf(txt_view.getText()));

    }

    private boolean isFirstTimeStartApp() {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        return ref.getBoolean("FirstTimeStartFlag", true);
    }

    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        }, milisegundos);
    }


}