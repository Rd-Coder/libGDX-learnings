package rdcoder.games.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DropGame extends Game {

    public SpriteBatch batch;
    public BitmapFont txtFont;

    public MainMenuScreen menuScreen;
    public GameScreen gameScreen;

    @Override
    public void create() {
        menuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);

        batch = new SpriteBatch();
        txtFont = new BitmapFont();

        this.setScreen(menuScreen);
    }

    @Override
    public void render() {
        super.render();
        resumeListener();
    }

    @Override
    public void dispose() {
        batch.dispose();
        txtFont.dispose();
        gameScreen.dispose();
        menuScreen.dispose();
    }

    private void resumeListener() {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			setScreen(menuScreen);
	}
    
}
