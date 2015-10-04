package org.borschlabs.physbodyeditor.ui;

import aurelienribon.bodyeditor.ui.DynamicObjectsPanel;
import aurelienribon.bodyeditor.ui.ProjectPanel;
import aurelienribon.bodyeditor.ui.RigidBodiesPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.borschlabs.physbodyeditor.Log;

import javax.swing.*;
import java.awt.*;

/**
 * @author octopussy
 */
@Singleton
public class EditorWindow extends JFrame {
   private JPanel rootPanel;
   private JPanel projectPanel;
   private JTabbedPane objectsTabbedPanel;

   private final Log log;

   @Inject
   public EditorWindow(Log log) {
      super("Phys body editor");
      this.log = log;

      setContentPane(rootPanel);

      projectPanel.setLayout(new BorderLayout());
      projectPanel.add(new ProjectPanel(this));
      objectsTabbedPanel.add("Rigid objects", new RigidBodiesPanel(log, this));
      objectsTabbedPanel.add("Dynamic objects", new DynamicObjectsPanel(log, this));

      /*objectsPanel.getModel().add(new RigidBodiesPanel(log, this), "Rigid bodies", null, false);
		objectsPanel.getModel().add(new DynamicObjectsPanel(log, this), "Dynamic objects", null, false);*/
   }
}
