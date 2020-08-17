package com.example.a2dgamedevelopment.object;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.a2dgamedevelopment.GameLoop;
import com.example.a2dgamedevelopment.Joystick;
import com.example.a2dgamedevelopment.R;

/**
 * Player is the main character of the game, which the user can control with a touch joystick
 * The player class is an extension of a Circle, which is an extension of a GameObject
 */

public class Player extends Circle{
    public static final double SPEED_PIXELS_PER_SECOND = 500.0; //was 400.0
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 10;
    private final Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints;


    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context, this);
        this.healthPoints = MAX_HEALTH_POINTS;

    }

    public void update() {
        // Update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        // Update position
        positionX += velocityX;
        positionY += velocityY;

        // Update direction
        if (velocityX != 0 || velocityY != 0) {
            // Normalize velocity to get direction (unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        healthBar.draw(canvas);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        // Only allows positive values
        if (healthPoints >= 0)
            this.healthPoints = healthPoints;
    }
}
