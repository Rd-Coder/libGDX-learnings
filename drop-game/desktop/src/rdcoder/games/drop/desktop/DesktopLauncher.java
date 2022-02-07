package rdcoder.games.drop.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import rdcoder.games.drop.DropGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Drop Game";
		config.width = 800;
		config.height = 480;
		config.addIcon("drop.png", FileType.Internal);
		new LwjglApplication(new DropGame(), config);
	}

	public static void teste(){
		System.out.println("Teste");
	}

}
