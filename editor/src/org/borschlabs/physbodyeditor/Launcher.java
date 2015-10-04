package org.borschlabs.physbodyeditor;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.borschlabs.physbodyeditor.ui.EditorWindow;
import org.borschlabs.physbodyeditor.ui.GdxRenderWindow;

import javax.swing.*;
import java.awt.*;

/**
 * @author octopussy
 */
public class Launcher {
   private static final int DEFAULT_EDITOR_WIDTH = 400;
   private static final int DEFAULT_EDITOR_HEIGHT = 800;
   private static final int MARGIN = 15;

   public static void main(final String[] args) {
      final Injector injector = Guice.createInjector(new ApplicationModule());

      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            Injection injection = injector.getInstance(Injection.class);
            injection.editorWindow.pack();

            injection.editorWindow.setVisible(true);
            injection.editorWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            injection.editorWindow.setSize(DEFAULT_EDITOR_WIDTH - MARGIN * 2, screenSize.height  - MARGIN * 2/*DEFAULT_EDITOR_HEIGHT*/);
            injection.editorWindow.setLocation(MARGIN, MARGIN);

            injection.renderWindow.pack();
            injection.renderWindow.setVisible(true);
            injection.renderWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            injection.renderWindow.setSize(screenSize.width - DEFAULT_EDITOR_WIDTH - MARGIN * 2, screenSize.height - MARGIN * 2);
            injection.renderWindow.setLocation(MARGIN + DEFAULT_EDITOR_WIDTH, MARGIN);
         }
      });
   }

   @Singleton
   private static class Injection {
      final EditorWindow editorWindow;
      final GdxRenderWindow renderWindow;

      @Inject
      private Injection(EditorWindow editorWindow, GdxRenderWindow renderWindow) {
         this.editorWindow = editorWindow;
         this.renderWindow = renderWindow;
      }
   }
}
