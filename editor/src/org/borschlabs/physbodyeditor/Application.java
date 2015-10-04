package org.borschlabs.physbodyeditor;

import org.borschlabs.physbodyeditor.ui.EditorWindow;
import org.borschlabs.physbodyeditor.ui.GdxRenderWindow;

import javax.swing.*;
import java.awt.*;

/**
 * @author octopussy
 */

public class Application {
   private static final int DEFAULT_EDITOR_WIDTH = 400;
   private static final int DEFAULT_EDITOR_HEIGHT = 800;
   private static final int MARGIN = 15;

   private static Application me;

   private EditorWindow editorWindow;
   private GdxRenderWindow renderWindow;

   public static Application getInstance() {
      return me;
   }

   public EditorWindow getEditorWindow() {
      return editorWindow;
   }

   public GdxRenderWindow getRenderWindow() {
      return renderWindow;
   }

   public Application() {
      me = this;
      try {
         /*System.setProperty("com.apple.macos.useScreenMenuBar", "true");
         System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
*/
         UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
      } catch (UnsupportedLookAndFeelException e) {
         // handle exception
      } catch (ClassNotFoundException e) {
         // handle exception
      } catch (InstantiationException e) {
         // handle exception
      } catch (IllegalAccessException e) {
         // handle exception
      }

      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            editorWindow = new EditorWindow();
            renderWindow = new GdxRenderWindow(editorWindow);

            editorWindow.pack();

            editorWindow.setVisible(true);
            editorWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            editorWindow.setSize(DEFAULT_EDITOR_WIDTH - MARGIN * 2, screenSize.height - MARGIN * 2/*DEFAULT_EDITOR_HEIGHT*/);
            editorWindow.setLocation(MARGIN, MARGIN);

            renderWindow.pack();
            renderWindow.setVisible(true);
            renderWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            renderWindow.setSize(screenSize.width - DEFAULT_EDITOR_WIDTH - MARGIN * 2, screenSize.height - MARGIN * 2);
            renderWindow.setLocation(MARGIN + DEFAULT_EDITOR_WIDTH, MARGIN);
         }
      });
   }

   public static void main(final String[] args) {
      new Application();
   }
}
