package org.borschlabs.physbodyeditor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author octopussy
 */
public class LogImpl implements Log {
   @Override
   public void i(String msg) {
      Logger.getLogger("tmp").log(Level.INFO, msg);
   }

   @Override
   public void d(String msg) {
      Logger.getLogger("tmp").log(Level.INFO, msg);
   }

   @Override
   public void e(String msg) {
      Logger.getLogger("tmp").log(Level.INFO, msg);
   }
}
