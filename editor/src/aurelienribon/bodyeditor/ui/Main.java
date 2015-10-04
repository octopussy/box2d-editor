package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.json.JSONException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Main {
    public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException ex) {
				} catch (InstantiationException ex) {
				} catch (IllegalAccessException ex) {
				} catch (UnsupportedLookAndFeelException ex) {
				}

				LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
				configuration.width = 800;
				configuration.height = 600;
				configuration.fullscreen = false;
				configuration.useGL30 = false;

/*
				LwjglCanvas glCanvas = new LwjglCanvas(new Canvas(), configuration);
				MainWindow mw = Ctx.window;

				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				mw.setSize(
					Math.min(1150, screenSize.width - 100),
					Math.min(800, screenSize.height - 100)
				);

				mw.setCanvas(glCanvas.getCanvas());
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
*/

				parseArgs(args);
			}
		});
    }

	private static void parseArgs(String[] args) {
		for (int i=1; i<args.length; i++) {
			if (args[i-1].equals("-f")) {
				try {
					File file = new File(args[i]).getCanonicalFile();
					Ctx.io.setProjectFile(file);
					Ctx.io.importFromFile();
				} catch (IOException ex) {
				} catch (JSONException ex) {
				}
			}
		}
	}
}
