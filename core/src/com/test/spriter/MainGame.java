package com.test.spriter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.SCMLReader;

public class MainGame extends ApplicationAdapter {

    private Data data;
    private Drawer<Sprite> drawer;
    private SpriteBatch batch;
    private Player testAnim;

    Player getSpiter(String entityName, String animationName, float scale) {
        Player player = new Player(data.getEntity(entityName));
        player.setScale(scale);
        player.setAnimation(animationName);
        return player;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        ShapeRenderer renderer = new ShapeRenderer();

        FileHandle handle = Gdx.files.internal("test.scml");
        data = new SCMLReader(handle.read()).getData();

        LibGDXLoader loader = new LibGDXLoader(data);
        loader.load(handle.file());

        drawer = new LibGDXDrawer(loader, batch, renderer);
        testAnim = getSpiter("entity_000", "NewAnimation", 1);
        testAnim.setPosition(300, 300);
    }

    @Override
    public void render() {
        testAnim.update();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        drawer.draw(testAnim);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
