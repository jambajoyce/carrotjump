package com.jambajoyce.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by joyceliu on 13/06/2014.
 */
public class Platform {
    Texture image;
    Vector2 position;
    Rectangle bounds;
    Vector2 velocity;


    public void Platform () {
        image = new Texture("carrot.png");
        position.set((float) Math.random()*Gdx.graphics.getWidth(), (float) Math.random()*Gdx.graphics.getHeight());
        bounds.set(position.x, position.y, image.getWidth(), image.getHeight());
        velocity.set(0,0);
    }
}
