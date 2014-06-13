package com.jambajoyce.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by joyceliu on 13/06/2014.
 */
public class Player {
    Texture image;
    Vector2 position;
    Rectangle bounds;
    Vector2 velocity;
    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();



    public void Player () {
        image = new Texture("avi.png");
        position.set(width / 2, height / 2);
        bounds.set(position.x, position.y, image.getWidth(), image.getHeight());
        velocity.set(0,0);
    }
}
