package aurelienribon.bodyeditor.newui;

import com.google.inject.Inject;
import org.borschlabs.physbodyeditor.ApplicationModule;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import com.badlogic.gdx.graphics.GL20;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.borschlabs.physbodyeditor.Log;

import javax.swing.*;
import java.awt.*;

/**
 * @author octopussy
 */
public class NewMainWindow extends JFrame {
   private JTabbedPane objectsTabbedPane;
   private JPanel rootPanel;
   private JPanel renderPanel;

   private final Log log;

   public static void main(final String[] args) {
      final Injector injector = Guice.createInjector(new ApplicationModule());

      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {
            NewMainWindow window = injector.getInstance(NewMainWindow.class);
         }
      });
   }

   @Inject public NewMainWindow(Log log) {
      super("Phys body editor");
      this.log = log;

      pack();

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setSize(
         Math.min(1150, screenSize.width - 100),
         Math.min(800, screenSize.height - 100)
      );

      LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
/*      configuration.width = 800;
      configuration.height = 600;*/
      configuration.fullscreen = false;
      configuration.useGL30 = false;

      setContentPane(rootPanel);

      LwjglCanvas glCanvas = new LwjglCanvas(new MyAppListener(), configuration);
      renderPanel.setLayout(new BorderLayout());
      renderPanel.add(glCanvas.getCanvas(), BorderLayout.CENTER);

      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setVisible(true);

      invalidate();
   }

   private class MyAppListener implements ApplicationListener {
      @Override
      public void create() {

      }

      @Override
      public void resize(int width, int height) {

         NewMainWindow.this.invalidate();
      }

      @Override
      public void render() {
         float delta = Gdx.graphics.getDeltaTime();
         Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
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
