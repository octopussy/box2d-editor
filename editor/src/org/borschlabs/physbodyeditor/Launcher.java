package org.borschlabs.physbodyeditor;

import com.google.inject.Guice;
import com.google.inject.Injector;
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

            EditorWindow window = injector.getInstance(EditorWindow.class);
            GdxRenderWindow renderWindow = injector.getInstance(GdxRenderWindow.class);

            window.pack();

            window.setVisible(true);
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setSize(DEFAULT_EDITOR_WIDTH - MARGIN * 2, screenSize.height  - MARGIN * 2/*DEFAULT_EDITOR_HEIGHT*/);
            window.setLocation(MARGIN, MARGIN);

            renderWindow.pack();
            renderWindow.setVisible(true);
            renderWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            renderWindow.setSize(screenSize.width - DEFAULT_EDITOR_WIDTH - MARGIN * 2, screenSize.height - MARGIN * 2);
            renderWindow.setLocation(MARGIN + DEFAULT_EDITOR_WIDTH, MARGIN);
         }
      });

      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {

         }
      });
   }
}
