package com.company.model.map;

import com.company.model.math.Section;

public class Wall {
    private Section section;
    private int color;

    public Wall(){
        section = new Section(0, 0, 0, 0);
        color = 0;
    }

    public Wall(Section section, int color){
        this.section = section;
        this.color = color;
    }

    public Wall(double aX, double aY, double bX, double bY, int color){
        section = new Section(aX, aY, bX, bY);
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public Section getSection() {
        return section;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
