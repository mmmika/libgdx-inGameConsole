package tests;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.strongjoshua.console.CommandExecutor;
import com.strongjoshua.console.GUIConsole;

public class StageTest extends ApplicationAdapter {
	Stage stage;
	GUIConsole console;
	Image image;

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new
				LwjglApplicationConfiguration();
		new LwjglApplication(new StageTest(), config);
	}

	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		console = new GUIConsole(new Skin(Gdx.files.classpath
				("tests/test_skin/uiskin.json")));
		console.setCommandExecutor(new CommandExecutor() {});

		stage.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				if (keycode == Input.Keys.F) {
					blink();
					return true;
				} else return super.keyUp(event, keycode);
			}
		});

		image = new Image(new Texture(Gdx.files.classpath(
				"tests/badlogic.jpg")));
		image.setScale(.5f);
		stage.addActor(image);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
		console.draw();
	}

	private void blink() {
		image.addAction(Actions.sequence(Actions.fadeOut(1),
				Actions.fadeIn(1)));
	}
}
