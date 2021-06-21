package com.automobile.assistance.ui.mechanic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.automobile.assistance.R;
import com.automobile.assistance.app.App;
import com.automobile.assistance.data.Repository;
import com.automobile.assistance.data.remote.pojo.User;
import com.automobile.assistance.databinding.ActivityMechanicBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import javax.inject.Inject;

public class MechanicActivity extends AppCompatActivity {

    @Inject
    Repository repo;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMechanicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getComponent().inject(this);

        binding = ActivityMechanicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.mechanic_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.mechanic_drawer_layout);
        NavigationView navigationView = findViewById(R.id.mechanic_nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_mechanic, R.id.nav_mechanic_profile, R.id.nav_mechanic_faq)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.mechanic_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        User user = repo.user().getUser();
        TextView name = binding.mechanicNavView.getHeaderView(0).findViewById(R.id.name);
        TextView email = binding.mechanicNavView.getHeaderView(0).findViewById(R.id.email);
        name.setText(user.fullName());
        email.setText(user.getEmail());
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.mechanic_nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}