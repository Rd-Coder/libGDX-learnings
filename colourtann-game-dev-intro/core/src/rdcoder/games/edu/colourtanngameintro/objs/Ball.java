package rdcoder.games.edu.colourtanngameintro.objs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Ball extends Rectangle {

    public int xSpeed;
    public int ySpeed;

    public Ball(int x, int y, int rad, int xSpeed, int ySpeed) {
        setX(x);
        setY(y);
        setWidth(rad*2);
        setHeight(rad*2);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update() {
        setX(x+xSpeed);
        setY(y+ySpeed);

        float radius = getRadius();
        if (x <= 0+radius || x >= Gdx.graphics.getWidth()-radius)
            xSpeed *= -1;
        if (y <= 0+getRadius() || y >= Gdx.graphics.getHeight()-radius)
            ySpeed *= -1;
    }

    public float getRadius() { return width/2f; }
    
}
