package com.example.bizlerstest.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bizlerstest.pojo.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "vehicle.sqlite";  //made constant
    private static final int DB_VERSION = 1;
    private static final String TABLE = "vehicle";
    private static final String ID = "id";
    private static final String NUMBER = "number";
    private static final String MAKE = "make";
    private static final String MODEL = "model";
    private static final String VARIANT = "variant";
    private static final String FUELTYPE = "fueltype";
    private static final String VEHICLEIMG = "vehicleImg";

    public DbHelper(@Nullable Context context) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = " create table " + TABLE
                +  "  ( " + ID + " integer primary key autoincrement , "
                + NUMBER + " text ,  " + MAKE  + " text , " + MODEL + " text , " + VARIANT  + " text , "
                + FUELTYPE + " text , " + VEHICLEIMG + " BLOB )";

        Log.e( " DBQuery " , "==========" + query );
        db.execSQL( query );
    }
    public boolean addVehicle(Vehicle vehicle) {//database get

        SQLiteDatabase db = getWritableDatabase( );
        ContentValues values = new ContentValues( );

        values.put( NUMBER, vehicle.getNumber( ) );
        values.put( MAKE, vehicle.getMake( ) );
        values.put( MODEL, vehicle.getModel( ) );
        values.put( VARIANT, vehicle.getVariant( ) );
        values.put( FUELTYPE, vehicle.getFueltype( ) );
       // values.put( VEHICLEIMG, vehicle.getVehicleimg());

        long no = db.insert( TABLE, null, values );
        if (no == 0) {
            return false;
        } else {
            return true;
        }
    }
    public List<Vehicle> getAllVehicle() {
        List<Vehicle> vehicles = new ArrayList<>( );
        SQLiteDatabase db = getReadableDatabase( );

        String query = "select * from " + TABLE;
        Cursor cursor = db.rawQuery( query, null );
        while (cursor.moveToNext( )) {
            int id = cursor.getInt( cursor.getColumnIndex( ID ) );
            String number = cursor.getString( cursor.getColumnIndex( NUMBER ) );
            String make = cursor.getString( cursor.getColumnIndex( MAKE ) );
            String model = cursor.getString( cursor.getColumnIndex( MODEL ) );
            String variant = cursor.getString( cursor.getColumnIndex( VARIANT ) );
            String fueltype = cursor.getString( cursor.getColumnIndex( FUELTYPE ) );
            Vehicle vehicle = new Vehicle(number,make,model,variant,fueltype );
            vehicle.setId(id);
            vehicles.add(vehicle);
        }
        if (cursor != null && !cursor.isClosed( )) {
            cursor.close( );
        }
        db.close();
        return vehicles;
    }

    public int update(Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NUMBER, vehicle.getNumber());

        // updating row
        return db.update(TABLE, values, ID + " = ?",
                new String[]{String.valueOf(vehicle.getId())});
    }

    //Deleting

    public void delete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = ?",
                new String[] { String.valueOf(id) });
    }//end

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
