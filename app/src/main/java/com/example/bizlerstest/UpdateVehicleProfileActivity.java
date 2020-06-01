package com.example.bizlerstest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bizlerstest.DB.DbHelper;
import com.example.bizlerstest.pojo.Vehicle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateVehicleProfileActivity extends AppCompatActivity {

    EditText edtVehicleNumber;
    TextView selectPhotes;
    ImageView viewPhotoes;
    Spinner spinnerMake,spinnerModel,spinnerVariant,spinnerFuelType;
    String[] make = {"Select Make", "2018", "2019","2020"};
    String[] model = {"Select Model", "Venue", "AURA", "Santro","New Creta"};
    String[] variant = {"Select Variant", "ABC", "XYZ"};
    String[] fuelType = {"Select FuelType", "Diesel", "Petrol"};
    ArrayAdapter adapterMake;
    ArrayAdapter adapterModel;
    ArrayAdapter adapterVariant;
    ArrayAdapter adapterFuelType;
    String imageEncoded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);
        selectPhotes  = (TextView) findViewById(R.id.selectPhotes);
        selectPhotes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ///Toast.makeText(UpdateVehicleProfileActivity.this,"Recod Added",Toast.LENGTH_SHORT ).show();

                if(ActivityCompat.checkSelfPermission(UpdateVehicleProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1000);
                }
                else {
                    startGallery();
                }
            }
        });
        viewPhotoes = (ImageView) findViewById(R.id.viewPhotoes);
        edtVehicleNumber = (EditText) findViewById(R.id.edtVehicleNumber);
        spinnerMake = (Spinner) findViewById(R.id.spinnerMake);
        spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        spinnerVariant = (Spinner) findViewById(R.id.spinnerVariant);
        spinnerFuelType = (Spinner) findViewById(R.id.spinnerFuelType);

        adapterMake = new ArrayAdapter(this, android.R.layout.simple_spinner_item, make);
        adapterMake.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMake.notifyDataSetChanged();
        spinnerMake.setAdapter(adapterMake);
        adapterModel = new ArrayAdapter(this, android.R.layout.simple_spinner_item, model);
        adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterModel.notifyDataSetChanged();
        spinnerModel.setAdapter(adapterModel);
        adapterFuelType = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fuelType);
        adapterFuelType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterFuelType.notifyDataSetChanged();
        spinnerFuelType.setAdapter(adapterFuelType);
        adapterVariant = new ArrayAdapter(this, android.R.layout.simple_spinner_item, variant);
        adapterVariant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterVariant.notifyDataSetChanged();
        spinnerVariant.setAdapter(adapterVariant);

        //  List<String> list = new ArrayList<String>();
        //list.add("Select");
    }

    //Select Photo
    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewPhotoes.setImageBitmap(bitmapImage);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
               /* SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("image1", imageEncoded);
                editor.apply();*/
            }
        }
    }//end

    public void Update(View view) {

        if ((edtVehicleNumber.length() == 0)) {
            Toast.makeText(UpdateVehicleProfileActivity.this, "Enter VehicleNumber ", Toast.LENGTH_LONG).show();
        }else {
            String number = edtVehicleNumber.getText().toString().trim();
            String make = spinnerMake.getSelectedItem().toString();
            String model = spinnerModel.getSelectedItem().toString();
            String variant = spinnerVariant.getSelectedItem().toString();
            String fueltype = spinnerFuelType.getSelectedItem().toString();
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

//        String vehicleImg= imageEncoded.toString();
            // vehicleImg = imageEncoded;

            Vehicle vehicle = new Vehicle(number, make, model, variant, fueltype);
            DbHelper dbHelper = new DbHelper(this);
           /* boolean isAddes = dbHelper.update(vehicle);
            if (isAddes) {
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }*/
        }
    }
}
