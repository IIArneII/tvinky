package com.company.model;

public class Angles {
    public static final int Ang360 = 4800;
    public static final int Ang180 = 2400;
    public static final int Ang90 = 1200;
    public static final int Ang60 = 800;
    public static final int Ang30 = 400;
    public static final int Ang6 = 80;

    public static double converteDegreeToRadian(double angle){
        return angle * (Math.PI / Ang180);
    }
}
