package org.borschlabs.physbodyeditor.ui;

import com.google.inject.Inject;
import org.borschlabs.physbodyeditor.Log;

import javax.swing.*;

/**
 * @author octopussy
 */
public class EditorWindow extends JFrame {
   private JPanel rootPanel;

   private final Log log;

   @Inject
   public EditorWindow(Log log) {
      super("Phys body editor");
      this.log = log;

      setContentPane(rootPanel);
   }
}
