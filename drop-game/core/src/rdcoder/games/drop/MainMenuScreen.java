package rdcoder.games.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final DropGame game;
    
    OrthographicCamera cam;

    public MainMenuScreen(final DropGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800f, 480f);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
    
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.txtFont.draw(game.batch, "Vamos pegar esses Gays!", 100, 150);
        game.txtFont.draw(game.batch, "Clique em qualquer lugar para começar", 100, 100);
        game.batch.end();

        touchListener();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    private void touchListener() {
        if (Gdx.input.isTouched()) {
            game.setScreen(game.gameScreen);
            dispose();
        }
    }
}
