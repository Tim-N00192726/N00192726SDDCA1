package com.company;

public class Watch {
    private int id;
    private String name;
    private String make;
    private String colour;
    private String chargingType;
    private double batteryLife;



    public Watch(String name, String make, String colour, String chargingType, double batteryLife) {
        this.name = name;
        this.make = make;
        this.colour = colour;
        this.chargingType = chargingType;
        this.batteryLife = batteryLife;
    }


    public Watch(int id, String name, String make, String colour, String chargingType, double batteryLife) {
        this.id = id;
        this.name = name;
        this.make = make;
        this.colour = colour;
        this.chargingType = chargingType;
        this.batteryLife = batteryLife;
    }



    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake(){
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColour(){
        return make;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getChargingType(){
        return chargingType;
    }

    public void setChargingType(String chargingType) {
        this.chargingType = chargingType;
    }

    public double getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(double batteryLife) {
        this.batteryLife = batteryLife;
    }

    @Override
    public String toString()
    {
        return "Programmer ID : " + id + ", Programmer name : " + name + ", Colour : " + colour + ", Charging Type : " + chargingType + ", Battery Life : " + batteryLife +"\n" ;
    }
} //end of class
