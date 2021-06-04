package com.company.model.map;

import com.company.model.entity.Character;
import com.company.model.math.Angles;
import com.company.model.math.Point;
import com.company.model.math.Section;

import java.io.Serializable;

public class WallCharacter extends Wall implements Serializable {
    private Character character;
    private int angleA;
    private int angleB;

    public WallCharacter(Character character, int angleA, int angleB, int texture){
        super(character.getX(), character.getY(), character.getX() + 0.5, character.getY(), 1, texture, 1);
        this.angleA = angleA;
        this.angleB = angleB;
        this.character = character;
    }

    public Section getSection() {
        section.setA(new Point(character.getX() + Math.cos(Angles.converteDegreeToRadian(character.getAngCharacter() + angleA)) / 7,
                character.getY() + Math.sin(Angles.converteDegreeToRadian(character.getAngCharacter() + angleA)) / 7));
        section.setB(new Point(character.getX() + Math.cos(Angles.converteDegreeToRadian(character.getAngCharacter() + angleB)) / 7,
                character.getY() + Math.sin(Angles.converteDegreeToRadian(character.getAngCharacter() + angleB)) / 7));
        return section;
    }
}
