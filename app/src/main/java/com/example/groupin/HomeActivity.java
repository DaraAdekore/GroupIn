package com.example.groupin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.groupin.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
//import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FloatingActionButton fb;
    RecyclerView rclView;
    ArrayList<model> dataholder;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);
        fb = findViewById(R.id.fb);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        fb.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddProject.class);
            startActivity(intent);
        });

        nav.setNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()){
                case R.id.item1:
                    Toast.makeText(getApplicationContext(),"Create new project",Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(HomeActivity.this, AddProject.class);
                    startActivity(intent);
                    break;

                case R.id.item2:
                    Toast.makeText(getApplicationContext(),"Your Projects",Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.item3:
                    Toast.makeText(getApplicationContext(),"Your tasks",Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.item4:
                    Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent intent4 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent4);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
            }
            return true;
        });

            rclView=findViewById(R.id.rclview);
            rclView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DBHelper(this).readalldata();
        dataholder=new ArrayList<>();

        while(cursor.moveToNext())
        {
            model obj = new model(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            dataholder.add(obj);
        }

        myAdapter adapter=new myAdapter(dataholder,getApplicationContext());
        rclView.setAdapter(adapter);



    }


}