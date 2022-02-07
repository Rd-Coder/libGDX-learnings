package rdcoder.games.edu.colourtanngameintro.objs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Racket extends Rectangle {

    public Racket(int x, int y, int w, int h) {
        setX(x);
        setY(y);
        setWidth(w);
        setHeight(h);
    }

    public void update() {
        int cursorX = Gdx.input.getX();
        int X_REL = (int)width/2;
        if (cursorX < X_REL || cursorX > Gdx.graphics.getWidth()-X_REL)
            return;
        x = cursorX-X_REL;
    }
    
}
