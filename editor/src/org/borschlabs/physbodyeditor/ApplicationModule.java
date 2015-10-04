package org.borschlabs.physbodyeditor;

import com.google.inject.AbstractModule;

/**
 * @author octopussy
 */
public class ApplicationModule extends AbstractModule {
   @Override
   protected void configure() {
      bind(Log.class).to(LogImpl.class);
   }
}
