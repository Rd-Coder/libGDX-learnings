package rdcoder.games.drop;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {

	// Graphics
	private OrthographicCamera cam;
	private Vector3 touchPos;
	private DropGame game;

	// Assets

	private Texture dropImg;
	private Texture bucketImg;

	private Sound dropSound;
	private Music rainMusic;
	
	// Objects
	private static final float OBJ_SIZE = 64;
	private Rectangle bucket;

	private Array<Rectangle> drops;
	private long lastDropTime;
	private int dropsCount;

	// Cons
	private final int OBJ_VELOCITY = 200;
	private float RIGHT_LIMIT, LEFT_LIMIT;


	// -- COSNTRUCTORS
	public GameScreen(final DropGame game) {
		this.game = game;

		// graphics
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800f, 480f);
		touchPos = new Vector3();

		// assets
		dropImg = new Texture(Gdx.files.internal("drop.png"));
		bucketImg = new Texture(Gdx.files.internal("bucket.png"));

		dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rainMusic.setLooping(true);

		//objects
		bucket = new Rectangle(
			cam.viewportWidth/2 - OBJ_SIZE/2,
			20, OBJ_SIZE, OBJ_SIZE
		);

		Pools.get(Rectangle.class, 10);
		drops = new Array<Rectangle>();
		lastDropTime = 0L;
		dropsCount = 0;
		spawnRainDrop();

		// cons
		RIGHT_LIMIT = cam.viewportWidth - bucket.width;
		LEFT_LIMIT = 0;
	}
	

	@Override
	public void render (float delta) {
		SpriteBatch batch = game.batch;

		ScreenUtils.clear(0, 0, 0.2f, 1);
		cam.update();

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
			game.txtFont.draw(batch, "Drops coletados: "+dropsCount, 40, 480);
			batch.draw(bucketImg, bucket.x, bucket.y, bucket.width, bucket.height);
			for (Rectangle drop : drops)
				batch.draw(dropImg, drop.x, drop.y);
		batch.end();

		moveBucketListener();
		spawnRainDrop();
		moveDrops();
	}

	@Override
	public void dispose () {
		dropImg.dispose();
		bucketImg.dispose();

		dropSound.dispose();
		rainMusic.dispose();
	}


	private void spawnRainDrop() {
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
			Rectangle drop = Pools.get(Rectangle.class).obtain();
			drop.x = MathUtils.random(0, cam.viewportWidth-OBJ_SIZE);
			drop.y = cam.viewportHeight;
			drop.width = drop.height = OBJ_SIZE;
			drops.add(drop);
			lastDropTime = TimeUtils.nanoTime();
		}
	}

	private void moveDrops() {
		Rectangle drop = null;
		boolean hitsBucket = false;
		for (Iterator<Rectangle> iterator = drops.iterator(); iterator.hasNext();) {
			drop = iterator.next();
			drop.y -= OBJ_VELOCITY * Gdx.graphics.getDeltaTime();
			hitsBucket = drop.overlaps(bucket);
			if (drop.y + drop.width < 0 || hitsBucket) {
				if (hitsBucket) {
					dropsCount++;
					dropSound.play(0.2f);
				}
				iterator.remove();
				Pools.free(drop);
			}
		}
	}

	private void moveBucketListener() {
		int BUCKET_VEL = OBJ_VELOCITY+25;
		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			bucket.x = cam.unproject(touchPos).x - bucket.width/2;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			bucket.x -= BUCKET_VEL * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			bucket.x += BUCKET_VEL * Gdx.graphics.getDeltaTime();

		if (bucket.x < LEFT_LIMIT) 
			bucket.x = LEFT_LIMIT;
		if (bucket.x > RIGHT_LIMIT)
			bucket.x = RIGHT_LIMIT;
	}

	// OVERRIDE FROM SCREEN

	@Override
	public void show() {
		rainMusic.setVolume(0.2f);
		rainMusic.play();
	}

	@Override
	public void hide() {}


	@Override
	public void resize(int width, int height) {}


	@Override
	public void pause() {}


	@Override
	public void resume() {}

}
