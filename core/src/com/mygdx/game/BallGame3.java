package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class BallGame3 implements Screen {
    SpriteBatch batch;
    Circle position;
    Vector2 speed;
    Vector2 gravity;
    Texture ballT;
    OrthographicCamera camera;
    final mainGame game;

    public BallGame3(mainGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, mainGame.WIDTH, mainGame.HEIGHT);
        batch = new SpriteBatch();
        Pixmap ball = new Pixmap(Gdx.files.internal("pelota.png"));
        Pixmap ballDim = new Pixmap(50, 50, ball.getFormat());
        ballDim.drawPixmap(ball, 0, 0, ball.getWidth(), ball.getHeight(), 0, 0, ballDim.getWidth(), ballDim.getHeight());
        ballT = new Texture(ballDim);
        position = new Circle(10,  20, 25);
        speed = new Vector2(3f, 25f);
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 234, 86, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (position.x > Gdx.graphics.getWidth() - position.radius*2 || position.x < 0)	speed.x = -speed.x;
        if (position.y > Gdx.graphics.getHeight() - position.radius*2 || position.y - (position.radius / 2) < 0) speed.y = - speed.y;
        speed.y = (float) (speed.y - 1.005);
        if(position.y < 0 && speed.y < 0.005) {speed.y = 0f; speed.x = (float) (speed.x * 0.995);}
        position.set(position.x + speed.x, position.y + speed.y, 25);
        batch.begin();
        batch.draw(new Texture(Gdx.files.internal("campo.png")), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(ballT, position.x , position.y );
        batch.end();
        if(Gdx.input.isTouched()){
            game.setScreen(new BallGame3(game));
            batch.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
