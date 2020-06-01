package com.example.bizlerstest.adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bizlerstest.R;

public class VehicleViewHolder extends RecyclerView.ViewHolder {
    TextView txtNumber,txtmodel,variant,fueltype,txtmake;
        RelativeLayout relative;
    public VehicleViewHolder(@NonNull View itemView) {
        super(itemView);

        txtNumber=itemView.findViewById(R.id.txtNumber );
        txtmodel=itemView.findViewById( R.id.txtmodel );
        variant=itemView.findViewById(R.id.variant );
        fueltype=itemView.findViewById(R.id.fueltype );
        txtmake=itemView.findViewById(R.id.txtmake );
        relative=itemView.findViewById(R.id.relative );
    }
}
