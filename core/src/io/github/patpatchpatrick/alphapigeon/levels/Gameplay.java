package io.github.patpatchpatrick.alphapigeon.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import io.github.patpatchpatrick.alphapigeon.dodgeables.AlienMissiles;
import io.github.patpatchpatrick.alphapigeon.dodgeables.Birds;
import io.github.patpatchpatrick.alphapigeon.dodgeables.Dodgeables;
import io.github.patpatchpatrick.alphapigeon.dodgeables.Meteors;
import io.github.patpatchpatrick.alphapigeon.dodgeables.MovingObjects.Dodgeable;
import io.github.patpatchpatrick.alphapigeon.dodgeables.PowerUps;
import io.github.patpatchpatrick.alphapigeon.dodgeables.Rockets;
import io.github.patpatchpatrick.alphapigeon.dodgeables.Teleports;
import io.github.patpatchpatrick.alphapigeon.dodgeables.UFOs;
import io.github.patpatchpatrick.alphapigeon.resources.GameVariables;

public class Gameplay extends Level {

    //Levels
    private LevelOne levelOne;
    private LevelTwo levelTwo;

    //GAME TIMES
    private float startTime = 0f;
    private float totalGameTime;
    private long currentTimeInMillis;
    private float powerUpShieldInterval;

    //Class to control the gameplay of the game
    public Gameplay(Dodgeables dodgeables) {
        super(dodgeables);

        levelOne = new LevelOne(dodgeables);
        levelTwo = new LevelTwo(dodgeables);

        startTime = TimeUtils.nanoTime() / GameVariables.MILLION_SCALE;


    }


    public void render(float stateTime, SpriteBatch batch) {

        // Render all the dodgeable objects
        this.dodgeables.render(stateTime, batch);

    }

    public void update(float stateTime) {

        // Update all levels and gameplay
        updateLevels();

        // Update all the dodgeable objects
        this.dodgeables.update(stateTime);

    }

    private void updateLevels() {

        // Method to keep track of how much time has passed and which level to run

        currentTimeInMillis = TimeUtils.nanoTime() / GameVariables.MILLION_SCALE;
        totalGameTime = currentTimeInMillis - startTime;
        powerUpShieldInterval = powerUps.getPowerUpShieldIntervalTime();

        if (totalGameTime > LEVEL_ONE_START_TIME && totalGameTime <= LEVEL_ONE_END_TIME) {

            levelOne.run(totalGameTime, currentTimeInMillis, powerUpShieldInterval);

        } else if (totalGameTime > LEVEL_TWO_START_TIME){

            levelTwo.run(totalGameTime, currentTimeInMillis, powerUpShieldInterval);

        }


    }
}