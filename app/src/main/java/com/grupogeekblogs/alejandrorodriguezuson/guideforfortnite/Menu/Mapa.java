package com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.Menu;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.multidex.MultiDex;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.google.firebase.database.DatabaseReference;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.R;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.armas;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.chat.Login;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.construcciones;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.mapa;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.noticias;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.skins;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.trucos;
import com.grupogeekblogs.alejandrorodriguezuson.guideforfortnite.twitter.twittermain;

public class Mapa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    PhotoViewAttacher mAttacher;

    private DatabaseReference mDatabase;

    DrawerLayout drawer;
    NavigationView navigationView;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        MultiDex.install(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main3);
        headerLayout.findViewById(R.id.prueba1);
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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

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
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id){

            case R.id.nav_trucos:
                Intent h= new Intent(Mapa.this,trucos.class);
                startActivity(h);
                break;
            case R.id.nav_armas:
                Intent i= new Intent(Mapa.this,armas.class);
                startActivity(i);
                break;
            case R.id.nav_mapa:
                Intent g= new Intent(Mapa.this,Mapa.class);
                startActivity(g);
                break;
            case R.id.nav_noticias:
                Intent s= new Intent(Mapa.this,noticias.class);
                startActivity(s);
                break;
            case R.id.nav_skins:
                Intent t= new Intent(Mapa.this,skins.class);
                startActivity(t);
                break;
            case R.id.nav_twitter:
                Intent k= new Intent(Mapa.this,twittermain.class);
                startActivity(k);
                break;
            case R.id.nav_construcciones:
                Intent p= new Intent(Mapa.this,construcciones.class);
                startActivity(p);
                break;
            case R.id.nav_chat:
                Intent c= new Intent(Mapa.this,Login.class);
                startActivity(c);
                break;
            // this is done, now let us go and intialise the home page.
            // after this lets start copying the above.
            // FOLLOW MEEEEE>>>
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
