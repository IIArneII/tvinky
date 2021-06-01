package com.company.model.map;

import com.company.model.entity.Character;
import com.company.model.math.Point;
import com.company.model.math.Section;

import java.io.Serializable;

public class WallCharacter extends Wall implements Serializable {
    Character character;

    public WallCharacter(Character character){
        super(character.getX() + 0.5, character.getY(), character.getX() - 0.5, character.getY(), 1, 1, 1);
        this.character = character;
    }

    public Section getSection() {
        section.setA(new Point(character.getX() + 1, character.getY() + 1));
        section.setB(new Point(character.getX() + 1, character.getY() - 1));
        return section;
    }
}
