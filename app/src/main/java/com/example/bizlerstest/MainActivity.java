package com.example.bizlerstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bizlerstest.DB.DbHelper;
import com.example.bizlerstest.adapter.VehicleAdapter;
import com.example.bizlerstest.pojo.Vehicle;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvVehicle=findViewById( R.id.rvVehicle );
        rvVehicle.setLayoutManager( new LinearLayoutManager( this ) );//
        DbHelper dbHelper=new DbHelper( this );//
        List<Vehicle> vehicleList=dbHelper.getAllVehicle();//
        VehicleAdapter adapter=new VehicleAdapter(vehicleList ,dbHelper,this );
        rvVehicle.setAdapter( adapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_vehicle, menu);
        MenuItem menuItem = menu.findItem(R.id.item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item:
                Intent intent = new Intent(this, AddVehicleNameActivity.class);
                startActivity(intent);
                return true;
           /* case R.id.item2:
                Intent intent1 = new Intent(this, UpdateVehicleProfileActivity.class);
                startActivity(intent1);
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
