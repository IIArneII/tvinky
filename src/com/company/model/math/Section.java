package com.company.model.math;

public class Section {
    private Point a, b;

    public Section(){
        a = new Point();
        b = new Point();
    }

    public Section(double aX, double aY, double bX, double bY){
        a = new Point(aX, aY);
        b = new Point(bX, bY);
    }

    public Section(Point a, Point b){
        this.a = a;
        this.b = b;
    }

    public double length(){
        return a.distance(b);
    }

    public double crossProduct(Section section){
        return (b.getX() - a.getX()) * (section.b.getY() - section.a.getY()) - (section.b.getX() - section.a.getX()) * (b.getY() - a.getY());
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public void setB(Point b) {
        this.b = b;
    }
}
