package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class BallGame extends ApplicationAdapter {
	SpriteBatch batch;
	Circle position;
	Vector2 speed;
	Texture ballT;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Pixmap ball = new Pixmap(Gdx.files.internal("pelota.png"));
		Pixmap ballDim = new Pixmap(50, 50, ball.getFormat());
		ballDim.drawPixmap(ball, 0, 0, ball.getWidth(), ball.getHeight(), 0, 0, ballDim.getWidth(), ballDim.getHeight());
		ballT = new Texture(ballDim);
		position = new Circle(new Random().nextInt(Gdx.graphics.getWidth()-212), new Random().nextInt(Gdx.graphics.getHeight()-270), 25);
		speed = new Vector2(10f, 10f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 234, 86, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (position.x > Gdx.graphics.getWidth() - position.radius*2 || position.x < 0) {
			speed.x = -speed.x;
		}
		if (position.y > Gdx.graphics.getHeight() - position.radius*2 || position.y < 0) {
			speed.y = -speed.y;
		}

		position.x += speed.x;
		position.y += speed.y;

		batch.begin();
		batch.draw(new Texture(Gdx.files.internal("campo.png")), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(ballT, position.x , position.y );

		batch.end();
	}
}
