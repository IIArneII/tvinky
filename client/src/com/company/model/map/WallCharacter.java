package com.company.model.map;

import com.company.model.entity.Character;
import com.company.model.math.Angles;
import com.company.model.math.Point;
import com.company.model.math.Section;

import java.io.Serializable;

public class WallCharacter extends Wall implements Serializable {
    Character character;

    public WallCharacter(Character character){
        super(character.getX(), character.getY(), character.getX() + 0.5, character.getY(), 1, 0, 1);
        this.character = character;
    }

    public Section getSection() {
        section.setA(new Point(character.getX() + Math.cos(Angles.converteDegreeToRadian(character.getAngCharacter() - Angles.Ang90)) / 8,
                character.getY() + Math.sin(Angles.converteDegreeToRadian(character.getAngCharacter() - Angles.Ang90)) / 8));
        section.setB(new Point(character.getX() + Math.cos(Angles.converteDegreeToRadian(character.getAngCharacter() + Angles.Ang90)) / 8,
                character.getY() + Math.sin(Angles.converteDegreeToRadian(character.getAngCharacter() + Angles.Ang90)) / 8));
        return section;
    }
}
