package com.example.bizlerstest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bizlerstest.DB.DbHelper;
import com.example.bizlerstest.R;
import com.example.bizlerstest.ViewProfileActivity;
import com.example.bizlerstest.pojo.Vehicle;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleViewHolder> {
    List<Vehicle> vehicleList;
    DbHelper dbHelper;
    private Context context;

    public  VehicleAdapter(List<Vehicle> vehicles, DbHelper dbHelper,Context vehicleProfile){ //constructor
        this.vehicleList =vehicles;
        this.dbHelper=dbHelper;
        this.context = vehicleProfile;

    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.list_vehicle,viewGroup,false  );
        return new VehicleViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder vehicleViewHolder, int i) {

        final Vehicle vehicle=vehicleList.get( i );
        vehicleViewHolder .txtNumber.setText( "Number :  "+ vehicle.getNumber() );
        vehicleViewHolder .txtmake.setText( "Make    :  "+ vehicle.getMake() );
        vehicleViewHolder .txtmodel.setText( "Model  :  "+ vehicle.getModel() );
        vehicleViewHolder .variant.setText( "Variant :  "+ vehicle.getVariant() );
        vehicleViewHolder .fueltype.setText( "FuelType : "+ vehicle.getFueltype() );
        //Picasso.with(context).load(vehicle.().into(vehicleViewHolder.articleImg);

        vehicleViewHolder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewProfileActivity.class);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return vehicleList.size();
    }
}
