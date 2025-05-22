package com.example.locallaundryapp.View.AppBodySection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.locallaundryapp.R;
import com.example.locallaundryapp.View.Fragments.HomeFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainBodyActivity extends AppCompatActivity {


    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar;
    View headerView;
    ImageView imageView, closeImgView;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_body);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        frameLayout = findViewById(R.id.frameLayout);
        toolbar = findViewById(R.id.toolber);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // header image and button introduce ------------------------
        headerView = navigationView.getHeaderView(0);
        imageView = headerView.findViewById(R.id.headerImage);
        closeImgView = headerView.findViewById(R.id.closeImgView);

        closeImgView.setOnClickListener(v->{
            drawerLayout.closeDrawer(GravityCompat.START);
        });


//        Picasso.get().load("https://res.cloudinary.com/daily-now/image/upload/f_auto,q_auto/v1/posts/e7ef4ff144eceab8d8d06a2317fbf83d?_a=AQAEuiZ").into(imageView);


        fragmentReplace(new HomeFragment());


        // Drawer layout Open and close = ====================================
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainBodyActivity.this,drawerLayout,toolbar,R.string.DrawerOpen,R.string.DrawerClose);

        drawerLayout.addDrawerListener(toggle);









        // navigation item selected ======================================
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //how to select item ---------------------------------------
                if (item.getItemId()==R.id.home_item){
                    Toast.makeText(MainBodyActivity.this, "Education Icon", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }if (item.getItemId() == R.id.price_list_item){
                    Toast.makeText(MainBodyActivity.this, "Win Icon", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }if (item.getItemId() == R.id.profile_item){
                    drawerLayout.closeDrawer(GravityCompat.START);

                }



                return true;
            }
        });


        // In MainBodyActivity.java

// Bottom Navigation Bar------------------------------------------------------------
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_item){

                Toast.makeText(this, "this is Home", Toast.LENGTH_SHORT).show();


                    return true;
                } else if (item.getItemId() == R.id.price_list_item) {
                Toast.makeText(this, "this is Price List", Toast.LENGTH_SHORT).show();
                return true;

            }else {
                return false;
            }

        });





    }

    // fragment replace ----------------------------------
    private void fragmentReplace(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


}