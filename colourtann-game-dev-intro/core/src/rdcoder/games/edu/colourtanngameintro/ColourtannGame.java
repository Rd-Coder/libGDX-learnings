package rdcoder.games.edu.colourtanngameintro;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ColourtannGame extends Game {

    Screen gameScreen;
    
    @Override
    public void create() {
        setScreen(gameScreen = new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        gameScreen.dispose();
    }
    
}
