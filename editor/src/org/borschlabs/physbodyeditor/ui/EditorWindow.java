package org.borschlabs.physbodyeditor.ui;

import aurelienribon.bodyeditor.ui.DynamicObjectsPanel;
import aurelienribon.bodyeditor.ui.ProjectPanel;
import aurelienribon.bodyeditor.ui.RigidBodiesPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author octopussy
 */

public class EditorWindow extends JFrame {
   private JPanel rootPanel;
   private JPanel projectPanel;
   private JTabbedPane objectsTabbedPanel;

   public EditorWindow() {
      super("Phys body editor");

      setContentPane(rootPanel);

      projectPanel.setLayout(new BorderLayout());
      projectPanel.add(new ProjectPanel(this));
      objectsTabbedPanel.add("Rigid objects", new RigidBodiesPanel(this));
      objectsTabbedPanel.add("Dynamic objects", new DynamicObjectsPanel(this));

      /*objectsPanel.getModel().add(new RigidBodiesPanel(log, this), "Rigid bodies", null, false);
		objectsPanel.getModel().add(new DynamicObjectsPanel(log, this), "Dynamic objects", null, false);*/
   }
}
