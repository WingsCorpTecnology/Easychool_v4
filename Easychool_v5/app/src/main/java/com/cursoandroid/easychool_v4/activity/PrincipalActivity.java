package com.cursoandroid.easychool_v4.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cursoandroid.easychool_v4.R;
import com.cursoandroid.easychool_v4.config.ConfiguracaoFirebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    private boolean manterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    }

    @Override
    protected void onStop() {
        super.onStop();

        manterConectado();
    }

    public void manterConectado(){
        manterConectado = (Boolean) getIntent().getSerializableExtra("manterConectado");

        if(manterConectado != true){
            autenticacao.signOut();
        }
    }
}