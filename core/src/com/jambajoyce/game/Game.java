package com.jambajoyce.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;


public class Game extends ApplicationAdapter {
        private SpriteBatch batch;
        private Texture img;
        private Texture playerImage;
        private Rectangle playerBounds;
        private Platform[] platforms;
        private int numPlatforms = 6;
        private int width;
        private int height;
        private Vector2 playerPosition;
        private Vector2 playerVelocity;
        private Vector2 gravity;
        private int score;
        private int state;
        private int state_start = 0;
        private int state_play = 1;
        private int state_end = 2;




        @Override
        public void create () {
            batch = new SpriteBatch();
            img = new Texture("missionbit.png");
            playerImage = new Texture("");
            width = Gdx.graphics.getWidth();
            height = Gdx.graphics.getHeight();
            playerPosition = new Vector2();
            playerVelocity = new Vector2();
            gravity = new Vector2();
            playerBounds = new Rectangle();
            platforms = new Platform[numPlatforms];

            resetGame();
        }

        @Override
        public void render () {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            updateGame();
            drawGame();
        }

        private void resetGame() {
            score = 0;
            playerPosition.set(width/2, 0);
            playerVelocity.set(0, 0);
            playerBounds.set(width/2, 0, playerImage.getWidth(), playerImage.getHeight());
            for (int i = 0; i < numPlatforms; i++) {
                platforms[i] = new Platform();
            }
            gravity.set(0, -20);

            //reset any game state variables here
        }

        private void updateGame() {
            //apply all game rules here and check for win or loss
            float deltaTime = Gdx.graphics.getDeltaTime();
            if (state == state_start) {
                if (Gdx.input.justTouched()) {
                    state = state_play;
                    playerVelocity.y = 1000;
                    playerVelocity.add(gravity);
                    playerPosition.mulAdd(playerVelocity, deltaTime);

                }
            }
            if (state == state_play) {
                playerVelocity.add(gravity); //the force of gravity will make the player decelerate
                playerPosition.mulAdd(playerVelocity, deltaTime); //use the player's current velocity and the time elapsed to determine the player's new position


                for (int i = 0; i < numPlatforms; i++) {
                    if (playerBounds.overlaps(platforms[i].bounds)) {
                        playerVelocity.y = 1000;
                    }
                }
            }

            playerBounds.setX(playerPosition.x);
            playerBounds.setY(playerPosition.y);
        }

        private void drawGame() {
            batch.begin();
            if (state == state_start) {
                batch.draw(img, 0, 0);
            }
            batch.end();
        }
}
