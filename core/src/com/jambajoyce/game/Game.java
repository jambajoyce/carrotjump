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
        private Player player;
        private Platform[] platforms;
        private int numPlatforms = 6;
        private int width;
        private int height;
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
            width = Gdx.graphics.getWidth();
            height = Gdx.graphics.getHeight();
            gravity = new Vector2();
            platforms = new Platform[numPlatforms];
            player = new Player();

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
                    player.velocity.y = 1000;
                    player.velocity.add(gravity);
                    player.position.mulAdd(player.velocity, deltaTime);

                }
            }

            if (state == state_play) {
                player.velocity.add(gravity); //the force of gravity will make the player decelerate
                player.position.mulAdd(player.velocity, deltaTime); //use the player's current velocity and the time elapsed to determine the player's new position

                for (int i = 0; i < numPlatforms; i++) {
                    if (player.bounds.overlaps(platforms[i].bounds)) {
                        if (!platforms[i].touched) {
                            platforms[i].touched = true;
                            score += 1 ;
                        }
                        player.velocity.y = player.velocity.y + 1000;
                    }
                }
            }

            player.bounds.setX(player.position.x);
            player.bounds.setY(player.position.y);
        }

        private void drawGame() {

            batch.begin();

            if (state == state_start) {
                batch.draw(img, 0, 0);
            }

            if (state == state_play) {

                // draw player
                batch.draw(player.image, player.position.x, player.position.y);

               // draw platforms
                for (int i = 0; i < numPlatforms; i++) {
                    batch.draw(platforms[i].image, platforms[i].position.x, platforms[i].position.y);

                }
            }
            batch.end();
        }
}
