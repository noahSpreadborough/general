package com.example.a2dgamedevelopment.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.a2dgamedevelopment.GameLoop;
import com.example.a2dgamedevelopment.R;

public class Spell extends Circle {

    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    public Spell(Context context, Player spellcaster) {
        super(
                context,
                ContextCompat.getColor(context, R.color.spell),
                spellcaster.getPositionX(),
                spellcaster.getPositionY(),
                20
        );
        //this.spellcaster = spellcaster;
        velocityX = spellcaster.getDirectionX()*MAX_SPEED;
        velocityY = spellcaster.getDirectionY()*MAX_SPEED;
    }

    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;
    }
}
