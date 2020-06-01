package com.example.bizlerstest.pojo;

public class Vehicle {


    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String number;
    private  String make;
    private  String model;
    private  String variant;
    private String fueltype;
   // private String vehicleimg;
  //  private byte[] vehicleimg;


    public Vehicle( String number, String make, String model, String variant, String fueltype) {
        this.number = number;
        this.make = make;
        this.model = model;
        this.variant = variant;
        this.fueltype = fueltype;
       // this.vehicleimg = vehicleimg;

    }


 /*   public byte[] getVehicleimg() {
        return vehicleimg;
    }

    public void setVehicleimg(byte[] vehicleimg) {
        this.vehicleimg = vehicleimg;
    }
*/
    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }
}
