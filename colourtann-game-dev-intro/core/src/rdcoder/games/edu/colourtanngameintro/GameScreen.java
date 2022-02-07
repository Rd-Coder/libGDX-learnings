package rdcoder.games.edu.colourtanngameintro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import rdcoder.games.edu.colourtanngameintro.objs.Ball;
import rdcoder.games.edu.colourtanngameintro.objs.Objs;
import rdcoder.games.edu.colourtanngameintro.objs.Racket;

public class GameScreen extends ScreenAdapter {
    
    ColourtannGame game;
    ShapeRenderer shape;
    BitmapFont font;

    Array<Ball> balls;
    Racket racket;

    public GameScreen(final ColourtannGame game) {
        this.game = game;
        shape = new ShapeRenderer();
        font = new BitmapFont();

        balls = new Array<Ball>();
        for (int i=0; i<1; i++)
            balls.add(
                new Ball(30, 30, 30, 5, 8)
            );

        racket = new Racket(0, 20, 120, 8);
        racket.update();
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Batch batch = new SpriteBatch();
        batch.begin();
        font.draw(batch, "Oi, um teste", 100, 100);
        batch.end();
        shape.begin(ShapeRenderer.ShapeType.Filled);
            Objs.drawRacket(shape, racket);
            racket.update();

            for (Ball ball : balls) {
                ball.update();
                Objs.drawBall(shape, ball);
            }
        shape.end();

        racketBallColisionListener();
    }

    public void racketBallColisionListener() {
        for (Ball ball : balls) {
            if (Objs.collide(ball, racket))
                ball.ySpeed*=-1;
        }
    }

    @Override
    public void dispose() {
        shape.dispose();
    }


}
