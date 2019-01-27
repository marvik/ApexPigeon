package io.github.patpatchpatrick.alphapigeon.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.patpatchpatrick.alphapigeon.Pigeon;
import io.github.patpatchpatrick.alphapigeon.Screens.GameScreen;
import io.github.patpatchpatrick.alphapigeon.Screens.HighScoreScreen;
import io.github.patpatchpatrick.alphapigeon.Screens.MainMenuScreen;
import io.github.patpatchpatrick.alphapigeon.Screens.SettingsScreen;

public class AccelerometerController {

    private Pigeon pigeon;
    private Body pigeonBody;
    private OrthographicCamera camera;

    private Boolean accelerometerAvailable;

    public AccelerometerController(Pigeon pigeon, OrthographicCamera camera){
        this.pigeon = pigeon;
        pigeonBody = this.pigeon.getBody();
        this.camera = camera;

        //Determine if an accelerometer is available
        accelerometerAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

    }


    public void processAccelerometerInput(){
        if (accelerometerAvailable){
            float accelX = Gdx.input.getAccelerometerX();
            float accelY = Gdx.input.getAccelerometerY();

            float yForce = -accelX;
            float xForce = accelY;

            pigeonBody.applyForceToCenter(xForce, yForce, true);

        }

    }






}