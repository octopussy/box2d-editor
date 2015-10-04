package org.borschlabs.physbodyeditor.ui;

import aurelienribon.bodyeditor.canvas.Canvas;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFrame;
import com.badlogic.gdx.graphics.GL20;

/**
 * @author octopussy
 */

public class GdxRenderWindow extends LwjglFrame {

   public GdxRenderWindow(EditorWindow editorWindow) {
      super(new Canvas(editorWindow), "", 0, 0);
   }

   private static class MyAppListener implements ApplicationListener {
      @Override
      public void create() {

      }

      @Override
      public void resize(int width, int height) {

      }

      @Override
      public void render() {
         Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      }

      @Override
      public void pause() {

      }

      @Override
      public void resume() {

      }

      @Override
      public void dispose() {

      }
   }
}
