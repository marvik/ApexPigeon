package io.github.patpatchpatrick.alphapigeon.dodgeables.MovingObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import io.github.patpatchpatrick.alphapigeon.AlphaPigeon;
import io.github.patpatchpatrick.alphapigeon.dodgeables.Dodgeables;
import io.github.patpatchpatrick.alphapigeon.resources.BodyEditorLoader;

public class Meteor extends Dodgeable {

    public final float WIDTH = 80f;
    private final float FORCE_X = -3000.0f;
    private final float FORCE_Y = -3000.0f;

    public Meteor(World gameWorld, AlphaPigeon game, OrthographicCamera camera, Dodgeables dodgeables) {
        super(gameWorld, game, camera, dodgeables);
        this.HEIGHT = WIDTH/2;

        //spawn a new meteor bird
        BodyDef meteorBodyDef = new BodyDef();
        meteorBodyDef.type = BodyDef.BodyType.DynamicBody;

        //spawn meteor at random width
        meteorBodyDef.position.set(MathUtils.random(0 - WIDTH/2, camera.viewportWidth), camera.viewportHeight + HEIGHT/2);
        dodgeableBody = gameWorld.createBody(meteorBodyDef);
        dodgeableBody.setTransform(dodgeableBody.getPosition().x, dodgeableBody.getPosition().y, MathUtils.degreesToRadians*-15);
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("json/Meteor.json"));
        FixtureDef meteorFixtureDef = new FixtureDef();
        meteorFixtureDef.density = 0.05f;
        meteorFixtureDef.friction = 0.5f;
        meteorFixtureDef.restitution = 0.3f;
        // set the meteor filter categories and masks for collisions
        meteorFixtureDef.filter.categoryBits = game.CATEGORY_METEOR;
        meteorFixtureDef.filter.maskBits = game.MASK_METEOR;
        loader.attachFixture(dodgeableBody, "Meteor", meteorFixtureDef, WIDTH);
        dodgeableBody.applyForceToCenter(FORCE_X, FORCE_Y, true);

    }

    public void init() {

        dodgeableBody.setActive(true);
        dodgeableBody.setTransform(MathUtils.random(0 - WIDTH/2, camera.viewportWidth), camera.viewportHeight + HEIGHT/2, dodgeableBody.getAngle());
        dodgeableBody.applyForceToCenter(FORCE_X, FORCE_Y, true);
        this.alive = true;

        applyScrollSpeed();

    }

}
