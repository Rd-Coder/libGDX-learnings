package rdcoder.games.edu.colourtanngameintro.objs;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class Objs {
    
    public static void drawBall(ShapeRenderer shape, Ball ball) {
        shape.circle(ball.x, ball.y, ball.getRadius());
    }

    public static void drawRacket(ShapeRenderer shape, Racket racket) {
        shape.rect(racket.x, racket.y, racket.width, racket.height);
    }

    public static boolean collide(Rectangle o1, Rectangle o2) {
        return 
            ( o1.x > o2.x + o2.width && o1.x + o1.width < o2.x )
            || ( o1.y > o2.y + o2.height && o1.y + o1.height < o2.y );
    }

}
